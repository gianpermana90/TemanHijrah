package com.example.temanhijrah;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Pengaturan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengaturan);
    }

    public void LaunchEditProfile(View view) {
        Intent intent = new Intent(this, EditProfil.class);
        startActivity(intent);
    }

    public void launchUbahPassword(View view) {
        Intent intent = new Intent(this, UbahPassword.class);
        startActivity(intent);
    }

    public void launchAbout(View view) {
        Intent intent = new Intent(this, About.class);
        startActivity(intent);
    }
}
