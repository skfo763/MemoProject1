package com.example.user.memoproject;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.user.memoproject.Models.MemoModels;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Date;

public class MemoActivity extends AppCompatActivity {

    private EditText title;
    private EditText getmemo;
    private Button memobutton;
    private String time;
    private ImageButton goback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);

        title = findViewById(R.id.memoactivity_textview_title);
        getmemo = findViewById(R.id.memoactivity_textview_memo);
        memobutton = findViewById(R.id.memoactivity_button_getmemo);
        time = DateFormat.getDateTimeInstance().format(new Date());
        goback = findViewById(R.id.toolbar1_goback);



        memobutton.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                MemoModels.Memos memos = new MemoModels.Memos();
                memos.title = title.getText().toString();
                memos.time = time;
                memos.memo = getmemo.getText().toString();

                FirebaseDatabase.getInstance().getReference().child("memolist").push().setValue(memos).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Intent intent = new Intent(MemoActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        });

        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MemoActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
