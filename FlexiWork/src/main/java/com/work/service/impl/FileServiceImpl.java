package com.work.service.impl; // 定义该类所在的包

import com.work.mapper.FileMapper; // 引入 FileMapper 接口
import com.work.pojo.File; // 引入 File POJO 类
import com.work.service.FileService; // 引入 FileService 接口
import org.springframework.beans.factory.annotation.Autowired; // 引入 Spring 的 Autowired 注解
import org.springframework.stereotype.Service; // 引入 Spring 的 Service 注解
import java.util.List; // 导入 List 类

// 标识该类为服务层组件
@Service
public class FileServiceImpl implements FileService {

    // 自动注入 FileMapper 依赖
    @Autowired
    private FileMapper fileMapper;

    // 添加文件记录
    @Override
    public void addFile(File file) {
        fileMapper.addFile(file);
    }

    // 根据文件ID获取文件记录
    @Override
    public File getFileById(Integer fileId) {
        return fileMapper.getFileById(fileId);
    }

    //根据 聊天双方id 获取文件记录
    @Override
    public File getFileByUserIds(Integer userIdFa, Integer userIdJie) {
        return fileMapper.getFileByUserIds(userIdFa, userIdJie);
    }

    //更新文件
    @Override
    public void updateFile(File file) {
        fileMapper.updateFile(file);
    }

    // 获取文件记录列表，支持分页
    @Override
    public List<File> getFileList(File file, Integer page, Integer limit) {
        return fileMapper.getFileList(file, page, limit);
    }

    // 获取文件记录总数
    @Override
    public Integer getFileListCount(File file) {
        return fileMapper.getFileListCount(file);
    }
}
