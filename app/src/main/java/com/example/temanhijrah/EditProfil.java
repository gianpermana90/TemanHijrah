package com.example.temanhijrah;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class EditProfil extends AppCompatActivity {
    private String id;
    private String name;
    private String accessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profil);

        Bundle bundle = getIntent().getExtras();
        id = bundle.getString("id");
        name = bundle.getString("name");
        accessToken = bundle.getString("accessToken");
    }

    private void launchPengaturan(View view) {
        Intent intent = new Intent(this, Pengaturan.class);
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        bundle.putString("name", name);
        bundle.putString("accessToken", accessToken);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
