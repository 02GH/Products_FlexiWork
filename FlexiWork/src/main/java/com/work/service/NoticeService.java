//NoticeService.java 文件定义了与通知管理相关的基本服务接口，提供了对通知数据的操作方法。
// 通过这个接口，其他组件（如服务实现类和控制器）可以方便地进行通知信息的增、删、查、改操作。
package com.work.service;

import com.work.pojo.Notice; // 引入 Notice POJO 类，表示通知信息

import java.util.List;

// 定义通知服务接口
public interface NoticeService {

	// 获取通知列表，支持分页
	public List<Notice> getNoticeList(Notice notice, Integer page, Integer limit);

	// 获取符合条件的通知总数
	public Integer getNoticeListCount(Notice notice);

	// 添加新的通知信息
	public void addNotice(Notice notice);

	// 更新已有的通知信息
	public void updateNotice(Notice notice);

	// 根据通知ID获取特定通知信息
	public Notice getNoticeById(Integer id);

	// 根据通知ID删除特定通知信息
	public void deleteNoticeById(int id);
}
