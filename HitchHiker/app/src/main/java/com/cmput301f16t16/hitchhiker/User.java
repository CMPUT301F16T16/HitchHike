package com.cmput301f16t16.hitchhiker;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

import io.searchbox.annotations.JestId;

import static java.sql.Types.NULL;

/**
 * Created by Leo Yoon on 13/10/2016.
 * Edited by Angus Abels & Willy Liao
 * Edited by Victoria Lee 10/11/2016.
 * We used our user class and put our code into http://www.parcelabler.com/ (accessed on 10/11/2016
 * this website for it to auto generate a parceled version of our code.
 */

public class User implements Serializable {

    private static long serialVersionUID = 44454L;
    @JestId
    protected String userName;
    protected Integer userType; // If userType = True, then user is both Rider & Driver. If False, the user is ONLY Rider.
    protected String userFirstName;
    protected String userLastName;
    protected String userEmail;
    protected Integer userPhoneNumber;
    protected String id;

    public User(String userName, String userFirstName, String userLastName, String userEmail, int userPhoneNumber, int userType) {
        this.userName = userName;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userEmail = userEmail;
        this.userPhoneNumber = userPhoneNumber;
        this.userType = userType;
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

    public Integer getUserType(){
        return userType;
    }

    public void setUserFirstName(String userFirstName){
        this.userFirstName = userFirstName;}


    public String getUserFirstName(){
        return userFirstName;

    }


    public void setUserLastName(String userLastName){
        this.userLastName = userLastName;}

    public String getUserLastName(){
        return userLastName;
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



