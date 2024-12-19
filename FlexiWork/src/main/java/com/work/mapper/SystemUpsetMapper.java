//SystemUpsetMapper.java 文件提供了与系统异常信息相关的基本 CRUD（创建、读取、更新和删除）操作接口，使用 MyBatis 进行数据持久化。
// 通过这个接口，其他服务或控制器可以方便地调用这些方法来操作系统异常数据，从而简化了数据库操作的实现。
package com.work.mapper;

import com.work.pojo.SystemUpset; // 引入 SystemUpset POJO 类
import org.apache.ibatis.annotations.Param; // 引入 MyBatis 的 Param 注解

import java.util.List;

// SystemUpsetMapper 接口，用于定义与系统异常相关的数据库操作
public interface SystemUpsetMapper {

	// 获取系统异常信息列表，支持分页
	public List<SystemUpset> getSystemUpsetList(@Param("systemUpset") SystemUpset systemUpset, @Param("page") Integer page, @Param("limit") Integer limit);

	// 获取符合条件的系统异常信息总数
	public Integer getSystemUpsetListCount(@Param("systemUpset") SystemUpset systemUpset);

	// 添加新的系统异常信息
	public void addSystemUpset(@Param("systemUpset") SystemUpset systemUpset);

	// 更新已有的系统异常信息
	public void updateSystemUpset(@Param("systemUpset") SystemUpset systemUpset);

	// 根据系统异常ID获取特定的系统异常信息
	public SystemUpset getSystemUpsetById(@Param("id") Integer id);

	// 根据系统异常ID删除系统异常信息
	public void deleteSystemUpsetById(@Param("id") int id);
}
