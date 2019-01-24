package com.example.tsaimengfu.cp103team2project.Management;

import java.io.Serializable;

public class User implements Serializable {
    private String id;
    private String userAccount;
    private String userName;
//    private Boolean priority;

    public User(String id, String userAccount, String userName) {
        this.id = id;
        this.userAccount = userAccount;
        this.userName = userName;
//        this.priority = priority;
    }
// 01
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

//    public Boolean getPriority() {
//        return priority;
//    }
//
//    public void setPriority(Boolean priority) {
//        this.priority = priority;
//    }
}
