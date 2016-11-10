package com.cmput301f16t16.hitchhiker;

import io.searchbox.annotations.JestId;

import static java.sql.Types.NULL;

/**
 * Created by Leo Yoon on 13/10/2016.
 * Edited by Angus Abels & Willy Liao
 */
public class User {
    @JestId
    protected String userName;
    protected Integer userType; // If userType = True, then user is both Rider & Driver. If False, the user is ONLY Rider.
    protected String userFirstName;
    protected String userLastName;
    protected String userEmail;
    protected int userPhoneNumber;
    protected String id;

    public User(String userName, String userFirstName, String userLastName, String userEmail, int userPhoneNumber, int userType) {
        this.userName = getUserName();
        this.userFirstName = getUserFirstName();
        this.userLastName = getUserLastName();
        this.userEmail = getUserEmail();
        this.userPhoneNumber = getUserPhoneNumber();
        this.userType = getUserType();
        this.userPhoneNumber = getUserPhoneNumber();
    }



    public String getUserName() {

        return this.userName;
    }

    public void setUserName(String userName) {

        this.userName = userName;
    }


    public void setUserType(Integer userType) {
        this.userType = userType;
    }


    public String getUserFirstName(){
        return this.userFirstName;
    }
//
    public String getUserLastName(){
        return this.userLastName;
    }

    public Integer getUserType(){
        return userType;
    }
    public String getId() {
        return id;
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

    public void setId(String id) {
        this.id = id;
    }
}

