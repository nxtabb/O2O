package com.hrbeu.O2O.Pojo;

import java.util.Date;
//本地账号
public class LocalAuth {
    //本地账号id
    private Long LocalAuthId;
    //用户名
    private String username;
    //密码
    private String password;
    //用户创建时间
    private Date createTime;
    //用户修改时间
    private Date lastEditTime;
    //关联的用户
    private PersonInfo personInfo;

    public LocalAuth(){}

    public LocalAuth(Long localAuthId, String username, String password, Date createTime, Date lastEditTime, PersonInfo personInfo) {
        LocalAuthId = localAuthId;
        this.username = username;
        this.password = password;
        this.createTime = createTime;
        this.lastEditTime = lastEditTime;
        this.personInfo = personInfo;
    }

    public Long getLocalAuthId() {
        return LocalAuthId;
    }

    public void setLocalAuthId(Long localAuthId) {
        LocalAuthId = localAuthId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public PersonInfo getPersonInfo() {
        return personInfo;
    }

    public void setPersonInfo(PersonInfo personInfo) {
        this.personInfo = personInfo;
    }
}
