package com.example.temanhijrah;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.temanhijrah.userModel.ApiUserClient;
import com.example.temanhijrah.userModel.ApiUserInterface;
import com.example.temanhijrah.userModel.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UbahPassword extends AppCompatActivity {

    private String id;
    private String name;
    private String accessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_password);

        Bundle bundle = getIntent().getExtras();
        id = bundle.getString("id");
        name = bundle.getString("name");
        accessToken = bundle.getString("accessToken");
    }


    public void changePassword(View view) {

        EditText oldPassView = findViewById(R.id.input_old_password);
        EditText newPassView = findViewById(R.id.input_new_password);
        EditText newPassRepeatView = findViewById(R.id.input_repeat_new_password);
        String oldPass = oldPassView.getText().toString();
        String newPass = newPassView.getText().toString();
        String newPassRepeat = newPassRepeatView.getText().toString();

        if (!newPass.equals(newPassRepeat)) {
            TextView errorMessage = findViewById(R.id.error_password);
            errorMessage.setVisibility(View.VISIBLE);
            return;
        }

        ApiUserInterface apiUserInterface = ApiUserClient.getClient(getResources().getString(R.string.url_api_main)).create(ApiUserInterface.class);
        Call<Result> user = apiUserInterface.gantiPassword(id, newPass, accessToken);
        user.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Log.d("Data ", " respon" + response.toString());
                if (response.code() == 200) {
                    Toast.makeText(getApplicationContext(), "Sukses mengganti password", Toast.LENGTH_SHORT).show();
                    launchPengaturan();
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Log.d("DataError ", "" + t.getMessage());
            }
        });


    }

    private void launchPengaturan() {
        Intent intent = new Intent(this, Pengaturan.class);
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        bundle.putString("name", name);
        bundle.putString("accessToken", accessToken);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
