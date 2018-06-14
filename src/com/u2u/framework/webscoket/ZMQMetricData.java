package com.u2u.framework.webscoket;

import java.io.Serializable;

/**
 * 封装监控指标的数据模型
 * 
 * @author WanWei
 * @date 2014-12-23 下午5:49:07
 */
public class ZMQMetricData implements Serializable {

	private static final long serialVersionUID = 2037627099739142410L;
	
	private String name;//指标名称
	
	private Object value;//指标值
	
	private long recordTime;//记录指标的时间戳

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public long getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(long recordTime) {
		this.recordTime = recordTime;
	}
}
