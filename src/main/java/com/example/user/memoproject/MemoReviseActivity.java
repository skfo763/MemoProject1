package com.example.user.memoproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.user.memoproject.Models.MemoModels;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Date;

public class MemoReviseActivity extends AppCompatActivity {

    private String titletext;
    private String time;
    private String memotext;
    private String memoUid;
    private EditText title;
    private EditText context;
    private Button revise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_revise);

        titletext = getIntent().getStringExtra("title");
        memotext = getIntent().getStringExtra("memo");
        memoUid = getIntent().getStringExtra("memoUid");
        time = DateFormat.getDateTimeInstance().format(new Date());
        title = findViewById(R.id.memoreviceactivity_textview_title);
        context = findViewById(R.id.memoreviceactivity_textview_memo);
        title.setText(titletext);
        context.setText(memotext);

        revise = findViewById(R.id.memoreviceactivity_button_getmemo);
        revise.setOnClickListener(new View.OnClickListener() {

            MemoModels.Memos memos = new MemoModels.Memos();

            @Override
            public void onClick(View view) {
                memos.title = title.getText().toString();
                memos.time = time;
                memos.memo = context.getText().toString();

                FirebaseDatabase.getInstance().getReference().child("memolist").child(memoUid).setValue(memos).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Intent intent = new Intent(MemoReviseActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        });
    }
}
