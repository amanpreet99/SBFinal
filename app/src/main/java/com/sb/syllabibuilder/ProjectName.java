package com.sb.syllabibuilder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class ProjectName extends AppCompatActivity {
    TextInputLayout t1;
    Button b1;
    ArrayList<Labuser> LVList= new ArrayList<>();
    public static final String fpn= "pnkey";
    String pn;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_name);
        t1=(TextInputLayout)findViewById(R.id.proname);
        b1=(Button)findViewById(R.id.pronext);
        LVList = (ArrayList<Labuser>) getIntent().getExtras().getSerializable("practical");
        sharedPreferences=getSharedPreferences("mypref", Context.MODE_PRIVATE);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(val()){
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString(fpn,pn);
                    editor.commit();
                    Intent intent = new Intent(ProjectName.this, LTextbooks.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("practical",LVList);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });
    }
    public boolean val(){
        pn= t1.getEditText().getText().toString().trim();
        if(pn.isEmpty()){
            t1.setError("Enter Project Description, If not any type NIL");
            return false;
        }
        return true;
    }
}
