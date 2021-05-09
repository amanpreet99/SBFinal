package com.sb.syllabibuilder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

public class CO extends AppCompatActivity {
    TextInputLayout c1,c2,c3,c4,c5,c6;
    String o1,o2,o3,o4,o5,o6;
    Button conext;
    public static final String fco1= "co1key";
    public static final String fco2= "co2key";
    public static final String fco3= "co3key";
    public static final String fco4= "co4key";
    public static final String fco5= "co5key";
    public static final String fco6= "co6key";

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_o);
        c1= (TextInputLayout)findViewById(R.id.co1);
        c2= (TextInputLayout)findViewById(R.id.co2);
        c3= (TextInputLayout)findViewById(R.id.co3);
        c4= (TextInputLayout)findViewById(R.id.co4);
        c5= (TextInputLayout)findViewById(R.id.co5);
        c6= (TextInputLayout)findViewById(R.id.co6);
        conext=(Button)findViewById(R.id.conext);
        sharedPreferences=getSharedPreferences("mypref", Context.MODE_PRIVATE);
        conext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(val())){
                    return;

                }
                else{
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString(fco1,o1);
                    editor.putString(fco2,o2);
                    editor.putString(fco3,o3);
                    editor.putString(fco4,o4);
                    editor.putString(fco5,o5);
                    editor.putString(fco6,o6);
                    editor.commit();
                    Intent intent= new Intent(CO.this, part_A.class);
                    startActivity(intent);
                }
            }
        });
    }
    public boolean val(){
        o1= c1.getEditText().getText().toString().trim();
        o2= c2.getEditText().getText().toString().trim();
        o3= c3.getEditText().getText().toString().trim();
        o4= c4.getEditText().getText().toString().trim();
        o5= c5.getEditText().getText().toString().trim();
        o6= c6.getEditText().getText().toString().trim();
       if(o1.isEmpty()){
          c1.setError("Enter co1");
          return false;
       }
       else if(o2.isEmpty()){
           c2.setError("Enter co2");
           return false;
       }
       else if(o3.isEmpty()){
           c3.setError("Enter co3");
           return false;
       }
       else if(o4.isEmpty()){
           c4.setError("Enter co4");
           return false;
       }
       else if(o5.isEmpty()){
           c5.setError("Enter co5");
           return false;
       }
       else if(o6.isEmpty()){
           c6.setError("Enter co6");
           return false;
       }
       else{
           return true;
       }
    }

}