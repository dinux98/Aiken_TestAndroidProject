package com.example.shopie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegistrationActivity extends AppCompatActivity {
    
    Button signup;
    EditText name , email , password;
    TextView signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        
        signup = findViewById(R.id.btn_signup);
        signin = findViewById(R.id.tv_signin);
        
//        signup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(RegistrationActivity.this,MainActivity.class);
//                startActivity(intent);
//            }
//        });


    }


    public void signup(View view){
        startActivity(new Intent(RegistrationActivity.this,MainActivity.class));
    }
    public void signin(View view){
        startActivity(new Intent(RegistrationActivity.this,LoginActivity.class));
    }

}