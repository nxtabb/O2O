package com.hrbeu.O2O.Pojo_sup;

import com.hrbeu.O2O.Pojo.Shop;
import com.hrbeu.O2O.enums.ShopStateEnum;
import org.apache.ibatis.plugin.Intercepts;

import java.util.List;

public class ShopExecution{
    //操作状态
    private int state;
    //操作状态描述
    private String stateInfo;
    //操作数量
    private Integer count;
    //操作的shop
    private Shop shop;
    //查询店铺列表时使用
    private List<Shop> shopList;

    public ShopExecution(){

    }
    //店铺操作失败后的构造器
    public ShopExecution(ShopStateEnum shopStateEnum){
        this.state = shopStateEnum.getState();
        this.stateInfo = shopStateEnum.getStateInfo();
    }
    //店铺操作成功后的构造器
    public ShopExecution(ShopStateEnum shopStateEnum,Shop shop){
        this.state = shopStateEnum.getState();
        this.stateInfo = shopStateEnum.getStateInfo();
        this.shop = shop;

    }
    //店铺操作成功后的构造器
    public ShopExecution(ShopStateEnum shopStateEnum,List<Shop> shopList){
        this.state = shopStateEnum.getState();
        this.stateInfo = shopStateEnum.getStateInfo();
        this.shopList = shopList;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
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

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public List<Shop> getShopList() {
        return shopList;
    }

    public void setShopList(List<Shop> shopList) {
        this.shopList = shopList;
    }
}
