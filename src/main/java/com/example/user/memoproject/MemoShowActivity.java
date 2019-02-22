package com.example.user.memoproject;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class MemoShowActivity extends AppCompatActivity {

    String titletext;
    String timetext;
    String memotext;
    String memoUid;

    private TextView title;
    private TextView memo;
    private TextView time;
    private ImageButton goback2;
    private ImageButton revise;
    private ImageButton delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_show);

        title = findViewById(R.id.memoshowactivity_title);
        time = findViewById(R.id.memoshowactivity_time);
        memo = findViewById(R.id.memoshowactivity_memotext);
        goback2 = findViewById(R.id.toolbar3_goback);
        revise = findViewById(R.id.toolbar3_revisememo);
        delete = findViewById(R.id.memoshowactivity_buttondelete);

        titletext = getIntent().getStringExtra("destinationuid");
        timetext = getIntent().getStringExtra("time");
        memotext = getIntent().getStringExtra("memo");

        title.setText(titletext);
        time.setText(timetext);
        memo.setText(memotext);


        FirebaseDatabase.getInstance().getReference().child("memolist").orderByChild("title").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    memoUid = snapshot.getKey();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        goback2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MemoShowActivity.this, MainActivity.class);
                ActivityOptions activityOptions = ActivityOptions.makeCustomAnimation(view.getContext(), R.anim.from_left, R.anim.to_right);
                startActivity(intent, activityOptions.toBundle());
                finish();
            }
        });

        revise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MemoShowActivity.this, MemoReviseActivity.class);
                intent.putExtra("title", titletext);
                intent.putExtra("memo", memotext);
                intent.putExtra("memoUid", memoUid);
                startActivity(intent);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(MemoShowActivity.this);
                dialog  .setTitle("삭제 알림")
                        .setMessage("삭제하시겠습니까?")
                        .setNegativeButton("삭제", new DialogInterface.OnClickListener() {
                            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                FirebaseDatabase.getInstance().getReference().child("memolist").child(memoUid).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Intent intent = new Intent(MemoShowActivity.this, MainActivity.class);
                                        ActivityOptions activityOptions = ActivityOptions.makeCustomAnimation(view.getContext(), R.anim.from_left, R.anim.to_right);
                                        startActivity(intent, activityOptions.toBundle());
                                        finish();
                                    }
                                });
                            }
                        })
                        .setPositiveButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getApplicationContext(), "삭제를 취소했습니다.", Toast.LENGTH_SHORT).show();

                            }
                        }).create().show();
            }
        });
    }
}
