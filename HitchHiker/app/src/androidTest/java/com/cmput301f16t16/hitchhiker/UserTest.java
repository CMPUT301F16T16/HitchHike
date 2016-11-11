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


}
