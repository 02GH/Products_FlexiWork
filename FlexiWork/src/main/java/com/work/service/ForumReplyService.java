//ForumReplyService.java 文件定义了与论坛回复管理相关的基本服务接口，提供了对回复数据的操作方法。
// 通过这个接口，其他组件（如服务实现类和控制器）可以方便地进行论坛回复的信息增、删、查、改操作。
package com.work.service;

import com.work.pojo.ForumReply; // 引入 ForumReply POJO 类，表示论坛回复信息

import java.util.List;

// 定义论坛回复服务接口
public interface ForumReplyService {

	// 获取论坛回复列表，支持分页
	public List<ForumReply> getForumReplyList(ForumReply forumReply, Integer page, Integer limit);

	// 获取符合条件的论坛回复总数
	public Integer getForumReplyListCount(ForumReply forumReply);

	// 添加新的论坛回复
	public void addForumReply(ForumReply forumReply);

	// 更新已有的论坛回复
	public void updateForumReply(ForumReply forumReply);

	// 根据论坛回复ID获取特定回复信息
	public ForumReply getForumReplyById(Integer id);

	// 根据回复ID删除论坛回复
	public void deleteForumReply(Integer replyId);
}
