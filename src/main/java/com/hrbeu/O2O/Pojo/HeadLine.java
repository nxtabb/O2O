package com.hrbeu.O2O.Pojo;

import java.util.Date;
//首页头条
public class HeadLine {
    //首页头条id
    private Long lineId;
    //首页头条名称
    private String lineName;
    //首页头条目标网站地址
    private String lineLink;
    //首页头条图片url
    private String lineImg;
    //首页头条优先级
    private Integer priority;
    //首页头条是否可用
    private Integer enableStatus;
    //首页头条创建时间
    private Date createTime;
    //首页头条修改时间
    private Date lastEditTime;

    public HeadLine(){}

    public HeadLine(Long lineId, String lineName, String lineLink, String lineImg, Integer priority, Integer enableStatus, Date createTime, Date lastEditTime) {
        this.lineId = lineId;
        this.lineName = lineName;
        this.lineLink = lineLink;
        this.lineImg = lineImg;
        this.priority = priority;
        this.enableStatus = enableStatus;
        this.createTime = createTime;
        this.lastEditTime = lastEditTime;
    }

    public Long getLineId() {
        return lineId;
    }

    public void setLineId(Long lineId) {
        this.lineId = lineId;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getLineLink() {
        return lineLink;
    }

    public void setLineLink(String lineLink) {
        this.lineLink = lineLink;
    }

    public String getLineImg() {
        return lineImg;
    }

    public void setLineImg(String lineImg) {
        this.lineImg = lineImg;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getEnableStatus() {
        return enableStatus;
    }

    public void setEnableStatus(Integer enableStatus) {
        this.enableStatus = enableStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(Date lastEditTime) {
        this.lastEditTime = lastEditTime;
    }
}
