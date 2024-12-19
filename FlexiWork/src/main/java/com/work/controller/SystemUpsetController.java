package com.work.controller;

import com.work.common.utils.BaseUtil;
import com.work.common.utils.JsonUtil;
import com.work.pojo.SystemUpset;
import com.work.pojo.User;
import com.work.service.SystemUpsetService;
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
@RequestMapping("/server/system") // 设置请求路径前缀
public class SystemUpsetController extends BaseUtil {

	@Autowired
	private SystemUpsetService systemUpsetService; // 注入系统设置服务

	// 跳转到系统设置页面
	@RequestMapping("/goAddSystemUpset")
	public ModelAndView goAddSystemUpset(ModelAndView mv, SystemUpset systemUpset) {
		// 获取当前系统设置，通常只需展示一条记录
		List<SystemUpset> systems = systemUpsetService.getSystemUpsetList(systemUpset, 0, 10);
		if (systems.size() > 0) {
			mv.addObject("system", systems.get(0)); // 将系统设置添加到模型中
		} else {
			mv.addObject("system", null); // 如果没有设置，添加null
		}
		mv.setViewName("server/systemUpsetManage/updateSystemUpset"); // 设置视图名称为更新系统设置页面
		return mv; // 返回ModelAndView对象
	}

	// 设置信息
	@RequestMapping("/addSystemUpset")
	public void addSystemUpset(HttpServletRequest request, HttpServletResponse response, SystemUpset systemUpset) {
		User serverUser = (User) request.getSession().getAttribute("serverUser"); // 从会话中获取当前用户
		systemUpset.setCreateTime(new Date()); // 设置系统设置创建时间
		systemUpset.setUserId(serverUser.getUserId()); // 设置创建者ID

		// 判断是添加还是更新操作
		if (systemUpset.getId() != null) {
			systemUpsetService.updateSystemUpset(systemUpset); // 更新已有的系统设置
		} else {
			systemUpsetService.addSystemUpset(systemUpset); // 添加新的系统设置
		}

		// 返回设置成功的JSON响应
		output(response, JsonUtil.buildFalseJson(0, "设置成功！"));
	}
}
