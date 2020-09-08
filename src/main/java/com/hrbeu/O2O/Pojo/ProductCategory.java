package com.hrbeu.O2O.Pojo;

import java.util.Date;

public class ProductCategory {
    //商品类别ID
    private Long productCategoryId;
    //商品所属店铺id
    private Long shopId;
    //商品类别名称
    private String productCategoryName;
    //商品类别优先级
    private Integer priority;
    //商品类别创建时间
    private Date createTime;
    public ProductCategory(){}

    public ProductCategory(Long productCategoryId, Long shopId, String productCategoryName, Integer priority, Date createTime) {
        this.productCategoryId = productCategoryId;
        this.shopId = shopId;
        this.productCategoryName = productCategoryName;
        this.priority = priority;
        this.createTime = createTime;
    }

    public Long getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Long productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getProductCategoryName() {
        return productCategoryName;
    }

    public void setProductCategoryName(String productCategoryName) {
        this.productCategoryName = productCategoryName;
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
}
