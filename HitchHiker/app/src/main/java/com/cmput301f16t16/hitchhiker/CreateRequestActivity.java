package com.cmput301f16t16.hitchhiker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CreateRequestActivity extends AppCompatActivity {

    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_request);

//        //Request manager has to be initialized to know about the created request
//        RequestListManager.initRequestListManager(this.getApplicationContext());

//        Bundle bundle = getIntent().getExtras();
//        User user = bundle.getParcelable("user");
        user = (User) getIntent().getSerializableExtra("user");

        //RequestList Manager has to be initialized to know about the new created request
//        RequestListManager.initRequestListManager(this.getApplicationContext());

    }

    public void CreateRequest(View view){

        Toast.makeText(this, "Creating Request", Toast.LENGTH_SHORT).show();
        RequestListController rlc = new RequestListController();

        EditText pickUpText = (EditText) findViewById(R.id.pick_up_edittext);
        EditText dropOffText = (EditText) findViewById(R.id.drop_off_edittext);
        //Fare estimate shouldnt be editable it should pop-up once the
        //dropOff and PickUp location are specified
        EditText estimate = (EditText) findViewById(R.id.suggested_fare);

        /**
         * Spei convert the dropOff Location and pickUp Location to
         * doubles long and lat and return them so they can be added to
         * the request object
         */
        String pickUp = pickUpText.getText().toString();
        String dropOff = dropOffText.getText().toString();
        //Havnt figured out how the Fare works yet (Spei's job)
        Double esitmate = Double.parseDouble(estimate.getText().toString());
        String username = user.getUserName().toString();
//        Fare fare = new Fare();
        Request newRequest = new Request(username, pickUp, dropOff, esitmate);
        ElasticsearchRequestController.AddRequestsTask addRequestsTask = new ElasticsearchRequestController.AddRequestsTask();
        addRequestsTask.execute(newRequest);
//        rlc.addRequest(newRequest);

        finish();


    }
}
