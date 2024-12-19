/*ForumReply.java 是一个 Java 类，用于表示论坛帖子的回复信息。该类通常用于存储用户对论坛帖子的评论或回复，包括回复内容、用户信息、时间戳等*/
package com.work.pojo; // 定义该类所在的包

import java.util.Date; // 导入 Date 类，用于处理日期时间

public class ForumReply {
	// 回复的唯一标识符
	private Integer replyId;
	// 关联的论坛对象，表示该回复所针对的帖子
	private Forum forum;
	// 关联的用户对象，表示发布该回复的用户
	private User user;
	// 回复的内容
	private String replyContent;
	// 回复中可能包含的图片
	private String replyImage;
	// 回复的时间
	private Date replyTime;
	// 回复的状态，通常用于标识回复是否有效
	private Integer status;

	// 用户相关信息
	private Integer userId; // 回复用户的ID
	private String realName; // 回复用户的真实姓名
	private String nickName; // 回复用户的昵称
	private String headerImage; // 回复用户的头像

	// 关联的论坛ID
	private Integer forumId;

	// Getter 和 Setter 方法，用于访问和修改私有字段

	public Integer getReplyId() {
		return replyId; // 获取回复的ID
	}
	public void setReplyId(Integer replyId) {
		this.replyId = replyId; // 设置回复的ID
	}

	public Forum getForum() {
		return forum; // 获取该回复对应的论坛对象
	}
	public void setForum(Forum forum) {
		this.forum = forum; // 设置该回复对应的论坛对象
	}

	public User getUser() {
		return user; // 获取发布该回复的用户对象
	}
	public void setUser(User user) {
		this.user = user; // 设置发布该回复的用户对象
	}

	public String getReplyContent() {
		return replyContent; // 获取回复的内容
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent; // 设置回复的内容
	}

	public String getReplyImage() {
		return replyImage; // 获取回复中附带的图片
	}
	public void setReplyImage(String replyImage) {
		this.replyImage = replyImage; // 设置回复中附带的图片
	}

	public Date getReplyTime() {
		return replyTime; // 获取回复的时间
	}
	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime; // 设置回复的时间
	}

	public Integer getStatus() {
		return status; // 获取回复的状态
	}
	public void setStatus(Integer status) {
		this.status = status; // 设置回复的状态
	}

	public Integer getUserId() {
		return userId; // 获取回复用户的ID
	}
	public void setUserId(Integer userId) {
		this.userId = userId; // 设置回复用户的ID
	}

	public String getRealName() {
		return realName; // 获取回复用户的真实姓名
	}
	public void setRealName(String realName) {
		this.realName = realName; // 设置回复用户的真实姓名
	}

	public String getNickName() {
		return nickName; // 获取回复用户的昵称
	}
	public void setNickName(String nickName) {
		this.nickName = nickName; // 设置回复用户的昵称
	}

	public String getHeaderImage() {
		return headerImage; // 获取回复用户的头像
	}
	public void setHeaderImage(String headerImage) {
		this.headerImage = headerImage; // 设置回复用户的头像
	}

	public Integer getForumId() {
		return forumId; // 获取关联的论坛ID
	}
	public void setForumId(Integer forumId) {
		this.forumId = forumId; // 设置关联的论坛ID
	}
}
