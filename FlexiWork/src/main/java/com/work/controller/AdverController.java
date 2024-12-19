package com.work.controller;

import com.work.common.utils.BaseUtil;
import com.work.common.utils.JsonUtil;
import com.work.pojo.Adver;
import com.work.pojo.User;
import com.work.service.AdverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

// 标注为Spring的控制器
@Controller
@RequestMapping("/server/adver") // 定义请求路径的根路径
public class AdverController extends BaseUtil{

	@Autowired // 自动注入AdverService
	private AdverService adverService;

	// 跳转到广告列表页面
	@RequestMapping("/goAdverList")
	public ModelAndView goAdverList(ModelAndView mv){
		mv.setViewName("server/adverManage/adverList"); // 设置视图名称
		return mv;
	}

	// 获取广告列表数据
	@RequestMapping("/getAdverList")
	public void getAdverList(HttpServletResponse response, Adver adver, Integer page, Integer limit){
		// 设置分页参数默认值
		if(page == null){
			page = 1;
		}
		if(limit == null){
			limit = 10;
		}
		// 获取广告总数和广告列表
		int totalCount = adverService.getAdverListCount(adver);
		List<Adver> list = adverService.getAdverList(adver, (page-1) * limit, limit);
		// 输出广告列表的JSON格式数据
		output(response, JsonUtil.buildJsonByTotalCount(list, totalCount));
	}

	// 跳转到添加广告页面
	@RequestMapping("/goAddAdver")
	public ModelAndView goAddAdver(ModelAndView mv){
		mv.setViewName("server/adverManage/addAdver"); // 设置视图名称
		return mv;
	}

	// 添加新广告
	@RequestMapping("/addAdver")
	public void addAdver(HttpServletRequest request, HttpServletResponse response, Adver adver){
		// 获取当前登录用户信息
		User serverUser = (User) request.getSession().getAttribute("serverUser");
		adver.setCreateTime(new Date()); // 设置创建时间
		adver.setUserId(serverUser.getUserId()); // 设置用户ID
		adverService.addAdver(adver); // 调用服务层方法添加广告
		// 输出成功添加的JSON格式数据
		output(response, JsonUtil.buildFalseJson(0, "添加成功！"));
	}

	// 跳转到编辑广告页面
	@RequestMapping("/goUpdateAdver")
	public ModelAndView goUpdateAdver(ModelAndView mv, Integer id){
		// 根据ID获取广告信息
		Adver adver = adverService.getAdverById(id);
		mv.addObject("adver", adver); // 将广告对象添加到模型中
		mv.setViewName("server/adverManage/updateAdver"); // 设置视图名称
		return mv;
	}

	// 编辑广告信息
	@RequestMapping("/updateAdver")
	public void updateAdver(HttpServletResponse response, Adver adver){
		adverService.updateAdver(adver); // 调用服务层方法更新广告
		// 输出成功编辑的JSON格式数据
		output(response, JsonUtil.buildFalseJson(0, "编辑成功！"));
	}

	// 删除广告
	@RequestMapping("/deleteAdver")
	public void deleteAdver(HttpServletResponse response, Integer id){
		adverService.deleteAdverById(id); // 调用服务层方法删除广告
		// 输出成功删除的JSON格式数据
		output(response, JsonUtil.buildFalseJson(0, "删除成功！"));
	}
}
