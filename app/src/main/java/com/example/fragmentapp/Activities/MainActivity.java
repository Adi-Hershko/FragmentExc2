package com.example.fragmentapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.fragmentapp.Fragments.MainPageFragment;
import com.example.fragmentapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void navigateToMainPageFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new MainPageFragment())
                .addToBackStack(null)  // Optional, depending on desired navigation behavior
                .commit();
    }


}