package com.cmput301f16t16.hitchhiker;

import junit.framework.TestCase;

import static java.lang.Boolean.FALSE;

/**
 * Created by Leo Yoon on 13/10/2016.
 */

public class UserTest extends TestCase {
    public void testGetUserName() {
        String userName = "TestUser";
        User user = new User(userName, "","","","", 0, "");
        assertEquals("TestUser", user.getUserName());
    }

    public void testSetUserName(){
        String userName = "TestUser";
        User user = new User(userName, "", "", "", "", 65, "");
        String userName2 = "TestUser2";
        user.setUserName(userName2);
        assertEquals("TestUser2", user.getUserName());
    }

    public void testupdateEmail(){
        String email = "yahoo.com";
        User user = new User("", "", "", "", "", 65, "");
        assertEquals(email, user.getUserEmail());

        String email2 = "lala@gmail";
        user.setUserEmail(email2);
        assertEquals(email2, user.getUserEmail());

    }

    /**
     * US 1.09.01 As a rider, I should see a description of the driver's vehicle
     */
    public void testGetCarDescription() {
        User user = new User("","","","","",65,"");
        String carDescription = "Fiat Multipla Gray";
        user.setCarDescription(carDescription);
        assertEquals(carDescription, user.getCarDescription());
    }



}
