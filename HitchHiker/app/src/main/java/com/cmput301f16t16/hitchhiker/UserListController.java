package com.cmput301f16t16.hitchhiker;

import java.io.IOException;

///**
// * Created by Leo Yoon on 07/11/2016.
// */
//
//public class UserListController {
//
//    // Lazy Singleton
//    private static UserList userList = null;
//
//    // If a userList isn't created yet it will catch error otherwise
//    // it will grab the users from the userManager and save the userList
//    static public UserList getUserList() {
//        if (userList == null) {
//            try {
//                userList = UserListManager.getUserManager().loadUserList();
//                userList.addUserListener(new UserListener() {
//                    @Override
//                    public void update() {
//                        saveUserList();
//                    }
//                });
//            } catch (IOException e) {
//                e.printStackTrace();
//                throw new RuntimeException("Couldn't deserialize UserList from UserListManager");
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//                throw new RuntimeException("Couldn't deserialize UserList from UserListManager");
//            }
//        }
//        return userList;
//    }
//
//    public static void saveUserList() {
//        try {
//            UserListManager.getUserManager().saveUserList(getUserList());
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new RuntimeException("Couldn't deserialize UserList from UserListManager");
//        }
//    }
//}

