//BannerServiceImpl.java 文件实现了横幅广告管理的业务逻辑，提供了对横幅广告数据的基本操作接口。通过该类，其他组件（如控制器）可以方便地进行横幅广告的增、删、查、改操作。
package com.work.service.impl;

import com.work.mapper.BannerMapper; // 引入 BannerMapper 接口
import com.work.pojo.Banner; // 引入 Banner POJO 类
import com.work.service.BannerService; // 引入 BannerService 接口
import org.springframework.beans.factory.annotation.Autowired; // 引入 Spring 的 Autowired 注解
import org.springframework.stereotype.Service; // 引入 Spring 的 Service 注解

import java.util.List;

// 标识该类为服务层组件
@Service
public class BannerServiceImpl implements BannerService {

	// 自动注入 BannerMapper 依赖
	@Autowired
	private BannerMapper bannerMapper;

	// 获取横幅广告列表，支持分页
	@Override
	public List<Banner> getBannerList(Banner banner, Integer page, Integer limit) {
		return bannerMapper.getBannerList(banner, page, limit);
	}

	// 获取符合条件的横幅广告总数
	@Override
	public Integer getBannerListCount(Banner banner) {
		return bannerMapper.getBannerListCount(banner);
	}

	// 添加新的横幅广告
	@Override
	public void addBanner(Banner banner) {
		bannerMapper.addBanner(banner);
	}

	// 更新已有的横幅广告信息
	@Override
	public void updateBanner(Banner banner) {
		bannerMapper.updateBanner(banner);
	}

	// 根据横幅广告ID获取特定的横幅广告信息
	@Override
	public Banner getBannerById(Integer id) {
		return bannerMapper.getBannerById(id);
	}
}
