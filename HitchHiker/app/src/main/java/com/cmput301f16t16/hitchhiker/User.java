package com.cmput301f16t16.hitchhiker;

import io.searchbox.annotations.JestId;

import static java.sql.Types.NULL;

/**
 * Created by Leo Yoon on 13/10/2016.
 */
public class User {
    //Boolean userType;
    //Integer userID;

    @JestId
    private String id;

    private String username;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    public User(String username, String firstName, String lastName, String phoneNumber) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
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
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
