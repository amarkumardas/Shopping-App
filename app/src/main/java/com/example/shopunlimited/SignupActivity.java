package com.example.shopunlimited;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {
EditText nameEd,emailEd,mobileEd,passwordEd;
Button signupBtn;
RadioGroup radioGroup;
RadioButton geneder_radio;
private String api_Link="https://oakspro.com/projects/project39/amarkumar/amar/signup_api.php";
private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        nameEd=findViewById(R.id.name_ed);
        emailEd=findViewById(R.id.email_ed);
        mobileEd=findViewById(R.id.phon_ed);
        passwordEd=findViewById(R.id.password_ed);
        signupBtn=findViewById(R.id.signup_btn);
        radioGroup=findViewById(R.id.gender_radiogp);

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user_name=nameEd.getText().toString();
                String user_email=emailEd.getText().toString();
                String user_mobile=mobileEd.getText().toString();
                String user_pass=passwordEd.getText().toString();

                progressDialog=new ProgressDialog(SignupActivity.this);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.setIndeterminate(true);//It means the "loading amount" is not measured.
                progressDialog.setMessage("Please Wait karo..");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                //or  progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

                Integer gender_id=radioGroup.getCheckedRadioButtonId();//get the id of checked radioButton
                geneder_radio=findViewById(gender_id);

                String user_sex=geneder_radio.getText().toString();

                if(!TextUtils.isEmpty(user_name) && !TextUtils.isEmpty(user_email) && !TextUtils.isEmpty(user_mobile) && !TextUtils.isEmpty(user_pass) && !TextUtils.isEmpty(user_sex)){
                    if(user_mobile.length()==10){
                        uploadData(user_email,user_mobile,user_name,user_pass,user_sex);
                    }else
                        Toast.makeText(SignupActivity.this, "Enter valid 10 digit mobile number", Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(SignupActivity.this, "Enter all fields", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void uploadData(String user_email, String user_mobile, String user_name, String user_pass, String user_sex) {
    progressDialog.show();
    //server logic of upload StringRequest                 //here we can access api because we have used Request.Method.POST
        StringRequest request_signup=new StringRequest(Request.Method.POST, api_Link, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {//response come on this method form server after signup
                   //response come from server converted to JSON
                try{
                 JSONObject jsonObject=new JSONObject(response);

                    String status=jsonObject.getString("status");
                    String message= jsonObject.getString("message");
                    progressDialog.dismiss();
                    //another dialog
                    showMessage(status,message);
             }catch(JSONException e){
                 e.printStackTrace();
                 progressDialog.dismiss();
                }
            }
        }, new Response.ErrorListener() {//sometime error due to internet or api error
            @Override
            public void onErrorResponse(VolleyError error) {
             //if we get any response error
                Toast.makeText(SignupActivity.this, "Please check Internet..", Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> data=new HashMap<>();
                data.put("name",user_name);
                data.put("email",user_email);
                data.put("mobile",user_mobile);
                data.put("password",user_pass);
                data.put("sex",user_sex);
                 return data;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request_signup);
    }

    private void showMessage(String status, String message) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Account Signup");
        builder.setMessage("\n"+status+"\n"+message);
        builder.setCancelable(true);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //when login is complited should go to Signin activity
                Intent intent=new Intent(SignupActivity.this,SigninActivity.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                   dialogInterface.dismiss();//closing the dialogbox
            }
        });
        builder.create().show();
    }
}