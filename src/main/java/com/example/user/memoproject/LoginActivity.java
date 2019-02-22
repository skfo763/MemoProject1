package com.example.user.memoproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    public static int LOGIN_CODE = 1001;
    private EditText id;
    private EditText pw;
    private Button login;
    private Button maker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        id = findViewById(R.id.loginactivity_id);
        pw = findViewById(R.id.loginactivity_pw);
        login = findViewById(R.id.loginactivity_login);
        maker = findViewById(R.id.loginactivity_maker);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!id.getText().toString().equals(null) && !pw.getText().toString().equals(null)){
                    if(id.getText().toString().equals("scy7351") && pw.getText().toString().equals("123456")){
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("id", "서창연");
                        startActivityForResult(intent, 0);
                    }else{
                        Toast.makeText(getApplicationContext(), "아이디 혹은 비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "정보를 입력해주세요", Toast.LENGTH_SHORT).show();
                }
            }
        });

        maker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "위 앱의 개발자는 초보 개발자 서창연입니다!\n화이팅!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
