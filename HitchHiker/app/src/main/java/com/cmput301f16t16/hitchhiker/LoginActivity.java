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
            Intent intent = new Intent(LoginActivity.this, RiderActivity.class);
            startActivity(intent);
            finish();
        }
        
        catch (Exception e) {
            Log.i("Error", "Failed to get the user out of the async object");
        }
        // TODO If the user is a rider, then go to the rider page

        // Todo If the user is a driver, then go to the driver page

    }

    public void GoToRegisterPage(View view){

        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);

    }
}
