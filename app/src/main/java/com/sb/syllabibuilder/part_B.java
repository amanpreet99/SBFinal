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

import java.util.ArrayList;

import static android.view.View.*;

public class part_B extends AppCompatActivity implements OnClickListener {


    LinearLayout layoutListB;
    Button buttonAdd,next;
    ArrayList<PartABContent> partBList = new ArrayList<>();
    ArrayList<PartABContent> partAList= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part__b);

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


        layoutListB = findViewById(R.id.layout_listB);
        buttonAdd = findViewById(R.id.button_addb);
        next=(Button)findViewById(R.id.pbnext);

        buttonAdd.setOnClickListener(this);
        next.setOnClickListener(this);
        partAList = (ArrayList<PartABContent>) getIntent().getExtras().getSerializable("partA");

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.button_addb:

                addView();

                break;


            case R.id.pbnext:

                if(checkIfValidAndRead()){

                    Intent intent = new Intent(part_B.this, Textbooks.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("partA",partAList);
                    bundle.putSerializable("partB",partBList);
                    intent.putExtras(bundle);
                    startActivity(intent);

                }

                break;

        }


    }

    private boolean checkIfValidAndRead() {
        partBList.clear();
        boolean result = true;

        for(int i=0;i<layoutListB.getChildCount();i++){

            View partBView = layoutListB.getChildAt(i);

            TextInputLayout edittitle = (TextInputLayout) partBView.findViewById(R.id.edit_title);
            TextInputLayout editcontent = (TextInputLayout) partBView.findViewById(R.id.edit_content);
            TextInputLayout edithours = (TextInputLayout) partBView.findViewById(R.id.edit_hours);

            PartABContent partABContent = new PartABContent();

            if(!edittitle.getEditText().getText().toString().equals("")){
                partABContent.setTitleedit(edittitle.getEditText().getText().toString());
            }else {
                result = false;
                break;
            }

            if(!editcontent.getEditText().getText().toString().equals("")){
                partABContent.setContentedit(editcontent.getEditText().getText().toString());
            }else {
                result = false;
                break;
            }

            if(!edithours.getEditText().getText().toString().equals("")){
                partABContent.setHourstitle(edithours.getEditText().getText().toString());
            }else {
                result = false;
                break;
            }



            partBList.add(partABContent);

        }

        if(partBList.size()==0){
            result = false;
            Toast.makeText(this, "Add Part-B Syllabus!", Toast.LENGTH_SHORT).show();
        }else if(!result){
            Toast.makeText(this, "Enter All Details Correctly!", Toast.LENGTH_SHORT).show();
        }


        return result;
    }
    private void addView() {

        final View partBView = getLayoutInflater().inflate(R.layout.row_add_parta,null,false);

        TextInputLayout editText1 = (TextInputLayout) partBView.findViewById(R.id.edit_title);
        TextInputLayout editText2 = (TextInputLayout) partBView.findViewById(R.id.edit_content);
        TextInputLayout editText3 = (TextInputLayout) partBView.findViewById(R.id.edit_hours);
        ImageView imageClose = (ImageView)partBView.findViewById(R.id.image_remove);

        imageClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeView(partBView);
            }
        });

        layoutListB.addView(partBView);

    }

    private void removeView(View view){

        layoutListB.removeView(view);

    }
}