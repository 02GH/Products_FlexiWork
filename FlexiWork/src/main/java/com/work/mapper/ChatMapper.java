//ChatMapper.java 文件提供了与聊天记录相关的基本 CRUD（创建、读取、更新和删除）操作接口，使用 MyBatis 进行数据持久化。
// 通过这个接口，其他服务或控制器可以方便地调用这些方法来操作聊天记录数据，从而简化了数据库操作的实现。使用 @Param 注解可以清晰地指定方法参数，增强了代码的可读性。
// 该接口的设计便于在应用中进行灵活的数据管理和查询，支持聊天记录的分页和统计功能。
package com.work.mapper;

import com.work.pojo.Chat; // 引入 Chat POJO 类
import org.apache.ibatis.annotations.Param; // 引入 MyBatis 的 Param 注解

import java.util.List;

// ChatMapper 接口，用于定义与聊天记录相关的数据库操作
public interface ChatMapper {

	// 获取聊天记录列表，支持分页
	public List<Chat> getChatList(@Param("chat") Chat chat, @Param("page") Integer page, @Param("limit") Integer limit);

	// 获取聊天记录总数
	public Integer getChatListCount(@Param("chat") Chat chat);

	// 添加聊天记录
	public void addChat(@Param("chat") Chat chat);

	// 更新聊天记录
	public void updateChat(@Param("chat") Chat chat);

	// 根据ID获取聊天记录
	public Chat getChatById(@Param("id") Integer id);

	// 根据ID删除聊天记录
	public void deleteChatById(@Param("id") Integer id);

	// 查找聊天消息，支持分页
	public List<Chat> findChatList(@Param("chat") Chat chat, @Param("page") Integer page, @Param("limit") Integer limit);

	// 查找聊天消息总数
	public Integer findChatListCount(@Param("chat") Chat chat);

	// 获取我的聊天记录
	public List<Chat> myChatList(@Param("chat") Chat chat);
}
