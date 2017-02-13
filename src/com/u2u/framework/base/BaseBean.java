package com.u2u.framework.base;

import java.sql.Timestamp;

import com.u2u.framework.util.DateUtil;

/**
 * @ClassName: BaseBean <br>
 * @Description: TODO <br>
 * @date 2015年4月15日 下午3:41:51 <br>
 * 
 * @author Freud
 */
public class BaseBean {

	/**
	 * 创建时间
	 */
	private Timestamp createDate;
	/**
	 * 最后修改时间
	 */
	private Timestamp operateDate;

	@SuppressWarnings("unused")
	private String viewDate;

	@SuppressWarnings("unused")
	private String operateViewDate;
	
	@SuppressWarnings("unused")
	private String createTime;

	@SuppressWarnings("unused")
	private String operatTimet;
	
	


	public String getCreateTime() {
		if (createDate == null) {
			return "";
		} else {
			return DateUtil.timestamp2String(createDate, DateUtil.PATTERN_STANDARD);
		}
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getOperatTimet() {
		if (operateDate == null) {
			return "";
		} else {
			return DateUtil.timestamp2String(operateDate, DateUtil.PATTERN_STANDARD);
		}
	}

	public void setOperatTimet(String operatTimet) {
		this.operatTimet = operatTimet;
	}

	public String getOperateViewDate() {
		if (operateDate == null) {
			return "";
		} else {
			return DateUtil
					.timestamp2String(operateDate, DateUtil.PATTERN_DATE);
		}
	}

	public String getViewDate() {
		if (createDate == null) {
			return "";
		} else {
			return DateUtil.timestamp2String(createDate, DateUtil.PATTERN_DATE);
		}
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public Timestamp getOperateDate() {
		return operateDate;
	}

	public void setOperateDate(Timestamp operateDate) {
		this.operateDate = operateDate;
	}

}
