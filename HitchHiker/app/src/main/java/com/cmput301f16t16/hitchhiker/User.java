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
 * <p/>
 * Learned to change String to Integer on: http://stackoverflow.com/questions/5585779/converting-string-to-int-in-java
 */
public class User implements Serializable {

    private static long serialVersionUID = 44454L;

    /**
     * The Id.
     */
    @JestId
    protected String id;

    /**
     * The User name.
     */
    protected String userName;
    /**
     * The User type.
     */
    protected Integer userType;
    /**
     * The User first name.
     */
    protected String userFirstName;
    /**
     * The User last name.
     */
    protected String userLastName;
    /**
     * The User email.
     */
    protected String userEmail;
    /**
     * The User phone number.
     */
    protected Integer userPhoneNumber;

    /**
     * Instantiates a new User.
     *
     * @param userName        the user name
     * @param userFirstName   the user first name
     * @param userLastName    the user last name
     * @param userEmail       the user email
     * @param userPhoneNumber the user phone number
     * @param userType        the user type
     */
    public User(String userName, String userFirstName, String userLastName, String userEmail, int userPhoneNumber, int userType) {
        this.userName = userName;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userEmail = userEmail;
        this.userPhoneNumber = userPhoneNumber;
        this.userType = userType;
    }

    /**
     * Gets user name.
     *
     * @return the user name
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * Sets user name.
     *
     * @param userName the user name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Sets user type.
     *
     * @param userType the user type
     */
    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    /**
     * Get user type integer.
     *
     * @return the integer
     */
    public Integer getUserType(){
        return userType;
    }

    /**
     * Set user first name.
     *
     * @param userFirstName the user first name
     */
    public void setUserFirstName(String userFirstName){
        this.userFirstName = userFirstName;}

    /**
     * Get user first name string.
     *
     * @return the string
     */
    public String getUserFirstName(){
        return userFirstName;
    }

    /**
     * Set user last name.
     *
     * @param userLastName the user last name
     */
    public void setUserLastName(String userLastName){
        this.userLastName = userLastName;}

    /**
     * Get user last name string.
     *
     * @return the string
     */
    public String getUserLastName(){
        return userLastName;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets user phone number.
     *
     * @param userPhoneNumber the user phone number
     */
    public void setUserPhoneNumber(int userPhoneNumber) {

        this.userPhoneNumber = userPhoneNumber;
    }

    /**
     * Gets user phone number.
     *
     * @return the user phone number
     */
    public int getUserPhoneNumber() {
        return this.userPhoneNumber;
    }

    /**
     * Sets user email.
     *
     * @param email the email
     */
    public void setUserEmail(String email) {
        this.userEmail = email;
    }

    /**
     * Gets user email.
     *
     * @return the user email
     */
    public String getUserEmail() {
        return this.userEmail;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }








}



