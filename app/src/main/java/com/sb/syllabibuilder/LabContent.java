package com.sb.syllabibuilder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputLayout;
import com.sb.syllabibuilder.mtech.MainActivity;

import java.util.ArrayList;

public class LabContent extends AppCompatActivity implements View.OnClickListener {

    LinearLayout layoutList;
    Button buttonAdd,next;
    ArrayList<Labuser> LVList= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_content);

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


        layoutList = findViewById(R.id.layout_practicals);
        buttonAdd = findViewById(R.id.button_addt);
        next=(Button)findViewById(R.id.textnext);

        buttonAdd.setOnClickListener(this);
        next.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.button_addt:

                addView();

                break;


            case R.id.textnext:

                if(checkIfValidAndRead()){

                    Intent intent = new Intent(LabContent.this, ProjectName.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("practical",LVList);
                    intent.putExtras(bundle);
                    startActivity(intent);

                }

                break;

        }


    }

    private boolean checkIfValidAndRead() {
        LVList.clear();
        boolean result = true;

        for(int i=0;i<layoutList.getChildCount();i++){

            View LVView = layoutList.getChildAt(i);

            TextInputLayout t1= (TextInputLayout)LVView.findViewById(R.id.pracno);
            TextInputLayout t2= (TextInputLayout)LVView.findViewById(R.id.pracname);

            Labuser lu = new Labuser();

            if(!t1.getEditText().getText().toString().equals("")){
                lu.setPracnumber(t1.getEditText().getText().toString());
            }else {
                result = false;
                break;
            }
            if(!t2.getEditText().getText().toString().equals("")){
                lu.setPracname(t2.getEditText().getText().toString());
            }else {
                result = false;
                break;
            }
            LVList.add(lu);

        }

        if(LVList.size()==0){
            result = false;
            Toast.makeText(this, "Add Practicals!", Toast.LENGTH_SHORT).show();
        }else if(!result){
            Toast.makeText(this, "Enter All Details Correctly!", Toast.LENGTH_SHORT).show();
        }


        return result;
    }

    private void addView() {
        final View LVView = getLayoutInflater().inflate(R.layout.practicalname,null,false);

        TextInputLayout t1= (TextInputLayout)LVView.findViewById(R.id.pracno);
        TextInputLayout t2= (TextInputLayout)LVView.findViewById(R.id.pracname);
        ImageView imageClose = (ImageView)LVView.findViewById(R.id.image_remove);
        imageClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeView(LVView);
            }
        });

        layoutList.addView(LVView);

    }

    private void removeView(View view){

        layoutList.removeView(view);

    }
}