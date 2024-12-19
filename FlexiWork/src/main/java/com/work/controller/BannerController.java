package com.work.controller;

import com.work.common.utils.BaseUtil;
import com.work.common.utils.JsonUtil;
import com.work.pojo.Banner;
import com.work.pojo.User;
import com.work.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

// 标识该类为 Spring 控制器
@Controller
@RequestMapping("/server/banner") // 指定访问路径的前缀
public class BannerController extends BaseUtil {

	// 注入 BannerService，以便进行业务操作
	@Autowired
	private BannerService bannerService;

	// 跳转到轮播列表页面
	@RequestMapping("/goBannerList")
	public ModelAndView goBannerList(ModelAndView mv) {
		mv.setViewName("server/bannerManage/bannerList"); // 设置视图名
		return mv; // 返回 ModelAndView 对象
	}

	// 获取轮播列表数据
	@RequestMapping("/getBannerList")
	public void getBannerList(HttpServletResponse response, Banner banner, Integer page, Integer limit) {
		if (page == null) {
			page = 1; // 默认第一页
		}
		if (limit == null) {
			limit = 10; // 默认每页显示10条
		}
		int totalCount = bannerService.getBannerListCount(banner); // 获取总记录数
		List<Banner> list = bannerService.getBannerList(banner, (page - 1) * limit, limit); // 获取当前页的轮播列表
		output(response, JsonUtil.buildJsonByTotalCount(list, totalCount)); // 输出 JSON 格式的数据
	}

	// 跳转到添加轮播页面
	@RequestMapping("/goAddBanner")
	public ModelAndView goAddBanner(ModelAndView mv) {
		mv.setViewName("server/bannerManage/addBanner"); // 设置视图名
		return mv; // 返回 ModelAndView 对象
	}

	// 添加新的轮播
	@RequestMapping("/addBanner")
	public void addBanner(HttpServletRequest request, HttpServletResponse response, Banner banner) {
		User serverUser = (User) request.getSession().getAttribute("serverUser"); // 从会话中获取当前用户信息
		banner.setCreateTime(new Date()); // 设置轮播的创建时间
		banner.setUserId(serverUser.getUserId()); // 设置创建用户ID
		bannerService.addBanner(banner); // 调用服务层添加轮播
		output(response, JsonUtil.buildFalseJson(0, "添加成功！")); // 输出添加成功的消息
	}

	// 跳转到编辑轮播页面
	@RequestMapping("/goUpdateBanner")
	public ModelAndView goUpdateBanner(ModelAndView mv, Integer id) {
		Banner banner = bannerService.getBannerById(id); // 根据ID获取轮播对象
		mv.addObject("banner", banner); // 将轮播对象添加到模型中
		mv.setViewName("server/bannerManage/updateBanner"); // 设置视图名
		return mv; // 返回 ModelAndView 对象
	}

	// 编辑轮播
	@RequestMapping("/updateBanner")
	public void updateBanner(HttpServletResponse response, Banner banner) {
		bannerService.updateBanner(banner); // 调用服务层更新轮播
		output(response, JsonUtil.buildFalseJson(0, "编辑成功！")); // 输出编辑成功的消息
	}

	// 禁用轮播
	@RequestMapping("/deleteBanner")
	public void deleteBanner(HttpServletResponse response, Banner banner) {
		bannerService.updateBanner(banner); // 调用服务层更新（可能是设置禁用状态）
		output(response, JsonUtil.buildFalseJson(0, "禁用成功！")); // 输出禁用成功的消息
	}
}
