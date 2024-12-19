//ForumServiceImpl.java 文件实现了论坛管理的业务逻辑，提供了对论坛数据的基本操作接口。通过该类，其他组件（如控制器）可以方便地进行论坛的增、删、查、改操作。
package com.work.service.impl;

import com.work.mapper.ForumMapper; // 引入 ForumMapper 接口
import com.work.pojo.Forum; // 引入 Forum POJO 类
import com.work.service.ForumService; // 引入 ForumService 接口
import org.springframework.beans.factory.annotation.Autowired; // 引入 Spring 的 Autowired 注解
import org.springframework.stereotype.Service; // 引入 Spring 的 Service 注解

import java.util.List;

// 标识该类为服务层组件
@Service
public class ForumServiceImpl implements ForumService {

	// 自动注入 ForumMapper 依赖
	@Autowired
	private ForumMapper forumMapper;

	// 获取论坛列表，支持分页
	@Override
	public List<Forum> getForumList(Forum forum, Integer page, Integer limit) {
		return forumMapper.getForumList(forum, page, limit);
	}

	// 获取符合条件的论坛总数
	@Override
	public Integer getForumListCount(Forum forum) {
		return forumMapper.getForumListCount(forum);
	}

	// 添加新的论坛
	@Override
	public void addForum(Forum forum) {
		forumMapper.addForum(forum);
	}

	// 更新已有的论坛信息
	@Override
	public void updateForum(Forum forum) {
		forumMapper.updateForum(forum);
	}

	// 根据论坛ID获取特定的论坛信息
	@Override
	public Forum getForumById(Integer id) {
		return forumMapper.getForumById(id);
	}
}
