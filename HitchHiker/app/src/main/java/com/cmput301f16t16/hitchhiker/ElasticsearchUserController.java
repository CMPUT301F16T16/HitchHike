package com.cmput301f16t16.hitchhiker;

import android.os.AsyncTask;
import android.util.Log;

import com.searchly.jestdroid.DroidClientConfig;
import com.searchly.jestdroid.JestClientFactory;
import com.searchly.jestdroid.JestDroidClient;

import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD
=======
import io.searchbox.core.Delete;
>>>>>>> 1b56c156b5b8a7a5e64f53b7b252e6830816ea1d
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;

/**
<<<<<<< HEAD
 * Created by Leo Yoon on 04/11/2016.
 */

public class ElasticsearchUserController {
    private static JestDroidClient client;

=======
 * Created by willyliao on 2016-11-09.
 */

public class ElasticsearchUserController{
    private static JestDroidClient client;

    // TODO we need a function that gets requests!
>>>>>>> 1b56c156b5b8a7a5e64f53b7b252e6830816ea1d
    public static class GetUsersTask extends AsyncTask<String, Void, ArrayList<User>> {
        @Override
        protected ArrayList<User> doInBackground(String... search_parameters) {
            verifySettings();

<<<<<<< HEAD
            ArrayList<User> userList = new ArrayList<User>();

            // Assume that search_parameters[0] is the only term we are interested in using
            Search search = new Search.Builder(search_parameters[0])
                    .addIndex("testing")
                    .addType("user")
                    .build();
            try {
                SearchResult result = client.execute(search);
                if (result.isSucceeded()) {
                    List<User> foundUsers = result.getSourceAsObjectList(User.class);
                    userList.addAll(foundUsers);

                }
                else {
                    Log.i("Error", "The search query failed to find any users that matched.");
                }
            }
            catch (Exception e) {
                Log.i("Error", "Something went wrong when we tried to communicated with the elasticsearch server!");
            }

            return userList;
        }
    }

    public static class AddUserTask extends AsyncTask<User, Void, Void> {

        @Override
        protected Void doInBackground(User... userList) {
            verifySettings();

            for (User user : userList) {
                Index index = new Index.Builder(user).index("testing").type("user").build();

                try {
                    // Double check if its Username that we are going to set here
                    DocumentResult result = client.execute(index);
                    if (result.isSucceeded()) {
                        user.setUserName(result.getId());
                    } else {
                        Log.i("Error", "Elastic search was not able to add the user.");
                    }
                } catch (Exception e) {
                    Log.i("Error", "We failed to add a user to elastic search!");
=======
            ArrayList<User> users = new ArrayList<User>();

            // Assumption: Only the first search_parameter[0] is used.

            String search_string = "{\"from\": 0, \"size\": 10000}";


            Search search = new Search.Builder(search_string).addIndex("3h$1k40puf8@ta!$0wpd4n3x2y!@1s").addType("user").build();

            try{
                SearchResult result = client.execute(search);
                if (result.isSucceeded()){
                    List<User> foundUsers = result.getSourceAsObjectList(User.class);
                    users.addAll(foundUsers);
                }
                else{
                    Log.i("Error", "The search executed but it didn't work.");
                }
            }
            catch (Exception e){
                Log.i("Error", "Executing the get requests method failed");
            }
            return users;
        }
    }


    // TODO we need a function which adds a request!
    public static class AddUsersTask extends AsyncTask<User, Void, Void> {

        @Override
        protected Void doInBackground(User... users) {
            verifySettings();

            for (User user: users) {
                Index index = new Index.Builder(user).index("3h$1k40puf8@ta!$0wpd4n3x2y!@1s").type("user").build();

                try {
                    DocumentResult result = client.execute(index);
                    if (result.isSucceeded()) {
                        user.setId(result.getId());
                    }
                    else {
                        Log.i("Error", "Elastic search was not able to add the request.");
                    }
                }
                catch (Exception e) {
                    Log.i("Uhoh", "We failed to add a request to elastic search!");
>>>>>>> 1b56c156b5b8a7a5e64f53b7b252e6830816ea1d
                    e.printStackTrace();
                }
            }

            return null;
        }
    }

<<<<<<< HEAD
        private static void verifySettings() {
            // if the client hasn't been initialized then we should make it!
            if (client == null) {
                DroidClientConfig.Builder builder = new DroidClientConfig.Builder("http://cmput301.softwareprocess.es:8080");
                DroidClientConfig config = builder.build();

                JestClientFactory factory = new JestClientFactory();
                factory.setDroidClientConfig(config);
                client = (JestDroidClient) factory.getObject();
            }
        }
=======

    private static void verifySettings() {
        // if the client hasn't been initialized then we should make it!
        if (client == null) {
            DroidClientConfig.Builder builder = new DroidClientConfig.Builder("http://cmput301.softwareprocess.es:8080");
            DroidClientConfig config = builder.build();

            JestClientFactory factory = new JestClientFactory();
            factory.setDroidClientConfig(config);
            client = (JestDroidClient) factory.getObject();
        }
    }
>>>>>>> 1b56c156b5b8a7a5e64f53b7b252e6830816ea1d

}
