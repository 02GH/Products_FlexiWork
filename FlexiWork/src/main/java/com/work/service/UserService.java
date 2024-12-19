//UserService.java 文件定义了与用户管理相关的基本服务接口，提供了对用户数据的操作方法。
// 通过这个接口，其他组件（如服务实现类和控制器）可以方便地进行用户信息的增、删、查、改操作
package com.work.service;

import com.work.pojo.User; // 引入 User POJO 类，表示用户信息

import java.util.List;

// 定义用户服务接口
public interface UserService {

	// 获取用户列表，支持分页
	public List<User> getUserList(User user, Integer page, Integer limit);

	// 获取符合条件的用户总数
	public Integer getUserListCount(User user);

	// 添加新的用户信息
	public void addUser(User user);

	// 根据用户ID获取特定用户信息
	public User getUserById(Integer userId);

	// 更新已有的用户信息
	public void updateUser(User user);

	// 根据条件获取用户信息
	public User getUser(User user);

	// 根据用户昵称获取用户信息
	public User getUserByNickName(User user);

}
