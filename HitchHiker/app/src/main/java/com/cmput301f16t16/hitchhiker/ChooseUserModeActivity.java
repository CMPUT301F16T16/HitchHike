package com.cmput301f16t16.hitchhiker;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.RadioButton;

/**
 * Created by V1CTORIA2LEE on 2016-11-26.
 */
public class ChooseUserModeActivity extends Activity {

    private User user;
    private RadioButton rider;
    private RadioButton driver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_user_mode);

        Bundle bundle = getIntent().getExtras();
        user = (User) getIntent().getSerializableExtra("user");

        rider = (RadioButton) findViewById(R.id.riderRadioButton);
        driver = (RadioButton) findViewById(R.id.driverRadioButton);
    }

    public void enterModeAction(View view) {
        if (rider.isChecked()) {
            goToRider();
        }
        else if (driver.isChecked()){
            goToDriver();
        }
    }

    public void goToRider() {
        rider.setChecked(false);
        Intent intent = new Intent(ChooseUserModeActivity.this, RiderActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    public void goToDriver() {
        driver.setChecked(false);
        Intent intent = new Intent(ChooseUserModeActivity.this, DriverActivity.class);
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