package com.u2u.ibms.common.beans;

import com.u2u.framework.base.BaseBean;

public class Alipay extends BaseBean {

	private int id;
	private String partner; // 合作者身份ID/帐号
	private String seller_id;// 卖家支付宝账号 //自己的支付宝
	private String out_trade_no; // 商户网站唯一订单号// 动态生成，内部唯一订单号
	private String subject; // 商品名称//商品名
	private String body;// 商品详情
	private String total_fee;// 总金额
	private String notify_url;// 服务器异步通知页面路径
	private String service;// 接口名称/固定
	private String payment_type;// 支付类型/ 默认为1（商品支付）
	private String input_charset;// 参数编码字符集/固定
	private String it_b_pay;// 未付款交易的超时时间
	private String sign;// 签名
	private String sign_type; // 签名方式/ 固定
	private int type;
	private int userId;
	private int orderId;
	private int billCheckId;
	private boolean status;

	private String notify_type; // 通知类型
	private String notify_id; // 通知校验ID
	private String ret_sign_type; // 签名方式
	private String ret_sign; // 签名
	private String trade_status; // 交易状态
	private String trade_no; // 支付宝交易号

	public String getNotify_type() {
		return notify_type;
	}

	public void setNotify_type(String notify_type) {
		this.notify_type = notify_type;
	}

	public String getNotify_id() {
		return notify_id;
	}

	public void setNotify_id(String notify_id) {
		this.notify_id = notify_id;
	}

	public String getRet_sign_type() {
		return ret_sign_type;
	}

	public void setRet_sign_type(String ret_sign_type) {
		this.ret_sign_type = ret_sign_type;
	}

	public String getRet_sign() {
		return ret_sign;
	}

	public void setRet_sign(String ret_sign) {
		this.ret_sign = ret_sign;
	}

	public String getTrade_status() {
		return trade_status;
	}

	public void setTrade_status(String trade_status) {
		this.trade_status = trade_status;
	}

	public String getTrade_no() {
		return trade_no;
	}

	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getBillCheckId() {
		return billCheckId;
	}

	public void setBillCheckId(int billCheckId) {
		this.billCheckId = billCheckId;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPartner() {
		return partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public String getSeller_id() {
		return seller_id;
	}

	public void setSeller_id(String seller_id) {
		this.seller_id = seller_id;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getPayment_type() {
		return payment_type;
	}

	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}

	public String getInput_charset() {
		return input_charset;
	}

	public void setInput_charset(String input_charset) {
		this.input_charset = input_charset;
	}

	public String getIt_b_pay() {
		return it_b_pay;
	}

	public void setIt_b_pay(String it_b_pay) {
		this.it_b_pay = it_b_pay;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getSign_type() {
		return sign_type;
	}

	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}

}
