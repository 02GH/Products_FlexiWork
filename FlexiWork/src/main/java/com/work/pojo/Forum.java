/*Forum.java 是一个 Java 类，用于表示论坛帖子的基本信息。该类通常用于存储和管理在论坛中发布的帖子，包括帖子标题、内容、用户信息等*/
package com.work.pojo; // 定义该类所在的包

import java.util.Date; // 导入 Date 类，用于处理日期时间
import java.util.List; // 导入 List 类，用于处理回复列表

public class Forum {
	// 论坛帖子的唯一标识符
	private Integer forumId;
	// 关联的用户对象，表示发布该帖子的用户
	private User user;
	// 帖子的标题
	private String title;
	// 帖子的内容
	private String content;
	// 帖子中的图片，可能用于附加图片内容
	private String image;
	// 帖子的有效状态，0表示无效，1表示有效
	private Integer isEffect;
	// 帖子的创建时间
	private Date createTime;
	// 该帖子的所有回复列表
	private List<ForumReply> forumReplys;

	// 用户相关信息
	private Integer userId; // 发布帖子的用户ID
	private String realName; // 发布帖子的用户真实姓名
	private String nickName; // 发布帖子的用户昵称
	private String headerImage; // 发布帖子的用户头像

	// Getter 和 Setter 方法，用于访问和修改私有字段

	public Integer getForumId() {
		return forumId; // 获取论坛帖子的ID
	}
	public void setForumId(Integer forumId) {
		this.forumId = forumId; // 设置论坛帖子的ID
	}

	public User getUser() {
		return user; // 获取发布该帖子的用户对象
	}
	public void setUser(User user) {
		this.user = user; // 设置发布该帖子的用户对象
	}

	public String getTitle() {
		return title; // 获取帖子的标题
	}
	public void setTitle(String title) {
		this.title = title; // 设置帖子的标题
	}

	public String getContent() {
		return content; // 获取帖子的内容
	}
	public void setContent(String content) {
		this.content = content; // 设置帖子的内容
	}

	public String getImage() {
		return image; // 获取帖子中的图片
	}
	public void setImage(String image) {
		this.image = image; // 设置帖子中的图片
	}

	public Integer getIsEffect() {
		return isEffect; // 获取帖子的有效状态
	}
	public void setIsEffect(Integer isEffect) {
		this.isEffect = isEffect; // 设置帖子的有效状态
	}

	public Date getCreateTime() {
		return createTime; // 获取帖子的创建时间
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime; // 设置帖子的创建时间
	}

	public List<ForumReply> getForumReplys() {
		return forumReplys; // 获取该帖子的所有回复
	}
	public void setForumReplys(List<ForumReply> forumReplys) {
		this.forumReplys = forumReplys; // 设置该帖子的所有回复
	}

	public Integer getUserId() {
		return userId; // 获取发布帖子的用户ID
	}
	public void setUserId(Integer userId) {
		this.userId = userId; // 设置发布帖子的用户ID
	}

	public String getRealName() {
		return realName; // 获取发布帖子的用户真实姓名
	}
	public void setRealName(String realName) {
		this.realName = realName; // 设置发布帖子的用户真实姓名
	}

	public String getNickName() {
		return nickName; // 获取发布帖子的用户昵称
	}
	public void setNickName(String nickName) {
		this.nickName = nickName; // 设置发布帖子的用户昵称
	}

	public String getHeaderImage() {
		return headerImage; // 获取发布帖子的用户头像
	}
	public void setHeaderImage(String headerImage) {
		this.headerImage = headerImage; // 设置发布帖子的用户头像
	}
}
