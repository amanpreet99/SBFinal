package com.sb.syllabibuilder;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class Register extends AppCompatActivity{

    Button signin,signup;
    TextInputLayout email,password, confirmpass;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        email= (TextInputLayout) findViewById(R.id.mail);
        password= (TextInputLayout) findViewById(R.id.pass);
        confirmpass= (TextInputLayout) findViewById(R.id.confirmpass);
        signup = (Button) findViewById(R.id.signupbutton);
        signin =(Button) findViewById(R.id.signinbutton);
        db= new DBHelper(this);

        signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String mail= email.getEditText().getText().toString();
                String pass= password.getEditText().getText().toString();
                if(!(validatemail()&validatpass() &validateconfrmpass())){
                    return;
                }
                else{
                    boolean checkmail= db.checkemail(mail);
                    if(checkmail==false){
                        Boolean insert= db.insertData(mail,pass);
                        if(insert==true){
                            Toast.makeText(Register.this,"Welcome",Toast.LENGTH_LONG).show();
                            Intent intent= new Intent(getApplicationContext(), Create.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(Register.this,"failed",Toast.LENGTH_LONG).show();
                        }
                    }
                    else{
                        Toast.makeText(Register.this,"User already exists",Toast.LENGTH_LONG).show();
                    }
                }

            }
        });

        signin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),Login.class);
                startActivity(intent);

            }
        });
    }



    private boolean validatemail(){
        String m= email.getEditText().getText().toString().trim();
        if(m.isEmpty()){
            email.setError("Field can not be empty");
            return false;
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(m).matches()){
            email.setError("Invalid E-Mail(abc@gndec.ac.in)");
            return false;
        }
        else{
            email.setError(null);
            email.setErrorEnabled(false);
            return true;
        }

    }

    private boolean validatpass(){
        String m= password.getEditText().getText().toString().trim();
        String checkpass="^"+
                "(?=.*[0-9])"+ //1 digit atleast
                "(?=.*[a-z])"+ //1 lower case
                "(?=.*[A-Z])"+//one upper case
                "(?=.*[a-zA-Z])"+//any letter
                "(?=.*[@#$%^&+=])"+//any one special character
                "(?=\\S+$)"+ //no white space
                ".{4,}"+//atleast 4 characters
                "$";
        if(m.isEmpty()){
            password.setError("Field can not be empty");
            return false;
        }
        else if(!m.matches(checkpass)){
            password.setError("Invalid Password! Use 1 character, 1 lowercase, 1 uppercase, 1 digit, no space");
            return false;
        }
        else{
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }

    }
    private boolean validateconfrmpass(){
        String m= password.getEditText().getText().toString().trim();
        String mc= confirmpass.getEditText().getText().toString().trim();
        if(mc.isEmpty()){
            confirmpass.setError("Field can not be empty");
            return false;
        }
        else if(!m.equals(mc)){
            confirmpass.setError("Password doesn't match");
            return false;
        }
        else{
            confirmpass.setError(null);
            confirmpass.setErrorEnabled(false);
            return true;
        }
    }
}