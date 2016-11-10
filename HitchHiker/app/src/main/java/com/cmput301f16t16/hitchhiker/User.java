package com.cmput301f16t16.hitchhiker;

import static java.sql.Types.NULL;

/**
 * Created by Leo Yoon on 13/10/2016.
 */
public class User {
    private String userName;
    private String userPassword;
    private Boolean userType; // If userType = True, then user is both Rider & Driver. If False, the user is ONLY Rider.
    private String userFirstName;
    private String userLastName;
    private String userEmail;
    private int userPhoneNumber;

    public User(String userName) {
        this.userName = userName;
        this.userPassword = new String();
        this.userFirstName = new String();
        this.userLastName = new String();
        this.userEmail = new String();
        this.userPhoneNumber = new Integer(NULL);
        this.userType = getUserType();
        this.userPhoneNumber = getUserPhoneNumber();
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

    public void setUserType(Boolean userType) {
        this.userType = userType;
    }

    public Boolean getUserType() {
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
