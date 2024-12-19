/*Notice.java 是一个 Java 类，用于表示通知信息。该类通常用于存储系统中用户发布的通知内容，包括通知的标题、内容、创建时间等属性。*/
package com.work.pojo; // 定义该类所在的包

import java.util.Date; // 导入 Date 类，用于处理日期时间

public class Notice {
	// 通知的唯一标识符
	private Integer id;
	// 与该通知相关的用户对象，表示发布通知的用户
	private User user;
	// 通知的标题
	private String title;
	// 通知的内容
	private String content;
	// 通知中可能包含的图片
	private String image;
	// 通知的创建时间
	private Date createTime;
	// 创建时间的字符串格式，可能用于展示
	private String createTimes;

	// 用户ID，用于关联发布通知的用户
	private Integer userId;
	// 用户的昵称，用于在通知中显示
	private String nickName;

	// 获取创建时间的字符串格式
	public String getCreateTimes() {
		return createTimes; // 获取创建时间字符串
	}

	// 设置创建时间的字符串格式
	public void setCreateTimes(String createTimes) {
		this.createTimes = createTimes; // 设置创建时间字符串
	}

	// 获取通知的唯一标识符
	public Integer getId() {
		return id; // 返回通知的ID
	}

	// 设置通知的唯一标识符
	public void setId(Integer id) {
		this.id = id; // 设置通知的ID
	}

	// 获取与通知相关的用户对象
	public User getUser() {
		return user; // 返回发布通知的用户对象
	}

	// 设置与通知相关的用户对象
	public void setUser(User user) {
		this.user = user; // 设置与通知相关的用户对象
	}

	// 获取通知的标题
	public String getTitle() {
		return title; // 返回通知的标题
	}

	// 设置通知的标题
	public void setTitle(String title) {
		this.title = title; // 设置通知的标题
	}

	// 获取通知的内容
	public String getContent() {
		return content; // 返回通知的内容
	}

	// 设置通知的内容
	public void setContent(String content) {
		this.content = content; // 设置通知的内容
	}

	// 获取通知中包含的图片
	public String getImage() {
		return image; // 返回通知中包含的图片
	}

	// 设置通知中包含的图片
	public void setImage(String image) {
		this.image = image; // 设置通知中包含的图片
	}

	// 获取通知的创建时间
	public Date getCreateTime() {
		return createTime; // 返回通知的创建时间
	}

	// 设置通知的创建时间
	public void setCreateTime(Date createTime) {
		this.createTime = createTime; // 设置通知的创建时间
	}

	// 获取用户的ID
	public Integer getUserId() {
		return userId; // 返回发布通知的用户ID
	}

	// 设置用户的ID
	public void setUserId(Integer userId) {
		this.userId = userId; // 设置发布通知的用户ID
	}

	// 获取用户的昵称
	public String getNickName() {
		return nickName; // 返回用户的昵称
	}

	// 设置用户的昵称
	public void setNickName(String nickName) {
		this.nickName = nickName; // 设置用户的昵称
	}
}
