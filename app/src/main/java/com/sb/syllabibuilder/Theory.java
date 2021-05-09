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

public class Theory extends AppCompatActivity{
      Spinner slecture,stut,selective,slab;
      TextInputLayout sname,scode,snum,spre,sadditional,scredits;
      Button snext;

    String lec[]={"Number of Lectures","1","2","3","4"};
    String tut[]={"Number of Tutorials","0","1"};
    String elective[]={"Elective Status","Elective","Compulsory"};
    String lab[]={"Does this subject have laboratory","Yes","No"};
    String l,t,e,la;
    String n="";
    String c="";
    String nu="";
    String p="";
    String add="";
    String cred="";

    public static final String flecture= "lecturekey";
    public static final String ftut= "tutkey";
    public static final String felective= "electivekey";
    public static final String flab= "labkey";
    public static final String fsubname= "subnamekey";
    public static final String fsubcode= "subcodekey";
    public static final String fnumerical= "numericalkey";
    public static final String fpre= "prekey";
    public static final String fadditional= "additionalkey";
    public static final String fcredits= "creditkey";

    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theory);

        sharedPreferences=getSharedPreferences("mypref", Context.MODE_PRIVATE);
        //BOTTOM NAVIGATION VIEW
        BottomNavigationView bottomNavigationView= findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.create);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.create:
                        return true;

                    case R.id.kuch:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.saved:
                        startActivity(new Intent(getApplicationContext(), Saved.class));
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });

        slecture= (Spinner) findViewById(R.id.tlecture);
        stut= (Spinner) findViewById(R.id.ttut);
        scredits= (TextInputLayout) findViewById(R.id.tcredits);
        selective= (Spinner) findViewById(R.id.telective);
        slab= (Spinner) findViewById(R.id.tlab);
        sname= (TextInputLayout)findViewById(R.id.tsubname);
        scode= (TextInputLayout)findViewById(R.id.tsubcode);
        snum= (TextInputLayout)findViewById(R.id.tnum);
        spre= (TextInputLayout)findViewById(R.id.tpre);
        sadditional= (TextInputLayout)findViewById(R.id.tadditional);
        snext=(Button)findViewById(R.id.tnext);

        //LECTURE ADAPTER
        ArrayAdapter<String> alec = new ArrayAdapter<String>(Theory.this, R.layout.support_simple_spinner_dropdown_item, lec){
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
        slecture.setAdapter(alec);
        slecture.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){

                }
                else{
                    l= parent.getItemAtPosition(position).toString().trim();
                    //Toast.makeText(getApplicationContext(),"Selected"+l,Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                l="";

            }
        });

        //TUTORIAL ADAPTER
        ArrayAdapter<String> atut = new ArrayAdapter<String>(Theory.this, R.layout.support_simple_spinner_dropdown_item, tut){
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
        stut.setAdapter(atut);
        stut.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){

                }
                else{
                    t= parent.getItemAtPosition(position).toString().trim();
                   // Toast.makeText(getApplicationContext(),"Selected"+t,Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                t="";

            }
        });

        //ELECTIVE ADAPTER
        ArrayAdapter<String> aele = new ArrayAdapter<String>(Theory.this, R.layout.support_simple_spinner_dropdown_item, elective){
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

        //LABORATORY ADAPTER
        ArrayAdapter<String> alab = new ArrayAdapter<String>(Theory.this, R.layout.support_simple_spinner_dropdown_item, lab){
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
        slab.setAdapter(alab);
        slab.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){

                }
                else{
                    la= parent.getItemAtPosition(position).toString().trim();
                    //Toast.makeText(getApplicationContext(),"Selected"+la,Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                la="";

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
                add= sadditional.getEditText().getText().toString().trim();
                cred= scredits.getEditText().getText().toString().trim();
                if(!(l.length()>0 && t.length()>0 && e.length()>0 && la.length()>0 && n.length()>0&& c.length()>0&& nu.length()>0&& p.length()>0 && add.length()>0&& cred.length()>0)){
                    snext.setEnabled(false);
                    Toast.makeText(getApplicationContext(),"Fill all fields",Toast.LENGTH_LONG).show();
                }
                else if(l.length()>0 && t.length()>0 && e.length()>0 && la.length()>0 && n.length()>0&& c.length()>0&& nu.length()>0&& p.length()>0 && add.length()>0&& cred.length()>0){
                    snext.setEnabled(true);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString(flecture,l);
                    editor.putString(ftut,t);
                    editor.putString(fsubname,n);
                    editor.putString(fsubcode,c);
                    editor.putString(fcredits,cred);
                    editor.putString(felective,e);
                    editor.putString(flab,la);
                    editor.putString(fnumerical,nu);
                    editor.putString(fpre,p);
                    editor.putString(fadditional,add);
                    editor.commit();
                    Intent intent=new Intent(Theory.this, CO.class);
                    startActivity(intent);
                }

            }
        });
    }
}