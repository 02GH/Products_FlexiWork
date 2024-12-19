/*Jianzhi.java 是一个 Java 类，通常用于表示兼职工作（或临时工作）的相关信息。该类包含了与兼职工作相关的各种属性，便于存储和管理该工作的信息。*/
package com.work.pojo; // 定义该类所在的包

public class Jianzhi {
	// 兼职工作的唯一标识符
	private Integer id;
	// 用户ID，表示发布该兼职工作的用户
	private Integer userId;
	// 工作ID，可能用于关联具体的工作内容
	private Integer workId;
	// 工作类别，表示兼职工作的类型
	private String workCategory;
	// 兼职工作的标题
	private String title;
	// 兼职工作的详细内容
	private String content;
	// 兼职工作相关的图片
	private String image;
	// 联系电话，用于应聘者联系发布者
	private String phone;
	// 发布该兼职工作的用户真实姓名
	private String realName;

	// Getter 和 Setter 方法，用于访问和修改私有字段

	public String getRealName() {
		return realName; // 获取发布该工作的用户真实姓名
	}

	public void setRealName(String realName) {
		this.realName = realName; // 设置发布该工作的用户真实姓名
	}

	public String getPhone() {
		return phone; // 获取发布该工作的用户联系电话
	}

	public void setPhone(String phone) {
		this.phone = phone; // 设置发布该工作的用户联系电话
	}

	public String getWorkCategory() {
		return workCategory; // 获取兼职工作的类别
	}

	public void setWorkCategory(String workCategory) {
		this.workCategory = workCategory; // 设置兼职工作的类别
	}

	public String getTitle() {
		return title; // 获取兼职工作的标题
	}

	public void setTitle(String title) {
		this.title = title; // 设置兼职工作的标题
	}

	public String getContent() {
		return content; // 获取兼职工作的详细内容
	}

	public void setContent(String content) {
		this.content = content; // 设置兼职工作的详细内容
	}

	public String getImage() {
		return image; // 获取兼职工作相关的图片
	}

	public void setImage(String image) {
		this.image = image; // 设置兼职工作相关的图片
	}

	public Integer getId() {
		return id; // 获取兼职工作的唯一标识符
	}

	public void setId(Integer id) {
		this.id = id; // 设置兼职工作的唯一标识符
	}

	public Integer getUserId() {
		return userId; // 获取发布该兼职工作的用户ID
	}

	public void setUserId(Integer userId) {
		this.userId = userId; // 设置发布该兼职工作的用户ID
	}

	public Integer getWorkId() {
		return workId; // 获取工作ID，关联具体的工作内容
	}

	public void setWorkId(Integer workId) {
		this.workId = workId; // 设置工作ID，关联具体的工作内容
	}
}
