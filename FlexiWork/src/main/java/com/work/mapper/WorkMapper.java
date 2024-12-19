//WorkMapper.java 文件提供了与工作信息相关的基本 CRUD（创建、读取、更新和删除）操作接口，使用 MyBatis 进行数据持久化。
// 通过这个接口，其他服务或控制器可以方便地调用这些方法来操作工作数据，从而简化了数据库操作的实现。
package com.work.mapper;

import com.work.pojo.Work; // 引入 Work POJO 类
import org.apache.ibatis.annotations.Param; // 引入 MyBatis 的 Param 注解

import java.util.List;

// WorkMapper 接口，用于定义与工作相关的数据库操作
public interface WorkMapper {

	// 获取工作信息列表，支持分页
	public List<Work> getWorkList(@Param("work") Work work, @Param("page") Integer page, @Param("limit") Integer limit);

	// 获取符合条件的工作信息总数
	public Integer getWorkListCount(@Param("work") Work work);

	// 添加新的工作信息
	public void addWork(@Param("work") Work work);

	// 更新已有的工作信息
	public void updateWork(@Param("work") Work work);

	// 根据工作ID获取特定的工作信息
	public Work getWorkById(@Param("id") Integer id);
}
