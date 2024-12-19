//ForumReplyMapper.java 文件提供了与论坛回复相关的基本 CRUD（创建、读取、更新和删除）操作接口，使用 MyBatis 进行数据持久化。
// 通过这个接口，其他服务或控制器可以方便地调用这些方法来操作论坛回复数据，从而简化了数据库操作的实现。使用 @Param 注解可以清晰地指定方法参数，增强了代码的可读性。
// 该接口的设计便于在应用中进行灵活的数据管理和查询，支持论坛回复的分页和统计功能。
// 这为用户提供了一个良好的互动体验，能够高效地浏览和管理论坛回复。
package com.work.mapper;

import com.work.pojo.ForumReply; // 引入 ForumReply POJO 类
import org.apache.ibatis.annotations.Param; // 引入 MyBatis 的 Param 注解

import java.util.List;

// ForumReplyMapper 接口，用于定义与论坛回复相关的数据库操作
public interface ForumReplyMapper {

	// 获取论坛回复列表，支持分页
	public List<ForumReply> getForumReplyList(@Param("forumReply") ForumReply forumReply, @Param("page") Integer page, @Param("limit") Integer limit);

	// 获取论坛回复总数
	public Integer getForumReplyListCount(@Param("forumReply") ForumReply forumReply);

	// 添加新的论坛回复
	public void addForumReply(@Param("forumReply") ForumReply forumReply);

	// 更新论坛回复
	public void updateForumReply(@Param("forumReply") ForumReply forumReply);

	// 根据回复ID获取特定论坛回复
	public ForumReply getForumReplyById(@Param("replyId") Integer replyId);

	// 根据回复ID删除论坛回复
	public void deleteForumReply(@Param("replyId") Integer replyId);
}
