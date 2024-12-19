//AdverService.java 文件定义了广告管理的基本服务接口，提供了对广告数据的操作方法。通过这个接口，其他组件（如服务实现类和控制器）能够方便地进行广告信息的增、删、查、改操作。
package com.work.service;

import com.work.pojo.Adver; // 引入 Adver POJO 类，表示广告信息

import java.util.List;

// 定义广告服务接口
public interface AdverService {

	// 获取广告列表，支持分页
	public List<Adver> getAdverList(Adver adver, Integer page, Integer limit);

	// 获取符合条件的广告总数
	public Integer getAdverListCount(Adver adver);

	// 添加新的广告信息
	public void addAdver(Adver adver);

	// 更新已有的广告信息
	public void updateAdver(Adver adver);

	// 根据广告ID获取特定广告信息
	public Adver getAdverById(Integer id);

	// 根据广告ID删除广告信息
	public void deleteAdverById(int id);
}
