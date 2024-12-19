//SystemUpsetServiceImpl.java 文件实现了系统异常管理的基本业务逻辑，提供了对系统异常数据的操作接口。
// 通过该类，其他组件（如控制器）可以方便地进行系统异常信息的增、删、查、改操作。
package com.work.service.impl;

import com.work.mapper.SystemUpsetMapper; // 引入 SystemUpsetMapper 接口，用于数据库操作
import com.work.pojo.SystemUpset; // 引入 SystemUpset POJO 类，表示系统异常信息
import com.work.service.SystemUpsetService; // 引入 SystemUpsetService 接口，用于定义服务层操作
import org.springframework.beans.factory.annotation.Autowired; // 引入 Spring 的 Autowired 注解
import org.springframework.stereotype.Service; // 引入 Spring 的 Service 注解

import java.util.List;

// 标识该类为服务层组件
@Service
public class SystemUpsetServiceImpl implements SystemUpsetService {

	// 自动注入 SystemUpsetMapper 依赖
	@Autowired
	private SystemUpsetMapper systemUpsetMapper;

	// 获取系统异常列表，支持分页
	@Override
	public List<SystemUpset> getSystemUpsetList(SystemUpset systemUpset, Integer page, Integer limit) {
		// 调用数据访问层的方法获取系统异常列表
		return systemUpsetMapper.getSystemUpsetList(systemUpset, page, limit);
	}

	// 获取符合条件的系统异常总数
	@Override
	public Integer getSystemUpsetListCount(SystemUpset systemUpset) {
		// 调用数据访问层的方法获取系统异常总数
		return systemUpsetMapper.getSystemUpsetListCount(systemUpset);
	}

	// 添加新的系统异常信息
	@Override
	public void addSystemUpset(SystemUpset systemUpset) {
		// 调用数据访问层的方法将系统异常信息添加到数据库
		systemUpsetMapper.addSystemUpset(systemUpset);
	}

	// 更新已有的系统异常信息
	@Override
	public void updateSystemUpset(SystemUpset systemUpset) {
		// 调用数据访问层的方法更新系统异常信息
		systemUpsetMapper.updateSystemUpset(systemUpset);
	}
}
