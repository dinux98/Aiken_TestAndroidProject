package com.example.shopie.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shopie.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {

    Button signup;
    EditText name , email , password;
    TextView signin;

    SharedPreferences sharedPreferences;

    private static final String TAG = "Registration";

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        name = findViewById(R.id.et_name);
        email = findViewById(R.id.et_email);
        password = findViewById(R.id.et_password);

        signup = findViewById(R.id.btn_signup);
        signin = findViewById(R.id.tv_signin);
        auth = FirebaseAuth.getInstance();


        SharedPreferences sharedPreferences = getSharedPreferences("onBoardingScreen",MODE_PRIVATE);
        Boolean isFirstTime = sharedPreferences.getBoolean("firstTime",true);
        if (isFirstTime){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("firstTime",false);
            editor.commit();
            startActivity(new Intent(RegistrationActivity.this,OnBoardingActivity.class));
            finish();

        }


        if (auth.getCurrentUser()!= null){
            startActivity(new Intent(RegistrationActivity.this,MainActivity.class));
            finish();
        }



    }


    public void signup(View view){

        String userName = name.getText().toString();
        String userEmail = email.getText().toString();
        String userPassword = password.getText().toString();



            if (TextUtils.isEmpty(userName)) {
                Toast.makeText(this, "User Name is Required!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(userEmail)) {
                Toast.makeText(this, "Email is Required!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (userPassword.length() < 6) {
                Toast.makeText(this, "Password is too short ,Use at least 6 characters!", Toast.LENGTH_SHORT).show();
                return;
            }


        auth.createUserWithEmailAndPassword(userEmail,userPassword)
                .addOnCompleteListener(RegistrationActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            Toast.makeText(RegistrationActivity.this, "Successfully Registered !", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegistrationActivity.this,MainActivity.class));
                        }else {
                            Toast.makeText(RegistrationActivity.this, "Registration Failed !" +task.getException(), Toast.LENGTH_SHORT).show();
                            Log.i(TAG, "Signup:");
                        }
                    }
                });


    }
    public void signin(View view){
        startActivity(new Intent(RegistrationActivity.this,LoginActivity.class));
    }
}