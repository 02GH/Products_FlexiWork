//NoticeMapper.java 文件提供了与通知信息相关的基本 CRUD（创建、读取、更新和删除）操作接口，使用 MyBatis 进行数据持久化。
// 通过这个接口，其他服务或控制器可以方便地调用这些方法来操作通知数据，从而简化了数据库操作的实现。
package com.work.mapper;

import com.work.pojo.Notice; // 引入 Notice POJO 类
import org.apache.ibatis.annotations.Param; // 引入 MyBatis 的 Param 注解

import java.util.List;

// NoticeMapper 接口，用于定义与通知信息相关的数据库操作
public interface NoticeMapper {

	// 获取通知信息列表，支持分页
	public List<Notice> getNoticeList(@Param("notice") Notice notice, @Param("page") Integer page, @Param("limit") Integer limit);

	// 获取符合条件的通知信息总数
	public Integer getNoticeListCount(@Param("notice") Notice notice);

	// 添加新的通知信息
	public void addNotice(@Param("notice") Notice notice);

	// 更新已有的通知信息
	public void updateNotice(@Param("notice") Notice notice);

	// 根据通知ID获取特定的通知信息
	public Notice getNoticeById(@Param("id") Integer id);

	// 根据通知ID删除通知信息
	public void deleteNoticeById(@Param("id") int id);
}
