package com.hrbeu.O2O.Pojo_sup;

public class Result<T> {
    private Boolean success;
    private T data;
    private String errorMsg;
    private Integer errorCode;
    public Result(){}
    //成功的构造方法
    public Result(boolean success, T data){
        this.success = success;
        this.data = data;
    }
    //查询失败的构造方法
    public Result(boolean success, String errorMsg, Integer errorCode){
        this.success = success;
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;

    }
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }
}
