package com.cmput301f16t16.hitchhiker;

/**
 * Created by Leo Yoon on 13/10/2016.
 */
public class User {
    private String userName;
    private String userPassword;
    private String userType;

    public User(String userName) {
        this.userName = userName;
        this.userPassword = new String();
    }

    public String getName() {
        return this.userName;
    }

    public void setName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return this.userPassword;
    }

    public void setPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserType(String userType) {
        return this.userType;
    }
}
