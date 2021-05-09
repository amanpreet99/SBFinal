package com.sb.syllabibuilder;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputLayout;

public class Create extends AppCompatActivity {
    Spinner course,semester,tp;
    TextInputLayout department,scheme;
    Button nextc;
    String st="";
    String zcourse,zsemester,ztp,zscheme,zdepartment;
    DBHelper dbHelper;
    SQLiteDatabase mydb;
    Cursor cursor;
    public static final String pref="mypref";
    public static final String fcourse= "coursekey";
    public static final String fsemester= "semesterkey";
    public static final String ftp= "thprkey";
    public static final String fscheme= "schemekey";
    public static final String fdepartment= "departmentkey";

    SharedPreferences sharedPreferences;

    //DROPDOWN VALUES
    String courses[]= {"Select Course","B.Tech.","M.Tech."};
    String semesterapplied[]={"Select Semester","1","2"};
    String sem[]={"Select Semester","3","4","5","6","7","8"};
    String thpt[]={"Theory/Practical?","Theory","Practical"};
    ArrayAdapter asem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        sharedPreferences=getSharedPreferences(pref, Context.MODE_PRIVATE);
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
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.saved:
                        startActivity(new Intent(getApplicationContext(),Saved.class));
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });

        //FIND VIEW BY ID
        course= (Spinner) findViewById(R.id.ccourse);
        department= (TextInputLayout)findViewById(R.id.cdepartment);
        semester=(Spinner) findViewById(R.id.csemester);
        scheme=( TextInputLayout)findViewById(R.id.cscheme);
        tp=(Spinner) findViewById(R.id.ctp);
        nextc=findViewById(R.id.cnext);

        //TO BE CHANGEDl BY DATA FETCHING FROM DATABASE
        st= getIntent().getStringExtra("value").trim();
        department.getEditText().setText(st);

        zdepartment= department.getEditText().getText().toString().trim();

        //ADAPTERS
        ArrayAdapter<String> acourse = new ArrayAdapter<String>(Create.this, R.layout.support_simple_spinner_dropdown_item, courses){
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
        course.setAdapter(acourse);
        course.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){

                }
                if(position==2){
                    zcourse= parent.getItemAtPosition(position).toString().trim();

                }
                //String selectedscheme = parent.getItemAtPosition(position).toString();
                if(position==1){
                    zcourse= parent.getItemAtPosition(position).toString().trim();
                    if(st.equals("Applied Science")){
                        ArrayAdapter asem = new ArrayAdapter(Create.this, R.layout.spin, semesterapplied){
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
                        semester.setAdapter(asem);
                        semester.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int p, long id) {
                                zsemester= parent.getItemAtPosition(p).toString().trim();
//                                String check=".{4,}";
//                                if(zscheme.isEmpty()){
//                                    scheme.setError("Enter field");
//                                }
//                                else if(!zscheme.matches(check)){
//                                    scheme.setError("Enter field as YYYY");
//                                }
//                                else{
                                    ArrayAdapter atp = new ArrayAdapter(Create.this, R.layout.spin, thpt){
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
                                    tp.setAdapter(atp);
                                    tp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                                            ztp=parent.getItemAtPosition(pos).toString().trim();
                                            if(pos==1 &&p!=0){
                                                nextc.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {
                                                        zscheme= scheme.getEditText().getText().toString().trim();
                                                        SharedPreferences.Editor editor=sharedPreferences.edit();
                                                        editor.putString(fcourse,zcourse);
                                                        editor.putString(fdepartment,zdepartment);
                                                        editor.putString(fsemester,zsemester);
                                                        editor.putString(fscheme,zscheme);
                                                        editor.putString(ftp,ztp);
                                                        editor.commit();
                                                        Intent intent= new Intent(Create.this, Theory.class);
                                                        startActivity(intent);
                                                    }
                                                });
                                            }
                                            else if(pos==2 &&p!=0){
                                                nextc.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {
                                                        zscheme= scheme.getEditText().getText().toString().trim();
                                                        SharedPreferences.Editor editor=sharedPreferences.edit();
                                                        editor.putString(fcourse,zcourse);
                                                        editor.putString(fdepartment,zdepartment);
                                                        editor.putString(fsemester,zsemester);
                                                        editor.putString(fscheme,zscheme);
                                                        editor.putString(ftp,ztp);
                                                        editor.commit();
                                                        Intent intent= new Intent(Create.this,Lab.class);
                                                        startActivity(intent);
                                                    }
                                                });
                                            }
                                            else{
                                                nextc.setError("Fill all fields");
                                                //Toast.makeText(getApplicationContext(),"Fill all fields",Toast.LENGTH_LONG).show();
                                            }
                                        }

                                        @Override
                                        public void onNothingSelected(AdapterView<?> parent) {

                                        }
                                    });
                                }
                      //      }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }
                    else{
                     asem= new ArrayAdapter(Create.this, R.layout.spin, sem){
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
                        semester.setAdapter(asem);
                        semester.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int p, long id) {
                                zsemester= parent.getItemAtPosition(p).toString().trim();

//                                String check=".{4,}";
////                                if(m.isEmpty()){
////                                    scheme.setError("Enter field");
////                                }
//                                if(!m.matches(check)){
//                                    scheme.setError("Enter field as YYYY");
//                                }
//                                else{
                                    ArrayAdapter atp = new ArrayAdapter(Create.this, R.layout.spin, thpt){
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
                                    tp.setAdapter(atp);
                                    tp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                                            ztp=parent.getItemAtPosition(pos).toString().trim();
                                            if(pos==1  &&p!=0){
                                                nextc.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {
                                                        zscheme= scheme.getEditText().getText().toString().trim();
                                                        SharedPreferences.Editor editor=sharedPreferences.edit();
                                                        editor.putString(fcourse,zcourse);
                                                        editor.putString(fdepartment,zdepartment);
                                                        editor.putString(fsemester,zsemester);
                                                        editor.putString(fscheme,zscheme);
                                                        editor.putString(ftp,ztp);
                                                        editor.commit();
                                                        Intent intent= new Intent(Create.this,Theory.class);
                                                        startActivity(intent);
                                                    }
                                                });
                                            }
                                            else if(pos==2  &&p!=0){
                                                nextc.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {
                                                        zscheme= scheme.getEditText().getText().toString().trim();
                                                        SharedPreferences.Editor editor=sharedPreferences.edit();
                                                        editor.putString(fcourse,zcourse);
                                                        editor.putString(fdepartment,zdepartment);
                                                        editor.putString(fsemester,zsemester);
                                                        editor.putString(fscheme,zscheme);
                                                        editor.putString(ftp,ztp);
                                                        editor.commit();
                                                        Intent intent= new Intent(Create.this,Lab.class);
                                                        startActivity(intent);
                                                    }
                                                });
                                            }
                                            else{
                                                }
                                        }

                                        @Override
                                        public void onNothingSelected(AdapterView<?> parent) {

                                        }
                                    });
                                }
//                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });


    }
}