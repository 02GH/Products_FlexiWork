package com.work.controller.pc;

import com.work.common.utils.BaseUtil;
import com.work.common.utils.JsonUtil;
import com.work.pojo.*;
import com.work.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

// 控制器注解，标识这是一个Spring MVC控制器
@Controller
@RequestMapping("/pc/action") // 设置请求路径的前缀为 "/pc/action"
public class PcActionController extends BaseUtil {

	@Autowired
	private WorkService workService; // 注入兼职工作服务
	@Autowired
	private ForumService forumService; // 注入论坛服务
	@Autowired
	private ForumReplyService forumReplyService; // 注入论坛回复服务
	@Autowired
	private CollectService collectService; // 注入收藏服务
	@Autowired
	private ChatService chatService; // 注入聊天服务
	@Autowired
	private MailboxService mailboxService; // 注入邮件服务
	@Autowired
	private JianzhiService jianzhiService; // 注入兼职服务
	@Autowired
	private FileService fileService; // 简历文件服务

	// 发布帖子
	@RequestMapping("/addForum")
	public void addForum(HttpServletRequest request, HttpServletResponse response, Forum forum) {
		User pcUser = (User) request.getSession().getAttribute("pcUser");
		if (pcUser == null) {
			output(response, JsonUtil.buildFalseJson(1, "您尚未登录，请先登录！"));
		} else {
			forum.setUserId(pcUser.getUserId()); // 设置当前用户ID
			forum.setCreateTime(new Date()); // 设置创建时间为当前时间
			forumService.addForum(forum); // 调用服务层添加帖子
			output(response, JsonUtil.buildFalseJson(0, "发布成功")); // 返回成功响应
		}
	}

	// 发布评论
	@RequestMapping("/addForumReply")
	public void addForumReply(HttpServletRequest request, HttpServletResponse response, ForumReply forumReply) {
		User pcUser = (User) request.getSession().getAttribute("pcUser");
		if (pcUser == null) {
			output(response, JsonUtil.buildFalseJson(1, "您尚未登录，请先登录！"));
		} else {
			forumReply.setUserId(pcUser.getUserId()); // 设置当前用户ID
			forumReply.setReplyTime(new Date()); // 设置回复时间为当前时间
			forumReplyService.addForumReply(forumReply); // 调用服务层添加回复
			output(response, JsonUtil.buildFalseJson(0, "发布成功")); // 返回成功响应
		}
	}

	// 收藏帖子
	@RequestMapping("/addCollect")
	public void addCollect(HttpServletRequest request, HttpServletResponse response, Collect collect) {
		User pcUser = (User) request.getSession().getAttribute("pcUser");
		List<Collect> collects = collectService.getCollectList(collect, null, null); // 查询已收藏的帖子
		collect.setUserId(pcUser.getUserId()); // 设置当前用户ID
		if (collects.size() > 0) {
			output(response, JsonUtil.buildFalseJson(0, "收藏成功")); // 如果已收藏，返回成功
		} else {
			collect.setCreateTime(new Date()); // 没有则设置创建时间
			collectService.addCollect(collect); // 调用服务层添加收藏
			output(response, JsonUtil.buildFalseJson(0, "收藏成功")); // 返回成功响应
		}
	}

	// 发布兼职
	@RequestMapping("/addWork")
	public void addWork(HttpServletRequest request, HttpServletResponse response, Work work) {
		User pcUser = (User) request.getSession().getAttribute("pcUser");
		if (pcUser == null) {
			output(response, JsonUtil.buildFalseJson(1, "您尚未登录，请先登录！"));
		} else {
			work.setUserId(pcUser.getUserId()); // 设置当前用户ID
			work.setCreateTime(new Date()); // 设置创建时间为当前时间
			workService.addWork(work); // 调用服务层添加兼职
			output(response, JsonUtil.buildFalseJson(0, "发布成功")); // 返回成功响应
		}
	}

	// 聊天消息查询
	@RequestMapping(value="/findChatList.action")
	public void findChat(User user, HttpServletRequest request, HttpServletResponse response) {
		User pcUser = (User) request.getSession().getAttribute("pcUser");
		Chat chat = new Chat();
		chat.setUserIdFa(pcUser.getUserId()); // 设置发送方ID
		chat.setUserIdJie(user.getUserId()); // 设置接收方ID
		List<Chat> chats = chatService.findChatList(chat, null, 30); // 获取聊天记录
		// 更新聊天记录为已读
		chat.setUserIdFa(user.getUserId());
		chat.setUserIdJie(pcUser.getUserId());
		chat.setIsLook(1); // 设置为已读
		chatService.updateChat(chat); // 更新状态
		output(response, JsonUtil.buildJsonByTotalCount(chats, 0)); // 返回聊天记录
	}

	// 聊天消息数目
	@RequestMapping(value="/findChatListCount.action")
	public void findChatListCount(User user, HttpServletRequest request, HttpServletResponse response) {
		User pcUser = (User) request.getSession().getAttribute("pcUser");
		Chat chat = new Chat();
		chat.setUserIdFa(pcUser.getUserId()); // 设置发送方ID
		chat.setUserIdJie(user.getUserId()); // 设置接收方ID
		Integer count = chatService.findChatListCount(chat); // 查询消息数量
		output(response, JsonUtil.buildFalseJson(count, "查询成功")); // 返回数量
	}

	// 发送聊天消息
	@RequestMapping(value="/addChat.action")
	public void addChat(User user, HttpServletRequest request, HttpServletResponse response, Chat chat) {
		User pcUser = (User) request.getSession().getAttribute("pcUser");
		chat.setUserIdFa(pcUser.getUserId()); // 设置发送方ID
		chat.setCreateTime(new Date()); // 设置发送时间
		// 判断聊天信号
		List<Chat> chats = chatService.findChatList(chat, null, null);
		if (chats.size() > 0) {
			chat.setChatSignal(chats.get(0).getChatSignal()); // 如果已有聊天，使用相同信号
		} else {
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
			String random = UUID.randomUUID().toString().replace("-", "").substring(0, 5);
			String chatSignal = format.format(new Date()) + random; // 创建新信号
			chat.setChatSignal(chatSignal);
		}
		chatService.addChat(chat); // 调用服务层添加聊天记录
		output(response, JsonUtil.buildFalseJson(0, "发送成功！")); // 返回成功响应
	}

	// 添加兼职申请
	@RequestMapping(value="/addjianzhi.action")
	public void addJianzhi(User user, HttpServletRequest request, HttpServletResponse response, Work work) {
		User pcUser = (User) request.getSession().getAttribute("pcUser");
		Jianzhi jianzhi = new Jianzhi();
		jianzhi.setUserId(pcUser.getUserId()); // 设置当前用户ID
		List<Jianzhi> list = jianzhiService.getList(jianzhi); // 检查兼职申请
		// 检查是否已申请过该兼职
		boolean exists = list.stream().anyMatch(e -> e.getWorkId().equals(work.getId()));
		if (exists) {
			output(response, JsonUtil.buildFalseJson(-11, "发送成功！")); // 已申请，返回相应信息
		} else {
			jianzhi.setWorkId(work.getId()); // 设置兼职ID
			jianzhiService.add(jianzhi); // 调用服务层添加申请
			output(response, JsonUtil.buildFalseJson(0, "发送成功！")); // 返回成功响应
		}
	}

	// 发送信件
	@RequestMapping(value="/addMailbox.action")
	public void addMailbox(Mailbox mailbox, HttpServletRequest request, HttpServletResponse response) {
		User pcUser = (User) request.getSession().getAttribute("pcUser");
		mailbox.setUserId(pcUser.getUserId()); // 设置当前用户ID
		mailbox.setCreateTime(new Date()); // 设置创建时间
		mailboxService.addMailbox(mailbox); // 调用服务层发送信件
		output(response, JsonUtil.buildFalseJson(0, "发送成功")); // 返回成功响应
	}

	// 上传简历
	@RequestMapping(value = "/uploadResume.action")
	public void uploadResume(@RequestParam("file") MultipartFile file, @RequestParam("userIdJie") Integer userIdJie, HttpServletRequest request, HttpServletResponse response, User user) {
		if (file.isEmpty()) {
			output(response, JsonUtil.buildJson("error", "文件不能为空"));
			return;
		}

		try {
			// 获取项目根目录
			String rootPath = request.getServletContext().getRealPath("/");
			// 设置文件保存目录
			String uploadDir = rootPath + "web/resume";
			java.io.File destinationFile = new java.io.File(uploadDir, file.getOriginalFilename());

			// 确保目录存在
			destinationFile.getParentFile().mkdirs();
			// 保存文件
			file.transferTo(destinationFile);

			// 创建 File 对象并设置属性
			com.work.pojo.File fileRecord = new com.work.pojo.File();
			fileRecord.setUserIdJie(userIdJie); // 设置接收者ID
			fileRecord.setWorkId("1"); // 设置 work_id
			fileRecord.setUserIdFa(((User) request.getSession().getAttribute("pcUser")).getUserId()); // 获取当前用户ID
			fileRecord.setFileAddr(destinationFile.getAbsolutePath()); // 设置文件存储地址
			fileRecord.setCreateTime(new Date()); // 设置创建时间

			// 检查是否已存在相同记录
			File existingFile = fileService.getFileByUserIds(fileRecord.getUserIdFa(), fileRecord.getUserIdJie());
			if (existingFile != null) {
				// 更新文件记录
				fileRecord.setFileId(existingFile.getFileId());
				fileService.updateFile(fileRecord);
			} else {
				// 添加新文件记录
				fileService.addFile(fileRecord);
			}

			output(response, JsonUtil.buildJson("success", "简历上传成功"));
		} catch (IOException e) {
			output(response, JsonUtil.buildJson("error", "简历上传失败: " + e.getMessage()));
		}
	}

	// 下载简历
	@RequestMapping(value = "/downloadResume.action")
	public void downloadResume(@RequestParam("userIdFa") Integer userIdFa, @RequestParam("userIdJie") Integer userIdJie, HttpServletResponse response) {
		// 根据 user_id_fa 和 user_id_jie 获取文件记录
		com.work.pojo.File fileRecord = fileService.getFileByUserIds(userIdFa, userIdJie);
		if (fileRecord == null) {
			output(response, JsonUtil.buildJson("error", "对方并未上传简历"));
			return;
		}

		// 获取文件对象
		java.io.File file = new java.io.File(fileRecord.getFileAddr());
		if (!file.exists()) {
			output(response, JsonUtil.buildJson("error", "文件不存在"));
			return;
		}

		// 设置响应的内容类型为二进制流
		response.setContentType("application/octet-stream");
		// 设置响应头，指定下载文件名
		response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");

		try (
				FileInputStream fis = new FileInputStream(file);
				OutputStream os = response.getOutputStream()
		) {
			byte[] buffer = new byte[1024];
			int bytesRead;
			while ((bytesRead = fis.read(buffer)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
			os.flush();
		} catch (IOException e) {
			output(response, JsonUtil.buildJson("error", "文件下载失败: " + e.getMessage()));
		}
	}
}
