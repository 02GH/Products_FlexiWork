//ForumReplyServiceImpl.java 文件实现了论坛回复管理的业务逻辑，提供了对论坛回复数据的基本操作接口。通过该类，其他组件（如控制器）可以方便地进行论坛回复的增、删、查、改操作。
package com.work.service.impl;

import com.work.mapper.ForumReplyMapper; // 引入 ForumReplyMapper 接口
import com.work.pojo.ForumReply; // 引入 ForumReply POJO 类
import com.work.service.ForumReplyService; // 引入 ForumReplyService 接口
import org.springframework.beans.factory.annotation.Autowired; // 引入 Spring 的 Autowired 注解
import org.springframework.stereotype.Service; // 引入 Spring 的 Service 注解

import java.util.List;

// 标识该类为服务层组件
@Service
public class ForumReplyServiceImpl implements ForumReplyService {

	// 自动注入 ForumReplyMapper 依赖
	@Autowired
	private ForumReplyMapper forumReplyMapper;

	// 获取论坛回复列表，支持分页
	@Override
	public List<ForumReply> getForumReplyList(ForumReply forumReply, Integer page, Integer limit) {
		return forumReplyMapper.getForumReplyList(forumReply, page, limit);
	}

	// 获取符合条件的论坛回复总数
	@Override
	public Integer getForumReplyListCount(ForumReply forumReply) {
		return forumReplyMapper.getForumReplyListCount(forumReply);
	}

	// 添加新的论坛回复
	@Override
	public void addForumReply(ForumReply forumReply) {
		forumReplyMapper.addForumReply(forumReply);
	}

	// 更新已有的论坛回复信息
	@Override
	public void updateForumReply(ForumReply forumReply) {
		forumReplyMapper.updateForumReply(forumReply);
	}

	// 根据论坛回复ID获取特定的回复信息
	@Override
	public ForumReply getForumReplyById(Integer id) {
		return forumReplyMapper.getForumReplyById(id);
	}

	// 根据论坛回复ID删除特定的回复
	@Override
	public void deleteForumReply(Integer replyId) {
		forumReplyMapper.deleteForumReply(replyId);
	}
}
