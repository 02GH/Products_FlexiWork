package com.work.pojo; // 定义该类所在的包

import java.util.Date; // 导入 Date 类，用于处理日期时间

public class File {
    // 文件记录的唯一标识符
    private Integer fileId;
    // 发送者 ID
    private Integer userIdFa;
    // 接收者 ID
    private Integer userIdJie;
    // 文件存储地址
    private String fileAddr;
    // 岗位 ID
    private String workId;
    // 文件创建时间
    private Date createTime;

    // 有参构造函数
    public File(Integer fileId, Integer userIdFa, Integer userIdJie, String fileAddr, String workId, Date createTime) {
        this.fileId = fileId;
        this.userIdFa = userIdFa;
        this.userIdJie = userIdJie;
        this.fileAddr = fileAddr;
        this.workId = workId;
        this.createTime = createTime;
    }

    // 无参构造函数
    public File() {
    }

    // Getter 和 Setter 方法，用于访问和修改私有字段

    public Integer getFileId() {
        return fileId; // 获取文件记录的ID
    }
    public void setFileId(Integer fileId) {
        this.fileId = fileId; // 设置文件记录的ID
    }

    public Integer getUserIdFa() {
        return userIdFa; // 获取发送者ID
    }
    public void setUserIdFa(Integer userIdFa) {
        this.userIdFa = userIdFa; // 设置发送者ID
    }

    public Integer getUserIdJie() {
        return userIdJie; // 获取接收者ID
    }
    public void setUserIdJie(Integer userIdJie) {
        this.userIdJie = userIdJie; // 设置接收者ID
    }

    public String getFileAddr() {
        return fileAddr; // 获取文件存储地址
    }
    public void setFileAddr(String fileAddr) {
        this.fileAddr = fileAddr; // 设置文件存储地址
    }

    public String getWorkId() {
        return workId; // 获取岗位ID
    }
    public void setWorkId(String workId) {
        this.workId = workId; // 设置岗位ID
    }

    public Date getCreateTime() {
        return createTime; // 获取文件创建时间
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime; // 设置文件创建时间
    }
}
