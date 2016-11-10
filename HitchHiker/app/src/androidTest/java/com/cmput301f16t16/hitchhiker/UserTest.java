package com.cmput301f16t16.hitchhiker;

import junit.framework.TestCase;

import static java.lang.Boolean.FALSE;

/**
 * Created by Leo Yoon on 13/10/2016.
 */

public class UserTest extends TestCase {
    public void testGetUserName() {
        String userName = "TestUser";
        User user = new User(userName);
        assertEquals("TestUser", user.getUserName());
    }

    public void testSetUserName(){
        String userName = "TestUser";
        User user = new User(userName);
        String userName2 = "TestUser2";
        user.setUserName(userName2);
        assertEquals("TestUser2", user.getUserName());
    }

    public void testGetUserPassword() {
        String userName = "TestUser";
        User user = new User(userName);
        String userPassword = "TestPassword";
        user.setUserPassword(userPassword);
        assertEquals("TestPassword",user.getUserPassword());
    }

    public void testSetUserPassword() {
        String userName = "TestUser";
        User user = new User(userName);
        String userPassword = "TestPassword";
        user.setUserPassword(userPassword);
        assertEquals("TestPassword", user.getUserPassword());
    }

    public void testSetUserType() {
        String userName = "TestUser";
        User user = new User(userName);
        Boolean Rider = FALSE;
        user.setUserType(Rider);
        assertEquals(Rider, user.getUserType());
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
        Integer phoneNumber = 1234567890;
        String userName = "testUser";
        User user = new User(userName);
        user.setUserPhoneNumber(phoneNumber);
    }

    public void testGetUserPhoneNumber() {
        Integer phoneNumber = 1234567890;
        String userName = "testUser";
        User user = new User(userName);
        user.setUserPhoneNumber(phoneNumber);

        Integer phoneNumber2 = 123456789;
        String userName2 = "testUser2";
        User user2 = new User(userName2);
        user2.setUserPhoneNumber(phoneNumber2);
        assertEquals(user2.getUserPhoneNumber(), user.getUserPhoneNumber());
    }

    public void testSetUserEmail() {
        String email = "testemail@email.com";
        String userName = "testUser";
        User user = new User(userName);
        user.setUserEmail(email);
        assertEquals(email, user.getUserEmail());

    }

}
