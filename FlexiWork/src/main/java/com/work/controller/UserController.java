package com.work.controller;

import com.work.common.utils.BaseUtil;
import com.work.common.utils.JsonUtil;
import com.work.common.utils.MD5Util;
import com.work.pojo.User;
import com.work.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

// 控制器注解，标识这是一个Spring MVC控制器
@Controller
@RequestMapping("/server/user") // 设置请求路径的前缀为 "/server/user"
public class UserController extends BaseUtil {

	@Autowired
	private UserService userService; // 注入用户服务，处理用户相关业务逻辑

	// 跳转到用户列表页面
	@RequestMapping("/goUserList")
	public ModelAndView goUserList(ModelAndView mv) {
		mv.setViewName("server/userManage/userList"); // 设置视图名称，指向用户列表页面
		return mv; // 返回ModelAndView对象
	}

	// 获取用户列表
	@RequestMapping("/getUserList")
	public void getUserList(HttpServletResponse response, User user, Integer page, Integer limit) {
		// 设置默认分页参数
		if (page == null) {
			page = 1; // 默认第一页
		}
		if (limit == null) {
			limit = 10; // 默认每页10条记录
		}

		// 获取用户总数
		Integer totalCount = userService.getUserListCount(user);
		// 获取当前页的用户列表
		List<User> list = userService.getUserList(user, (page - 1) * limit, limit);

		// 返回JSON格式的响应，包括用户列表和总数
		output(response, JsonUtil.buildJsonByTotalCount(list, totalCount));
	}

	// 跳转到添加用户页面
	@RequestMapping("/goAddUser")
	public ModelAndView goAddUser(ModelAndView mv) {
		mv.setViewName("server/userManage/addUser"); // 设置视图名称，指向添加用户页面
		return mv; // 返回ModelAndView对象
	}

	// 添加用户
	@RequestMapping("/addUser")
	public void addUser(HttpServletResponse response, User user) {
		user.setCreateTime(new Date()); // 设置用户创建时间为当前时间
		user.setPassword(MD5Util.MD5Encode(user.getPassword(), "utf-8")); // 对用户密码进行MD5加密
		userService.addUser(user); // 调用服务层添加用户
		output(response, JsonUtil.buildFalseJson(0, "添加成功！")); // 返回添加成功的JSON响应
	}

	// 跳转到编辑用户页面
	@RequestMapping("/goUpdateUser")
	public ModelAndView goUpdateUser(ModelAndView mv, Integer userId) {
		User user = userService.getUserById(userId); // 根据用户ID获取用户信息
		mv.addObject("user", user); // 将用户对象添加到模型中，以便在视图中使用
		mv.setViewName("server/userManage/updateUser"); // 设置视图名称，指向编辑用户页面
		return mv; // 返回ModelAndView对象
	}

	// 编辑用户
	@RequestMapping("/updateUser")
	public void updateUser(HttpServletResponse response, User user) {
		userService.updateUser(user); // 调用服务层更新用户信息
		output(response, JsonUtil.buildFalseJson(0, "编辑成功！")); // 返回编辑成功的JSON响应
	}

	// 禁用用户
	@RequestMapping("/deleteUser")
	public void deleteUser(HttpServletResponse response, User user) {
		userService.updateUser(user); // 调用服务层禁用用户
		output(response, JsonUtil.buildFalseJson(0, "禁用成功！")); // 返回禁用成功的JSON响应
	}
}
