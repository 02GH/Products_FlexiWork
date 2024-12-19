//MailboxMapper.java 文件提供了与邮箱信息相关的基本 CRUD（创建、读取、更新和删除）操作接口，使用 MyBatis 进行数据持久化。
// 通过这个接口，其他服务或控制器可以方便地调用这些方法来操作邮箱数据，从而简化了数据库操作的实现。
package com.work.mapper;

import com.work.pojo.Mailbox; // 引入 Mailbox POJO 类
import org.apache.ibatis.annotations.Param; // 引入 MyBatis 的 Param 注解

import java.util.List;

// MailboxMapper 接口，用于定义与邮箱信息相关的数据库操作
public interface MailboxMapper {

	// 获取邮箱信息列表，支持分页
	public List<Mailbox> getMailboxList(@Param("mailbox") Mailbox mailbox, @Param("page") Integer page, @Param("limit") Integer limit);

	// 获取符合条件的邮箱信息总数
	public Integer getMailboxListCount(@Param("mailbox") Mailbox mailbox);

	// 添加新的邮箱信息
	public void addMailbox(@Param("mailbox") Mailbox mailbox);

	// 更新已有的邮箱信息
	public void updateMailbox(@Param("mailbox") Mailbox mailbox);

	// 根据邮箱ID获取特定的邮箱信息
	public Mailbox getMailboxById(@Param("id") Integer id);

	// 根据邮箱ID删除邮箱信息
	public void deleteMailboxById(@Param("id") int id);
}
