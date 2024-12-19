package com.work.service; // 定义该接口所在的包

import com.work.pojo.File; // 引入 File POJO 类
import java.util.List; // 导入 List 类

// 定义文件服务接口
public interface FileService {

    // 添加文件记录
    void addFile(File file);

    // 根据文件ID获取文件记录
    File getFileById(Integer fileId);

    // 根据 user_id_fa 和 user_id_jie 获取文件记录
    File getFileByUserIds(Integer userIdFa, Integer userIdJie);

    // 更新文件记录
    void updateFile(File file);

    // 获取文件记录列表，支持分页
    List<File> getFileList(File file, Integer page, Integer limit);

    // 获取文件记录总数
    Integer getFileListCount(File file);
}
