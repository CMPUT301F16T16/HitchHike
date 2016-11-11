package com.cmput301f16t16.hitchhiker;

import android.test.AndroidTestCase;

import android.content.Context;
import android.test.AndroidTestCase;

import junit.framework.TestCase;

import android.test.AndroidTestCase;


import java.io.IOException;

import static java.security.AccessController.getContext;
import static junit.framework.Assert.assertTrue;

/**
 * Created by Leo Yoon on 07/11/2016.
 */

public class UserListManagerTest extends AndroidTestCase {

    public void testUserToString() {
        UserList userList = new UserList();
        User user = new User("testUser","Angus", "Abels", "email@hotmaotmail.com", 1233123, 2);
        userList.addUser(user);
        try {
            String userName = UserListManager.userListToString(userList);
            assertTrue("String length greater than zero", userName.length()>0);
            UserList userList2 = UserListManager.userListFromString(userName);
            assertTrue("userList2 does not contain test habit", userList2.hasUser(user));
        } catch (IOException e) {
            assertTrue("IOException thrown"+e, false);
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            assertTrue("Class not found Exception"+e, false);
        }
    }

    public void testUserListManager() {
        try {
            UserList userList = new UserList();
            String userName = "testUser";
            User user = new User("jfhkshfdk","Angus", "Abels", "email@hotmaotmail.com", 1233123, 2);
            userList.addUser(user);
            UserListManager userListManager = new UserListManager(getContext());
            userListManager.saveUserList(userList);
            UserList userList2 = userListManager.loadUserList();
            assertTrue("Test User is in user list 1", userList.hasUser(user));
            assertTrue("Test User is in user list 2", userList2.hasUser(user));
        } catch (IOException e) {
            e.printStackTrace();
            assertTrue("IOException Thrown"+e.toString(),false);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            assertTrue("Classes not found Exception"+e.toString(), false);
        }
    }
}
