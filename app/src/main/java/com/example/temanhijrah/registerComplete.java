package com.example.temanhijrah;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class registerComplete extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_complete);
    }

    public void launchLogin(View view) {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}
