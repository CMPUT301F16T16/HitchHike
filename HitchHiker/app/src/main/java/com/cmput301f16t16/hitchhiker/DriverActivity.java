package com.cmput301f16t16.hitchhiker;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.Serializable;

/**
 * The type Driver activity.
 */
public class DriverActivity extends AppCompatActivity {
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);

        Bundle bundle = getIntent().getExtras();
        user = (User) getIntent().getSerializableExtra("user");
    }

    /**
     * Browse request action.
     *
     * @param view the view
     */
    public void BrowseRequestAction(View view) {
        Intent intent = new Intent(DriverActivity.this, BrowseRequestActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    /**
     * User profile page action.
     *
     * @param view the view
     */
    public void UserProfilePageAction(View view) {
        Intent intent = new Intent(DriverActivity.this, UserProfileActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }


    // Code taken from http://stackoverflow.com/questions/6413700/android-proper-way-to-use-onbackpressed-with-toast
    // on Nov 24, 2016
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Exit App?")
                .setMessage("Exit Hitch Hiker?")
                .setNegativeButton("No", null)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        finish();
                    }
                }).create().show();


    }
}


