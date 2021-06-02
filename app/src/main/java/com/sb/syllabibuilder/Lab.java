package com.sb.syllabibuilder;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputLayout;
import com.sb.syllabibuilder.mtech.MainActivity;

public class Lab extends AppCompatActivity{
    Spinner sprac,selective;
    TextInputLayout sname,scode,snum,spre,sspecial,scredits;
    Button snext;

    String prac[]={"Number of Practical ","1","2","3","4"};
    String elective[]={"Elective Status","Elective","Compulsory"};
    String pr,e;
    String n="";
    String c="";
    String nu="";
    String p="";
    String sp="";
    String cred="";

    public static final String fpractical= "practicalkey";
    public static final String felective= "electivelabkey";
    public static final String fsubname= "subnamelabkey";
    public static final String fsubcode= "subcodelabkey";
    public static final String fnumerical= "numericallabkey";
    public static final String fpre= "prelabkey";
    public static final String fspecial= "specialkey";
    public static final String fcredits= "creditlabkey";

    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab);

        sharedPreferences=getSharedPreferences("mypref", Context.MODE_PRIVATE);
        //BOTTOM NAVIGATION VIEW
        BottomNavigationView bottomNavigationView= findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.btech);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.btech:
                        return true;

                    case R.id.mtech:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });

        sprac= (Spinner) findViewById(R.id.pprac);
        scredits= (TextInputLayout) findViewById(R.id.pcredits);
        selective= (Spinner) findViewById(R.id.pelective);
        sname= (TextInputLayout)findViewById(R.id.psubname);
        scode= (TextInputLayout)findViewById(R.id.psubcode);
        snum= (TextInputLayout)findViewById(R.id.pnum);
        spre= (TextInputLayout)findViewById(R.id.ppre);
        sspecial= (TextInputLayout)findViewById(R.id.pspecial);
        snext=(Button)findViewById(R.id.pnext);

        //LECTURE ADAPTER
        ArrayAdapter<String> aprac = new ArrayAdapter<String>(Lab.this, R.layout.support_simple_spinner_dropdown_item, prac){
            @Override
            public boolean isEnabled(int position) {
                if(position==0){
                    return false;
                }
                return true;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view=super.getDropDownView(position,convertView,parent);
                TextView textView=(TextView) view;
                if(position==0){
                    textView.setTextColor(Color.GRAY);
                }
                else{
                    textView.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        sprac.setAdapter(aprac);
        sprac.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){

                }
                else{
                    pr= parent.getItemAtPosition(position).toString().trim();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                pr="";

            }
        });

        //ELECTIVE ADAPTER
        ArrayAdapter<String> aele = new ArrayAdapter<String>(Lab.this, R.layout.support_simple_spinner_dropdown_item, elective){
            @Override
            public boolean isEnabled(int position) {
                if(position==0){
                    return false;
                }
                return true;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view=super.getDropDownView(position,convertView,parent);
                TextView textView=(TextView) view;
                if(position==0){
                    textView.setTextColor(Color.GRAY);
                }
                else{
                    textView.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        selective.setAdapter(aele);
        selective.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){

                }
                else{
                    e= parent.getItemAtPosition(position).toString().trim();
                    //Toast.makeText(getApplicationContext(),"Selected"+e,Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                e="";
            }
        });

        //BUTTON
        snext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                n= sname.getEditText().getText().toString().trim();
                c= scode.getEditText().getText().toString().toUpperCase().trim();
                nu= snum.getEditText().getText().toString().trim();
                p= spre.getEditText().getText().toString().trim();
                sp= sspecial.getEditText().getText().toString().trim();
                cred= scredits.getEditText().getText().toString().trim();
                if(!(pr.length()>0 && e.length()>0 && n.length()>0 && c.length()>0&& nu.length()>0&& p.length()>0 && sp.length()>0&& cred.length()>0)){
                    Toast.makeText(getApplicationContext(),"Fill all fields",Toast.LENGTH_LONG).show();
                }
                else if(!(n.endsWith("Laboratory")| n.endsWith("laboratory"))){
                    Toast.makeText(getApplicationContext(),"Put Laboratory at end of Subject Name",Toast.LENGTH_LONG).show();
                }
                else if(!(c.startsWith("L"))){
                    Toast.makeText(getApplicationContext(),"Subject code should start with L",Toast.LENGTH_LONG).show();
                }
                else if(pr.length()>0 && e.length()>0 && n.length()>0&& c.length()>0&& nu.length()>0&& p.length()>0 && sp.length()>0&& cred.length()>0){
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString(fpractical,pr);
                    editor.putString(fsubname,n);
                    editor.putString(fsubcode,c);
                    editor.putString(fcredits,cred);
                    editor.putString(felective,e);
                    editor.putString(fnumerical,nu);
                    editor.putString(fpre,p);
                    editor.putString(fspecial,sp);
                    editor.commit();
                    Intent intent=new Intent(Lab.this, LCO.class);
                    startActivity(intent);
                }

            }
        });
    }
}