package com.u2u.ibms.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.u2u.ibms.common.beans.UserMessage;

public interface UserMessageMapper {

	public List<UserMessage> getByUserId(@Param("userId") Integer userId,
			RowBounds rb);

	public List<UserMessage> getByMessageId(@Param("msgId") Integer msgId,
			RowBounds rb);
	
	public List<UserMessage> getByMessageIdAndUserId(@Param("msgId") Integer msgId,@Param("userId") Integer userId,
			RowBounds rb);
	public void insert(UserMessage userMessage);

	public void update(UserMessage userMessage);

	public void deleteById(@Param("id") int id);

	public void deleteByUserId(@Param("userId") int userId);

	public void deleteByMessageId(@Param("msgId") int msgId);
}
