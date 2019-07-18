package com.example.temanhijrah;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class DzikirCounter extends AppCompatActivity {
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dzikir_counter);

        addSpinner();

        putValue();
    }

    private void putValue() {
        TextView countTotal = findViewById(R.id.count_total);
        countTotal.setText(String.valueOf(this.count));
        TextView counter = findViewById(R.id.count);
        TextView countMax = findViewById(R.id.count_max);
        counter.setText(String.valueOf(this.count % Integer.parseInt(countMax.getText().toString())));
        TextView countPutaran = findViewById(R.id.count_putaran);
        countPutaran.setText(String.valueOf(this.count / Integer.parseInt(countMax.getText().toString())));
    }

    public void addSpinner(){
        Spinner spinner = findViewById(R.id.dzikir_list);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.dzikir_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void count(View view) {
        this.count++;
        putValue();
    }

    public void resetCount(View view) {
        this.count = 0;
        putValue();
    }
}
