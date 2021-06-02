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

public class Lebooks extends AppCompatActivity implements View.OnClickListener {

    LinearLayout layoutList;
    Button buttonAdd,next;
    ArrayList<Content> TList= new ArrayList<>();
    ArrayList<Content> RBList= new ArrayList<>();
    ArrayList<Content> EBList= new ArrayList<>();
    ArrayList<Labuser> LVList= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lebooks);

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


        layoutList = findViewById(R.id.layout_lists_ebooks);
        buttonAdd = findViewById(R.id.button_addt);
        next=(Button)findViewById(R.id.textnext);

        buttonAdd.setOnClickListener(this);
        next.setOnClickListener(this);

        TList = (ArrayList<Content>) getIntent().getExtras().getSerializable("ltextbooks");
        RBList = (ArrayList<Content>) getIntent().getExtras().getSerializable("lreference");
        LVList = (ArrayList<Labuser>) getIntent().getExtras().getSerializable("practical");

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.button_addt:

                addView();

                break;


            case R.id.textnext:

                if(checkIfValidAndRead()){

                    Intent intent = new Intent(Lebooks.this, LOnlineVideos.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("practical",LVList);
                    bundle.putSerializable("ltextbooks",TList);
                    bundle.putSerializable("lreference",RBList);
                    bundle.putSerializable("lebooks",EBList);
                    intent.putExtras(bundle);
                    startActivity(intent);

                }

                break;

        }


    }

    private boolean checkIfValidAndRead() {
        EBList.clear();
        boolean result = true;

        for(int i=0;i<layoutList.getChildCount();i++){

            View EBView = layoutList.getChildAt(i);

            TextInputLayout t1= (TextInputLayout)EBView.findViewById(R.id.names);

            Content content = new Content();

            if(!t1.getEditText().getText().toString().equals("")){
                content.setTitleedit(t1.getEditText().getText().toString());
            }else {
                result = false;
                break;
            }
            EBList.add(content);

        }

        if(EBList.size()==0){
            result = false;
            Toast.makeText(this, "Add E-Books!", Toast.LENGTH_SHORT).show();
        }else if(!result){
            Toast.makeText(this, "Enter All Details Correctly!", Toast.LENGTH_SHORT).show();
        }


        return result;
    }

    private void addView() {
        final View EBView = getLayoutInflater().inflate(R.layout.activity_addsingle,null,false);

        TextInputLayout t1= (TextInputLayout)EBView.findViewById(R.id.names);
        ImageView imageClose = (ImageView)EBView.findViewById(R.id.image_remove);
        imageClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeView(EBView);
            }
        });

        layoutList.addView(EBView);

    }

    private void removeView(View view){

        layoutList.removeView(view);

    }
}