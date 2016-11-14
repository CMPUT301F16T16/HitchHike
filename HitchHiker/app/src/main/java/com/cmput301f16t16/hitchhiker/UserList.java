package com.cmput301f16t16.hitchhiker;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Jae-yeon on 10/13/2016.
 */
public class UserList implements Serializable{

    private static final long serialVersionUID = 12L;

    /**
     * The User list.
     */
    protected ArrayList<User> userList = null;

    /**
     * The User listeners.
     */
    protected transient ArrayList<UserListener> userListeners = null;

    /**
     * Instantiates a new User list.
     */
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

    /**
     * Has user boolean.
     *
     * @param user the user
     * @return the boolean
     */
    public boolean hasUser(User user) {
        return userList.contains(user);
    }


    /**
     * Delete.
     *
     * @param user the user
     */
    public void delete(User user) {
        userList.remove(user);
        notifyListeners();
    }

    /**
     * Gets users.
     *
     * @return the users
     */
    public Collection<User> getUsers() {
        return userList;
    }

    /**
     * Notify listeners.
     */
    public void notifyListeners() {
        for (UserListener userListener : getUserListeners()) {
            userListener.update();
        }
    }

    /**
     * Gets user.
     *
     * @param index the index
     * @return the user
     */
    public User getUser(int index) {
        return userList.get(index);
    }

    /**
     * Add user listener.
     *
     * @param userListener the user listener
     */
    public void addUserListener(UserListener userListener) {
        getUserListeners().add(userListener);
    }

    /**
     * Remove user listener.
     *
     * @param userListener the user listener
     */
    public void removeUserListener(UserListener userListener) {
        getUserListeners().remove(userListener);
    }

    /**
     * Add user.
     *
     * @param user the user
     */
    public void addUser(User user) {
        userList.add(user);
        notifyListeners();
    }
}
