package com.cmput301f16t16.hitchhiker;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Jae-yeon on 10/13/2016.
 */
public class UserList implements Serializable{

    private static final long serialVersionUID = 12L;

    protected ArrayList<User> userList = null;

    protected transient ArrayList<UserListener> userListeners = null;

    public UserList(){
        userList = new ArrayList<User>();
        userListeners = new ArrayList<UserListener>();
    }

    private ArrayList<UserListener> getUserListeners() {
        if(userListeners == null) {
            userListeners = new ArrayList<UserListener>();
        }
        return userListeners;
    }

    public boolean hasUser(User user) {
        return userList.contains(user);
    }

    public void add(User user) {
        userList.add(user);
        notifyListeners();
    }

    public void delete(User user) {
        userList.remove(user);
        notifyListeners();
    }

    public Collection<User> getUsers() {
        return userList;
    }

    public void notifyListeners() {
        for (UserListener userListener : getUserListeners()) {
            userListener.update();
        }
    }

    public User getUser(int index) {
        return userList.get(index);
    }

    public void addUserListener(UserListener userListener) {
        getUserListeners().add(userListener);
    }

    public void removeUserListener(UserListener userListener) {
        getUserListeners().remove(userListener);
    }
}
