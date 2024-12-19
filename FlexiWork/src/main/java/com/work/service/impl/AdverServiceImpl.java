//AdverServiceImpl.java 文件实现了广告信息管理的业务逻辑，提供了对广告数据的基本操作接口。通过该类，其他组件（如控制器）可以方便地进行广告的增、删、查、改操作。
package com.work.service.impl;

import com.work.mapper.AdverMapper; // 引入 AdverMapper 接口
import com.work.pojo.Adver; // 引入 Adver POJO 类
import com.work.service.AdverService; // 引入 AdverService 接口
import org.springframework.beans.factory.annotation.Autowired; // 引入 Spring 的 Autowired 注解
import org.springframework.stereotype.Service; // 引入 Spring 的 Service 注解

import java.util.List;

// 标识该类为服务层组件
@Service
public class AdverServiceImpl implements AdverService {

	// 自动注入 AdverMapper 依赖
	@Autowired
	private AdverMapper adverMapper;

	// 获取广告列表，支持分页
	@Override
	public List<Adver> getAdverList(Adver adver, Integer page, Integer limit) {
		return adverMapper.getAdverList(adver, page, limit);
	}

	// 获取符合条件的广告总数
	@Override
	public Integer getAdverListCount(Adver adver) {
		return adverMapper.getAdverListCount(adver);
	}

	// 添加新的广告信息
	@Override
	public void addAdver(Adver adver) {
		adverMapper.addAdver(adver);
	}

	// 更新已有的广告信息
	@Override
	public void updateAdver(Adver adver) {
		adverMapper.updateAdver(adver);
	}

	// 根据广告ID获取特定的广告信息
	@Override
	public Adver getAdverById(Integer id) {
		return adverMapper.getAdverById(id);
	}

	// 根据ID删除特定广告
	@Override
	public void deleteAdverById(int id) {
		adverMapper.deleteAdverById(id);
	}
}
