package com.cmput301f16t16.hitchhiker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by V1CTORIA2LEE on 2016-11-12.
 */
public class BrowseRequestActivity extends AppCompatActivity{

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_request);

        Bundle bundle = getIntent().getExtras();
        user = (User) getIntent().getSerializableExtra("user");

    }

}
