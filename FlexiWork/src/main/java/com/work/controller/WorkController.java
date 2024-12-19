package com.work.controller;

import com.work.common.utils.BaseUtil;
import com.work.common.utils.JsonUtil;
import com.work.pojo.Work;
import com.work.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

// 控制器注解，标识这是一个Spring MVC控制器
@Controller
@RequestMapping("/server/work") // 设置请求路径的前缀为 "/server/work"
public class WorkController extends BaseUtil {

	@Autowired
	private WorkService workService; // 注入兼职工作服务，用于处理兼职相关的业务逻辑

	// 跳转到兼职列表页面
	@RequestMapping("/goWorkList")
	public ModelAndView goWorkList(ModelAndView mv) {
		mv.setViewName("server/workManage/workList"); // 设置视图名称，指向兼职列表页面
		return mv; // 返回ModelAndView对象
	}

	// 获取兼职列表
	@RequestMapping("/getWorkList")
	public void getWorkList(HttpServletResponse response, Work work, Integer page, Integer limit) {
		// 设置默认分页参数
		if (page == null) {
			page = 1; // 默认第一页
		}
		if (limit == null) {
			limit = 10; // 默认每页10条记录
		}
		// 获取兼职工作总数
		int totalCount = workService.getWorkListCount(work);
		// 获取当前页的兼职工作列表
		List<Work> list = workService.getWorkList(work, (page - 1) * limit, limit);
		// 返回JSON格式的响应，包括兼职工作列表和总数
		output(response, JsonUtil.buildJsonByTotalCount(list, totalCount));
	}

	// 跳转到编辑兼职页面
	@RequestMapping("/goUpdateWork")
	public ModelAndView goUpdateWork(ModelAndView mv, Integer id) {
		// 根据兼职ID获取兼职工作信息
		Work work = workService.getWorkById(id);
		mv.addObject("work", work); // 将兼职工作对象添加到模型中，以便在视图中使用
		mv.setViewName("server/workManage/updateWork"); // 设置视图名称，指向编辑兼职页面
		return mv; // 返回ModelAndView对象
	}

	// 编辑兼职
	@RequestMapping("/updateWork")
	public void updateWork(HttpServletResponse response, Work work) {
		workService.updateWork(work); // 调用服务层更新兼职信息
		output(response, JsonUtil.buildFalseJson(0, "编辑成功！")); // 返回编辑成功的JSON响应
	}

	// 禁用兼职
	@RequestMapping("/deleteWork")
	public void deleteWork(HttpServletResponse response, Work work) {
		workService.updateWork(work); // 调用服务层禁用兼职工作
		output(response, JsonUtil.buildFalseJson(0, "禁用成功！")); // 返回禁用成功的JSON响应
	}
}
