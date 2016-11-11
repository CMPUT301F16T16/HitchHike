package com.cmput301f16t16.hitchhiker;


import junit.framework.TestCase;

/**
 * Created by Jae-yeon on 10/13/2016.
 */

public class UserListTest extends TestCase {
    public void testAdd(){
        UserList userList = new UserList();
        User user = new User("TestUser");
        userList.addUser(user);
        assertTrue(userList.hasUser(user));
    }
    public void testDelete(){
        UserList userList = new UserList();
        User user = new User("TestUser");
        userList.addUser(user);
        userList.delete(user);
        assertFalse(userList.hasUser(user));

    }
    public void testGetUser(){
        UserList userList = new UserList();
        User user = new User("TestUser");
        userList.addUser(user);
        User returnedUser = userList.getUser(0);
        assertEquals(returnedUser.getUserName(), user.getUserName());

    }
    public void testHasUser(){

    }
}
