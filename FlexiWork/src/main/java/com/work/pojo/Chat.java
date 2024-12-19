/*Chat.java 是一个 Java 类，用于表示聊天信息的实体。该类在聊天应用或系统中通常用于存储和管理用户之间的消息*/
package com.work.pojo; // 定义该类所在的包

import java.util.Date; // 导入 Date 类，用于处理日期时间

public class Chat {
	// 聊天记录的唯一标识符
	private Integer id;
	// 发消息的用户对象
	private User userFa;
	// 接收消息的用户对象
	private User userJie;
	// 聊天内容，存储消息文本
	private String content;
	// 聊天内容的图片，可能用于附加图片消息
	private String image;
	// 消息的创建时间
	private Date createTime;
	// 发件方是否删除消息的状态
	private Integer isRemoveFa;
	// 接收方是否删除消息的状态
	private Integer isRemoveJie;
	// 聊天的信号，可能用于标识消息类型或状态
	private String chatSignal;
	// 消息是否已被查看的状态
	private Integer isLook;

	// 发件方用户ID
	private Integer userIdFa;
	// 发件方用户昵称
	private String nickNameFa;
	// 发件方用户头像
	private String headerImageFa;
	// 接收方用户ID
	private Integer userIdJie;
	// 接收方用户昵称
	private String nickNameJie;
	// 接收方用户头像
	private String headerImageJie;

	// Getter 和 Setter 方法，用于访问和修改私有字段

	public Integer getId() {
		return id; // 获取聊天记录的ID
	}
	public void setId(Integer id) {
		this.id = id; // 设置聊天记录的ID
	}

	public User getUserFa() {
		return userFa; // 获取发件方用户对象
	}
	public void setUserFa(User userFa) {
		this.userFa = userFa; // 设置发件方用户对象
	}

	public User getUserJie() {
		return userJie; // 获取接收方用户对象
	}
	public void setUserJie(User userJie) {
		this.userJie = userJie; // 设置接收方用户对象
	}

	public String getContent() {
		return content; // 获取聊天内容
	}
	public void setContent(String content) {
		this.content = content; // 设置聊天内容
	}

	public String getImage() {
		return image; // 获取聊天图片
	}
	public void setImage(String image) {
		this.image = image; // 设置聊天图片
	}

	public Date getCreateTime() {
		return createTime; // 获取消息创建时间
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime; // 设置消息创建时间
	}

	public Integer getIsRemoveFa() {
		return isRemoveFa; // 获取发件方删除状态
	}
	public void setIsRemoveFa(Integer isRemoveFa) {
		this.isRemoveFa = isRemoveFa; // 设置发件方删除状态
	}

	public Integer getIsRemoveJie() {
		return isRemoveJie; // 获取接收方删除状态
	}
	public void setIsRemoveJie(Integer isRemoveJie) {
		this.isRemoveJie = isRemoveJie; // 设置接收方删除状态
	}

	public String getChatSignal() {
		return chatSignal; // 获取聊天信号
	}
	public void setChatSignal(String chatSignal) {
		this.chatSignal = chatSignal; // 设置聊天信号
	}

	public Integer getIsLook() {
		return isLook; // 获取消息查看状态
	}
	public void setIsLook(Integer isLook) {
		this.isLook = isLook; // 设置消息查看状态
	}

	public Integer getUserIdFa() {
		return userIdFa; // 获取发件方用户ID
	}
	public void setUserIdFa(Integer userIdFa) {
		this.userIdFa = userIdFa; // 设置发件方用户ID
	}

	public String getNickNameFa() {
		return nickNameFa; // 获取发件方用户昵称
	}
	public void setNickNameFa(String nickNameFa) {
		this.nickNameFa = nickNameFa; // 设置发件方用户昵称
	}

	public String getHeaderImageFa() {
		return headerImageFa; // 获取发件方用户头像
	}
	public void setHeaderImageFa(String headerImageFa) {
		this.headerImageFa = headerImageFa; // 设置发件方用户头像
	}

	public Integer getUserIdJie() {
		return userIdJie; // 获取接收方用户ID
	}
	public void setUserIdJie(Integer userIdJie) {
		this.userIdJie = userIdJie; // 设置接收方用户ID
	}

	public String getNickNameJie() {
		return nickNameJie; // 获取接收方用户昵称
	}
	public void setNickNameJie(String nickNameJie) {
		this.nickNameJie = nickNameJie; // 设置接收方用户昵称
	}

	public String getHeaderImageJie() {
		return headerImageJie; // 获取接收方用户头像
	}
	public void setHeaderImageJie(String headerImageJie) {
		this.headerImageJie = headerImageJie; // 设置接收方用户头像
	}
}
