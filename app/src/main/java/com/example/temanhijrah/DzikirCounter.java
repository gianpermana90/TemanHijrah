package com.example.temanhijrah;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class DzikirCounter extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private int count = 0;
    private Spinner spinner;

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
        spinner = findViewById(R.id.dzikir_list);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.dzikir_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    public void count(View view) {
        this.count++;
        putValue();
    }

    public void resetCount(View view) {
        this.count = 0;
        putValue();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        TextView lafal, arti;
        switch (position) {
            case 0:
                lafal = (TextView) findViewById(R.id.arabic_text);
                lafal.setText(R.string.lafal_tasbih);
                arti = (TextView) findViewById(R.id.arti_dzikir);
                arti.setText(R.string.arti_tasbih);
                break;
            case 1:
                lafal = (TextView) findViewById(R.id.arabic_text);
                lafal.setText(R.string.lafal_hamdalah);
                arti = (TextView) findViewById(R.id.arti_dzikir);
                arti.setText(R.string.arti_hamdalah);
                break;
            case 2:
                lafal = (TextView) findViewById(R.id.arabic_text);
                lafal.setText(R.string.lafal_takbir);
                arti = (TextView) findViewById(R.id.arti_dzikir);
                arti.setText(R.string.arti_takbir);
                break;
            case 3:
                lafal = (TextView) findViewById(R.id.arabic_text);
                lafal.setText(R.string.lafal_tahlil);
                arti = (TextView) findViewById(R.id.arti_dzikir);
                arti.setText(R.string.arti_tahlil);
                break;
            default:
                break;
        }
        resetCount(view);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
