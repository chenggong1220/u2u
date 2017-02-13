package com.u2u.ibms.rest.pay.vo;


public class AlipayResponse {

//	private static final String BLOB_TEMPLATE = "partner=\"{0}\"&seller_id=\"{1}\"&out_trade_no=\"{2}\"&subject=\"{3}\"&body=\"{4}\"&total_fee=\"{5}\"&notify_url=\"{6}\"&service=\"{7}\"&payment_type=\"{8}\"&_input_charset=\"{9}\"&it_b_pay=\"{10}\"&sign=\"{11}\"&sign_type=\"{12}\"";
	private String partner; // 合作者身份ID/帐号
	private String seller_id;// 卖家支付宝账号 //自己的支付宝
	private String out_trade_no; // 商户网站唯一订单号// 动态生成，内部唯一订单号
	private String subject; // 商品名称//商品名
	private String body;// 商品详情
	private String total_fee;// 总金额
	private String notify_url;// 服务器异步通知页面路径
	private String service;// 接口名称/固定
	private String payment_type;// 支付类型/ 默认为1（商品支付）
	private String _input_charset;// 参数编码字符集/固定
	private String it_b_pay;// 未付款交易的超时时间
	private String sign;// 签名
	private String sign_type; // 签名方式/ 固定

//	private String blob;

//	public String getBlob() {
//		return MessageFormat.format(BLOB_TEMPLATE, partner, seller_id,
//				out_trade_no, subject, body, total_fee, notify_url, service,
//				payment_type, _input_charset, it_b_pay, sign, sign_type);
//	}
//
//	public void setBlob(String blob) {
//		this.blob = blob;
//	}

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

	public String get_input_charset() {
		return _input_charset;
	}

	public void set_input_charset(String _input_charset) {
		this._input_charset = _input_charset;
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
