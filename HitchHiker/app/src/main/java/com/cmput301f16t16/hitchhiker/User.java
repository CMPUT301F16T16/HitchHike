package com.cmput301f16t16.hitchhiker;

import static java.sql.Types.NULL;

/**
 * Created by Leo Yoon on 13/10/2016.
 */
public class User {
    private String userName;
    private String userPassword;
    private String userType;
    private String userFirstName;
    private String userLastName;
    private String userEmail;
    private String userPhoneNumber;

    public User(String userName) {
        this.userName = userName;
        this.userPassword = new String();
        this.userFirstName = new String();
        this.userLastName = new String();
        this.userEmail = new String();
        this.userPhoneNumber = new String();

    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return this.userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserType(String userType) {
        return this.userType;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserFirstName() {
        return this.userFirstName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserLastName() {
        return this.userLastName;
    }

    public void setUserPhoneNumber(String phoneNumber) {
        this.userPhoneNumber = phoneNumber;
    }

    public String getUserPhoneNumber() {
        return this.userPhoneNumber;
    }

    public void setUserEmail(String email) {
        this.userEmail = email;
    }

    public String getUserEmail() {
        return this.userEmail;
    }
}
