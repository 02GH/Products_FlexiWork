package com.work.mapper; // 定义该接口所在的包

import com.work.pojo.File; // 引入 File POJO 类
import org.apache.ibatis.annotations.Param; // 引入 MyBatis 的 Param 注解

import java.util.List; // 导入 List 类

// FileMapper 接口，用于定义与文件记录相关的数据库操作
public interface FileMapper {

    // 添加文件记录
    void addFile(@Param("file") File file);

    // 根据ID获取文件记录
    File getFileById(@Param("fileId") Integer fileId);

    // 根据 user_id_fa 和 user_id_jie 获取文件记录
    File getFileByUserIds(@Param("userIdFa") Integer userIdFa, @Param("userIdJie") Integer userIdJie);

    // 更新文件记录
    void updateFile(@Param("file") File file);

    // 获取文件记录列表，支持分页
    List<File> getFileList(@Param("file") File file,
                           @Param("page") Integer page,
                           @Param("limit") Integer limit);

    // 获取文件记录总数
    Integer getFileListCount(@Param("file") File file);


}
