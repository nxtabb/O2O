package com.hrbeu.O2O.enums;

public enum ProductCategoryStateEnum {

    SUCCESS(1,"创建成功"),
    INNER_ERROR(-1001,"内部系统错误"),
    EMPTY_LIST(-1002,"添加数小于1");


    private int state;
    private String stateInfo;
    private ProductCategoryStateEnum(int state, String stateInfo){
        this.state = state;
        this.stateInfo = stateInfo;
    }
    public static ProductCategoryStateEnum stateOf(int state){
        for(ProductCategoryStateEnum categoryStateEnum:values()){
            if(categoryStateEnum.getState()==state){
                return categoryStateEnum;
            }
        }
        return null;
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
}
