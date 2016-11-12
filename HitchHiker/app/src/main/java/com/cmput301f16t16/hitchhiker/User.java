package com.cmput301f16t16.hitchhiker;

import android.os.Parcel;
import android.os.Parcelable;

import io.searchbox.annotations.JestId;

import static java.sql.Types.NULL;

/**
 * Created by Leo Yoon on 13/10/
 * Edited by Angus Abels & Willy Liao
 * Edited by Victoria Lee 10/11/2016.
 * We used our user class and put our code into http://www.parcelabler.com/ (accessed on 10/11/2016
 * this website for it to auto generate a parceled version of our code.
 */

public class User implements Parcelable {
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

    protected User(Parcel in) {
        userName = in.readString();
        userType = in.readByte() == 0x00 ? null : in.readInt();
        userFirstName = in.readString();
        userLastName = in.readString();
        userEmail = in.readString();
        userPhoneNumber = in.readInt();
        id = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userName);
        if (userType == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(userType);
        }
        dest.writeString(userFirstName);
        dest.writeString(userLastName);
        dest.writeString(userEmail);
        dest.writeInt(userPhoneNumber);
        dest.writeString(id);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}


