//BannerService.java 文件定义了与横幅广告管理相关的基本服务接口，提供了对横幅广告数据的操作方法。
// 通过这个接口，其他组件（如服务实现类和控制器）可以方便地进行横幅广告信息的增、删、查、改操作。
package com.work.service;

import com.work.pojo.Banner; // 引入 Banner POJO 类，表示横幅广告信息

import java.util.List;

// 定义横幅广告服务接口
public interface BannerService {

	// 获取横幅广告列表，支持分页
	public List<Banner> getBannerList(Banner banner, Integer page, Integer limit);

	// 获取符合条件的横幅广告总数
	public Integer getBannerListCount(Banner banner);

	// 添加新的横幅广告信息
	public void addBanner(Banner banner);

	// 更新已有的横幅广告信息
	public void updateBanner(Banner banner);

	// 根据横幅广告ID获取特定横幅广告信息
	public Banner getBannerById(Integer id);
}
