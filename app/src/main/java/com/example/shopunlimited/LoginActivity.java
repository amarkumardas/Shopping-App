package com.example.shopunlimited;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
     EditText mobileEd,passwordEd;
     Button loginBtn;
     TextView signupTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mobileEd=findViewById(R.id.mobile_ed);
        passwordEd=findViewById(R.id.password_ed);
        loginBtn=findViewById(R.id.login_btn);
        signupTxt=findViewById(R.id.signup_txt);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=mobileEd.getText().toString();
                String userPassword=passwordEd.getText().toString();

                if(!TextUtils.isEmpty(user)){
                    if(!TextUtils.isEmpty(userPassword)){
                        if(user.equals("Amar")){

                            if(userPassword.equals("123"))
                                Toast.makeText(LoginActivity.this, "Login SUCCESS", Toast.LENGTH_SHORT).show();
                            else
                                Toast.makeText(LoginActivity.this, "Please enter valid password", Toast.LENGTH_SHORT).show();

                        }else
                            Toast.makeText(LoginActivity.this, "Username not found", Toast.LENGTH_SHORT).show();

                    }else
                        Toast.makeText(LoginActivity.this, "Please enter Password", Toast.LENGTH_SHORT).show();

                }else
                    Toast.makeText(LoginActivity.this, "Please enter Username", Toast.LENGTH_SHORT).show();
            }
        });

        signupTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(intent);
            }
        });
    }
}