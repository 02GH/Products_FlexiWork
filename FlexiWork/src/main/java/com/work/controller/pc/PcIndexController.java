package com.work.controller.pc;

import com.work.common.utils.BaseUtil;
import com.work.common.utils.JsonUtil;
import com.work.common.utils.Page;
import com.work.pojo.*;
import com.work.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.List;

// 控制器注解，标识这是一个Spring MVC控制器
@Controller
@RequestMapping("/pc/index") // 设置请求路径的前缀为 "/pc/index"
public class PcIndexController extends BaseUtil {

	@Autowired
	private AdverService adverService; // 广告服务
	@Autowired
	private NoticeService noticeService; // 公告服务
	@Autowired
	private WorkService workService; // 兼职服务
	@Autowired
	private UserService userService; // 用户服务
	@Autowired
	private ForumService forumService; // 论坛服务
	@Autowired
	private ChatService chatService; // 聊天服务
	@Autowired
	private BannerService bannerService; // 轮播图服务
	@Autowired
	private SystemUpsetService systemUpsetService; // 系统配置服务
	@Autowired
	private ForumReplyService forumReplyService; // 论坛回复服务


	// 公共头部
	@RequestMapping(value="/goHeader.action")
	public ModelAndView goHeader(HttpServletRequest request, ModelAndView mv, SystemUpset systemUpset) {
		// 查询系统配置
		List<SystemUpset> systemUpsets = systemUpsetService.getSystemUpsetList(systemUpset, null, null);
		if (systemUpsets.size() > 0) {
			mv.addObject("systemUpset", systemUpsets.get(0)); // 将系统配置添加到模型中
		}
		mv.setViewName("pc/header"); // 设置视图名称为头部
		return mv;
	}

	// 公共尾部
	@RequestMapping(value="/goFooter.action")
	public ModelAndView goFooter(ModelAndView mv, SystemUpset systemUpset) {
		// 查询系统配置
		List<SystemUpset> systemUpsets = systemUpsetService.getSystemUpsetList(systemUpset, null, null);
		if (systemUpsets.size() > 0) {
			mv.addObject("systemUpset", systemUpsets.get(0)); // 将系统配置添加到模型中
		}
		mv.setViewName("pc/footer"); // 设置视图名称为尾部
		return mv;
	}

	// 跳转到首页面
	@RequestMapping(value="/goIndex.action")
	public ModelAndView goLogin(HttpServletRequest request, ModelAndView mv, Work work, QueryVo vo) {
		// 判断是否是滚动条
		String isScroll = "f"; // 默认不滚动
		// 获取兼职信息
		Page<Work> page = new Page<Work>();
		if (work.getWorkCategory() != null && !work.getWorkCategory().equals("")) {
			isScroll = "t"; // 如果有工作类别则设置为滚动
		}

		work.setIsEffect(1); // 只获取有效的兼职
		work.setIsAuditing(1); // 只获取审核通过的兼职
		if (vo.getSize() != 8) {
			vo.setSize(8); // 每页显示8条
		}
		if (vo.getPage() == null) {
			vo.setPage(1); // 默认第一页
		}

		// 设置分页信息
		page.setSize(vo.getSize());
		if (vo != null) {
			// 判断当前页
			if (vo.getPage() != null) {
				page.setPage(vo.getPage());
				vo.setStartRow((vo.getPage() - 1) * vo.getSize());
			}
			page.setTotal(workService.getWorkListCount(work)); // 获取总数
			page.setRows(workService.getWorkList(work, (vo.getPage() - 1) * vo.getSize(), vo.getSize())); // 获取兼职数据
		}
		mv.addObject("page", page); // 将分页数据添加到模型中

		// 查询系统配置
		SystemUpset systemUpset = new SystemUpset();
		List<SystemUpset> systemUpsets = systemUpsetService.getSystemUpsetList(systemUpset, null, null);
		if (systemUpsets.size() > 0) {
			mv.addObject("systemUpset", systemUpsets.get(0)); // 将系统配置添加到模型中
		}

		// 轮播图
		Banner banner = new Banner();
		banner.setIsEffect(1); // 只获取有效的轮播图
		List<Banner> banners = bannerService.getBannerList(banner, null, null);
		mv.addObject("banners", banners); // 将轮播图添加到模型中

		// 获取当前用户
		User pcUser = (User) request.getSession().getAttribute("pcUser");
		mv.addObject("pcUser", pcUser); // 将当前用户添加到模型中

		mv.addObject("isScroll", isScroll); // 添加滚动标识
		mv.setViewName("pc/index"); // 设置视图名称为首页
		return mv;
	}

	// 首页获取分类兼职
	@RequestMapping("/getWorkByWorkCate")
	public void getWorkByWorkCate(HttpServletResponse response, Work work) {
		work.setIsEffect(1); // 只获取有效的兼职
		work.setIsAuditing(1); // 只获取审核通过的兼职
		Integer count = workService.getWorkListCount(work); // 获取兼职总数
		List<Work> works = workService.getWorkList(work, 0, 8); // 获取前8条兼职
		SimpleDateFormat cTime = new SimpleDateFormat("yyyy-MM-dd"); // 日期格式化
		if (works.size() > 0) {
			for (int i = 0; i < works.size(); i++) {
				works.get(i).setCreateTimes(cTime.format(works.get(i).getCreateTime())); // 格式化创建时间
			}
		}
		output(response, JsonUtil.buildJsonByTotalCount(works, count)); // 返回兼职数据和总数
	}

	// 查看兼职
	@RequestMapping(value="/goFindWork.action")
	public ModelAndView goFindWork(ModelAndView mv, Work work) {
		// 查询系统配置
		SystemUpset systemUpset = new SystemUpset();
		List<SystemUpset> systemUpsets = systemUpsetService.getSystemUpsetList(systemUpset, null, null);
		if (systemUpsets.size() > 0) {
			mv.addObject("systemUpset", systemUpsets.get(0)); // 将系统配置添加到模型中
		}
		work = workService.getWorkById(work.getId()); // 根据ID获取兼职
		mv.addObject("work", work); // 将兼职信息添加到模型中
		mv.setViewName("pc/find_work"); // 设置视图名称为查看兼职页面
		return mv;
	}

	// 查看用户资料
	@RequestMapping(value="/goFindUser.action")
	public ModelAndView goFindUser(HttpServletRequest request, ModelAndView mv, SystemUpset systemUpset, User user) {
		// 查询系统配置
		List<SystemUpset> systemUpsets = systemUpsetService.getSystemUpsetList(systemUpset, null, null);
		if (systemUpsets.size() > 0) {
			mv.addObject("systemUpset", systemUpsets.get(0)); // 将系统配置添加到模型中
		}
		user = userService.getUserById(user.getUserId()); // 根据用户ID获取用户信息
		mv.addObject("user", user); // 将用户信息添加到模型中
		User pcUser = (User) request.getSession().getAttribute("pcUser");
		mv.addObject("pcUser", pcUser); // 将当前用户添加到模型中
		mv.setViewName("pc/user_info"); // 设置视图名称为用户信息页面
		return mv;
	}

	// 跳转到聊天页面
	@RequestMapping(value="/goChat.action")
	public ModelAndView goChat(HttpServletRequest request, ModelAndView mv, SystemUpset systemUpset, User user) {
		// 查询系统配置
		List<SystemUpset> systemUpsets = systemUpsetService.getSystemUpsetList(systemUpset, null, null);
		if (systemUpsets.size() > 0) {
			mv.addObject("systemUpset", systemUpsets.get(0)); // 将系统配置添加到模型中
		}
		user = userService.getUserById(user.getUserId()); // 根据用户ID获取用户信息
		int userId = user.getUserId();
		mv.addObject("user", user); // 将用户信息添加到模型中

		// 获取Session信息
		User pcUser = (User) request.getSession().getAttribute("pcUser");
		mv.addObject("pcUser", pcUser); // 将当前用户添加到模型中
		if (pcUser == null) {
			mv.setViewName("pc/login"); // 如果未登录，跳转到登录页面
		} else if (pcUser.getUserId() == userId) {
			mv.addObject("updatePage", "y"); // 如果是自己则跳转到首页
			mv.setViewName("pc/index");
		} else {
			// 更新聊天记录为已读
			Chat chat = new Chat();
			chat.setUserIdFa(user.getUserId());
			chat.setUserIdJie(pcUser.getUserId());
			chat.setIsLook(1);
			chatService.updateChat(chat); // 更新聊天记录
			mv.setViewName("pc/chat"); // 设置视图名称为聊天页面
		}
		return mv;
	}

	// 跳转到论坛页面
	@RequestMapping(value="/goForum.action")
	public ModelAndView goForum(HttpServletRequest request, ModelAndView mv, QueryVo vo) {
		User pcUser = (User) request.getSession().getAttribute("pcUser");
		mv.addObject("pcUser", pcUser); // 将当前用户添加到模型中

		// 获取广告
		Adver adver = new Adver();
		List<Adver> advers = adverService.getAdverList(adver, null, null);
		mv.addObject("advers", advers); // 将广告添加到模型中

		// 获取论坛帖子
		Page<Forum> page = new Page<Forum>();
		Forum forum = new Forum();
		forum.setIsEffect(1); // 只获取有效的论坛帖子
		if (vo.getSize() != 10) {
			vo.setSize(10); // 默认每页显示10条
		}
		if (vo.getPage() == null) {
			vo.setPage(1); // 默认第一页
		}

		// 设置分页信息
		page.setSize(vo.getSize());
		if (vo != null) {
			// 判断当前页
			if (vo.getPage() != null) {
				page.setPage(vo.getPage());
				vo.setStartRow((vo.getPage() - 1) * vo.getSize());
			}
			page.setTotal(forumService.getForumListCount(forum)); // 获取总数
			page.setRows(forumService.getForumList(forum, (vo.getPage() - 1) * vo.getSize(), vo.getSize())); // 获取论坛帖子数据
		}

		// 获取每个帖子的评论
		ForumReply forumReply = new ForumReply();
		for (int i = 0; i < page.getRows().size(); i++) {
			forumReply.setForumId(page.getRows().get(i).getForumId());
			page.getRows().get(i).setForumReplys(forumReplyService.getForumReplyList(forumReply, null, null)); // 获取评论并设置到帖子中
		}
		mv.addObject("page", page); // 将分页数据添加到模型中
		mv.setViewName("pc/forum"); // 设置视图名称为论坛页面
		return mv;
	}

	// 跳转到信箱页面
	@RequestMapping(value="/goMailbox.action")
	public ModelAndView goMailbox(HttpServletRequest request, ModelAndView mv, SystemUpset systemUpset) {
		// 查询系统配置
		List<SystemUpset> systemUpsets = systemUpsetService.getSystemUpsetList(systemUpset, null, null);
		if (systemUpsets.size() > 0) {
			mv.addObject("systemUpset", systemUpsets.get(0)); // 将系统配置添加到模型中
		}
		User pcUser = (User) request.getSession().getAttribute("pcUser");
		mv.addObject("pcUser", pcUser); // 将当前用户添加到模型中
		if (pcUser == null) {
			mv.setViewName("pc/login"); // 如果未登录，跳转到登录页面
		} else {
			mv.setViewName("pc/mailbox"); // 否则跳转到邮箱页面
		}
		return mv;
	}

	// 跳转到发布兼职页面
	@RequestMapping(value="/goPublishWork.action")
	public ModelAndView goPublishWork(HttpServletRequest request, ModelAndView mv, SystemUpset systemUpset) {
		// 查询系统配置
		List<SystemUpset> systemUpsets = systemUpsetService.getSystemUpsetList(systemUpset, null, null);
		if (systemUpsets.size() > 0) {
			mv.addObject("systemUpset", systemUpsets.get(0)); // 将系统配置添加到模型中
		}
		User pcUser = (User) request.getSession().getAttribute("pcUser");
		mv.addObject("pcUser", pcUser); // 将当前用户添加到模型中
		if (pcUser == null) {
			mv.setViewName("pc/login"); // 如果未登录，跳转到登录页面
		} else {
			mv.setViewName("pc/publish_work"); // 否则跳转到发布兼职页面
		}
		return mv;
	}

	// 跳转到公告页面
	@RequestMapping(value="/goNotice.action")
	public ModelAndView goNotice(HttpServletRequest request, ModelAndView mv, QueryVo vo) {
		// 获取公告信息
		Page<Notice> page = new Page<Notice>();
		Notice notice = new Notice();
		if (vo.getSize() != 12) {
			vo.setSize(12); // 默认每页显示12条
		}
		if (vo.getPage() == null) {
			vo.setPage(1); // 默认第一页
		}

		// 设置分页信息
		page.setSize(vo.getSize());
		if (vo != null) {
			// 判断当前页
			if (vo.getPage() != null) {
				page.setPage(vo.getPage());
				vo.setStartRow((vo.getPage() - 1) * vo.getSize());
			}
			page.setTotal(noticeService.getNoticeListCount(notice)); // 获取总数
			page.setRows(noticeService.getNoticeList(notice, (vo.getPage() - 1) * vo.getSize(), vo.getSize())); // 获取公告数据
		}
		mv.addObject("page", page); // 将分页数据添加到模型中
		mv.setViewName("pc/notice"); // 设置视图名称为公告页面
		return mv;
	}

	// 获取公告
	@RequestMapping(value="/getNotice.action")
	public void getNotice(Notice notice, HttpServletRequest request, HttpServletResponse response) {
		notice = noticeService.getNoticeById(notice.getId()); // 根据ID获取公告
		SimpleDateFormat cTime = new SimpleDateFormat("yyyy-MM-dd"); // 日期格式化
		notice.setCreateTimes(cTime.format(notice.getCreateTime())); // 格式化创建时间
		output(response, JsonUtil.objectToJson(notice)); // 返回公告数据
	}


}
