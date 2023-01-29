package com.example.fixingmaterial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.FrameLayout;

public class profileActivity extends AppCompatActivity {

    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        frameLayout = findViewById(R.id.fragmentLayout);


        switch (profileFragment.chooseButton) {

            case 1:
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragmentLayout, new myProfile())
                        .commit();
                break;
            case 2:
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragmentLayout, new myOrders())
                        .commit();
                break;


        }
    }
}