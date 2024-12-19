//JianzhiMapper.java 文件提供了与兼职信息相关的基本数据操作接口，使用 MyBatis 进行数据持久化。
// 通过这个接口，其他服务或控制器可以方便地添加和查询兼职信息，从而简化了数据库操作的实现。
package com.work.mapper;

import com.work.pojo.Jianzhi; // 引入 Jianzhi POJO 类
import org.apache.ibatis.annotations.Param; // 引入 MyBatis 的 Param 注解

import java.util.List;

// JianzhiMapper 接口，用于定义与兼职信息相关的数据库操作
public interface JianzhiMapper {

	// 添加新的兼职信息
	public void addjianzhi(@Param("jianzhi") Jianzhi jianzhi);

	// 获取兼职信息列表
	public List<Jianzhi> getList(@Param("jianzhi") Jianzhi jianzhi);
}
