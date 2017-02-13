package com.u2u.ibms.rest.message.service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.u2u.framework.util.DateUtil;
import com.u2u.ibms.common.beans.Feedback;
import com.u2u.ibms.common.beans.Message;
import com.u2u.ibms.common.beans.UserInfo;
import com.u2u.ibms.common.beans.UserMessage;
import com.u2u.ibms.common.mapper.FeedbackMapper;
import com.u2u.ibms.common.mapper.MessageMapper;
import com.u2u.ibms.common.mapper.UserInfoMapper;
import com.u2u.ibms.common.mapper.UserMessageMapper;
import com.u2u.ibms.rest.auth.vo.FeedBackRequest;
import com.u2u.ibms.rest.message.vo.MessageRequest;
import com.u2u.ibms.rest.message.vo.MessageResponse;
import com.u2u.ibms.rest.message.vo.MessagesRequest;

@Service
public class MobileMessageService {

	@Autowired
	private FeedbackMapper feedbackMapper;
	@Autowired
	private MessageMapper messageMapper;
	@Autowired
	private UserInfoMapper userInfoMapper;
	@Autowired
	private UserMessageMapper userMessageMapper;
	
	public List<MessageResponse> getMessages(String username,
			MessagesRequest request) throws Exception {
		MessageResponse response = null;
		List<MessageResponse> list = new ArrayList<MessageResponse>();
		if (request.getPage() == null || request.getPageSize() == null) {
			throw new Exception("参数不合法");
		}
		UserInfo userinfo = userInfoMapper.getUserByUserName(username);
		Integer page = Integer.parseInt(request.getPage())-1;// 当前第几页
		Integer pageSize = Integer.parseInt(request.getPageSize());// 每页显示多少条记录
		List<UserMessage> userMessages = userMessageMapper.getByUserId(userinfo.getId(), new RowBounds(page,pageSize));
		for(UserMessage userMessage:userMessages){
			Message message = messageMapper.getMessageById(userMessage.getMsgId());
			response = new MessageResponse();
			response.setId(message.getId() + "");
			response.setStatus(userMessage.isStatus()?"0":"1");
			response.setTitle(message.getTitle());
			response.setDescription(message.getContent());
			response.setCreateDate(timestamp2Str(message.getCreateDate()));
			list.add(response);
		}
		return list;
	}

	private String timestamp2Str(Timestamp ts) {
		String tsStr = "";
		DateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		try {
			tsStr = sdf.format(ts);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tsStr;
	}

	public MessageResponse getSingleMessage(MessageRequest request)
			throws Exception {
		MessageResponse response = new MessageResponse();
		if (request == null || request.getId() == null) {
			throw new Exception("参数不合法");
		}
		Message message = messageMapper.getMessageById(Integer.parseInt(request.getId()));
		//通过消息ID和用于ID
		UserInfo userinfo = userInfoMapper.getUserByUserName(request.getUsername());
		List<UserMessage> userMessages= userMessageMapper.getByMessageIdAndUserId(message.getId(), userinfo.getId(), new RowBounds());
		for(UserMessage userMessage:userMessages){
			if(userMessage.getMsgId() == message.getId()){
				response = new MessageResponse();
				response.setId(message.getId() + "");
				response.setStatus(userMessage.isStatus()?"0":"1");
				response.setTitle(message.getTitle());
				response.setDescription(message.getContent());
				response.setCreateDate(timestamp2Str(message.getCreateDate()));
			}
		}
		return response;

	}

	public void feedback(FeedBackRequest request) throws Exception {
		Feedback feedback = new Feedback();
		feedback.setPhone(request.getPhone());
		feedback.setFeedback(request.getDescription());
		feedback.setStatus(0);
		feedback.setCreateDate(DateUtil.currentTimestamp());
		feedback.setOperateDate(DateUtil.currentTimestamp());
		feedbackMapper.insert(feedback);
	}

	public static void main(String[] args) throws Exception {
		FeedBackRequest feedback = new FeedBackRequest();
		feedback.setPhone("111");
		feedback.setDescription("康");
		System.out.println(new ObjectMapper().writeValueAsString(feedback));
	}
}
