package com.cmput301f16t16.hitchhiker;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * The type Login activity.
 * <p> This is the View where the user can input his name and login.</p>
 */
public class LoginActivity extends AppCompatActivity {

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    /**
     * Go to main page.
     * <p> when called, the function will check if the user is a driver
     * or a rider and go to their respective activities.</p>
     * <p> Uses ElasticSearch to see if user exists.</p>
     *
     * @param view the view
     * @see DriverActivity
     * @see RiderActivity
     * @see ElasticsearchUserController
     */
    public void GoToMainPage(View view){

        // Get username the user has inputted.
        EditText userNameText = (EditText) findViewById(R.id.userNameText);
        String userName = userNameText.getText().toString();

        // Goes into elasticsearch to try and find if the username exists
        ElasticsearchUserController.GetUserTask getUserTask = new ElasticsearchUserController.GetUserTask();
        getUserTask.execute(userName);

        try {
            // return the user object in which the username corresponds to
            user = getUserTask.get();
            Log.i("User Name : ", user.getUserName());
            Log.i("User Type: ", user.getUserType().toString());

            // If the user is a rider, then go to the rider page
            if (user.getUserType() == 1) {

                Intent intent = new Intent(LoginActivity.this, RiderActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
                finish();
            }

            // If the user is a driver, then go to the driver page
            else if (user.getUserType() == 2 || user.getUserType() == 3) {
                Intent intent = new Intent(LoginActivity.this, ChooseUserModeActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
                finish();
            }
        }

        // If username does not exist, show text: The username you have entered does not exist
        catch (Exception e) {
            TextView userDoesNotExistText = (TextView) findViewById(R.id.userDoesNotExistText);
            userDoesNotExistText.setText("The username you have entered does not exist.");
            userDoesNotExistText.setTextColor(Color.RED);
            Log.i("Error", "Failed to get the user out of the async object");
        }

    }

    /**
     * Go to register page.
     * <p> when called, will bring the user to the RegisterActivity</p>
     *
     * @param view the view
     * @see RegisterActivity
     */
    public void GoToRegisterPage(View view){
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);

    }

}
