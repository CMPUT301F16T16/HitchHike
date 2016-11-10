package com.cmput301f16t16.hitchhiker;

import io.searchbox.annotations.JestId;

import static java.sql.Types.NULL;

/**
 * Created by Leo Yoon on 13/10/2016.
 */
public class User {
<<<<<<< HEAD
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
=======
    //Boolean userType;
    //Integer userID;

    @JestId
    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String userEmail;
    private Integer userType;

    public User(String username, String firstName, String lastName, String phoneNumber, String userEmail, Integer userType) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.userEmail = userEmail;
        this.userType = userType;
    }

    public String getUsername(){
        return this.username;
    }

    public String getFirstName(){
        return this.firstName;
    }

    public String getLastName(){
        return this.lastName;
    }

    public String getPhoneNumber(){
        return this.phoneNumber;
>>>>>>> 1b56c156b5b8a7a5e64f53b7b252e6830816ea1d
    }

    public String getUserEmail(){
        return this.userEmail;
    }

    public Integer getUserType(){
        return userType;
    }


    public String getId() {
        return id;
    }

<<<<<<< HEAD
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
=======
    public void setId(String id) {
        this.id = id;
    }


>>>>>>> 1b56c156b5b8a7a5e64f53b7b252e6830816ea1d
}
