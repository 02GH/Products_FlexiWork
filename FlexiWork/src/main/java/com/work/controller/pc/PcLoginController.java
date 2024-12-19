package com.work.controller.pc;

import com.work.common.utils.BaseUtil;
import com.work.common.utils.JsonUtil;
import com.work.common.utils.MD5Util;
import com.work.pojo.SystemUpset;
import com.work.pojo.User;
import com.work.service.SystemUpsetService;
import com.work.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

// 控制器注解，标识这是一个Spring MVC控制器
@Controller
@RequestMapping("/pc/login") // 设置请求路径的前缀为 "/pc/login"
public class PcLoginController extends BaseUtil {

	@Autowired
	private UserService userService; // 用户服务
	@Autowired
	private SystemUpsetService systemUpsetService; // 系统配置服务

	// 跳转到登录页面
	@RequestMapping(value="/goLogin")
	public ModelAndView goLogin(HttpServletResponse response, HttpServletRequest request, ModelAndView mv, SystemUpset systemUpset) {
		// 获取系统配置
		List<SystemUpset> systemUpsets = systemUpsetService.getSystemUpsetList(systemUpset, null, null);
		if (systemUpsets.size() > 0) {
			mv.addObject("systemUpset", systemUpsets.get(0)); // 将系统配置添加到模型中
		}
		User pcUser = (User) request.getSession().getAttribute("pcUser"); // 获取当前Session中的用户信息
		// 如果用户未登录，跳转到登录页面；如果已登录，跳转到首页
		if (pcUser == null) {
			mv.setViewName("pc/login");
		} else {
			mv.addObject("updatePage", "y");
			mv.setViewName("pc/index");
		}
		return mv; // 返回视图
	}

	// 执行登录
	@RequestMapping(value="/doLogin")
	public void doLogin(HttpServletRequest request, HttpServletResponse response, User user) {
		User pcUser = (User) request.getSession().getAttribute("pcUser"); // 获取当前Session中的用户信息
		if (pcUser == null) { // 如果用户未登录
			// 校验账号和密码
			if (user.getUserId() == null) {
				output(response, JsonUtil.buildFalseJson(1, "账号不能为空")); // 返回错误信息
			}
			if (user.getPassword() == null || user.getPassword().equals("")) {
				output(response, JsonUtil.buildFalseJson(1, "密码不能为空")); // 返回错误信息
			}
			// 根据用户ID获取用户信息
			User userInfo = userService.getUserById(user.getUserId());
			if (userInfo == null) {
				output(response, JsonUtil.buildFalseJson(1, "账号不正确或系统不存在该用户")); // 返回错误信息
			}
			// 校验密码
			if (!userInfo.getPassword().equals(MD5Util.MD5Encode(user.getPassword(), "utf-8"))) {
				output(response, JsonUtil.buildFalseJson(1, "您输入的密码不正确,请重试")); // 返回错误信息
			}
			// 判断账号是否被禁用
			if (userInfo.getIsEffect() != 1) {
				output(response, JsonUtil.buildFalseJson(1, "对不起您的账号已被禁用")); // 返回错误信息
			} else {
				// 执行登录逻辑，设置Session
				request.getSession().setAttribute("pcUser", userInfo);
				output(response, JsonUtil.buildFalseJson(0, "登录成功")); // 返回成功信息
			}
		} else {
			output(response, JsonUtil.buildFalseJson(0, "您已登录，无需重复登录")); // 如果已经登录
		}
	}

	// 退出登录
	@RequestMapping(value="/outLogin")
	public void outLogin(HttpServletRequest request, HttpServletResponse response, User user) {
		User pcUser = (User) request.getSession().getAttribute("pcUser"); // 获取当前Session中的用户信息
		if (pcUser != null) {
			request.getSession().invalidate(); // 销毁Session
			output(response, JsonUtil.buildFalseJson(0, "退出登录成功!")); // 返回成功信息
		} else {
			output(response, JsonUtil.buildFalseJson(1, "您还未登陆不能执行退出操作")); // 返回错误信息
		}
	}

	// 跳转到注册页面
	@RequestMapping(value="/goRegister")
	public ModelAndView goRegister(ModelAndView mv, HttpServletRequest request, SystemUpset systemUpset) {
		// 获取系统配置
		List<SystemUpset> systemUpsets = systemUpsetService.getSystemUpsetList(systemUpset, null, null);
		if (systemUpsets.size() > 0) {
			mv.addObject("systemUpset", systemUpsets.get(0)); // 将系统配置添加到模型中
		}
		User user = (User) request.getSession().getAttribute("pcUser"); // 获取当前Session中的用户信息
		// 如果用户已登录，跳转到首页；否则，跳转到注册页面
		if (user != null) {
			mv.setViewName("pc/index");
		} else {
			mv.setViewName("pc/register");
		}
		return mv; // 返回视图
	}

	// 检测昵称是否重复
	@RequestMapping(value="/findUserByNickName")
	public void findUserByNickName(HttpServletRequest request, HttpServletResponse response, User user) {
		User userInfo = userService.getUserByNickName(user); // 根据昵称获取用户信息
		User pcUser = (User) request.getSession().getAttribute("pcUser"); // 获取当前Session中的用户信息
		// 判断当前用户是否登录
		if (pcUser != null) {
			if (userInfo != null && !pcUser.getNickName().equals(user.getNickName())) {
				output(response, JsonUtil.buildFalseJson(0, "该昵称已被用户使用")); // 返回昵称被使用的提示
			} else {
				output(response, JsonUtil.buildFalseJson(1, "该昵称未被用户使用")); // 返回昵称未被使用的提示
			}
		} else {
			if (userInfo != null) {
				output(response, JsonUtil.buildFalseJson(0, "该昵称已被用户使用")); // 返回昵称被使用的提示
			} else {
				output(response, JsonUtil.buildFalseJson(1, "该昵称未被用户使用")); // 返回昵称未被使用的提示
			}
		}
	}

	// 执行注册
	@RequestMapping(value="/doRegister")
	public void doRegister(HttpServletRequest request, HttpServletResponse response, User user) {
		user.setIsEffect(1); // 设置用户有效
		user.setUserLeven(0); // 默认用户等级
		user.setIsWork(0); // 默认用户未工作
		user.setPassword(MD5Util.MD5Encode(user.getPassword(), "utf-8")); // 对密码进行加密
		user.setCreateTime(new Date()); // 设置创建时间
		userService.addUser(user); // 添加用户
		Integer userId = user.getUserId(); // 获取用户ID
		output(response, JsonUtil.buildFalseJson(0, String.valueOf(userId))); // 返回成功信息
	}

	// 判断账号有没有设置密保
	@RequestMapping(value="/isQuestion")
	public void isQuestion(HttpServletRequest request, HttpServletResponse response, User user) {
		User userInfo = userService.getUserById(user.getUserId()); // 根据用户ID获取用户信息
		if (userInfo == null || userInfo.getIsEffect() == 0) {
			output(response, JsonUtil.buildFalseJson(1, "该账号不存在！")); // 返回账号不存在的提示
		} else if (userInfo.getQuestion() == null || userInfo.getQuestion().equals("")) {
			output(response, JsonUtil.buildFalseJson(1, "该账号没有设置密保！")); // 返回未设置密保的提示
		} else {
			output(response, JsonUtil.buildFalseJson(0, "该账号已设置密保！")); // 返回已设置密保的提示
		}
	}

	// 跳转到找回密码页面
	@RequestMapping(value="/goForgetPassword")
	public ModelAndView goForgetPassword(ModelAndView mv, HttpServletRequest request, User user) {
		// 获取系统配置
		SystemUpset systemUpset = new SystemUpset();
		List<SystemUpset> systemUpsets = systemUpsetService.getSystemUpsetList(systemUpset, null, null);
		if (systemUpsets.size() > 0) {
			mv.addObject("systemUpset", systemUpsets.get(0)); // 将系统配置添加到模型中
		}
		User userInfo = userService.getUserById(user.getUserId()); // 获取用户信息
		mv.addObject("user", userInfo); // 将用户信息添加到模型中
		mv.setViewName("pc/forget_password"); // 设置视图名称为找回密码页面
		return mv; // 返回视图
	}

	// 判断答案是否正确
	@RequestMapping(value="/isAnswer")
	public void isAnswer(HttpServletRequest request, HttpServletResponse response, User user) {
		User userInfo = userService.getUserById(user.getUserId()); // 根据用户ID获取用户信息
		if (userInfo.getAnswer().equals(user.getAnswer())) { // 校验答案
			request.getSession().setAttribute("temUser", userInfo); // 将用户信息存入临时Session
			output(response, JsonUtil.buildFalseJson(0, "答案正确！")); // 返回正确提示
		} else {
			output(response, JsonUtil.buildFalseJson(1, "答案错误！")); // 返回错误提示
		}
	}

	// 跳转到设置新密码页面
	@RequestMapping(value="/goSetPassword")
	public ModelAndView goSetPassword(ModelAndView mv, HttpServletRequest request) {
		// 获取系统配置
		SystemUpset systemUpset = new SystemUpset();
		List<SystemUpset> systemUpsets = systemUpsetService.getSystemUpsetList(systemUpset, null, null);
		if (systemUpsets.size() > 0) {
			mv.addObject("systemUpset", systemUpsets.get(0)); // 将系统配置添加到模型中
		}
		mv.setViewName("pc/set_password"); // 设置视图名称为设置新密码页面
		return mv; // 返回视图
	}

	// 修改密码
	@RequestMapping("/updatePassword")
	public void updatePassword(HttpServletRequest request, HttpServletResponse response, String passwordYes, String password) {
		User temUser = (User) request.getSession().getAttribute("temUser"); // 获取临时Session中的用户信息
		User user = new User();
		if (passwordYes.equals(password)) { // 校验新密码与确认密码是否一致
			user.setUserId(temUser.getUserId()); // 设置用户ID
			user.setPassword(MD5Util.MD5Encode(password, "utf-8")); // 对新密码进行加密
			userService.updateUser(user); // 更新用户信息
			output(response, JsonUtil.buildFalseJson(0, "修改密码成功")); // 返回成功提示
		} else {
			output(response, JsonUtil.buildFalseJson(1, "前后密码不一致")); // 返回错误提示
		}
	}
}
