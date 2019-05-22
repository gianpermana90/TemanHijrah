package com.example.temanhijrah;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class forgotPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
    }

    public void launchForgotPasswordComplete(View view) {
        Intent intent = new Intent(this, ForgotPasswordComplete.class);
        startActivity(intent);
    }
}
