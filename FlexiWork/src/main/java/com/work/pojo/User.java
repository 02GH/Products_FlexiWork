/*User.java 是一个 Java 类，用于表示用户的相关信息，通常用于用户管理模块。在典型的应用程序中，该类充当了用户数据的模型，封装了与用户相关的属性及其访问方法。*/
package com.work.pojo; // 定义该类所在的包

import java.util.Date; // 导入 Date 类，用于处理日期时间

public class User {
	// 用户唯一标识符
	private Integer userId;

	// 用户真实姓名
	private String realName;

	// 用户昵称
	private String nickName;

	// 用户性别，通常用整数表示（例如 1 为男性，2 为女性）
	private Integer sex;

	// 用户电话
	private String phone;

	// 用户出生日期
	private Date pirthday;

	// 用户电子邮箱
	private String email;

	// 用户头像的路径或URL
	private String headerImage;

	// 密码提示问题
	private String question;

	// 密码提示问题的答案
	private String answer;

	// 用户类别，例如管理员、普通用户等
	private String userCategory;

	// 用户级别，通常用于区分用户权限
	private Integer userLeven;

	// 用户密码，通常需要进行加密保存
	private String password;

	// 是否有效的标志，通常用于标识用户的状态（例如：1 有效，0 无效）
	private Integer isEffect;

	// 工作状态，标识用户是否在职
	private Integer isWork;

	// 备注信息
	private String remark;

	// 创建时间，记录用户信息的创建时间
	private Date createTime;

	// 获取工作状态
	public Integer getIsWork() {
		return isWork; // 返回用户工作状态
	}

	// 设置工作状态
	public void setIsWork(Integer isWork) {
		this.isWork = isWork; // 设置用户工作状态
	}

	// 获取备注信息
	public String getRemark() {
		return remark; // 返回备注信息
	}

	// 设置备注信息
	public void setRemark(String remark) {
		this.remark = remark; // 设置备注信息
	}

	// 获取用户ID
	public Integer getUserId() {
		return userId; // 返回用户ID
	}

	// 设置用户ID
	public void setUserId(Integer userId) {
		this.userId = userId; // 设置用户ID
	}

	// 获取真实姓名
	public String getRealName() {
		return realName; // 返回用户真实姓名
	}

	// 设置真实姓名
	public void setRealName(String realName) {
		this.realName = realName; // 设置用户真实姓名
	}

	// 获取昵称
	public String getNickName() {
		return nickName; // 返回用户昵称
	}

	// 设置昵称
	public void setNickName(String nickName) {
		this.nickName = nickName; // 设置用户昵称
	}

	// 获取性别
	public Integer getSex() {
		return sex; // 返回用户性别
	}

	// 设置性别
	public void setSex(Integer sex) {
		this.sex = sex; // 设置用户性别
	}

	// 获取电话
	public String getPhone() {
		return phone; // 返回用户电话
	}

	// 设置电话
	public void setPhone(String phone) {
		this.phone = phone; // 设置用户电话
	}

	// 获取出生日期
	public Date getPirthday() {
		return pirthday; // 返回用户出生日期
	}

	// 设置出生日期
	public void setPirthday(Date pirthday) {
		this.pirthday = pirthday; // 设置用户出生日期
	}

	// 获取电子邮箱
	public String getEmail() {
		return email; // 返回用户电子邮箱
	}

	// 设置电子邮箱
	public void setEmail(String email) {
		this.email = email; // 设置用户电子邮箱
	}

	// 获取头像
	public String getHeaderImage() {
		return headerImage; // 返回用户头像
	}

	// 设置头像
	public void setHeaderImage(String headerImage) {
		this.headerImage = headerImage; // 设置用户头像
	}

	// 获取密码提示问题
	public String getQuestion() {
		return question; // 返回密码提示问题
	}

	// 设置密码提示问题
	public void setQuestion(String question) {
		this.question = question; // 设置密码提示问题
	}

	// 获取密码提示问题的答案
	public String getAnswer() {
		return answer; // 返回密码提示问题的答案
	}

	// 设置密码提示问题的答案
	public void setAnswer(String answer) {
		this.answer = answer; // 设置密码提示问题的答案
	}

	// 获取用户类别
	public String getUserCategory() {
		return userCategory; // 返回用户类别
	}

	// 设置用户类别
	public void setUserCategory(String userCategory) {
		this.userCategory = userCategory; // 设置用户类别
	}

	// 获取用户级别
	public Integer getUserLeven() {
		return userLeven; // 返回用户级别
	}

	// 设置用户级别
	public void setUserLeven(Integer userLeven) {
		this.userLeven = userLeven; // 设置用户级别
	}

	// 获取密码
	public String getPassword() {
		return password; // 返回用户密码
	}

	// 设置密码
	public void setPassword(String password) {
		this.password = password; // 设置用户密码
	}

	// 获取有效性标志
	public Integer getIsEffect() {
		return isEffect; // 返回用户有效性标志
	}

	// 设置有效性标志
	public void setIsEffect(Integer isEffect) {
		this.isEffect = isEffect; // 设置用户有效性标志
	}

	// 获取创建时间
	public Date getCreateTime() {
		return createTime; // 返回用户信息的创建时间
	}

	// 设置创建时间
	public void setCreateTime(Date createTime) {
		this.createTime = createTime; // 设置用户信息的创建时间
	}
}
