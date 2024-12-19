//JianzhiService.java 文件定义了与兼职信息管理相关的基本服务接口，提供了对兼职数据的操作方法。
// 通过这个接口，其他组件（如服务实现类和控制器）可以方便地进行兼职信息的增、查操作。
package com.work.service;

import com.work.pojo.Jianzhi; // 引入 Jianzhi POJO 类，表示兼职信息

import java.util.List;

// 定义兼职服务接口
public interface JianzhiService {

    // 添加新的兼职信息
    void add(Jianzhi jianzhi);

    // 获取兼职信息列表
    List<Jianzhi> getList(Jianzhi jianzhi);
}
