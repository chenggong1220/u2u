package com.u2u.ibms.common.mapper;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.u2u.ibms.common.beans.Message;

public interface MessageMapper {

	List<Message> getMessages(RowBounds rb, @Param("title") String title, @Param("startDate") Timestamp startDate,
			@Param("endDate") Timestamp endDate, @Param("type") String type);

	List<Message> getMessageByIds(@Param("ids") List<Integer> ids);

	Message getMessageById(@Param("id") Integer id);

	void insertMessage(Message message);

	void updateMessage(Message message);

	void deleteMessage(@Param("id") Integer id);

	void deleteMessages(@Param("ids") List<Integer> ids);

	List<Integer> getUserMessagesIds(int userId);

}
