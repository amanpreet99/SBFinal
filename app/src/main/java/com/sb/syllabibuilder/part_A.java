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


public class part_A extends AppCompatActivity implements OnClickListener {

    LinearLayout layoutList;
    Button buttonAdd,next;
    ArrayList<PartABContent> partAList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part_a);

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


        layoutList = findViewById(R.id.layout_list);
        buttonAdd = findViewById(R.id.button_add);
        next=(Button)findViewById(R.id.pnext);

        buttonAdd.setOnClickListener(this);
        next.setOnClickListener(this);
       // buttonSubmitList.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.button_add:

                addView();

                break;


            case R.id.pnext:

                if(checkIfValidAndRead()){

                    Intent intent = new Intent(part_A.this, part_B.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("partA",partAList);
                    intent.putExtras(bundle);
                    startActivity(intent);

                }

                break;

        }


    }

    private boolean checkIfValidAndRead() {
        partAList.clear();
        boolean result = true;

        for(int i=0;i<layoutList.getChildCount();i++){

            View partAView = layoutList.getChildAt(i);

            TextInputLayout edittitle = (TextInputLayout) partAView.findViewById(R.id.edit_title);
            TextInputLayout editcontent = (TextInputLayout) partAView.findViewById(R.id.edit_content);
            TextInputLayout edithours = (TextInputLayout) partAView.findViewById(R.id.edit_hours);

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



            partAList.add(partABContent);

        }

        if(partAList.size()==0){
            result = false;
            Toast.makeText(this, "Add Part-A Syllabus!", Toast.LENGTH_SHORT).show();
        }else if(!result){
            Toast.makeText(this, "Enter All Details Correctly!", Toast.LENGTH_SHORT).show();
        }


        return result;
    }
    private void addView() {

        final View partAView = getLayoutInflater().inflate(R.layout.row_add_parta,null,false);

        TextInputLayout editText1 = (TextInputLayout) partAView.findViewById(R.id.edit_title);
        TextInputLayout editText2 = (TextInputLayout) partAView.findViewById(R.id.edit_content);
        TextInputLayout editText3 = (TextInputLayout) partAView.findViewById(R.id.edit_hours);
        ImageView imageClose = (ImageView)partAView.findViewById(R.id.image_remove);

        imageClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeView(partAView);
            }
        });

        layoutList.addView(partAView);

    }

    private void removeView(View view){

        layoutList.removeView(view);

    }
}
