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

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputLayout;
import com.sb.syllabibuilder.mtech.MainActivity;

public class Create extends AppCompatActivity {
    Spinner department,semester,tp;
    TextInputLayout scheme;
    Button nextc;
    String zcourse,zsemester,ztp,zscheme,zdepartment;
    public static final String pref="mypref";
    public static final String fcourse= "coursekey";
    public static final String fsemester= "semesterkey";
    public static final String ftp= "thprkey";
    public static final String fscheme= "schemekey";
    public static final String fdepartment= "departmentkey";

    SharedPreferences sharedPreferences;

    //DROPDOWN VALUES
    String[] dpmnt={"Select Department","Applied Science","Civil Engineering","Computer Science and Engineering","Electrical Engineering","Electronics and Communicaton Engineering","Information Technology","Mechanical Engineering","Production Engineering"};
    String semesterapplied[]={"Select Semester","1","2"};
    String sem[]={"Select Semester","3","4","5","6","7","8"};
    String thpt[]={"Theory/Practical?","Theory","Practical"};
    ArrayAdapter asem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        sharedPreferences = getSharedPreferences(pref, Context.MODE_PRIVATE);
        //BOTTOM NAVIGATION VIEW
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.btech);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.btech:
                        return true;

                    case R.id.mtech:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });

        //FIND VIEW BY ID
        department = (Spinner) findViewById(R.id.cdepartment);
        semester = (Spinner) findViewById(R.id.csemester);
        scheme = (TextInputLayout) findViewById(R.id.cscheme);
        tp = (Spinner) findViewById(R.id.ctp);
        nextc = findViewById(R.id.cnext);

        //ADAPTERS
        ArrayAdapter<String> adpmt = new ArrayAdapter<String>(Create.this, R.layout.support_simple_spinner_dropdown_item, dpmnt) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    return false;
                }
                return true;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView textView = (TextView) view;
                if (position == 0) {
                    textView.setTextColor(Color.GRAY);
                } else {
                    textView.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        department.setAdapter(adpmt);
        department.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {

                } else {
                    zdepartment = parent.getItemAtPosition(position).toString().trim();
                    if (position == 1) {
                        ArrayAdapter asem = new ArrayAdapter(Create.this, R.layout.spin, semesterapplied) {
                            @Override
                            public boolean isEnabled(int position) {
                                if (position == 0) {
                                    return false;
                                }
                                return true;
                            }

                            @Override
                            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                                View view = super.getDropDownView(position, convertView, parent);
                                TextView textView = (TextView) view;
                                if (position == 0) {
                                    textView.setTextColor(Color.GRAY);
                                } else {
                                    textView.setTextColor(Color.BLACK);
                                }
                                return view;
                            }
                        };
                        semester.setAdapter(asem);
                        semester.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int p, long id) {
                                zsemester = parent.getItemAtPosition(p).toString().trim();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    } else {
                        asem = new ArrayAdapter(Create.this, R.layout.spin, sem) {
                            @Override
                            public boolean isEnabled(int position) {
                                if (position == 0) {
                                    return false;
                                }
                                return true;
                            }

                            @Override
                            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                                View view = super.getDropDownView(position, convertView, parent);
                                TextView textView = (TextView) view;
                                if (position == 0) {
                                    textView.setTextColor(Color.GRAY);
                                } else {
                                    textView.setTextColor(Color.BLACK);
                                }
                                return view;
                            }
                        };
                        semester.setAdapter(asem);
                        semester.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int p, long id) {
                                zsemester = parent.getItemAtPosition(p).toString().trim();

                            }

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


        ArrayAdapter<String> atp = new ArrayAdapter<String>(Create.this, R.layout.spin, thpt) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    return false;
                }
                return true;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView textView = (TextView) view;
                if (position == 0) {
                    textView.setTextColor(Color.GRAY);
                } else {
                    textView.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        tp.setAdapter(atp);
        tp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                ztp = parent.getItemAtPosition(pos).toString().trim();
                if (pos == 1) {
                    nextc.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            zscheme = scheme.getEditText().getText().toString().trim();
                            zcourse = "B.Tech.";
                            if (zcourse != null & zdepartment != null & zscheme != null & zsemester != null & ztp != null) {
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString(fcourse, zcourse);
                                editor.putString(fdepartment, zdepartment);
                                editor.putString(fsemester, zsemester);
                                editor.putString(fscheme, zscheme);
                                editor.putString(ftp, ztp);
                                editor.commit();
                                Intent intent = new Intent(Create.this, Theory.class);
                                startActivity(intent);
                            }

                        }

                    });
                } else if (pos == 2) {
                    nextc.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            zscheme = scheme.getEditText().getText().toString().trim();
                            zcourse = "B.Tech.";
                            if (zcourse != null & zdepartment != null & zscheme != null & zsemester != null & ztp != null) {
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString(fcourse, zcourse);
                                editor.putString(fdepartment, zdepartment);
                                editor.putString(fsemester, zsemester);
                                editor.putString(fscheme, zscheme);
                                editor.putString(ftp, ztp);
                                editor.commit();
                                Intent intent = new Intent(Create.this, Lab.class);
                                startActivity(intent);
                            }

                        }

                    });
                } else {

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }}