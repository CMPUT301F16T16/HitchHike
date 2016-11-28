package com.cmput301f16t16.hitchhiker;

import android.util.Log;

import java.io.IOException;

/**
 * Created by Leo Yoon on 07/11/2016.
 */
public class UserListController {

    // Lazy Singleton
    private static UserList userList = null;

    /**
     * Gets user list.
     * @return the user list
     * If a userList isn't created yet it will catch error otherwise
     * it will grab the users from the userManager and save the userList
     */
    static public UserList getUserList() {
        if (userList == null) {
            try {
                userList = UserListManager.getUserManager().loadUserList();
                userList.addUserListener(new UserListener() {
                    @Override
                    public void update() {
                        saveUserList();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Couldn't deserialize UserList from UserListManager");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                throw new RuntimeException("Couldn't deserialize UserList from UserListManager");
            }
        }
        return userList;
    }

    /**
     * Save user list.
     * @see UserListManager
     */
    public static void saveUserList() {
        try {
            UserListManager.getUserManager().saveUserList(getUserList());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Couldn't deserialize UserList from UserListManager");
        }
    }

    /**
     * Add user into elasticsearch
     * @param newUser the new user
     * @see ElasticsearchUserController
     */
    public void addUser(User newUser){
        ElasticsearchUserController.AddUsersTask addUsersTask = new ElasticsearchUserController.AddUsersTask();
        addUsersTask.execute(newUser);
    }

    /**
     * Find user if it exists
     * @param userName the user name
     * @return the user
     * @see ElasticsearchUserController
     */
    public User findUser(String userName){
        User user = null;
        // Goes into elasticsearch to try and find if the username exists
        ElasticsearchUserController.GetUserTask getUserTask = new ElasticsearchUserController.GetUserTask();
        getUserTask.execute(userName);
        try {
            user = getUserTask.get();
        }
        catch(Exception e){
            Log.i("Error", "Failed to get the user out of the async object");
        }
        return user;
    }
}

