//MailboxService.java 文件定义了与邮箱管理相关的基本服务接口，提供了对邮箱数据的操作方法。
// 通过这个接口，其他组件（如服务实现类和控制器）可以方便地进行邮箱信息的增、删、查、改操作。
package com.work.service;

import com.work.pojo.Mailbox; // 引入 Mailbox POJO 类，表示邮箱信息

import java.util.List;

// 定义邮箱服务接口
public interface MailboxService {

	// 获取邮箱列表，支持分页
	public List<Mailbox> getMailboxList(Mailbox mailbox, Integer page, Integer limit);

	// 获取符合条件的邮箱总数
	public Integer getMailboxListCount(Mailbox mailbox);

	// 添加新的邮箱信息
	public void addMailbox(Mailbox mailbox);

	// 更新已有的邮箱信息
	public void updateMailbox(Mailbox mailbox);

	// 根据邮箱ID获取特定邮箱信息
	public Mailbox getMailboxById(Integer id);

	// 根据邮箱ID删除特定邮箱信息
	public void deleteMailboxById(int id);

}
