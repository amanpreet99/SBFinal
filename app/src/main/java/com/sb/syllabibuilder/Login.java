package com.sb.syllabibuilder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class Login extends AppCompatActivity {
    TextInputLayout email,password;
    Button signin,signup;
    TextView forget;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email= (TextInputLayout) findViewById(R.id.mail);
        password= (TextInputLayout) findViewById(R.id.pass);
        signup = (Button) findViewById(R.id.signupbutton);
        signin =(Button) findViewById(R.id.signinbutton);
        forget= (TextView)findViewById(R.id.foregtpass);
        db= new DBHelper(this);
        getSupportActionBar().hide();

        signin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String mail= email.getEditText().getText().toString();
                String pass= password.getEditText().getText().toString();
                if(mail.equals("")||pass.equals("")){
                    email.setError("Enter e-mail");
                    password.setError("Enter password");}
                else{
                    boolean checkpassword= db.checkpass(mail,pass);
                    if(checkpassword==true){
                        Toast.makeText(Login.this,"Login successful",Toast.LENGTH_LONG).show();
                        Intent intent= new Intent(getApplicationContext(), Create.class);
                        startActivity(intent);

                    }
                    else if(checkpassword==false){
                        password.setError("Wrong password");
                    }
                    else{
                        Toast.makeText(Login.this,"Invalid credentials",Toast.LENGTH_LONG).show();
                    }
                }

            }
        });
        signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent intent= new Intent(getApplicationContext(),Register.class);
                startActivity(intent);

            }
        });

    }
    public void clickforget(View v){
        Intent i = new Intent(getApplicationContext(),ForgetPass.class);
        startActivity(i);

    }
}