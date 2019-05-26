package com.example.temanhijrah;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void launchRegister(View view) {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

    public void launchLupaPassword(View view) {
        Intent intent = new Intent(this, forgotPassword.class);
        startActivity(intent);
    }

    public void launchBeranda(View view) {
        Intent intent = new Intent(this, Beranda.class);
        startActivity(intent);
    }
}