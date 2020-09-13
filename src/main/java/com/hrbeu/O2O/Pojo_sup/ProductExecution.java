package com.hrbeu.O2O.Pojo_sup;

import com.hrbeu.O2O.Pojo.Product;
import com.hrbeu.O2O.Pojo.Shop;
import com.hrbeu.O2O.enums.ProductStateEnum;

import java.util.List;

public class ProductExecution {
    //操作状态
    private Integer state;
    //操作状态描述
    private String stateInfo;
    //操作数量
    private Integer count;
    //操作的shop
    private Product product;
    //查询店铺列表时使用
    private List<Product> productList;
    public ProductExecution(){}
    //失败的构造器
    public ProductExecution(ProductStateEnum productStateEnum){
        this.state = productStateEnum.getState();
        this.stateInfo = productStateEnum.getStateInfo();
    }
    //成功的构造器
    public ProductExecution(ProductStateEnum productStateEnum,Product product){
        this.state = productStateEnum.getState();
        this.stateInfo = productStateEnum.getStateInfo();
        this.product = product;
    }
    //成功的构造器2
    public ProductExecution(ProductStateEnum productStateEnum,List<Product> productList){
        this.state = productStateEnum.getState();
        this.stateInfo = productStateEnum.getStateInfo();
        this.productList = productList;
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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
