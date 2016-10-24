package com.cmput301f16t16.hitchhiker;

import junit.framework.TestCase;

/**
 * Created by Leo Yoon on 13/10/2016.
 */

public class UserTest extends TestCase {
    public void testGetUserName() {
        String userName = "TestUser";
        User user = new User(userName);
        assertEquals("TestUser", user.getName());
    }

    public void testSetUserName(){
        String userName = "TestUser";
        User user = new User(userName);
        String userName2 = "TestUser2";
        user.setName(userName2);
        assertEquals("TestUser", user.getName());
    }

    public void testGetUserPassword() {
        String userName = "TestUser";
        User user = new User(userName);
        String userPassword = "TestPassword";
        user.setPassword(userPassword);
        assertEquals("TestPassword",user.getPassword());
    }

    public void testSetUserPassword() {
        String userName = "TestUser";
        User user = new User(userName);
        String userPassword = "TestPassword";
        user.setPassword(userPassword);
        assertEquals("TestPassword", user.getPassword());

    }

    public void testSetUserType() {
        String userName = "TestUser";
        User user = new User(userName);
        String userType = "Rider";
        user.setUserType(userType);
        assertEquals("Rider", user.getUserType(userType));
    }

    public void testSetUserFirstName() {
        String userName = "testUser";
        User user = new User(userName);
        String firstName = "Angus";
        user.setUserFirstName(firstName);

    }

    public void testGetUserFirstName() {
        String userName = "testUser";
        User user = new User(userName);
        String firstName = "Angus";
        user.setUserFirstName(firstName);
        assertEquals(firstName,user.getUserFirstName());
    }
    public void testSetUserLastName() {
        String userName = "testUser";
        User user = new User(userName);
        String lastName = "Abels";
        user.setUserLastName(lastName);
        assertEquals(lastName,user.getUserLastName());
    }

    public void testGetUserLastName() {
        String userName = "testUser";
        User user = new User(userName);
        String lastName = "Abels";
        user.setUserLastName(lastName);
        assertEquals(lastName,user.getUserLastName());
    }
    public void testSetUserPhoneNumber() {
        Integer phoneNumber = 12345789;
        String userName = "testUser";
        User user = new User(userName);
        user.setUserPhoneNumber(phoneNumber);

    }
    public void testGetUserPhoneNumber() {
        Integer phoneNumber = 123456789;
        String userName = "testUser";
        User user = new User(userName);
        user.setUserPhoneNumber(phoneNumber);
        assertEquals(phoneNumber, user.getUserPhoneNumber());
    }

}
