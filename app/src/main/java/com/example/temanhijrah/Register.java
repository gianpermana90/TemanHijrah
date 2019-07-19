package com.example.temanhijrah;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Objects;

public class Register extends AppCompatActivity {

    private TextView displayDate;
    private DatePickerDialog.OnDateSetListener onDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        displayDate = findViewById(R.id.date_picker);

        displayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(Register.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, onDateSetListener, day, month, year);
                Objects.requireNonNull(datePickerDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date = dayOfMonth + "/" + (month + 1) + "/" + year;
                displayDate.setText(date);
            }
        };
    }

    public void register(View view) {
        TextView errorFieldMessage = findViewById(R.id.error_fiald_empty);
        errorFieldMessage.setVisibility(View.INVISIBLE);
        TextView errorPassMessage = findViewById(R.id.error_password);
        errorPassMessage.setVisibility(View.INVISIBLE);

        EditText editText = findViewById(R.id.input_email);
        String username = editText.getText().toString();
        //Log.i("FORM", username);
        editText = findViewById(R.id.input_nama);
        String firstname = editText.getText().toString();
        //Log.i("FORM", firstname);
        String lastname = "";
        RadioGroup radioGroup = findViewById(R.id.radio_gender);
        Integer idSelected = radioGroup.getCheckedRadioButtonId();
        String gender = "";
        Log.i("GENDER", String.valueOf(idSelected));
        if (idSelected != -1) {
            RadioButton radioSelected = findViewById(idSelected);
            gender = String.valueOf(radioSelected.getText());
        }
        editText = findViewById(R.id.input_password);
        String password = editText.getText().toString();
        //Log.i("FORM", password);
        editText = findViewById(R.id.input_confirm_password);
        String confirmPassword = editText.getText().toString();
        //Log.i("FORM", confirmPassword);
        Button datePicker = findViewById(R.id.date_picker);
        String birthday = datePicker.getText().toString();
        //Log.i("FORM", birthday);

        if (isAnyEmpty(username, password, firstname, gender, birthday)) {
            errorFieldMessage.setVisibility(View.VISIBLE);
            return;
        }
        if (!password.equals(confirmPassword)) {
            errorPassMessage.setVisibility(View.VISIBLE);
            return;
        }

        /*ApiUserInterface apiUserInterface = ApiUserClient.getClient(getResources().getString(R.string.url_api_main)).create(ApiUserInterface.class);
        Call<User> userCall = apiUserInterface.register(username, firstname, lastname, birthday, gender, username, "+628xx", password);
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d("Data ", " respon" + response.raw().toString());
                if (response.code() == 200) {*/
                    launchRegisterComplete();
/*                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Error occured", Toast.LENGTH_LONG);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("DataError ", "" + t.getMessage());
            }
        });*/
    }

    private boolean isAnyEmpty(String username, String password, String firstname, String gender, String birthday) {
        CheckBox agreement = findViewById(R.id.terms);

        if (agreement.isChecked()) {
            return (username.length() == 0 || password.length() == 0 || firstname.length() == 0 || gender.length() == 0 || birthday.length() == 0);
        }
        return true;
    }

    public void launchRegisterComplete() {
        Intent intent = new Intent(this, registerComplete.class);
        startActivity(intent);
    }
}
