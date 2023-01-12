package com.example.foodorder.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.foodorder.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                nextActivity();
            }
        }, 2000);
    }

    private void nextActivity() {
        FirebaseUser user =FirebaseAuth.getInstance().getCurrentUser();
        if(user==null){
            Intent intent =new Intent(SplashActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }
        else
        {
            Intent intent =new Intent(SplashActivity.this,MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
    }
}