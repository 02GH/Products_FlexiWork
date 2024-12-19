//UserServiceImpl.java 文件实现了用户管理的基本业务逻辑，提供了对用户数据的操作接口。通过该类，其他组件（如控制器）可以方便地进行用户信息的增、删、查、改操作。
package com.work.service.impl;

import com.work.mapper.UserMapper; // 引入 UserMapper 接口，用于数据库操作
import com.work.pojo.User; // 引入 User POJO 类，表示用户信息
import com.work.service.UserService; // 引入 UserService 接口，用于定义服务层操作
import org.springframework.beans.factory.annotation.Autowired; // 引入 Spring 的 Autowired 注解
import org.springframework.stereotype.Service; // 引入 Spring 的 Service 注解

import java.util.List;

// 标识该类为服务层组件
@Service
public class UserServiceImpl implements UserService {

	// 自动注入 UserMapper 依赖
	@Autowired
	private UserMapper userMapper;

	// 获取用户列表，支持分页
	@Override
	public List<User> getUserList(User user, Integer page, Integer limit) {
		// 调用数据访问层的方法获取用户列表
		return userMapper.getUserList(user, page, limit);
	}

	// 获取符合条件的用户总数
	@Override
	public Integer getUserListCount(User user) {
		// 调用数据访问层的方法获取用户总数
		return userMapper.getUserListCount(user);
	}

	// 添加新的用户信息
	@Override
	public void addUser(User user) {
		// 调用数据访问层的方法将用户信息添加到数据库
		userMapper.addUser(user);
	}

	// 根据用户ID获取特定用户信息
	@Override
	public User getUserById(Integer userId) {
		// 调用数据访问层的方法根据ID获取用户信息
		User user = userMapper.getUserById(userId);
		return user; // 返回获取到的用户信息
	}

	// 更新已有的用户信息
	@Override
	public void updateUser(User user) {
		// 调用数据访问层的方法更新用户信息
		userMapper.updateUser(user);
	}

	// 根据用户对象获取用户信息
	@Override
	public User getUser(User user) {
		// 调用数据访问层的方法根据用户对象获取用户信息
		return userMapper.getUser(user);
	}

	// 根据用户昵称获取用户信息
	@Override
	public User getUserByNickName(User user) {
		// 调用数据访问层的方法根据昵称获取用户信息
		return userMapper.getUserByNickName(user);
	}
}
