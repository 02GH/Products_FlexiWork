//ChatService.java 文件定义了与聊天记录管理相关的基本服务接口，提供了对聊天记录数据的操作方法。
// 通过这个接口，其他组件（如服务实现类和控制器）可以方便地进行聊天记录的信息增、删、查、改操作。
package com.work.service;

import com.work.pojo.Chat; // 引入 Chat POJO 类，表示聊天信息

import java.util.List;

// 定义聊天服务接口
public interface ChatService {

	// 获取聊天记录列表，支持分页
	public List<Chat> getChatList(Chat chat, Integer page, Integer limit);

	// 获取符合条件的聊天记录总数
	public Integer getChatListCount(Chat chat);

	// 添加新的聊天记录
	public void addChat(Chat chat);

	// 更新已有的聊天记录
	public void updateChat(Chat chat);

	// 根据聊天记录ID获取特定聊天信息
	public Chat getChatById(Integer id);

	// 根据聊天记录ID删除聊天信息
	public void deleteChatByChatId(int id);

	// 查找聊天记录列表，支持分页
	public List<Chat> findChatList(Chat chat, Integer page, Integer limit);

	// 查找符合条件的聊天记录总数
	public Integer findChatListCount(Chat chat);

	// 获取用户的聊天记录列表
	public List<Chat> myChatList(Chat chat);
}
