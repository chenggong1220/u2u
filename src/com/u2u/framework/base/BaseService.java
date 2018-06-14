package com.u2u.framework.base;

import java.sql.Timestamp;

import com.u2u.framework.util.DateUtil;
import com.u2u.framework.util.StringUtils;

public abstract class BaseService {

	protected String getStringCondition(String condition) {
		if (StringUtils.isEmpty(condition)) {
			return null;
		} else {
			return condition;
		}
	}

	protected Integer getIntegerCondition(String src) {
		try {
			if (StringUtils.isNotEmpty(src) && Integer.valueOf(src.trim()) != null) {
				return Integer.valueOf(src);
			}
		} catch (Exception e) {
		}
		return null;
	}

	protected Float getFloatCondition(String src) {
		try {
			if (StringUtils.isNotEmpty(src) && Float.valueOf(src.trim()) != null) {
				return Float.valueOf(src);
			}
		} catch (Exception e) {
		}
		return null;
	}

	protected Boolean getBooleanCondition(String src) {
		try {
			if (StringUtils.isNotEmpty(src) && Boolean.valueOf(src.trim()) != null) {
				return Boolean.valueOf(src);
			}
		} catch (Exception e) {
		}
		return null;
	}

	protected Timestamp getStartDate(BaseCondition condition) {
		try {
			if (StringUtils.isNotEmpty(condition.getStartDate())) {
				return DateUtil.string2Timestamp(condition.getStartDate() + " 00:00:00", DateUtil.PATTERN_STANDARD);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	protected Timestamp getEndDate(BaseCondition condition) {
		try {
			if (StringUtils.isNotEmpty(condition.getEndDate())) {
				return DateUtil.string2Timestamp(condition.getEndDate() + " 23:59:59", DateUtil.PATTERN_STANDARD);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
