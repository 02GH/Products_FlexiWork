//CollectMapper.java 文件提供了与收藏记录相关的基本 CRUD（创建、读取、更新和删除）操作接口，使用 MyBatis 进行数据持久化。
// 通过这个接口，其他服务或控制器可以方便地调用这些方法来操作用户的收藏数据，从而简化了数据库操作的实现。使用 @Param 注解可以清晰地指定方法参数，增强了代码的可读性。
// 该接口的设计便于在应用中进行灵活的数据管理和查询，支持收藏记录的分页和统计功能。
package com.work.mapper;

import com.work.pojo.Collect; // 引入 Collect POJO 类
import org.apache.ibatis.annotations.Param; // 引入 MyBatis 的 Param 注解

import java.util.List;

// CollectMapper 接口，用于定义与收藏记录相关的数据库操作
public interface CollectMapper {

	// 获取收藏记录列表，支持分页
	public List<Collect> getCollectList(@Param("collect") Collect collect, @Param("page") Integer page, @Param("limit") Integer limit);

	// 获取收藏记录总数
	public Integer getCollectListCount(@Param("collect") Collect collect);

	// 添加收藏记录
	public void addCollect(@Param("collect") Collect collect);

	// 根据ID获取收藏记录
	public Collect getCollectById(@Param("id") Integer id);

	// 根据ID删除收藏记录
	public void deleteCollectById(@Param("id") int id);

	// 删除特定的收藏记录（可以根据多个条件删除）
	public void deleteMyCollect(@Param("collect") Collect collect);
}
