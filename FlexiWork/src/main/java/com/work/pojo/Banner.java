/*Banner.java 是一个 Java 类，表示网站中的横幅（Banner）信息。该类通常用于展示在网站上的广告或重要信息区域*/
package com.work.pojo; // 定义该类所在的包

import java.util.Date; // 导入 Date 类，用于处理日期时间

public class Banner {
	// 横幅的唯一标识符
	private Integer id;
	// 横幅所属用户对象
	private User user;
	// 横幅的标题，描述横幅内容
	private String title;
	// 横幅的图片URL
	private String image;
	// 横幅的排序状态，可能用于展示的顺序或优先级
	private Integer orderStatus;
	// 横幅是否有效的状态，通常用于逻辑删除或禁用
	private Integer isEffect;
	// 横幅的创建时间
	private Date createTime;

	// 用户ID，可能用于与用户信息的关联
	private Integer userId;
	// 用户的昵称，可能用于展示横幅所属用户的名字
	private String nickName;



	// **********************************************Getter 和 Setter 方法，用于访问和修改私有字段

	public Integer getId() {
		return id; // 获取横幅的ID
	}
	public void setId(Integer id) {
		this.id = id; // 设置横幅的ID
	}

	public User getUser() {
		return user; // 获取横幅所属用户
	}
	public void setUser(User user) {
		this.user = user; // 设置横幅所属用户
	}

	public String getTitle() {
		return title; // 获取横幅标题
	}
	public void setTitle(String title) {
		this.title = title; // 设置横幅标题
	}

	public String getImage() {
		return image; // 获取横幅图片URL
	}
	public void setImage(String image) {
		this.image = image; // 设置横幅图片URL
	}

	public Integer getOrderStatus() {
		return orderStatus; // 获取横幅的排序状态
	}
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus; // 设置横幅的排序状态
	}

	public Integer getIsEffect() {
		return isEffect; // 获取横幅的有效状态
	}
	public void setIsEffect(Integer isEffect) {
		this.isEffect = isEffect; // 设置横幅的有效状态
	}

	public Date getCreateTime() {
		return createTime; // 获取横幅创建时间
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime; // 设置横幅创建时间
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
