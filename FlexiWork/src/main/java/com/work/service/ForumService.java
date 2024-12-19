//ForumService.java 文件定义了与论坛管理相关的基本服务接口，提供了对论坛数据的操作方法。
// 通过这个接口，其他组件（如服务实现类和控制器）可以方便地进行论坛信息的增、删、查、改操作。
package com.work.service;

import com.work.pojo.Forum; // 引入 Forum POJO 类，表示论坛信息

import java.util.List;

// 定义论坛服务接口
public interface ForumService {

	// 获取论坛列表，支持分页
	public List<Forum> getForumList(Forum forum, Integer page, Integer limit);

	// 获取符合条件的论坛总数
	public Integer getForumListCount(Forum forum);

	// 添加新的论坛
	public void addForum(Forum forum);

	// 更新已有的论坛信息
	public void updateForum(Forum forum);

	// 根据论坛ID获取特定论坛信息
	public Forum getForumById(Integer id);

}
