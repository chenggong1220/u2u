package com.u2u.ibms.web.message.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.u2u.common.component.dingding.DingdingUtils;
import com.u2u.common.component.sms.AlidayuSmsMessage;
import com.u2u.common.component.sms.ThridPartySmsTemplate;
import com.u2u.framework.base.BaseService;
import com.u2u.framework.exception.ServiceAuthorizeException;
import com.u2u.framework.sys.authorize.beans.User;
import com.u2u.framework.sys.authorize.service.AuthorizeService;
import com.u2u.framework.util.DateUtil;
import com.u2u.ibms.common.beans.SysMessage;
import com.u2u.ibms.common.mapper.SysMessageMapper;
import com.u2u.ibms.web.message.vo.SysMessageCondition;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class SysMessageService extends BaseService {

	@Autowired
	private SysMessageMapper sysMessageMapper;

	@Autowired
	private AuthorizeService authorizeService;

	@Autowired
	private ThridPartySmsTemplate smsTemplate;

	public List<SysMessage> get(RowBounds rb, SysMessageCondition condition)
			throws ServiceAuthorizeException {
		List<SysMessage> sysMessages = sysMessageMapper.getMessages(rb,
				getStringCondition(condition.getTitle()),
				getStartDate(condition), getEndDate(condition),
				getStringCondition(condition.getType()));
		for (final SysMessage sysMessage : sysMessages) {
			this.convert(sysMessage);
		}
		return sysMessages;
	}

	public SysMessage getById(int id) throws ServiceAuthorizeException {
		return this.convert(sysMessageMapper.getMessageById(id));
	}

	private SysMessage convert(final SysMessage sysMessage)
			throws ServiceAuthorizeException {
		sysMessage.setUser(authorizeService.getUser(sysMessage.getUserId()));
		return sysMessage;
	}

	public void insert(SysMessage sysMessage) throws Exception {
		User user = authorizeService.getUser(sysMessage.getUserId());

		if (sysMessage.getType().equals("短信")) {
			Map<String, String> maps = new HashMap<String, String>();
			maps.put("AppName", "优尼斯租赁");
			maps.put("VerifyCode", sysMessage.getContent());
			AlidayuSmsMessage message = new AlidayuSmsMessage();
			message.setSignName("zhuce");
			message.setTemplateCode("zhuce");
			message.setPhoneNum(user.getMobile());
			message.bulidContext(maps);
			smsTemplate.sendMessage(message);
		} else {
			// 钉钉
			DingdingUtils.sendToCorpConversation(user.getDingding(),
					sysMessage.getContent());
		}
		sysMessage.setCreateDate(DateUtil.currentTimestamp());
		sysMessage.setOperateDate(DateUtil.currentTimestamp());
		sysMessageMapper.insertMessage(sysMessage);
	}

	public void delete(int id) {
		sysMessageMapper.deleteMessage(id);
	}
}
