package com.u2u.ibms.common.mapper;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.u2u.ibms.common.beans.SysMessage;

public interface SysMessageMapper {

	List<SysMessage> getMessages(RowBounds rb, @Param("title") String title,
			@Param("startDate") Timestamp startDate,
			@Param("endDate") Timestamp endDate, @Param("type") String type);

	List<SysMessage> getMessageByIds(@Param("ids") List<Integer> ids);

	SysMessage getMessageById(@Param("id") Integer id);

	void insertMessage(SysMessage sysMessage);

	void updateMessage(SysMessage sysMessage);

	void deleteMessage(@Param("id") Integer id);

	void deleteMessages(@Param("ids") List<Integer> ids);
}
