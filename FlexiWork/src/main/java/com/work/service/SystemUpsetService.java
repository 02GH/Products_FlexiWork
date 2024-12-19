//SystemUpsetService.java 文件定义了与系统故障管理相关的基本服务接口，提供了对系统故障数据的操作方法。
// 通过这个接口，其他组件（如服务实现类和控制器）可以方便地进行系统故障信息的增、删、查、改操作。
package com.work.service;

import com.work.pojo.SystemUpset; // 引入 SystemUpset POJO 类，表示系统故障信息

import java.util.List;

// 定义系统故障服务接口
public interface SystemUpsetService {

	// 获取系统故障列表，支持分页
	public List<SystemUpset> getSystemUpsetList(SystemUpset systemUpset, Integer page, Integer limit);

	// 获取符合条件的系统故障总数
	public Integer getSystemUpsetListCount(SystemUpset systemUpset);

	// 添加新的系统故障信息
	public void addSystemUpset(SystemUpset systemUpset);

	// 更新已有的系统故障信息
	public void updateSystemUpset(SystemUpset systemUpset);
}
