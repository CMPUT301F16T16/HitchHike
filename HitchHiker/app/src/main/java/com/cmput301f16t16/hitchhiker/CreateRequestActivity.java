package com.cmput301f16t16.hitchhiker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * The type Create request activity.
 */
public class CreateRequestActivity extends AppCompatActivity {
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_request);
        user = (User) getIntent().getSerializableExtra("user");
    }

    /**
     * Create request with the user inputs.
     *
     * @param view the view
     */
    public void CreateRequest(View view){
        RequestListController rc = new RequestListController();
        Toast.makeText(this, "Creating Request", Toast.LENGTH_SHORT).show();

        EditText pickUpText = (EditText) findViewById(R.id.pick_up_edittext);
        EditText dropOffText = (EditText) findViewById(R.id.drop_off_edittext);
        EditText suggestedFareText = (EditText) findViewById(R.id.suggested_fare);

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
        String suggestedFare = suggestedFareText.getText().toString();

        if (pickUp.equals("") || dropOff.equals("") || suggestedFare.equals("")){

        }
        else{
            Double price = Double.parseDouble(suggestedFare);
            String userName = user.getUserName();
            Request newRequest = new Request(userName, pickUp, dropOff, price);
            String result = rc.addRequest(newRequest);

            if (result == null){
                Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
                this.finish();
            }
            else {
                Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
