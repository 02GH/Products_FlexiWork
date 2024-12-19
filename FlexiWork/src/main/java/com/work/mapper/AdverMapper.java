//AdverMapper.java 文件提供了与广告相关的基本 CRUD（创建、读取、更新和删除）操作接口，使用 MyBatis 进行数据持久化。
// 通过这个接口，其他服务或控制器可以方便地调用这些方法来操作广告数据，简化了数据库操作的复杂性。
// 使用 @Param 注解可以清晰地指定方法参数，增强了代码的可读性。
package com.work.mapper;

import com.work.pojo.Adver; // 引入 Adver POJO 类
import org.apache.ibatis.annotations.Param; // 引入 MyBatis 的 Param 注解

import java.util.List;

// AdverMapper 接口，用于定义与广告相关的数据库操作
public interface AdverMapper {

	// 获取广告列表，支持分页
	public List<Adver> getAdverList(@Param("adver") Adver adver, @Param("page") Integer page, @Param("limit") Integer limit);

	// 获取广告总数
	public Integer getAdverListCount(@Param("adver") Adver adver);

	// 添加广告
	public void addAdver(@Param("adver") Adver adver);

	// 更新广告
	public void updateAdver(@Param("adver") Adver adver);

	// 根据ID获取广告
	public Adver getAdverById(@Param("id") Integer id);

	// 根据ID删除广告
	public void deleteAdverById(@Param("id") int id);
}
