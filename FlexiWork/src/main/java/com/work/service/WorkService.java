//WorkService.java 文件定义了与工作管理相关的基本服务接口，提供了对工作数据的操作方法。
// 通过这个接口，其他组件（如服务实现类和控制器）可以方便地进行工作信息的增、删、查、改操作。
package com.work.service;

import com.work.pojo.Work; // 引入 Work POJO 类，表示工作信息

import java.util.List;

// 定义工作服务接口
public interface WorkService {

	// 获取工作列表，支持分页
	public List<Work> getWorkList(Work work, Integer page, Integer limit);

	// 获取符合条件的工作总数
	public Integer getWorkListCount(Work work);

	// 添加新的工作信息
	public void addWork(Work work);

	// 更新已有的工作信息
	public void updateWork(Work work);

	// 根据工作ID获取特定工作信息
	public Work getWorkById(Integer id);

}
