/*Work.java 是一个 Java 类，表示与工作的相关信息。它通常用于存储和管理用户发布的工作内容，可能用于职场、项目、任务或新闻等上下文中。*/
package com.work.pojo; // 定义该类所在的包

import java.util.Date; // 导入 Date 类，用于处理日期时间

public class Work {
	// 工作的唯一标识符，通常用于数据库中的主键
	private Integer id;

	// 与该工作相关的用户对象，表示发布工作的用户
	private User user;

	// 工作类别，例如项目、任务等
	private String workCategory;

	// 工作标题
	private String title;

	// 工作内容的详细描述
	private String content;

	// 工作相关的图片路径或URL
	private String image;

	// 工作状态的有效性标志，通常用于表示工作是否可见
	private Integer isEffect;

	// 工作审核状态的标志，通常用于表示工作是否正在审核中
	private Integer isAuditing;

	// 相关联系人的电话号码
	private String phone;

	// 工作创建时间，记录工作信息的创建时间
	private Date createTime;

	// 创建时间的字符串表示，可能用于界面展示
	private String createTimes;

	// 发布工作的用户ID
	private Integer userId;

	// 发布工作的用户真实姓名
	private String realName;

	// 发布工作的用户头像的路径或URL
	private String headerImage;

	// 获取审核状态
	public Integer getIsAuditing() {
		return isAuditing; // 返回工作审核状态
	}

	// 设置审核状态
	public void setIsAuditing(Integer isAuditing) {
		this.isAuditing = isAuditing; // 设置工作审核状态
	}

	// 获取创建时间的字符串表示
	public String getCreateTimes() {
		return createTimes; // 返回创建时间的字符串表示
	}

	// 设置创建时间的字符串表示
	public void setCreateTimes(String createTimes) {
		this.createTimes = createTimes; // 设置创建时间的字符串表示
	}

	// 获取工作ID
	public Integer getId() {
		return id; // 返回工作ID
	}

	// 设置工作ID
	public void setId(Integer id) {
		this.id = id; // 设置工作ID
	}

	// 获取与工作相关的用户对象
	public User getUser() {
		return user; // 返回与工作相关的用户对象
	}

	// 设置与工作相关的用户对象
	public void setUser(User user) {
		this.user = user; // 设置与工作相关的用户对象
	}

	// 获取工作类别
	public String getWorkCategory() {
		return workCategory; // 返回工作类别
	}

	// 设置工作类别
	public void setWorkCategory(String workCategory) {
		this.workCategory = workCategory; // 设置工作类别
	}

	// 获取工作标题
	public String getTitle() {
		return title; // 返回工作标题
	}

	// 设置工作标题
	public void setTitle(String title) {
		this.title = title; // 设置工作标题
	}

	// 获取工作内容
	public String getContent() {
		return content; // 返回工作内容
	}

	// 设置工作内容
	public void setContent(String content) {
		this.content = content; // 设置工作内容
	}

	// 获取工作相关的图片
	public String getImage() {
		return image; // 返回工作相关的图片
	}

	// 设置工作相关的图片
	public void setImage(String image) {
		this.image = image; // 设置工作相关的图片
	}

	// 获取工作有效性标志
	public Integer getIsEffect() {
		return isEffect; // 返回工作有效性标志
	}

	// 设置工作有效性标志
	public void setIsEffect(Integer isEffect) {
		this.isEffect = isEffect; // 设置工作有效性标志
	}

	// 获取联系电话
	public String getPhone() {
		return phone; // 返回联系电话
	}

	// 设置联系电话
	public void setPhone(String phone) {
		this.phone = phone; // 设置联系电话
	}

	// 获取创建时间
	public Date getCreateTime() {
		return createTime; // 返回工作创建时间
	}

	// 设置创建时间
	public void setCreateTime(Date createTime) {
		this.createTime = createTime; // 设置工作创建时间
	}

	// 获取用户ID
	public Integer getUserId() {
		return userId; // 返回发布工作的用户ID
	}

	// 设置用户ID
	public void setUserId(Integer userId) {
		this.userId = userId; // 设置发布工作的用户ID
	}

	// 获取用户真实姓名
	public String getRealName() {
		return realName; // 返回发布工作的用户真实姓名
	}

	// 设置用户真实姓名
	public void setRealName(String realName) {
		this.realName = realName; // 设置发布工作的用户真实姓名
	}

	// 获取用户头像
	public String getHeaderImage() {
		return headerImage; // 返回发布工作的用户头像
	}

	// 设置用户头像
	public void setHeaderImage(String headerImage) {
		this.headerImage = headerImage; // 设置发布工作的用户头像
	}
}
