package com.example.shopie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth auth;

    Button signup;
    EditText email , password;
    TextView signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.et_email);
        password = findViewById(R.id.et_password);

        signup = findViewById(R.id.btn_signup);
        signin = findViewById(R.id.tv_signin);

        auth = FirebaseAuth.getInstance();

    }

    public void signin(View view){

        String userEmail = email.getText().toString();
        String userPassword = password.getText().toString();


        if (TextUtils.isEmpty(userEmail)) {
            Toast.makeText(this, "Email is Required!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (userPassword.length()< 6) {
            Toast.makeText(this, "Password is too short ,Use at least 6 characters!", Toast.LENGTH_SHORT).show();

        }

        auth.signInWithEmailAndPassword(userEmail,userPassword)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this,MainActivity.class));

                        }else {
                            Toast.makeText(LoginActivity.this, "Sign Up before Login", Toast.LENGTH_SHORT).show();
                        }

                    }
                });



    }

    public void signup(View view){
        startActivity(new Intent(LoginActivity.this,RegistrationActivity.class));
    }
}