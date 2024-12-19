/*SystemUpset.java 是一个 Java 类，表示系统中的“系统设置”或“公司设置”的相关信息。它通常用于存储和管理与企业或系统相关的一些基本信息，比如公司名称、地址、联系方式等。*/
package com.work.pojo; // 定义该类所在的包

import java.util.Date; // 导入 Date 类，用于处理日期时间

public class SystemUpset {
	// 唯一标识符，通常用于数据库中的主键
	private Integer id;

	// 与该设置相关的用户对象，表示修改或创建设置的用户
	private User user;

	// 欢迎文本，可能用于系统的欢迎界面
	private String welcomText;

	// 公司地址
	private String companyAddres;

	// 公司名称
	private String company;

	// QQ 联系方式
	private String qq;

	// 电话联系方式
	private String phone;

	// 公司Logo的路径或URL
	private String companyLogo;

	// 微信联系方式
	private String weChat;

	// 创建时间，记录设置信息的创建时间
	private Date createTime;

	// 用户ID，用于关联修改或创建设置的用户
	private Integer userId;

	// 获取唯一标识符
	public Integer getId() {
		return id; // 返回系统设置的ID
	}

	// 设置唯一标识符
	public void setId(Integer id) {
		this.id = id; // 设置系统设置的ID
	}

	// 获取公司地址
	public String getCompanyAddres() {
		return companyAddres; // 返回公司地址
	}

	// 设置公司地址
	public void setCompanyAddres(String companyAddres) {
		this.companyAddres = companyAddres; // 设置公司地址
	}

	// 获取与设置相关的用户对象
	public User getUser() {
		return user; // 返回与设置相关的用户对象
	}

	// 设置与设置相关的用户对象
	public void setUser(User user) {
		this.user = user; // 设置与设置相关的用户对象
	}

	// 获取欢迎文本
	public String getWelcomText() {
		return welcomText; // 返回欢迎文本
	}

	// 设置欢迎文本
	public void setWelcomText(String welcomText) {
		this.welcomText = welcomText; // 设置欢迎文本
	}

	// 获取公司名称
	public String getCompany() {
		return company; // 返回公司名称
	}

	// 设置公司名称
	public void setCompany(String company) {
		this.company = company; // 设置公司名称
	}

	// 获取QQ联系方式
	public String getQq() {
		return qq; // 返回QQ联系方式
	}

	// 设置QQ联系方式
	public void setQq(String qq) {
		this.qq = qq; // 设置QQ联系方式
	}

	// 获取电话联系方式
	public String getPhone() {
		return phone; // 返回电话联系方式
	}

	// 设置电话联系方式
	public void setPhone(String phone) {
		this.phone = phone; // 设置电话联系方式
	}

	// 获取公司Logo
	public String getCompanyLogo() {
		return companyLogo; // 返回公司Logo
	}

	// 设置公司Logo
	public void setCompanyLogo(String companyLogo) {
		this.companyLogo = companyLogo; // 设置公司Logo
	}

	// 获取微信联系方式
	public String getWeChat() {
		return weChat; // 返回微信联系方式
	}

	// 设置微信联系方式
	public void setWeChat(String weChat) {
		this.weChat = weChat; // 设置微信联系方式
	}

	// 获取创建时间
	public Date getCreateTime() {
		return createTime; // 返回创建时间
	}

	// 设置创建时间
	public void setCreateTime(Date createTime) {
		this.createTime = createTime; // 设置创建时间
	}

	// 获取用户ID
	public Integer getUserId() {
		return userId; // 返回用户ID
	}

	// 设置用户ID
	public void setUserId(Integer userId) {
		this.userId = userId; // 设置用户ID
	}
}
