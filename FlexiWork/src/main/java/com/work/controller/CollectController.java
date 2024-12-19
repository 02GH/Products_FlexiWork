package com.work.controller;

// 导入所需的类和包
import com.work.common.utils.BaseUtil;
import com.work.common.utils.JsonUtil;
import com.work.pojo.Collect;
import com.work.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

// 控制器注解，标识该类为控制器
@Controller
@RequestMapping("/server/collect") // 设置请求的根路径
public class CollectController extends BaseUtil {

	// 自动注入 CollectService 服务
	@Autowired
	private CollectService collectService;

	// 去收藏列表页面
	@RequestMapping("/goCollectList") // 映射请求路径
	public ModelAndView goCollectList(ModelAndView mv) {
		mv.setViewName("server/collectManage/collectList"); // 设置视图名称
		return mv; // 返回 ModelAndView 对象
	}

	// 获取收藏列表
	@RequestMapping("/getCollectList") // 映射请求路径
	public void getCollectList(HttpServletResponse response, Collect collect, Integer page, Integer limit) {
		// 设置默认分页参数
		if (page == null) {
			page = 1; // 默认页码为1
		}
		if (limit == null) {
			limit = 10; // 默认每页条目数为10
		}

		// 获取收藏总数
		int totalCount = collectService.getCollectListCount(collect);
		// 获取当前页的收藏列表数据
		List<Collect> list = collectService.getCollectList(collect, (page - 1) * limit, limit);

		// 输出 JSON 格式的响应
		output(response, JsonUtil.buildJsonByTotalCount(list, totalCount));
	}

	// 删除收藏
	@RequestMapping("/deleteCollect") // 映射请求路径
	public void deleteCollect(HttpServletResponse response, Integer id) {
		// 根据 ID 删除收藏
		collectService.deleteCollectById(id);
		// 输出删除成功的 JSON 响应
		output(response, JsonUtil.buildFalseJson(0, "删除成功！"));
	}
}
