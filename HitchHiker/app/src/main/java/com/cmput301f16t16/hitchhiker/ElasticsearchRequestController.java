package com.cmput301f16t16.hitchhiker;

import android.os.AsyncTask;
import android.util.Log;

import com.searchly.jestdroid.DroidClientConfig;
import com.searchly.jestdroid.JestClientFactory;
import com.searchly.jestdroid.JestDroidClient;

import java.util.ArrayList;
import java.util.List;

import io.searchbox.core.Delete;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;

/**
 * Created by willyliao on 2016-11-04.
 */
public class ElasticsearchRequestController {
    private static JestDroidClient client;

    /**
     * The type Get requests task.
     */
// TODO we need a function that gets requests!
    public static class GetRequestsTask extends AsyncTask<String, Void, ArrayList<Request>> {
        private String userName;

        @Override
        protected ArrayList<Request> doInBackground(String... search_parameters) {
            verifySettings();
            ArrayList<Request> requests = new ArrayList<Request>();
            String search_string = "{\"from\": 0, \"size\": 10000, \"query\": {\"match\": {\"Rider\": \""+userName+"\"}}}";
            Search search = new Search.Builder(search_string).addIndex("3h$1k40puf8@ta!$0wpd4n3x2y!@1s").addType("request").build();
            try{
                SearchResult result = client.execute(search);
                if (result.isSucceeded()){
                    List<Request> foundRequests = result.getSourceAsObjectList(Request.class);
                    requests.addAll(foundRequests);
                } else{
                    Log.i("Error", "The search executed but it didn't work.");
                }
            } catch (Exception e){
                Log.i("Error", "Executing the get requests method failed");
            }
            return requests;
        }
        public void setUserName(String userName){
            this.userName = userName;
        }
    }

    public static class GetPendingTask extends AsyncTask<String, Void, ArrayList<Request>> {
        private String pending;

        @Override
        protected ArrayList<Request> doInBackground(String... search_parameters) {
            verifySettings();

            ArrayList<Request> requests = new ArrayList<Request>();

            // Assumption: Only the first search_parameter[0] is used.

            String search_string = "{\"from\": 0, \"size\": 10000, \"query\": {\"match\": {\"requestStatus\": \"" + pending + "\"}}}";

            Search search = new Search.Builder(search_string).addIndex("3h$1k40puf8@ta!$0wpd4n3x2y!@1s").addType("request").build();

            try {
                SearchResult result = client.execute(search);
                if (result.isSucceeded()) {
                    List<Request> foundRequests = result.getSourceAsObjectList(Request.class);
                    requests.addAll(foundRequests);
                } else {
                    Log.i("Error", "The search executed but it didn't work.");
                }
            } catch (Exception e) {
                Log.i("Error", "Executing the get requests method failed");
            }
            return requests;
        }
        public void setPending(String pending){
            this.pending = pending;
        }
    }



    public static class GetDriverTask extends AsyncTask<String, Void, ArrayList<Request>> {
        private String status;
        @Override
        protected ArrayList<Request> doInBackground(String... search_parameters) {
            verifySettings();
            ArrayList<Request> requests = new ArrayList<Request>();
            String search_string = "{\"from\": 0, \"size\": 10000, \"query\": {\"match\": {\"requestStatus\": \"" + status + "\"}}}";
            Search search = new Search.Builder(search_string).addIndex("3h$1k40puf8@ta!$0wpd4n3x2y!@1s").addType("request").build();
            try {
                SearchResult result = client.execute(search);
                if (result.isSucceeded()) {
                    List<Request> foundRequests = result.getSourceAsObjectList(Request.class);
                    requests.addAll(foundRequests);
                } else {
                    Log.i("Error", "The search executed but it didn't work.");
                }
            } catch (Exception e) {
                Log.i("Error", "Executing the get requests method failed");
            }
            return requests;
        }
        public void setStatus(String status){
            this.status = status;
        }
    }






//    public static class GetAcceptTask extends AsyncTask<String, Void, ArrayList<Request>> {
//        private String accepted;
//        private String driver;
//
//        @Override
//        protected ArrayList<Request> doInBackground(String... search_parameters) {
//            verifySettings();
//
//            ArrayList<Request> requests = new ArrayList<Request>();
//
//            // Assumption: Only the first search_parameter[0] is used.
//
//            //String search_string = "{\"from\": 0, \"size\": 10000, \"query\": {\"match\": {\"requestStatus\": \"" + accepted + "\"} {}}";
//
//            String search_string =
//                    "{ \"from\" : 0, \"size\" : 500,\n" +
//                            "  \"query\": {\n" +
//                            "    \"bool\": {\n" +
//                            "      \"must\": { \"match\": { \"Driver\": \"" + driver + "\" }},\n" +
//                            "      \"should\": [\n" +
//                            "              { \"match\": { \"requestStatus\": \"" + accepted + "\" }},\n" +
//                            "      ],\n" +
//                            "      \"minimum_should_match\": \"1\"\n" +
//                            "    }\n" +
//                            "  }\n" +
//                            "}";
//
//            Search search = new Search.Builder(search_string).addIndex("3h$1k40puf8@ta!$0wpd4n3x2y!@1s").addType("request").build();
//
//            try {
//                SearchResult result = client.execute(search);
//                if (result.isSucceeded()) {
//                    List<Request> foundRequests = result.getSourceAsObjectList(Request.class);
//                    requests.addAll(foundRequests);
//                } else {
//                    Log.i("Error", "The search executed but it didn't work.");
//                }
//            } catch (Exception e) {
//                Log.i("Error", "Executing the get requests method failed");
//            }
//            return requests;
//        }
//        public void setAccepted(String accepted){
//            this.accepted = accepted;
//        }
//
//        public void setDriver(String driver) {
//            this.driver = driver;
//        }
//
//
//    }






//    public static class GetDriverRequestTask extends AsyncTask<String, Void, ArrayList<Request>> {
//        private String userName;
//        private String pending;
//        private String accepted;
//
//        @Override
//        protected ArrayList<Request> doInBackground(String... search_parameters) {
//            verifySettings();
//
//            ArrayList<Request> pendings = new ArrayList<Request>();
//
//            //String search_string = "{\n" + "\"query\": {\n" + "\"match\" : {\n" + "\"" + userName + "\" : \n" + "\"" + requestStatus + "\"\n" + "}\n" + "}\n" + "}";
//            //String search_string = "{\"from\": 0, \"size\": 10000, \"query\": " + "{\"match\": {\"Rider\": \""+userName+"\"} {" + "\"Status\": \""+requestStatus+"\"}}}";
//            //String search_string = "{\"from\": 0, \"size\": 10000, \"query\": {\"match\": {\"Rider\": \""+userName+"\"} {\"requestStatus\": \""+requestStatus+"\"}}}";
//
//
//            String search_string =
//                    "{ \"from\" : 0, \"size\" : 500,\n" +
//                            "  \"query\": {\n" +
//                            "    \"bool\": {\n" +
//                            //"      \"must\": { \"match\": { \"Rider\": \"" + userName + "\" }},\n" +
//                            "      \"should\": [\n" +
//                            "              { \"match\": { \"requestStatus\": \"" + pending + "\" }},\n" +
//                            "              { \"match\": { \"requestStatus\": \"" + accepted + "\" }}\n" +
//                            "      ],\n" +
//                            "      \"minimum_should_match\": \"1\"\n" +
//                            //"    }\n" +
//                            "  }\n" +
//                            "}";
//            Search search = new Search.Builder(search_string).addIndex("3h$1k40puf8@ta!$0wpd4n3x2y!@1s").addType("request").build();
//
//            try{
//                SearchResult result = client.execute(search);
//                if (result.isSucceeded()){
//                    List<Request> foundRequests = result.getSourceAsObjectList(Request.class);
//                    pendings.addAll(foundRequests);
//                }
//                else{
//                    Log.i("Error", "The search executed but it didn't work.");
//                }
//            }
//            catch (Exception e){
//                Log.i("Error", "Executing the get requests method failed");
//            }
//            return pendings;
//        }
//
//        /**
//         * Set user name.
//         *
//         * @param userName the user name
//         */
//        public void setUserName(String userName){ this.userName = userName;}
//        public void setPending(String pending) { this.pending = pending;}
//        public void setAccepted(String accepted) { this.accepted = accepted; }
//
//
//    }

    /**
     * The type Get browsing requests task.
     */
// TODO we need a function which populates browsing_request!
    public static class GetBrowsingRequestsTask extends AsyncTask<String, Void, ArrayList<Request>> {

        @Override
        protected ArrayList<Request> doInBackground(String... search_parameters) {
            verifySettings();

            ArrayList<Request> requests = new ArrayList<Request>();

            // Assumption: Only the first search_parameter[0] is used.

            String search_string = "{\"from\": 0, \"size\": 10000}";

            Search search = new Search.Builder(search_string).addIndex("3h$1k40puf8@ta!$0wpd4n3x2y!@1s").addType("request").build();

            try{
                SearchResult result = client.execute(search);
                if (result.isSucceeded()){
                    List<Request> foundRequests = result.getSourceAsObjectList(Request.class);
                    requests.addAll(foundRequests);
                }
                else{
                    Log.i("Error", "The search executed but it didn't work.");
                }
            }
            catch (Exception e){
                Log.i("Error", "Executing the get requests method failed");
            }
            return requests;
        }
    }

    /**
     * The type Add requests task.
     */
// TODO we need a function which adds a request!
    public static class AddRequestsTask extends AsyncTask<Request, Void, Void> {

        @Override
        protected Void doInBackground(Request... requests) {
            verifySettings();

            for (Request request: requests) {
                Index index = new Index.Builder(request).index("3h$1k40puf8@ta!$0wpd4n3x2y!@1s").type("request").build();

                try {
                    DocumentResult result = client.execute(index);
                    if (result.isSucceeded()) {
                        request.setId(result.getId());
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


    /**
     * The type Delete request task.
     */
// TODO we need a function which adds a request!
    public static class DeleteRequestTask extends AsyncTask<Request, Void, Void> {
        /**
         * The Item id.
         */
        String itemId;

        @Override
        protected Void doInBackground(Request... requests) {
            verifySettings();

            try {
                client.execute(new Delete.Builder(itemId).index("3h$1k40puf8@ta!$0wpd4n3x2y!@1s").type("request").build());
            }
            catch (Exception e) {
                Log.i("Uhoh", "We failed to delete a request from elastic search!");
                e.printStackTrace();
            }

            return null;
        }

        /**
         * Set item id.
         *
         * @param itemId the item id
         */
        public void setItemId(String itemId){
            this.itemId = itemId;
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