package com.example.user.memoproject;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.memoproject.fragments.datefragment;
import com.example.user.memoproject.fragments.datelist_fragment;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class DateShowActivity extends AppCompatActivity {

    private String eventname, location, startdate, enddate, fatality;
    private TextView name, place, start, end, spinner;
    private ImageButton close, revise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_show);

        eventname = getIntent().getStringExtra("eventname");
        location = getIntent().getStringExtra("location");
        startdate = getIntent().getStringExtra("startdate");
        enddate = getIntent().getStringExtra("enddate");
        fatality = getIntent().getStringExtra("fatality");

        name = findViewById(R.id.customactivity_eventname);
        place = findViewById(R.id.customactivity_location);
        start = findViewById(R.id.customactivity_startdate);
        end = findViewById(R.id.customactivity_enddate);
        spinner = findViewById(R.id.customactivity_fatality);
        close = findViewById(R.id.toolbar5_goback);
        revise = findViewById(R.id.toolbar5_revisememo);

        name.setText(eventname);
        place.setText(location);
        start.setText(startdate);
        end.setText(enddate);
        spinner.setText(fatality);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DateShowActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        revise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DateShowActivity.this, "준비중입니다", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
