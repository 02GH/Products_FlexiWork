//WorkServiceImpl.java 文件实现了工作信息管理的基本业务逻辑，提供了对工作数据的操作接口。通过该类，其他组件（如控制器）可以方便地进行工作信息的增、删、查、改操作。
package com.work.service.impl;

import com.work.mapper.WorkMapper; // 引入 WorkMapper 接口，用于数据库操作
import com.work.pojo.Work; // 引入 Work POJO 类，表示工作信息
import com.work.service.WorkService; // 引入 WorkService 接口，用于定义服务层操作
import org.springframework.beans.factory.annotation.Autowired; // 引入 Spring 的 Autowired 注解
import org.springframework.stereotype.Service; // 引入 Spring 的 Service 注解

import java.util.List;

// 标识该类为服务层组件
@Service
public class WorkServiceImpl implements WorkService {

	// 自动注入 WorkMapper 依赖
	@Autowired
	private WorkMapper workMapper;

	// 获取工作列表，支持分页
	@Override
	public List<Work> getWorkList(Work work, Integer page, Integer limit) {
		// 调用数据访问层的方法获取工作列表
		return workMapper.getWorkList(work, page, limit);
	}

	// 获取符合条件的工作总数
	@Override
	public Integer getWorkListCount(Work work) {
		// 调用数据访问层的方法获取工作总数
		return workMapper.getWorkListCount(work);
	}

	// 添加新的工作信息
	@Override
	public void addWork(Work work) {
		// 调用数据访问层的方法将工作信息添加到数据库
		workMapper.addWork(work);
	}

	// 更新已有的工作信息
	@Override
	public void updateWork(Work work) {
		// 调用数据访问层的方法更新工作信息
		workMapper.updateWork(work);
	}

	// 根据工作ID获取特定工作信息
	@Override
	public Work getWorkById(Integer id) {
		// 调用数据访问层的方法根据ID获取工作信息
		return workMapper.getWorkById(id);
	}
}
