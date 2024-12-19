//JianzhiServiceImpl.java 文件实现了兼职信息管理的基本业务逻辑，提供了对兼职数据的操作接口。通过该类，其他组件（如控制器）可以方便地进行兼职信息的增、查操作。
package com.work.service.impl;

import com.work.mapper.JianzhiMapper; // 引入 JianzhiMapper 接口
import com.work.pojo.Jianzhi; // 引入 Jianzhi POJO 类
import com.work.service.JianzhiService; // 引入 JianzhiService 接口
import org.springframework.beans.factory.annotation.Autowired; // 引入 Spring 的 Autowired 注解
import org.springframework.stereotype.Service; // 引入 Spring 的 Service 注解

import java.util.List;

// 标识该类为服务层组件
@Service
public class JianzhiServiceImpl implements JianzhiService {

    // 自动注入 JianzhiMapper 依赖
    @Autowired
    private JianzhiMapper jianzhiMapper;

    // 添加新的兼职信息
    @Override
    public void add(Jianzhi jianzhi) {
        jianzhiMapper.addjianzhi(jianzhi); // 调用数据访问层的方法将兼职信息添加到数据库
    }

    // 获取兼职信息列表
    @Override
    public List<Jianzhi> getList(Jianzhi jianzhi) {
        return jianzhiMapper.getList(jianzhi); // 调用数据访问层的方法获取符合条件的兼职信息列表
    }
}
