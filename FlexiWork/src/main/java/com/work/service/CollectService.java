//CollectService.java 文件定义了与用户收藏管理相关的基本服务接口，提供了对收藏数据的操作方法。
// 通过这个接口，其他组件（如服务实现类和控制器）可以方便地进行收藏信息的增、删、查、改操作。
package com.work.service;

import com.work.pojo.Collect; // 引入 Collect POJO 类，表示收藏信息

import java.util.List;

// 定义收藏服务接口
public interface CollectService {

	// 获取收藏列表，支持分页
	public List<Collect> getCollectList(Collect collect, Integer page, Integer limit);

	// 获取符合条件的收藏总数
	public Integer getCollectListCount(Collect collect);

	// 添加新的收藏信息
	public void addCollect(Collect collect);

	// 根据收藏ID获取特定收藏信息
	public Collect getCollectById(Integer id);

	// 根据收藏ID删除收藏信息
	public void deleteCollectById(int id);

	// 删除用户的特定收藏信息
	public void deleteMyCollect(Collect collect);
}
