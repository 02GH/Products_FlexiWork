/*Collect.java 是一个 Java 类，表示用户对论坛帖子的收藏信息。该类通常用于跟踪哪些用户收藏了哪些论坛帖子，以便在用户界面上显示用户的收藏内容*/
package com.work.pojo; // 定义该类所在的包

import java.util.Date; // 导入 Date 类，用于处理日期时间

public class Collect {
	// 收藏记录的唯一标识符
	private Integer id;
	// 关联的论坛对象，表示被收藏的论坛帖子
	private Forum forum;
	// 关联的用户对象，表示收藏该帖子的用户
	private User user;
	// 收藏的创建时间
	private Date createTime;

	// 用户ID，表示收藏该帖子的用户
	private Integer userId;
	// 论坛ID，表示被收藏的论坛帖子的ID
	private Integer forumId;

	// Getter 和 Setter 方法，用于访问和修改私有字段

	public Integer getId() {
		return id; // 获取收藏记录的ID
	}
	public void setId(Integer id) {
		this.id = id; // 设置收藏记录的ID
	}

	public Forum getForum() {
		return forum; // 获取被收藏的论坛对象
	}
	public void setForum(Forum forum) {
		this.forum = forum; // 设置被收藏的论坛对象
	}

	public User getUser() {
		return user; // 获取收藏该帖子的用户对象
	}
	public void setUser(User user) {
		this.user = user; // 设置收藏该帖子的用户对象
	}

	public Date getCreateTime() {
		return createTime; // 获取收藏的创建时间
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime; // 设置收藏的创建时间
	}

	public Integer getUserId() {
		return userId; // 获取用户ID
	}
	public void setUserId(Integer userId) {
		this.userId = userId; // 设置用户ID
	}

	public Integer getForumId() {
		return forumId; // 获取论坛ID
	}
	public void setForumId(Integer forumId) {
		this.forumId = forumId; // 设置论坛ID
	}
}
