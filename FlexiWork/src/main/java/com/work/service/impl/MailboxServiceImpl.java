//MailboxServiceImpl.java 文件实现了邮箱管理的基本业务逻辑，提供了对邮箱数据的操作接口。通过该类，其他组件（如控制器）可以方便地进行邮箱信息的增、删、查、改操作。
package com.work.service.impl;

import com.work.mapper.MailboxMapper; // 引入 MailboxMapper 接口
import com.work.pojo.Mailbox; // 引入 Mailbox POJO 类
import com.work.service.MailboxService; // 引入 MailboxService 接口
import org.springframework.beans.factory.annotation.Autowired; // 引入 Spring 的 Autowired 注解
import org.springframework.stereotype.Service; // 引入 Spring 的 Service 注解

import java.util.List;

// 标识该类为服务层组件
@Service
public class MailboxServiceImpl implements MailboxService {

	// 自动注入 MailboxMapper 依赖
	@Autowired
	private MailboxMapper mailboxMapper;

	// 获取邮箱列表，支持分页
	@Override
	public List<Mailbox> getMailboxList(Mailbox mailbox, Integer page, Integer limit) {
		return mailboxMapper.getMailboxList(mailbox, page, limit); // 调用数据访问层方法获取邮箱列表
	}

	// 获取符合条件的邮箱总数
	@Override
	public Integer getMailboxListCount(Mailbox mailbox) {
		return mailboxMapper.getMailboxListCount(mailbox); // 调用数据访问层方法获取邮箱总数
	}

	// 添加新的邮箱信息
	@Override
	public void addMailbox(Mailbox mailbox) {
		mailboxMapper.addMailbox(mailbox); // 调用数据访问层方法将邮箱信息添加到数据库
	}

	// 更新已有的邮箱信息
	@Override
	public void updateMailbox(Mailbox mailbox) {
		mailboxMapper.updateMailbox(mailbox); // 调用数据访问层方法更新邮箱信息
	}

	// 根据邮箱ID获取特定的邮箱信息
	@Override
	public Mailbox getMailboxById(Integer id) {
		return mailboxMapper.getMailboxById(id); // 调用数据访问层方法根据ID获取邮箱信息
	}

	// 根据ID删除邮箱信息
	@Override
	public void deleteMailboxById(int id) {
		mailboxMapper.deleteMailboxById(id); // 调用数据访问层方法根据ID删除邮箱信息
	}
}
