package com.example.user.memoproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.example.user.memoproject.fragments.datefragment;
import com.example.user.memoproject.fragments.memofragment;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private BottomNavigationView navigationView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationView = findViewById(R.id.mainactivity_navigationview);
        toolbar = findViewById(R.id.mainactivity_toolbar);
        toolbar.findViewById(R.id.toolbar_addmemo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MemoActivity.class);
                startActivity(intent);
            }
        });

        getSupportFragmentManager().beginTransaction().replace(R.id.mainactivity_framelayout, new memofragment()).commit();

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {
                    case (R.id.action_memo):
                        getSupportFragmentManager().beginTransaction().replace(R.id.mainactivity_framelayout, new memofragment()).commit();
                        return true;

                    case (R.id.action_calander):
                        getSupportFragmentManager().beginTransaction().replace(R.id.mainactivity_framelayout, new datefragment()).commit();
                        return true;
                }
                return false;
            }
        });
    }
}
