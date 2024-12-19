//NoticeServiceImpl.java 文件实现了通知管理的基本业务逻辑，提供了对通知数据的操作接口。通过该类，其他组件（如控制器）可以方便地进行通知信息的增、删、查、改操作。
package com.work.service.impl;

import com.work.mapper.NoticeMapper; // 引入 NoticeMapper 接口
import com.work.pojo.Notice; // 引入 Notice POJO 类
import com.work.service.NoticeService; // 引入 NoticeService 接口
import org.springframework.beans.factory.annotation.Autowired; // 引入 Spring 的 Autowired 注解
import org.springframework.stereotype.Service; // 引入 Spring 的 Service 注解

import java.util.List;

// 标识该类为服务层组件
@Service
public class NoticeServiceImpl implements NoticeService {

	// 自动注入 NoticeMapper 依赖
	@Autowired
	private NoticeMapper noticeMapper;

	// 获取通知列表，支持分页
	@Override
	public List<Notice> getNoticeList(Notice notice, Integer page, Integer limit) {
		return noticeMapper.getNoticeList(notice, page, limit); // 调用数据访问层方法获取通知列表
	}

	// 获取符合条件的通知总数
	@Override
	public Integer getNoticeListCount(Notice notice) {
		return noticeMapper.getNoticeListCount(notice); // 调用数据访问层方法获取通知总数
	}

	// 添加新的通知信息
	@Override
	public void addNotice(Notice notice) {
		noticeMapper.addNotice(notice); // 调用数据访问层方法将通知信息添加到数据库
	}

	// 更新已有的通知信息
	@Override
	public void updateNotice(Notice notice) {
		noticeMapper.updateNotice(notice); // 调用数据访问层方法更新通知信息
	}

	// 根据通知ID获取特定的通知信息
	@Override
	public Notice getNoticeById(Integer id) {
		return noticeMapper.getNoticeById(id); // 调用数据访问层方法根据ID获取通知信息
	}

	// 根据ID删除通知信息
	@Override
	public void deleteNoticeById(int id) {
		noticeMapper.deleteNoticeById(id); // 调用数据访问层方法根据ID删除通知信息
	}
}
