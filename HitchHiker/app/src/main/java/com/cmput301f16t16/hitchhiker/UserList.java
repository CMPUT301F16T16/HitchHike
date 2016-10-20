package com.cmput301f16t16.hitchhiker;

import java.util.ArrayList;

/**
 * Created by Jae-yeon on 10/13/2016.
 */
public class UserList {
    private ArrayList<User> userList = new ArrayList<User>();

    public boolean hasUser(User user) {
        return userList.contains(user);
    }

    public void add(User user) {
        userList.add(user);
    }

    public void delete(User user) {
        userList.remove(user);
    }

    public User getUser(int index) {
        return userList.get(index);
    }
}
