package com.cmput301f16t16.hitchhiker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CreateRequestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_request);
    }

    public void CreateRequest(View view){

        EditText pickUpText = (EditText) findViewById(R.id.pick_up_edittext);
        EditText dropOffText = (EditText) findViewById(R.id.drop_off_edittext);

        String pickUpString = pickUpText.getText().toString();
        String dropOffString = dropOffText.getText().toString();

        Request newRequest = new Request(pickUpString, dropOffString);
        ElasticsearchController.AddRequestsTask addRequestsTask = new ElasticsearchController.AddRequestsTask();
        addRequestsTask.execute(newRequest);

        finish();


    }
}
