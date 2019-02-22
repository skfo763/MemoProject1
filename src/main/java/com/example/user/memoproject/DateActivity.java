package com.example.user.memoproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.example.user.memoproject.Models.DateModels;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DateActivity extends AppCompatActivity {

    private EditText datename;
    private EditText location;
    private EditText startdate;
    private EditText enddate;
    private Spinner spinner;
    private ImageButton imageButton;
    private String year;
    private String month;
    private String day;
    private String getspinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);

        year = getIntent().getStringExtra("year");
        month = getIntent().getStringExtra("month");
        day = getIntent().getStringExtra("day");
        datename = findViewById(R.id.dateactivity_eventname);
        location = findViewById(R.id.dateactivity_location);
        startdate = findViewById(R.id.dateactivity_startdate);
        enddate = findViewById(R.id.dateactivity_enddate);
        spinner = findViewById(R.id.dateactivity_spinner);
        imageButton = findViewById(R.id.dateactivity_save);

        startdate.setText(day + " / " + month + " / " + year);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                getspinner = (String) adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            DateModels.Dates dates = new DateModels.Dates();

            @Override
            public void onClick(View view) {
                dates.startyear = year;
                dates.startmonth = month;
                dates.startday = day;
                dates.enddate = enddate.getText().toString();
                dates.eventname = datename.getText().toString();
                dates.location = location.getText().toString();
                dates.fatality = getspinner;

                FirebaseDatabase.getInstance().getReference().child("datelist").push().setValue(dates).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Intent intent = new Intent(DateActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        });

    }
}
