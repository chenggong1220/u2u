package com.u2u.ibms.rest.pay.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.u2u.framework.config.AppConfiguration;
import com.u2u.framework.util.DateUtil;
import com.u2u.framework.util.LogUtil;
import com.u2u.ibms.common.beans.Alipay;
import com.u2u.ibms.common.beans.BillCheck;
import com.u2u.ibms.common.beans.Income;
import com.u2u.ibms.common.beans.Pays;
import com.u2u.ibms.common.beans.UserInfo;
import com.u2u.ibms.common.mapper.AlipayMapper;
import com.u2u.ibms.common.mapper.BillCheckMapper;
import com.u2u.ibms.common.mapper.IncomeMapper;
import com.u2u.ibms.common.mapper.PaysMapper;
import com.u2u.ibms.common.mapper.UserInfoMapper;
import com.u2u.ibms.common.util.CommonIdGenerator;
import com.u2u.ibms.common.util.alipay.AlipayNotify;
import com.u2u.ibms.common.util.alipay.SignUtils;
import com.u2u.ibms.rest.pay.vo.AlipayCallbackRequest;
import com.u2u.ibms.rest.pay.vo.AlipayRequest;
import com.u2u.ibms.rest.pay.vo.AlipayResponse;
import com.u2u.ibms.rest.pay.vo.AlipayVerifyRequest;
import com.u2u.ibms.rest.pay.vo.AlipayVerifyResponse;
import com.u2u.ibms.web.schedule.ScheduleBillCheck;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class MobilePayService {

	private static Log log = LogFactory.getLog(MobilePayService.class);
	@Autowired
	private AlipayMapper alipayMapper;

	@Autowired
	private UserInfoMapper userInfoMapper;

	@Autowired
	private BillCheckMapper billCheckMapper;

	@Autowired
	private PaysMapper paysMapper;

	@Autowired
	private IncomeMapper incomeMapper;

	public AlipayResponse getAlipayInfos(AlipayRequest request) {

		AlipayResponse response = new AlipayResponse();
		/**
		 * 固定的常量
		 */
		response.setPartner(PARTNER);
		response.setSeller_id(SELLER_ID);
		response.setNotify_url(getNOTIFY_URL());
		response.setService(SERVICE);
		response.setPayment_type(PAYMENT_TYPE);
		response.set_input_charset(_INPUT_CHASET);
		response.setIt_b_pay(IT_B_PAY);
		response.setSign_type(SIGN_TYPE);

		/**
		 * 每个交易不同，变化的部分
		 */
		// 商户网站唯一订单号，动态生成，内部唯一订单号
		response.setOut_trade_no(CommonIdGenerator.generateAlipayId());
		// 商品名称//商品名
		// 商品详情
		if (request.getType() == 0) {
			response.setSubject("会员费");
			response.setBody(request.getUsername() + "的会员费");
		} else if (request.getType() == 1) {
			response.setSubject("押金");
			response.setBody(request.getUsername() + "的押金");
		} else if (request.getType() == 2) {
			response.setSubject("租金");
			response.setBody(request.getUsername() + "的租金");
		}
		// 总金额
		response.setTotal_fee(request.getAmount() + "");
		response.setSign(sign(getOrderInfo(response.getSubject(),
				response.getBody(), response.getTotal_fee(),
				response.getOut_trade_no())));

		Alipay alipay = new Alipay();

		alipay.setPartner(response.getPartner());
		alipay.setSeller_id(response.getSeller_id());
		alipay.setOut_trade_no(response.getOut_trade_no());
		alipay.setSubject(response.getSubject());
		alipay.setBody(response.getBody());
		alipay.setTotal_fee(response.getTotal_fee());
		alipay.setNotify_url(response.getNotify_url());
		alipay.setService(response.getService());
		alipay.setPayment_type(response.getPayment_type());
		alipay.setInput_charset(response.get_input_charset());
		alipay.setIt_b_pay(response.getIt_b_pay());
		alipay.setSign(response.getSign());
		alipay.setSign_type(response.getSign_type());
		alipay.setType(request.getType());

		UserInfo userInfo = userInfoMapper.getUserByUserName(request
				.getUsername());
		alipay.setUserId(userInfo.getId());
		alipay.setOrderId(0);
		if (request.getType() == 2) {
			alipay.setBillCheckId(request.getBillCheckId());
		}
		alipay.setStatus(false);
		alipay.setCreateDate(DateUtil.currentTimestamp());
		alipay.setOperateDate(DateUtil.currentTimestamp());
		alipayMapper.insert(alipay);
		return response;
	}

	public AlipayVerifyResponse verifyAlipayOrder(AlipayVerifyRequest request) {
		AlipayVerifyResponse response = new AlipayVerifyResponse();
		response.setStatus(false);
		UserInfo userInfo = userInfoMapper.getUserByUserName(request
				.getUsername());
		if (userInfo != null) {
			Alipay alipay = alipayMapper.getById(request.getOut_trade_no());
			if (alipay != null) {
				response.setStatus(alipay.isStatus());
			}
		}
		return response;
	}

	@SuppressWarnings("rawtypes")
	public void alipayCallback(HttpServletRequest servletRequest,
			AlipayCallbackRequest request) {

		LogUtil.info(log, "================== Start Mobile Payment =================");
		Map<String, String> params = new HashMap<String, String>();
		Map requestParams = servletRequest.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			// valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
			params.put(name, valueStr);
		}


		if (!AlipayNotify.verify(params)) {// 验证失败
			throw new RuntimeException("支付宝回调消息验证签名失败");
		}

		if (request.getTrade_status().equals("TRADE_FINISHED")
				|| request.getTrade_status().equals("TRADE_SUCCESS")) {

			LogUtil.info(log, "支付宝回调成功-" + request.getOut_trade_no());

			Alipay alipay = alipayMapper.getById(request.getOut_trade_no());

			if (alipay.isStatus()) {
				return;
			}
			alipay.setStatus(true);
			alipay.setNotify_type(request.getNotify_type());
			alipay.setNotify_id(request.getNotify_id());
			alipay.setRet_sign_type(request.getSign_type());
			alipay.setRet_sign(request.getSign());
			alipay.setTrade_status(request.getTrade_status());
			alipay.setTrade_no(request.getTrade_no());
			alipayMapper.update(alipay);

			UserInfo userInfo = userInfoMapper.getById(alipay.getUserId());
			if (alipay.getType() == 0) {
				userInfo.setPayMemberAmount(userInfo.getPayMemberAmount()
						+ Float.valueOf(alipay.getTotal_fee()));
				userInfo.setHasMemberAmount(true);
				userInfo.setOperateDate(DateUtil.currentTimestamp());
				userInfoMapper.update(userInfo);
			} else if (alipay.getType() == 1) {
				userInfo.setPayDeposit(userInfo.getPayDeposit()
						+ Float.valueOf(alipay.getTotal_fee()));
				userInfo.setHasDeposited(true);
				userInfo.setOperateDate(DateUtil.currentTimestamp());
				userInfoMapper.update(userInfo);
			} else if (alipay.getType() == 2) {
				BillCheck billCheck = billCheckMapper.getById(alipay
						.getBillCheckId());
				billCheck.setPayAmount(billCheck.getPayAmount()
						+ Float.valueOf(alipay.getTotal_fee()));
				if (billCheck.getPayAmount() >= billCheck.getAllAmount()) {
					billCheck.setPayStatus(1);
				}
				billCheck.setOperateDate(DateUtil.currentTimestamp());
				billCheckMapper.update(billCheck);
			}
			
			LogUtil.info(log, "================== 插入Paymet数据 =================");
			Pays pay = new Pays();
			pay.setPayId(CommonIdGenerator.generatePayId());
			pay.setPaySource(0);
			pay.setType(alipay.getType());
			pay.setOrderId(alipay.getOrderId());
			pay.setBillCheckId(alipay.getBillCheckId());
			pay.setUserId(alipay.getUserId());
			pay.setAmount(Float.valueOf(alipay.getTotal_fee()));
			pay.setCreateDate(DateUtil.currentTimestamp());
			pay.setOperateDate(DateUtil.currentTimestamp());
			paysMapper.insert(pay);
			LogUtil.info(log, "================== Success 插入Paymet数据 =================");

			Income income = new Income();
			income.setOrderId(pay.getOrderId());
			income.setBillId(pay.getBillCheckId());
			if (userInfo != null) {
				income.setCustomer(userInfo.getRealname());
			} else {
				income.setCustomer("");
			}
			income.setType(alipay.getType());
			income.setAmount(pay.getAmount());
			income.setPayAmount(pay.getAmount());
			income.setInvoice(true);
			income.setInvoiceStatus(false);
			income.setOperateDate(DateUtil.currentTimestamp());
			income.setCreateDate(DateUtil.currentTimestamp());
			incomeMapper.insert(income);

		} else if (request.getTrade_status().equals("TRADE_CLOSE")) {
			LogUtil.info(log, "支付宝回调交易关闭-" + request.getOut_trade_no());
			return;
		} else if (request.getTrade_status().equals("WAIT_BUYER_PAY")) {
			LogUtil.info(log, "支付宝回调等待支付-" + request.getOut_trade_no());
			throw new RuntimeException("交易未结束的支付宝回调通知["
					+ request.getTrade_status() + "]");
		}
	}

	public String sign(String content) {
		String sign = SignUtils.sign(content, RSA_PRIVATE);
		return sign;
	}

	public String getOrderInfo(String subject, String body, String price,
			String outTradeNo) {
		// 签约合作者身份ID
		String orderInfo = "partner=" + "\"" + PARTNER + "\"";
		// 签约卖家支付宝账号
		orderInfo += "&seller_id=" + "\"" + SELLER_ID + "\"";
		// 商户网站唯一订单号
		orderInfo += "&out_trade_no=" + "\"" + outTradeNo + "\"";
		// 商品名称
		orderInfo += "&subject=" + "\"" + subject + "\"";
		// 商品详情
		orderInfo += "&body=" + "\"" + body + "\"";
		// 商品金额
		orderInfo += "&total_fee=" + "\"" + price + "\"";
		// 服务器异步通知页面路径
		orderInfo += "&notify_url=" + "\"" + getNOTIFY_URL() + "\"";
		// 服务接口名称， 固定值
		orderInfo += "&service=" + "\"" + SERVICE + "\"";
		// 支付类型， 固定值
		orderInfo += "&payment_type=" + "\"" + PAYMENT_TYPE + "\"";
		// 参数编码， 固定值
		orderInfo += "&_input_charset=" + "\"" + _INPUT_CHASET + "\"";
		// 设置未付款交易的超时时间
		// 默认30分钟，一旦超时，该笔交易就会自动被关闭。
		// 取值范围：1m～15d。
		// m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
		// 该参数数值不接受小数点，如1.5h，可转换为90m。
		orderInfo += "&it_b_pay=" + "\"" + IT_B_PAY + "\"";
		// extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
		// orderInfo += "&extern_token=" + "\"" + extern_token + "\"";
		// 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
		// orderInfo += "&return_url=\"m.alipay.com\"";
		// 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
		// orderInfo += "&paymethod=\"expressGateway\"";
		return orderInfo;
	}

	/**
	 * 平台的帐号信息
	 */
	public static final String PARTNER = "2088221712884726"; // 合作者身份ID/帐号
	private static final String SELLER_ID = "ulzfb@unislease.com";// 卖家支付宝账号
																	// //自己的支付宝
	// 商户私钥，pkcs8格式
	public static final String RSA_PRIVATE = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBANb+7EBCsADonnmn"
			+ "0ieVPOObGUbAtRJNLk+zYowdviugmeU+gRs51QDXfZ8ahQn7oMD+FwqqTJdlfUka"
			+ "JuFYdA14sEAH9dVZk1IbNRfVBLe9G5H/sAUxPON+WV57ukj/02PldQZvf70GtSjY"
			+ "J9AjDgtLcONIJ6jAV3w5jaiQp/XnAgMBAAECgYAmxpt9i4mK4GmUr3vrv7LXqiWI"
			+ "6UC/KERuA4CGnOarn6+h9P/8FRYhrLvvci0Ee+mmZS1qswRVWeyavqi8g+OKJc7b"
			+ "Ym1Lezd2ZcnR91Eq1TiMVJzLGO6PAPLI1KhuUwvzxa4HfRJpD1nUik7YmlneSLx2"
			+ "fYjKn/cZw6iq8E1ZQQJBAPC9CG/J6kCWLko1bXzoRpzhzcYpy5lX1XMG8IP3d5sX"
			+ "VqcMJDjcTl1vPu2Jbwg2PXm+OKEhJFtDAnfDBJ1rjq8CQQDkoBxG+1zV3wsZVRc3"
			+ "jlFHqDCTxXkk+9c3YR5dRHFUOZu4LSsXUqTxHaG/k1Bb2chkOJ4iUcwycvRei1eb"
			+ "CZpJAkBJGk3+jET7Gd+ynyjBPlN6/kKbQ0PVK8vgYyIUIMiMKvkgPtqc/aE6VDa6"
			+ "sPordzURzAasP0EoCWAtd+Xl26x3AkA8DtNe0ilRBYak4PV4Yqp3aHdaWkW4sW+X"
			+ "3KtdVN1wgrGlcDghqFQsAty6trglaNB7g2QHH/XESu5m57PbxmoBAkEApHQJojah"
			+ "JXgdZ/MWDIET1ezpZdFhgvoC4q5QqhXVJGcNTJlnw5EVHF/riBFN22mMCZThkX78"
			+ "Kta+FqJ/bwxoRw==";

	// 支付宝公钥
	public static final String RSA_PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";

	/**
	 * 不变的
	 */
	private String NOTIFY_URL;

	public String getNOTIFY_URL() {
		if (StringUtils.isNotEmpty(NOTIFY_URL)) {
			return NOTIFY_URL;
		} else {
			return AppConfiguration.getInstance().getString("NOTIFY_URL");
		}
	}

	private static final String SERVICE = "mobile.securitypay.pay";// 接口名称/固定
	private static final String PAYMENT_TYPE = "1";// 支付类型/ 默认为1（商品支付）
	public static final String _INPUT_CHASET = "utf-8";// 参数编码字符集/固定
	private static final String IT_B_PAY = "30m";// 未付款交易的超时时间
	public static final String SIGN_TYPE = "RSA"; // 签名方式/ 固定
}
