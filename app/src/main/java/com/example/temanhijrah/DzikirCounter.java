package com.example.temanhijrah;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class DzikirCounter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dzikir_counter);

        addSpinner();
    }

    public void addSpinner(){
        Spinner spinner = (Spinner) findViewById(R.id.dzikir_list);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.dzikir_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}
