/*Mailbox.java 是一个 Java 类，用于表示邮箱信息。该类通常用于存储用户的邮件信息，包括邮件的标题、内容、创建时间等属性。*/
package com.work.pojo; // 定义该类所在的包

import java.util.Date; // 导入 Date 类，用于处理日期时间

public class Mailbox {
	// 邮件的唯一标识符
	private Integer id;
	// 邮件相关的用户对象，表示发送或接收邮件的用户
	private User user;
	// 邮件的标题
	private String title;
	// 邮件的内容
	private String content;
	// 邮件中可能包含的图片
	private String image;
	// 邮件的创建时间
	private Date createTime;

	// 用户ID，用于关联用户信息
	private Integer userId;
	// 用户的昵称，用于显示或标识
	private String nickName;

	// Getter 和 Setter 方法，用于访问和修改私有字段

	public Integer getId() {
		return id; // 获取邮件的唯一标识符
	}

	public void setId(Integer id) {
		this.id = id; // 设置邮件的唯一标识符
	}

	public User getUser() {
		return user; // 获取邮件相关的用户对象
	}

	public void setUser(User user) {
		this.user = user; // 设置邮件相关的用户对象
	}

	public String getTitle() {
		return title; // 获取邮件的标题
	}

	public void setTitle(String title) {
		this.title = title; // 设置邮件的标题
	}

	public String getContent() {
		return content; // 获取邮件的内容
	}

	public void setContent(String content) {
		this.content = content; // 设置邮件的内容
	}

	public String getImage() {
		return image; // 获取邮件中包含的图片
	}

	public void setImage(String image) {
		this.image = image; // 设置邮件中包含的图片
	}

	public Date getCreateTime() {
		return createTime; // 获取邮件的创建时间
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime; // 设置邮件的创建时间
	}

	public Integer getUserId() {
		return userId; // 获取用户ID
	}

	public void setUserId(Integer userId) {
		this.userId = userId; // 设置用户ID
	}

	public String getNickName() {
		return nickName; // 获取用户的昵称
	}

	public void setNickName(String nickName) {
		this.nickName = nickName; // 设置用户的昵称
	}
}
