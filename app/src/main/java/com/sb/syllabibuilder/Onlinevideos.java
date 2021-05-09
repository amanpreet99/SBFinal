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

public class Onlinevideos extends AppCompatActivity implements View.OnClickListener {

    LinearLayout layoutList;
    Button buttonAdd,next;
    ArrayList<PartABContent> partBList = new ArrayList<>();
    ArrayList<PartABContent> partAList= new ArrayList<>();
    ArrayList<Content> TList= new ArrayList<>();
    ArrayList<Content> RBList= new ArrayList<>();
    ArrayList<Content> EBList= new ArrayList<>();
    ArrayList<Content> OVList= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onlinevideos);

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


        layoutList = findViewById(R.id.layout_lists_online_videos);
        buttonAdd = findViewById(R.id.button_addt);
        next=(Button)findViewById(R.id.textnext);

        buttonAdd.setOnClickListener(this);
        next.setOnClickListener(this);
        partAList = (ArrayList<PartABContent>) getIntent().getExtras().getSerializable("partA");
        partBList = (ArrayList<PartABContent>) getIntent().getExtras().getSerializable("partB");
        TList = (ArrayList<Content>) getIntent().getExtras().getSerializable("textbooks");
        RBList = (ArrayList<Content>) getIntent().getExtras().getSerializable("reference");
        EBList = (ArrayList<Content>) getIntent().getExtras().getSerializable("ebooks");

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.button_addt:

                addView();

                break;


            case R.id.textnext:

                if(checkIfValidAndRead()){

                    Intent intent = new Intent(Onlinevideos.this, Final.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("partA",partAList);
                    bundle.putSerializable("partB",partBList);
                    bundle.putSerializable("textbooks",TList);
                    bundle.putSerializable("reference",RBList);
                    bundle.putSerializable("ebooks",EBList);
                    bundle.putSerializable("onlinevideos",OVList);
                    intent.putExtras(bundle);
                    startActivity(intent);

                }

                break;

        }


    }

    private boolean checkIfValidAndRead() {
        OVList.clear();
        boolean result = true;

        for(int i=0;i<layoutList.getChildCount();i++){

            View OVView = layoutList.getChildAt(i);

            TextInputLayout t1= (TextInputLayout)OVView.findViewById(R.id.names);

            Content content = new Content();

            if(!t1.getEditText().getText().toString().equals("")){
                content.setTitleedit(t1.getEditText().getText().toString());
            }else {
                result = false;
                break;
            }
            OVList.add(content);

        }

        if(OVList.size()==0){
            result = false;
            Toast.makeText(this, "Add Links for online videos!", Toast.LENGTH_SHORT).show();
        }else if(!result){
            Toast.makeText(this, "Enter All Details Correctly!", Toast.LENGTH_SHORT).show();
        }


        return result;
    }

    private void addView() {
        final View OVView = getLayoutInflater().inflate(R.layout.activity_addsingle,null,false);

        TextInputLayout t1= (TextInputLayout)OVView.findViewById(R.id.names);
        ImageView imageClose = (ImageView)OVView.findViewById(R.id.image_remove);
        imageClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeView(OVView);
            }
        });

        layoutList.addView(OVView);

    }

    private void removeView(View view){

        layoutList.removeView(view);

    }
}