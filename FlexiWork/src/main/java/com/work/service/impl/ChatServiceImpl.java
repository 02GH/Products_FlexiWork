//ChatServiceImpl.java 文件实现了聊天记录管理的业务逻辑，提供了对聊天记录数据的基本操作接口。通过该类，其他组件（如控制器）可以方便地进行聊天记录的增、删、查、改操作。
package com.work.service.impl;

import com.work.mapper.ChatMapper; // 引入 ChatMapper 接口
import com.work.pojo.Chat; // 引入 Chat POJO 类
import com.work.service.ChatService; // 引入 ChatService 接口
import org.springframework.beans.factory.annotation.Autowired; // 引入 Spring 的 Autowired 注解
import org.springframework.stereotype.Service; // 引入 Spring 的 Service 注解

import java.util.List;

// 标识该类为服务层组件
@Service
public class ChatServiceImpl implements ChatService {

	// 自动注入 ChatMapper 依赖
	@Autowired
	private ChatMapper chatMapper;

	// 获取聊天记录列表，支持分页
	@Override
	public List<Chat> getChatList(Chat chat, Integer page, Integer limit) {
		return chatMapper.getChatList(chat, page, limit);
	}

	// 获取符合条件的聊天记录总数
	@Override
	public Integer getChatListCount(Chat chat) {
		return chatMapper.getChatListCount(chat);
	}

	// 添加新的聊天记录
	@Override
	public void addChat(Chat chat) {
		chatMapper.addChat(chat);
	}

	// 更新已有的聊天记录
	@Override
	public void updateChat(Chat chat) {
		chatMapper.updateChat(chat);
	}

	// 根据聊天记录ID获取特定的聊天记录信息
	@Override
	public Chat getChatById(Integer id) {
		return chatMapper.getChatById(id);
	}

	// 根据聊天记录ID删除特定聊天记录
	@Override
	public void deleteChatByChatId(int id) {
		chatMapper.deleteChatById(id);
	}

	// 查询符合条件的聊天记录列表
	@Override
	public List<Chat> findChatList(Chat chat, Integer page, Integer limit) {
		return chatMapper.findChatList(chat, page, limit);
	}

	// 获取符合条件的聊天记录总数
	@Override
	public Integer findChatListCount(Chat chat) {
		return chatMapper.findChatListCount(chat);
	}

	// 获取当前用户的聊天记录列表
	@Override
	public List<Chat> myChatList(Chat chat) {
		return chatMapper.myChatList(chat);
	}
}
