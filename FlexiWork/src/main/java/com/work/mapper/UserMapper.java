//UserMapper.java 文件提供了与用户信息相关的基本 CRUD（创建、读取、更新和删除）操作接口，使用 MyBatis 进行数据持久化。
// 通过这个接口，其他服务或控制器可以方便地调用这些方法来操作用户数据，从而简化了数据库操作的实现。
package com.work.mapper;

import com.work.pojo.User; // 引入 User POJO 类
import org.apache.ibatis.annotations.Param; // 引入 MyBatis 的 Param 注解

import java.util.List;

// UserMapper 接口，用于定义与用户相关的数据库操作
public interface UserMapper {

	// 获取用户信息列表，支持分页
	public List<User> getUserList(@Param("user") User user, @Param("page") Integer page, @Param("limit") Integer limit);

	// 获取符合条件的用户信息总数
	public Integer getUserListCount(@Param("user") User user);

	// 添加新的用户信息
	public void addUser(@Param("user") User user);

	// 更新已有的用户信息
	public void updateUser(@Param("user") User user);

	// 根据用户ID获取特定的用户信息
	public User getUserById(@Param("userId") Integer userId);

	// 根据用户对象获取用户信息，可能用于根据多个条件查询
	public User getUser(@Param("user") User user);

	// 根据昵称获取特定用户信息
	public User getUserByNickName(@Param("user") User user);
}
