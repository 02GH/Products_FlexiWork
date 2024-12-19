/*Adver.java 是一个 Java 类，通常用于表示广告相关的实体*/
package com.work.pojo; // 定义该类所在的包

import java.util.Date; // 导入 Date 类，用于处理日期时间

public class Adver {
	// 广告的唯一标识符
	private Integer id;
	// 广告所属用户对象
	private User user;
	// 广告的标题
	private String title;
	// 广告的内容
	private String content;
	// 广告的图片URL
	private String image;
	// 广告的位置，可能用于前端展示的顺序或分类
	private Integer position;
	// 广告创建时间
	private Date createTime;

	// 用户ID，可能用于与用户信息的关联
	private Integer userId;
	// 用户的昵称，可能用于展示广告所属用户的名字
	private String nickName;

	// *************************************Getter 和 Setter 方法，用于访问和修改私有字段

	public Integer getId() {
		return id; // 获取广告的ID
	}
	public void setId(Integer id) {
		this.id = id; // 设置广告的ID
	}

	public User getUser() {
		return user; // 获取广告所属用户
	}
	public void setUser(User user) {
		this.user = user; // 设置广告所属用户
	}

	public String getTitle() {
		return title; // 获取广告标题
	}
	public void setTitle(String title) {
		this.title = title; // 设置广告标题
	}

	public String getContent() {
		return content; // 获取广告内容
	}
	public void setContent(String content) {
		this.content = content; // 设置广告内容
	}

	public String getImage() {
		return image; // 获取广告图片URL
	}
	public void setImage(String image) {
		this.image = image; // 设置广告图片URL
	}

	public Integer getPosition() {
		return position; // 获取广告位置
	}
	public void setPosition(Integer position) {
		this.position = position; // 设置广告位置
	}

	public Date getCreateTime() {
		return createTime; // 获取广告创建时间
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime; // 设置广告创建时间
	}

	public Integer getUserId() {
		return userId; // 获取用户ID
	}
	public void setUserId(Integer userId) {
		this.userId = userId; // 设置用户ID
	}

	public String getNickName() {
		return nickName; // 获取用户昵称
	}
	public void setNickName(String nickName) {
		this.nickName = nickName; // 设置用户昵称
	}
}
