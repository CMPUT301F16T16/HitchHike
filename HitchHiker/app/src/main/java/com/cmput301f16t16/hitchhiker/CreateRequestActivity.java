package com.cmput301f16t16.hitchhiker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CreateRequestActivity extends AppCompatActivity {

    private User user;
    private String pickUp;
    private String dropOff;
    private double price;
    private String requestCreator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_request);

        Bundle bundle = getIntent().getExtras();
        user = (User) getIntent().getSerializableExtra("user");
    }



    public void CreateRequestAction(View view){

        EditText pickUpText = (EditText) findViewById(R.id.pick_up_edittext);
        EditText dropOffText = (EditText) findViewById(R.id.drop_off_edittext);
        EditText priceText = (EditText) findViewById(R.id.suggested_fare);

        pickUp = pickUpText.getText().toString();
        dropOff = dropOffText.getText().toString();
        price = Double.parseDouble(priceText.getText().toString());
        requestCreator = user.getUserName();

        Request newRequest = new Request(requestCreator, pickUp, dropOff, price);
        RequestListController rlc = new RequestListController();
        rlc.addRequest(newRequest);
        Toast.makeText(this, "Thanks for the request!", Toast.LENGTH_SHORT).show();


//        ElasticsearchRequestController.AddRequestsTask addRequestsTask = new ElasticsearchRequestController.AddRequestsTask();
//        addRequestsTask.execute(newRequest);
        finish();


    }
}
