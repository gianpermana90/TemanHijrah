package com.example.temanhijrah;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.temanhijrah.userModel.ApiUserClient;

public class Login extends AppCompatActivity {

    private ApiUserClient userClient;

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

    public void launchBeranda(/*String id, String name, String accessToken*/) {

        Intent intent = new Intent(this, Beranda.class);
        /*Bundle bundle = new Bundle();
        bundle.putString("id", id);
        bundle.putString("name", name);
        bundle.putString("accessToken", accessToken);
        intent.putExtras(bundle);
        FragmentMain fragmentMain = new FragmentMain();
        fragmentMain.setArguments(bundle);*/

        startActivity(intent);
    }

    public void login(View view) {
        EditText editText = findViewById(R.id.input_email);
        String username = editText.getText().toString();

        editText = findViewById(R.id.input_password);
        String password = editText.getText().toString();

        if (username.length() == 0 || password.length() == 0) {
            if (username.length() == 0) {
                TextView requiredEmail = findViewById(R.id.error_email_empty);
                requiredEmail.setVisibility(View.VISIBLE);
            } else {
                TextView requiredEmail = findViewById(R.id.error_email_empty);
                requiredEmail.setVisibility(View.INVISIBLE);
            }
            if (password.length() == 0) {
                TextView requiredPass = findViewById(R.id.error_pass_empty);
                requiredPass.setVisibility(View.VISIBLE);
            } else {
                TextView requiredPass = findViewById(R.id.error_pass_empty);
                requiredPass.setVisibility(View.INVISIBLE);
            }
            return;
        } else {/*
            ApiUserInterface apiUserInterface = ApiUserClient.getClient(getResources().getString(R.string.url_api_main)).create(ApiUserInterface.class);
            final Call<User> u = apiUserInterface.login(username, password);
            u.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    Log.d("Data ", " respon" + response.raw().toString());
                    User user = response.body();
                    if (response.code() == 200) {
                        Result result = user.getResult();
                        String id = result.getId();
                        String name = result.getFirstName() + " " + result.getLastName();
                        String accessToken = result.getAccessToken();
                   */
            launchBeranda(/*id, name, accessToken*/);
                 /*   } else if (response.code() / 100 == 4) {
                        TextView error = (TextView) findViewById(R.id.not_found);
                        error.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Log.d("DataError ", "" + t.getMessage());
                }
            });*/
            /*Call<Result> user = apiUserInterface.login(username,password);
            user.enqueue(new Callback<Result>() {
                @Override
                public void onResponse(Call<Result> call, Response<Result> response) {
                    Log.d("Data ", " respon" + response.raw().toString());
                    if (response.code() == 200) {
                        Result result = response.body().;
                        String id = result.getId();
                        Log.i("id", result.toString());
                        String name = (result != null ? result.getFirstName() + " " : null) + result.getLastName();
                        String accessToken = result.getAccessToken();

                        launchBeranda(id, name, accessToken);
                    }else if (response.code() == 404){
                        TextView error = (TextView) findViewById(R.id.not_found);
                        error.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onFailure(Call<Result> call, Throwable t) {
                    Log.d("DataError ", "" + t.getMessage());
                }
            });*/
        }
    }
}
