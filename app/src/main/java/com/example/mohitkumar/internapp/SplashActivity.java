package com.example.mohitkumar.internapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mohitkumar.internapp.Activities.HomeScreen;
import com.example.mohitkumar.internapp.Activities.MainActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread myThread=new Thread(){
            @Override
            public void run() {
                try {
                    sleep(2000);
                   // String a = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("OTP","COOL");
                  //  if(a.equals("COOL")){
                    SharedPreferences sharedPref = getSharedPreferences("data",MODE_PRIVATE);
                    int number = sharedPref.getInt("isLogged", 0);
                    if(number == 0) {
                        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                        finish();}
//                        SharedPreferences.Editor prefEditor = sharedPref.edit();
//                        prefEditor.putInt("isLogged",1);
//                        prefEditor.commit();
                    else {
                        Intent intent=new Intent(SplashActivity.this,HomeScreen.class);
                        startActivity(intent);
                        finish();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();

    }
}
