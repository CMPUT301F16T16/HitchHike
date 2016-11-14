package com.cmput301f16t16.hitchhiker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void GoToMainPage(View view){
        // TODO get user from Elastic Search
        EditText userNameText = (EditText) findViewById(R.id.userNameText);
        String userName = userNameText.getText().toString();


        ElasticsearchUserController.GetUserTask getUserTask = new ElasticsearchUserController.GetUserTask();
        getUserTask.execute(userName);

        try {
            user = getUserTask.get();
            Log.i("User Name : ", user.getUserName());
            Log.i("User Type: ", user.getUserType().toString());

            // TODO If the user is a rider, then go to the rider page
            if (user.getUserType() == 1) {

                Intent intent = new Intent(LoginActivity.this, RiderActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
                finish();
            }

            // Todo If the user is a driver, then go to the driver page
            else if (user.getUserType() == 2 || user.getUserType() == 3) {
                Intent intent = new Intent(LoginActivity.this, DriverActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
                finish();
            }
        }

        catch (Exception e) { // If the userName doesn't exist on Elasticsearch!
            Log.i("Error", "Failed to get the user out of the async object");
        }


    }

    public void GoToRegisterPage(View view){
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);

    }
}
