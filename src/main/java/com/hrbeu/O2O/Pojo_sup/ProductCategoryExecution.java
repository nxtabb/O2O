package com.hrbeu.O2O.Pojo_sup;

import com.hrbeu.O2O.Pojo.ProductCategory;
import com.hrbeu.O2O.enums.ProductCategoryStateEnum;

import java.util.List;

public class ProductCategoryExecution {
    //结果状态
    private Integer state;
    //操作状态描述
    private String stateInfo;
    //操作的shop
    private ProductCategory productCategory;
    //查询店铺列表时使用
    private List<ProductCategory> productCategoryList;

    public ProductCategoryExecution(){}
    //失败是使用的构造器
    public ProductCategoryExecution(ProductCategoryStateEnum productCategoryStateEnum){
        this.state = productCategoryStateEnum.getState();
        this.stateInfo = productCategoryStateEnum.getStateInfo();
    }
    //成功时的构造器
    public ProductCategoryExecution(ProductCategoryStateEnum stateEnum,List<ProductCategory> productCategoryList){
        this.state= stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.productCategoryList = productCategoryList;

    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public List<ProductCategory> getProductCategoryList() {
        return productCategoryList;
    }

    public void setProductCategoryList(List<ProductCategory> productCategoryList) {
        this.productCategoryList = productCategoryList;
    }
}
