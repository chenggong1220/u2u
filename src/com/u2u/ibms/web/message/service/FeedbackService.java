package com.u2u.ibms.web.message.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.u2u.framework.base.BaseService;
import com.u2u.framework.sys.authorize.security.SecurityContextUtil;
import com.u2u.framework.util.DateUtil;
import com.u2u.ibms.common.beans.Feedback;
import com.u2u.ibms.common.mapper.FeedbackMapper;
import com.u2u.ibms.web.message.condition.FeedbackCondition;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class FeedbackService extends BaseService {

	@Autowired
	private FeedbackMapper feedbackMapper;

	public List<Feedback> getAll(FeedbackCondition condition, RowBounds rb) {
		return feedbackMapper.getAll(getStringCondition(condition.getPhone()),
				getIntegerCondition(condition.getStatus()),
				getStartDate(condition), getEndDate(condition), rb);
	}

	public Feedback getById(int id) {
		return feedbackMapper.getById(id);
	}

	public void insert(Feedback feedback) {
		feedback.setCreateDate(DateUtil.currentTimestamp());
		feedback.setOperateDate(DateUtil.currentTimestamp());
		feedbackMapper.insert(feedback);
	}

	public void update(Feedback feedback) {
		Feedback exist = feedbackMapper.getById(feedback.getId());
		exist.setStatus(1);
		exist.setHandlePerson(SecurityContextUtil.getUserName());
		exist.setHandleMethod(feedback.getHandleMethod());
		exist.setOperateDate(DateUtil.currentTimestamp());
		feedbackMapper.update(exist);
	}

	public void delete(int id) {
		feedbackMapper.delete(id);
	}
}
