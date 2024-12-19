package com.work.common.utils; // 定义该类所在的包

import com.work.pojo.SystemUpset; // 引入系统配置类
import com.work.pojo.User; // 引入用户类
import com.work.service.SystemUpsetService; // 引入系统配置服务类
import org.springframework.beans.factory.annotation.Autowired; // 引入自动装配注解
import org.springframework.web.servlet.HandlerInterceptor; // 引入HandlerInterceptor接口
import org.springframework.web.servlet.ModelAndView; // 引入模型视图类

import javax.servlet.http.HttpServletRequest; // 引入HTTP请求类
import javax.servlet.http.HttpServletResponse; // 引入HTTP响应类
import java.util.List; // 引入List接口

public class LoginInterceptor implements HandlerInterceptor {

	@Autowired
	private SystemUpsetService systemUpsetService; // 自动注入系统配置服务

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {
		// 请求处理完成后的操作，当前未实现
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {
		// 请求处理后的操作，当前未实现
	}

	/**
	 * 在请求处理之前进行拦截，用于验证用户登录状态
	 *
	 * @param request 请求对象
	 * @param response 响应对象
	 * @param handler 处理器对象
	 * @return boolean 返回 true 继续执行请求，返回 false 中止请求
	 * @throws Exception 异常
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// 系统配置
		SystemUpset systemUpset = new SystemUpset(); // 创建系统配置对象
		List<SystemUpset> systemUpsets = systemUpsetService.getSystemUpsetList(systemUpset, null, null); // 获取系统配置列表
		if(systemUpsets.size() > 0){
			request.setAttribute("systemUpset", systemUpsets.get(0)); // 将第一个配置存入请求属性
		}

		// 请求链接
		String url = request.getRequestURI(); // 获取请求的 URI
		User pcUser = (User) request.getSession().getAttribute("pcUser"); // 从 session 获取个人中心用户
		User serverUser = (User) request.getSession().getAttribute("serverUser"); // 从 session 获取后台用户

		// 对个人中心和动作请求进行拦截
		if(url.contains("pc/personal") || url.contains("pc/action")) {
			if(pcUser != null) { // 如果个人中心用户存在
				return true; // 继续请求处理
			} else {
				request.setAttribute("msg", "您还没有登录，请先登录！"); // 提示用户未登录
				request.getRequestDispatcher("/WEB-INF/jsp/pc/login.jsp").forward(request, response); // 跳转到登录页面
				return false; // 中止请求处理
			}
		}

		// 后台管理进行拦截
		if(url.contains("server")) {
			if(serverUser != null) { // 如果后台用户存在
				return true; // 继续请求处理
			} else {
				request.setAttribute("msg", "您还没有登录，请先登录！"); // 提示用户未登录
				request.getRequestDispatcher("/WEB-INF/jsp/server/login.jsp").forward(request, response); // 跳转到后台登录页面
				return false; // 中止请求处理
			}
		}

		return true; // 对于其他请求，继续处理
	}
}
