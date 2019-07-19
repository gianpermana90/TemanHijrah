package com.example.temanhijrah;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class forgotPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
    }

    public void sendRequest(View view) {

        TextView emailView = (TextView) findViewById(R.id.input_email);
        String email = emailView.getText().toString();

    /*    ApiUserInterface apiUserInterface = ApiUserClient.getClient(getResources().getString(R.string.url_api_main)).create(ApiUserInterface.class);
        Call<Result> resultCall = apiUserInterface.lupaPassword(email);
        resultCall.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Log.d("Data ", " respon" + response.raw().toString());
                if (response.code() == 200) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Berhasil mengirim permintaan", Toast.LENGTH_LONG);
                    toast.show();
      */
        launchForgotPasswordComplete();
        /*        } else if (response.code() == 404) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Email tidak ditmukan", Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Gagal mengirim", Toast.LENGTH_LONG);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Log.d("DataError ", "" + t.getMessage());
            }
        });*/

    }

    public void launchForgotPasswordComplete() {
        Intent intent = new Intent(this, ForgotPasswordComplete.class);
        startActivity(intent);
    }
}
