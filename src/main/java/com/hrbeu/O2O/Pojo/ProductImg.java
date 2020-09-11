package com.hrbeu.O2O.Pojo;

import java.util.Date;

public class ProductImg {
    //商品图片id
    private Long productImgId;
    //商品图片地址
    private String imgAddr;
    //图片说明
    private String imgDesc;
    //图片显示优先级
    private Integer priority;
    //创建时间
    private Date createTime;
    //所属商品
    private Long productId;
    public ProductImg(){}

    public ProductImg(Long productImgId, String imgAddr, String imgDesc, Integer priority, Date createTime, Long productId) {
        this.productImgId = productImgId;
        this.imgAddr = imgAddr;
        this.imgDesc = imgDesc;
        this.priority = priority;
        this.createTime = createTime;
        this.productId = productId;
    }

    public Long getProductImgId() {
        return productImgId;
    }

    public void setProductImgId(Long productImgId) {
        this.productImgId = productImgId;
    }

    public String getImgAddr() {
        return imgAddr;
    }

    public void setImgAddr(String imgAddr) {
        this.imgAddr = imgAddr;
    }

    public String getImgDesc() {
        return imgDesc;
    }

    public void setImgDesc(String imgDesc) {
        this.imgDesc = imgDesc;
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

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
