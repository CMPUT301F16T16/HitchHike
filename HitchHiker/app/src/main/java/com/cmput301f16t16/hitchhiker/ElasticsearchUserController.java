package com.cmput301f16t16.hitchhiker;

import android.os.AsyncTask;
import android.util.Log;

import com.searchly.jestdroid.DroidClientConfig;
import com.searchly.jestdroid.JestClientFactory;
import com.searchly.jestdroid.JestDroidClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.searchbox.core.Delete;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Get;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;

/**
 * Created by willyliao on 2016-11-09.
 */

public class ElasticsearchUserController{
    private static JestDroidClient client;

    // TODO we need a function that gets requests!
    public static class GetUsersTask extends AsyncTask<String, Void, ArrayList<User>> {
        @Override
        protected ArrayList<User> doInBackground(String... search_parameters) {
            verifySettings();
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

    // TODO we need a function which gets a single user (for login)
    public static class GetUserTask extends AsyncTask<String, Void, User> {
        @Override
        protected User doInBackground(String... search_parameters) {
            verifySettings();
            String search_string = "{\"from\": 0, \"size\": 1, \"query\": {\"match\": {\"username\": \"" + search_parameters[0] + "\"}}}";

            Search search = new Search.Builder(search_string).addIndex("3h$1k40puf8@ta!$0wpd4n3x2y!@1s").addType("user").build();

            User user = null;

            try {
                SearchResult result = client.execute(search);
                if (result.isSucceeded()) {
                    user = result.getSourceAsObject(User.class);
                } else {
                    Log.i("Error", "We could not find the desired user in Elasticsearch");
                    return null;
                }
            } catch (IOException e) {
                e.printStackTrace();
                Log.i("Error", "We could not contact Elasticsearch");
            }
            return user;
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
                    e.printStackTrace();
                }
            }

            return null;
        }
    }

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

}
