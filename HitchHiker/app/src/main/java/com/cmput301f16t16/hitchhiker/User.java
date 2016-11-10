package com.cmput301f16t16.hitchhiker;

import static java.sql.Types.NULL;

/**
 * Created by Leo Yoon on 13/10/2016.
 */
public class User {
    protected String userName;
    protected String userPassword;
    protected int userType;
    protected String userFirstName;
    protected String userLastName;
    protected String userEmail;
    protected int userPhoneNumber;

    public User(String userName) {
        this.userName = userName;
//        this.userPassword = new String();
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userEmail = userEmail;
//        this.userPhoneNumber = new Integer(NULL);
        this.userType = getUserType();
        this.userPhoneNumber = getUserPhoneNumber();
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }



    public void setUserType(int userType) {
        this.userType = userType;
    }

    public int getUserType() {
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

    public void setUserPhoneNumber(int userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public int getUserPhoneNumber() {
        return this.userPhoneNumber;
    }

    public void setUserEmail(String email) {
        this.userEmail = email;
    }

    public String getUserEmail() {
        return this.userEmail;
    }
}
