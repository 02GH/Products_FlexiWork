//BannerMapper.java 文件提供了与横幅广告相关的基本 CRUD（创建、读取、更新和删除）操作接口，使用 MyBatis 进行数据持久化。
// 通过这个接口，其他服务或控制器可以方便地调用这些方法来操作横幅广告数据，从而简化了数据库操作的实现。
// 使用 @Param 注解可以清晰地指定方法参数，增强了代码的可读性。该接口的设计便于在应用中进行灵活的数据管理和查询。
package com.work.mapper;

import com.work.pojo.Banner; // 引入 Banner POJO 类
import org.apache.ibatis.annotations.Param; // 引入 MyBatis 的 Param 注解

import java.util.List;

// BannerMapper 接口，用于定义与横幅广告相关的数据库操作
public interface BannerMapper {

	// 获取横幅广告列表，支持分页
	public List<Banner> getBannerList(@Param("banner") Banner banner, @Param("page") Integer page, @Param("limit") Integer limit);

	// 获取横幅广告总数
	public Integer getBannerListCount(@Param("banner") Banner banner);

	// 添加横幅广告
	public void addBanner(@Param("banner") Banner banner);

	// 更新横幅广告
	public void updateBanner(@Param("banner") Banner banner);

	// 根据ID获取横幅广告
	public Banner getBannerById(@Param("id") Integer id);

}
