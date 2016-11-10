package com.cmput301f16t16.hitchhiker;

import android.os.AsyncTask;
import android.util.Log;

import com.searchly.jestdroid.DroidClientConfig;
import com.searchly.jestdroid.JestClientFactory;
import com.searchly.jestdroid.JestDroidClient;

import java.util.ArrayList;
import java.util.List;

import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;

/**
 * Created by Leo Yoon on 04/11/2016.
 */

public class ElasticsearchUserController {
    private static JestDroidClient client;

public static class AddUserTask extends AsyncTask<User, Void, Void> {

    @Override
    protected Void doInBackground(User... userList) {
        verifySettings();

        for (User user : userList) {
            Index index = new Index.Builder(user).index("3h$1k40puf8@ta!$0wpd4n3x2y!@1s").type("user").build();

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
//                config.isDiscoveryEnabled();

            JestClientFactory factory = new JestClientFactory();
            factory.setDroidClientConfig(config);
            client = (JestDroidClient) factory.getObject();
        }
    }

}
