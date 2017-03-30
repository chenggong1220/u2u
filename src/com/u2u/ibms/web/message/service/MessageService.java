package com.u2u.ibms.web.message.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.u2u.common.component.jpush.JPushUtils;
import com.u2u.common.component.sms.AlidayuSmsMessage;
import com.u2u.common.component.sms.ThridPartySmsTemplate;
import com.u2u.framework.base.BaseCondition;
import com.u2u.framework.base.BaseService;
import com.u2u.framework.util.DateUtil;
import com.u2u.ibms.common.beans.Message;
import com.u2u.ibms.common.beans.UserInfo;
import com.u2u.ibms.common.beans.UserMessage;
import com.u2u.ibms.common.mapper.MessageMapper;
import com.u2u.ibms.common.mapper.UserInfoMapper;
import com.u2u.ibms.common.mapper.UserMessageMapper;
import com.u2u.ibms.web.message.vo.MessageVo;

import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class MessageService extends BaseService {

	@Autowired
	private MessageMapper messageMapper;

	@Autowired
	private UserMessageMapper userMessageMapper;

	@Autowired
	private ThridPartySmsTemplate smsTemplate;

	@Autowired
	private UserInfoMapper userInfoMapper;

	public List<Message> get(RowBounds rb, MessageVo request,
			BaseCondition condition) {
		List<Message> messages = messageMapper.getMessages(rb,
				getStringCondition(request.getTitle()),
				getStartDate(condition), getEndDate(condition),
				getStringCondition(request.getType()));
		for (final Message message : messages) {
			this.convert(message);
		}
		return messages;
	}

	public Message getById(int id) {
		return this.convert(messageMapper.getMessageById(id));
	}

	public Message convert(final Message message) {
		if (message.getMsgType().equals("个人消息")) {
			List<UserMessage> userMessages = userMessageMapper.getByMessageId(
					message.getId(), new RowBounds());
			if (CollectionUtils.isNotEmpty(userMessages)) {
				for (UserMessage userMessage : userMessages) {
					message.setUsername((StringUtils.isNotEmpty(message
							.getUsername()) ? message.getUsername() : "")
							+ ","
							+ userInfoMapper.getById(userMessage.getUserId())
									.getUsername());
				}
				message.setUsername(StringUtils.removeStart(
						message.getUsername(), ","));
			} else {
				message.setUsername("所有用户");
			}
		} else {
			message.setUsername("所有用户");
		}
		return message;
	}

	public void insert(Message message, String[] userIds) {
		message.setCreateDate(DateUtil.currentTimestamp());
		message.setOperateDate(DateUtil.currentTimestamp());
		messageMapper.insertMessage(message);

		ArrayList<String> pushRegIDs = new ArrayList<String>();
		if (message.getMsgType().equals("个人消息")) {
			for (String userId : userIds) {
				try {
					UserInfo userInfo = userInfoMapper
							.getById(getIntegerCondition(userId));
					if (message.getType().equals("短信")) {
						Map<String, String> maps = new HashMap<String, String>();
						maps.put("AppName", "优尼斯租赁");
						maps.put("VerifyCode", message.getContent());
						AlidayuSmsMessage smsmessage = new AlidayuSmsMessage();
						smsmessage.setSignName("zhuce");
						smsmessage.setTemplateCode("zhuce");
						smsmessage.setPhoneNum(userInfo.getMobile());
						smsmessage.bulidContext(maps);
						// smsTemplate.sendMessage(smsmessage);
					}
					
					//Set RegID for Selected Users
					if(userInfo.getDeviceRegID()!= null && userInfo.isPushMessage()){
						pushRegIDs.add(userInfo.getDeviceRegID());
					}
					
					UserMessage userMessage = new UserMessage();
					userMessage.setMsgId(message.getId());
					userMessage.setUserId(getIntegerCondition(userId));
					userMessage.setOperateDate(DateUtil.currentTimestamp());
					userMessage.setCreateDate(DateUtil.currentTimestamp());
					userMessageMapper.insert(userMessage);			
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		} else {
			List<UserInfo> userInfos = userInfoMapper.getUserInfos(
					new RowBounds(), null, null, null, null,null);
			for (UserInfo userInfo : userInfos) {
				try {
					if (message.getType().equals("短信")) {
						Map<String, String> maps = new HashMap<String, String>();
						maps.put("AppName", "优尼斯租赁");
						maps.put("VerifyCode", message.getContent());
						AlidayuSmsMessage smsmessage = new AlidayuSmsMessage();
						smsmessage.setSignName("zhuce");
						smsmessage.setTemplateCode("zhuce");
						smsmessage.setPhoneNum(userInfo.getMobile());
						smsmessage.bulidContext(maps);
						// smsTemplate.sendMessage(smsmessage);
					}
					UserMessage userMessage = new UserMessage();
					userMessage.setMsgId(message.getId());
					userMessage.setUserId(userInfo.getId());
					userMessage.setOperateDate(DateUtil.currentTimestamp());
					userMessage.setCreateDate(DateUtil.currentTimestamp());
					userMessageMapper.insert(userMessage);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				//Set RegID for All Users
				if(userInfo.getDeviceRegID()!= null && userInfo.isPushMessage()){
					pushRegIDs.add(userInfo.getDeviceRegID());
				}
			}
		}
		
		if (message.getType().equals("APP") && pushRegIDs != null) {
			//极光消息推送
			JPushUtils.pushMessage(message.getTitle(), message.getContent(), pushRegIDs);
		}
	}

	public void delete(int id) {
		messageMapper.deleteMessage(id);
		userMessageMapper.deleteByMessageId(id);
	}
	
}
