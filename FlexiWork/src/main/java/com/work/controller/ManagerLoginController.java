package com.work.controller;

import com.work.common.utils.BaseUtil;
import com.work.common.utils.JsonUtil;
import com.work.common.utils.MD5Util;
import com.work.pojo.SystemUpset;
import com.work.pojo.User;
import com.work.service.SystemUpsetService;
import com.work.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

// 控制器注解，标识这是一个Spring MVC控制器
@Controller
@RequestMapping("/manager/login") // 设置请求路径前缀
public class ManagerLoginController extends BaseUtil {

	@Resource
	private UserService userService; // 用户服务
	@Resource
	private SystemUpsetService systemUpsetService; // 系统设置服务

	/**
	 * 管理后台登陆接口
	 *
	 * @param request
	 * @param response
	 * @param user
	 */
	@RequestMapping("/dologin")
	public void doLogin(HttpServletRequest request, HttpServletResponse response, User user) throws Exception {
		User serverUser = (User) request.getSession().getAttribute("serverUser"); // 从会话中获取当前登录的用户
		if (serverUser == null) { // 如果用户未登录
			// 检查用户ID是否为空
			if (user.getUserId() == null) {
				output(response, JsonUtil.buildFalseJson(1, "账号不能为空")); // 返回错误信息
			} else {
				// 检查密码是否为空
				if (user.getPassword() == null || user.getPassword().isEmpty()) {
					output(response, JsonUtil.buildFalseJson(1, "密码不能为空"));
				} else {
					// 根据用户ID获取用户信息
					User userInfo = userService.getUserById(user.getUserId());
					// 用户不存在或不是管理员
					if (userInfo == null || userInfo.getUserLeven() != 1) {
						output(response, JsonUtil.buildFalseJson(1, "账号不正确或系统不存在该用户"));
					} else {
						// 验证密码是否正确
						if (!userInfo.getPassword().equals(MD5Util.MD5Encode(user.getPassword(), "utf-8"))) {
							output(response, JsonUtil.buildFalseJson(1, "您输入的密码不正确,请重试"));
						} else {
							// 判断账号是否被禁用
							if (userInfo.getIsEffect() != 1) {
								output(response, JsonUtil.buildFalseJson(1, "对不起您的账号已被禁用"));
							} else {
								// 执行登录成功的逻辑
								request.getSession().setAttribute("serverUser", userInfo); // 将用户信息存入会话
								output(response, JsonUtil.buildFalseJson(0, "登陆成功,欢迎登录兼职平台管理系统"));
							}
						}
					}
				}
			}
		} else { // 若用户已登录
			output(response, JsonUtil.buildFalseJson(0, "登陆成功,欢迎登录兼职平台管理系统"));
		}
	}

	// 跳转到首页
	@RequestMapping("/goIndex")
	public ModelAndView goHome(ModelAndView mv, HttpServletRequest request, SystemUpset systemUpset) {
		User serverUser = (User) request.getSession().getAttribute("serverUser");
		if (serverUser == null) { // 如果用户已退出
			mv.setViewName("server/login"); // 跳转到登录页面
		} else {
			// 获取系统设置
			List<SystemUpset> systems = systemUpsetService.getSystemUpsetList(systemUpset, 0, 10);
			if (systems.size() > 0) {
				mv.addObject("system", systems.get(0)); // 添加系统设置到模型中
			} else {
				mv.addObject("system", null);
			}
			mv.setViewName("server/index"); // 跳转到首页
		}
		mv.addObject("serverUser", serverUser); // 将当前用户添加到模型中
		return mv;
	}

	// 跳转到登录页面
	@RequestMapping("/goServerLogin")
	public ModelAndView goHome(ModelAndView mv) {
		mv.setViewName("server/login"); // 设置视图名称
		return mv; // 返回ModelAndView对象
	}

	// 跳转到主页面
	@RequestMapping("/goMain")
	public ModelAndView goMain(ModelAndView mv, HttpServletRequest request) {
		mv.setViewName("server/main"); // 设置视图名称
		return mv; // 返回ModelAndView对象
	}

	// 管理员退出登录
	@RequestMapping("/dologout")
	public void managerLogOut(ModelAndView modelView, HttpServletRequest request, HttpServletResponse response) {
		request.getSession().invalidate(); // 使当前会话失效
		output(response, JsonUtil.buildFalseJson(0, "退出成功")); // 返回退出成功的JSON响应
	}

	// 跳转到修改密码页面
	@RequestMapping("/goUpdatePassword")
	public ModelAndView goUpdatePassword(HttpServletRequest request, ModelAndView mv) {
		mv.setViewName("server/updatePwd"); // 设置视图名称
		return mv; // 返回ModelAndView对象
	}

	// 修改密码
	@RequestMapping("/updatePassword")
	public void updatePassword(HttpServletRequest request, HttpServletResponse response, String rePassword, String password) {
		User serverUser = (User) request.getSession().getAttribute("serverUser"); // 从会话获取当前用户
		User user = new User();
		// 检查两次输入的密码是否一致
		if (password.equals(rePassword)) {
			user.setUserId(serverUser.getUserId()); // 设置用户ID
			user.setPassword(MD5Util.MD5Encode(password, "utf-8")); // 对新密码进行加密
			userService.updateUser(user); // 调用服务层更新用户信息
			request.getSession().invalidate(); // 使当前会话失效
			output(response, JsonUtil.buildFalseJson(0, "修改密码成功")); // 返回修改成功的JSON响应
		} else {
			output(response, JsonUtil.buildFalseJson(1, "前后密码不一致")); // 返回错误信息
		}
	}
}
