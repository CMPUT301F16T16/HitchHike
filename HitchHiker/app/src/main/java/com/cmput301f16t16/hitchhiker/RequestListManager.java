package com.cmput301f16t16.hitchhiker;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by Angus on 11/11/2016.
 */
public class RequestListManager {
    static final String prefile = "RequestList";
    static final String hlKey = "requestList";
    Context context;


    //creates a requestListManager with all the context of that page
    static private RequestListManager requestListManager = null;

    public RequestListManager(Context context) {
        this.context = context;
    }

    public static void initRequestListManager(Context context) {
        if (requestListManager == null) {
            if (context == null) {
                throw new RuntimeException("missing context for RequestListManager");
            }
            requestListManager = new RequestListManager(context);
        }
    }

    public static RequestListManager getRequestManager() {
        if (requestListManager == null) {
            throw new RuntimeException("Did not initialize RequestListManager");
        }
        return requestListManager;
    }

    public RequestList loadRequestList() throws IOException, ClassNotFoundException {
        SharedPreferences settings = context.getSharedPreferences(prefile, Context.MODE_PRIVATE);
        String requestListData = settings.getString(hlKey, "");
        if (requestListData.equals("")) {
            return new RequestList();
        }
        else {
            return requestListFromString(requestListData);
        }
    }

    //decodes requestList data to string
    static public RequestList requestListFromString(String requestListData) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bi = new ByteArrayInputStream(Base64.decode(requestListData, Base64.DEFAULT));
        ObjectInputStream oi = new ObjectInputStream(bi);
        return (RequestList) oi.readObject();

    }


    //encodes the request data back to string
    static public String requestListToString(RequestList rl) throws IOException {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream oo = new ObjectOutputStream(bo);
        oo.writeObject(rl);
        oo.close();
        byte requestBytes [] = bo.toByteArray();
        return Base64.encodeToString(requestBytes, Base64.DEFAULT);
    }

    public void saveRequestList(RequestList rl) throws IOException {
        SharedPreferences settings = context.getSharedPreferences(prefile, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(hlKey, requestListToString(rl));
        editor.commit();
    }
}
