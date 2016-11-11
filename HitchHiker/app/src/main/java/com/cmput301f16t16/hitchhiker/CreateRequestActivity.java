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

        Bundle bundle = getIntent().getExtras();
        user = bundle.getParcelable("user");
    }

    public void CreateRequest(View view){
        EditText pickUpText = (EditText) findViewById(R.id.pick_up_edittext);
        EditText dropOffText = (EditText) findViewById(R.id.drop_off_edittext);
        //Fare estimate shouldnt be editable it should pop-up once the
        //dropOff and PickUp location are specified
        //EditText estimate = (EditText) findViewById(R.id.suggested_fare);

        /*
         * Spei convert the dropOff Location and pickUp Location to
         * doubles long and lat and return them so they can be added to
         * the request object */

        String pickUp = pickUpText.getText().toString();
        String dropOff = dropOffText.getText().toString();
        //String price = priceText.getText().toString();

        //Havent figured out how the Fare works yet (Spei's job)
        //Integer estimate = Integer.parseInt(estimate.getText().toString());

        Request newRequest = new Request(/*user.getUserName(),*/ pickUp, dropOff); //, estimate);
        ElasticsearchRequestController.AddRequestsTask addRequestsTask = new ElasticsearchRequestController.AddRequestsTask();
        addRequestsTask.execute(newRequest);
        finish();


        RequestListController rlc = new RequestListController();
        rlc.addRequest(newRequest);
        Toast.makeText(this, "Added a request", Toast.LENGTH_SHORT).show();


    }
}
