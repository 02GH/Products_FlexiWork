//CollectServiceImpl.java 文件实现了收藏记录管理的业务逻辑，提供了对收藏记录数据的基本操作接口。通过该类，其他组件（如控制器）可以方便地进行收藏记录的增、删、查操作。
package com.work.service.impl;

import com.work.mapper.CollectMapper; // 引入 CollectMapper 接口
import com.work.pojo.Collect; // 引入 Collect POJO 类
import com.work.service.CollectService; // 引入 CollectService 接口
import org.springframework.beans.factory.annotation.Autowired; // 引入 Spring 的 Autowired 注解
import org.springframework.stereotype.Service; // 引入 Spring 的 Service 注解

import java.util.List;

// 标识该类为服务层组件
@Service
public class CollectServiceImpl implements CollectService {

	// 自动注入 CollectMapper 依赖
	@Autowired
	private CollectMapper collectMapper;

	// 获取收藏记录列表，支持分页
	@Override
	public List<Collect> getCollectList(Collect collect, Integer page, Integer limit) {
		return collectMapper.getCollectList(collect, page, limit);
	}

	// 获取符合条件的收藏记录总数
	@Override
	public Integer getCollectListCount(Collect collect) {
		return collectMapper.getCollectListCount(collect);
	}

	// 添加新的收藏记录
	@Override
	public void addCollect(Collect collect) {
		collectMapper.addCollect(collect);
	}

	// 根据收藏记录ID获取特定的收藏记录信息
	@Override
	public Collect getCollectById(Integer id) {
		return collectMapper.getCollectById(id);
	}

	// 根据收藏记录ID删除特定收藏记录
	@Override
	public void deleteCollectById(int id) {
		collectMapper.deleteCollectById(id);
	}

	// 删除当前用户的收藏记录
	@Override
	public void deleteMyCollect(Collect collect) {
		collectMapper.deleteMyCollect(collect);
	}
}
