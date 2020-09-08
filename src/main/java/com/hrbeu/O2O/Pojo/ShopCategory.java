package com.hrbeu.O2O.Pojo;

import java.util.Date;
//店铺类别
public class ShopCategory {
    //店铺种类id
    private Long shopCategoryId;
    //店铺种类姓名
    private String shopCategoryName;
    //店铺种类描述
    private String shopCategoryDesc;
    //店铺种类图片
    private String shopCategoryImg;
    //优先级
    private Integer priority;
    //创建时间
    private Date createTime;
    //修改时间
    private Date lastEditTime;
    //上级店铺种类id
    private ShopCategory parent;
    public ShopCategory(){}

    public ShopCategory(Long shopCategoryId, String shopCategoryName, String shopCategoryDesc, String shopCategoryImg, Integer priority, Date createTime, Date lastEditTime, ShopCategory parent) {
        this.shopCategoryId = shopCategoryId;
        this.shopCategoryName = shopCategoryName;
        this.shopCategoryDesc = shopCategoryDesc;
        this.shopCategoryImg = shopCategoryImg;
        this.priority = priority;
        this.createTime = createTime;
        this.lastEditTime = lastEditTime;
        this.parent = parent;
    }

    public Long getShopCategoryId() {
        return shopCategoryId;
    }

    public void setShopCategoryId(Long shopCategoryId) {
        this.shopCategoryId = shopCategoryId;
    }

    public String getShopCategoryName() {
        return shopCategoryName;
    }

    public void setShopCategoryName(String shopCategoryName) {
        this.shopCategoryName = shopCategoryName;
    }

    public String getShopCategoryDesc() {
        return shopCategoryDesc;
    }

    public void setShopCategoryDesc(String shopCategoryDesc) {
        this.shopCategoryDesc = shopCategoryDesc;
    }

    public String getShopCategoryImg() {
        return shopCategoryImg;
    }

    public void setShopCategoryImg(String shopCategoryImg) {
        this.shopCategoryImg = shopCategoryImg;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
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

    public ShopCategory getParent() {
        return parent;
    }

    public void setParent(ShopCategory parent) {
        this.parent = parent;
    }
}
