//ForumMapper.java 文件提供了与论坛主题相关的基本 CRUD（创建、读取、更新和删除）操作接口，使用 MyBatis 进行数据持久化。
// 通过这个接口，其他服务或控制器可以方便地调用这些方法来操作论坛主题数据，从而简化了数据库操作的实现。使用 @Param 注解可以清晰地指定方法参数，增强了代码的可读性。
// 该接口的设计便于在应用中进行灵活的数据管理和查询，支持论坛主题的分页和统计功能。
// 这为用户提供了一个良好的论坛体验，能够高效地浏览和管理主题。
package com.work.mapper;

import com.work.pojo.Forum; // 引入 Forum POJO 类
import org.apache.ibatis.annotations.Param; // 引入 MyBatis 的 Param 注解

import java.util.List;

// ForumMapper 接口，用于定义与论坛主题相关的数据库操作
public interface ForumMapper {

	// 获取论坛主题列表，支持分页
	public List<Forum> getForumList(@Param("forum") Forum forum, @Param("page") Integer page, @Param("limit") Integer limit);

	// 获取论坛主题总数
	public Integer getForumListCount(@Param("forum") Forum forum);

	// 添加新的论坛主题
	public void addForum(@Param("forum") Forum forum);

	// 更新论坛主题
	public void updateForum(@Param("forum") Forum forum);

	// 根据论坛ID获取特定论坛主题
	public Forum getForumById(@Param("forumId") Integer forumId);
}
