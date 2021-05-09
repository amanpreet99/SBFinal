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

public class ReferenceBooks extends AppCompatActivity implements View.OnClickListener {

    LinearLayout layoutList;
    Button buttonAdd,next;
    ArrayList<PartABContent> partBList = new ArrayList<>();
    ArrayList<PartABContent> partAList= new ArrayList<>();
    ArrayList<Content> TList= new ArrayList<>();
    ArrayList<Content> RBList= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reference_books);

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


        layoutList = findViewById(R.id.layout_lists_reference);
        buttonAdd = findViewById(R.id.button_addt);
        next=(Button)findViewById(R.id.textnext);

        buttonAdd.setOnClickListener(this);
        next.setOnClickListener(this);
        partAList = (ArrayList<PartABContent>) getIntent().getExtras().getSerializable("partA");
        partBList = (ArrayList<PartABContent>) getIntent().getExtras().getSerializable("partB");
        TList = (ArrayList<Content>) getIntent().getExtras().getSerializable("textbooks");

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.button_addt:

                addView();

                break;


            case R.id.textnext:

                if(checkIfValidAndRead()){

                    Intent intent = new Intent(ReferenceBooks.this, ebooks.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("partA",partAList);
                    bundle.putSerializable("partB",partBList);
                    bundle.putSerializable("textbooks",TList);
                    bundle.putSerializable("reference",RBList);
                    intent.putExtras(bundle);
                    startActivity(intent);

                }

                break;

        }


    }

    private boolean checkIfValidAndRead() {
        RBList.clear();
        boolean result = true;

        for(int i=0;i<layoutList.getChildCount();i++){

            View RBView = layoutList.getChildAt(i);

            TextInputLayout t1= (TextInputLayout)RBView.findViewById(R.id.names);

            Content content = new Content();

            if(!t1.getEditText().getText().toString().equals("")){
                content.setTitleedit(t1.getEditText().getText().toString());
            }else {
                result = false;
                break;
            }
            RBList.add(content);

        }

        if(RBList.size()==0){
            result = false;
            Toast.makeText(this, "Add Reference Books!", Toast.LENGTH_SHORT).show();
        }else if(!result){
            Toast.makeText(this, "Enter All Details Correctly!", Toast.LENGTH_SHORT).show();
        }


        return result;
    }

    private void addView() {
        final View RBView = getLayoutInflater().inflate(R.layout.activity_addsingle,null,false);

        TextInputLayout t1= (TextInputLayout)RBView.findViewById(R.id.names);
        ImageView imageClose = (ImageView)RBView.findViewById(R.id.image_remove);
        imageClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeView(RBView);
            }
        });

        layoutList.addView(RBView);

    }

    private void removeView(View view){

        layoutList.removeView(view);

    }
}