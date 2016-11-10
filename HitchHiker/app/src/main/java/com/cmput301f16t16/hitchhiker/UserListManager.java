package com.cmput301f16t16.hitchhiker;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by Leo Yoon on 07/11/2016.
 */
public class UserListManager {
    static final String prefile = "UserList";
    static final String userListKey = "userList";
    Context context;

    // Creates a userListManager with all the context of that page
    static private UserListManager userListManager = null;
    public static void initUserManager(Context context) {
        if (userListManager == null) {
            if (context == null) {
                throw new RuntimeException("Missing context file for UserListManager");
            }
            userListManager = new UserListManager(context);
        }
    }

    public static UserListManager getUserManager() {
        if (userListManager == null) {
            throw new RuntimeException("Did not initialize UserListManager");
        }
        return userListManager;
    }

    public UserListManager(Context context) {
        this.context = context;
    }

    // Loads the content/context of the habits to the controller to be saved or loaded
    // in Base64 Objects
    public UserList loadUserList() throws IOException, ClassNotFoundException {
        SharedPreferences settings = context.getSharedPreferences(prefile, Context.MODE_PRIVATE);
        String userListData = settings.getString(userListKey, "");
        if (userListData.equals("")) {
            return new UserList();
        }
        else {
            return userListFromString(userListData);
        }
    }

    // Decodes userList data to string
    public static UserList userListFromString(String userListData) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bi = new ByteArrayInputStream(Base64.decode(userListData, Base64.DEFAULT));
        ObjectInputStream oi = new ObjectInputStream(bi);
        return (UserList) oi.readObject();
    }

    // Encodes the User data to string
    static public String userListToString(UserList userList) throws IOException {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream oo = new ObjectOutputStream(bo);
        oo.writeObject(userList);
        oo.close();
        byte userBytes [] = bo.toByteArray();
        return Base64.encodeToString(userBytes, Base64.DEFAULT);
    }

    public void saveUserList(UserList userList) throws IOException {
        SharedPreferences settings = context.getSharedPreferences(prefile, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(userListKey, userListToString(userList));
        editor.commit();
    }


}
