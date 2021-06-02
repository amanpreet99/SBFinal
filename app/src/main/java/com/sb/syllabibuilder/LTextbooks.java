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

public class LTextbooks extends AppCompatActivity implements View.OnClickListener {

    LinearLayout layoutListtextbooks;
    Button buttonAdd,next;
    ArrayList<Content> TList= new ArrayList<>();
    ArrayList<Labuser> LVList= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_l_textbooks);

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

        LVList = (ArrayList<Labuser>) getIntent().getExtras().getSerializable("practical");
        layoutListtextbooks = findViewById(R.id.textbook_layout_lists);
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

                    Intent intent = new Intent(LTextbooks.this,LReferenceBooks.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("ltextbooks",TList);
                    bundle.putSerializable("practical",LVList);
                    intent.putExtras(bundle);
                    startActivity(intent);

                }

                break;

        }


    }

    private boolean checkIfValidAndRead() {
        TList.clear();
        boolean result = true;

        for(int i=0;i<layoutListtextbooks.getChildCount();i++){

            View TBView = layoutListtextbooks.getChildAt(i);

            TextInputLayout t1= (TextInputLayout)TBView.findViewById(R.id.names);

            Content content = new Content();

            if(!t1.getEditText().getText().toString().equals("")){
                content.setTitleedit(t1.getEditText().getText().toString());
            }else {
                result = false;
                break;
            }
            TList.add(content);

        }

        if(TList.size()==0){
            result = false;
            Toast.makeText(this, "Add Textbooks!", Toast.LENGTH_SHORT).show();
        }else if(!result){
            Toast.makeText(this, "Enter All Details Correctly!", Toast.LENGTH_SHORT).show();
        }


        return result;
    }

    private void addView() {
        final View TBView = getLayoutInflater().inflate(R.layout.activity_addsingle,null,false);

        TextInputLayout t1= (TextInputLayout)TBView.findViewById(R.id.names);
        ImageView imageClose = (ImageView)TBView.findViewById(R.id.image_remove);
        imageClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeView(TBView);
            }
        });

        layoutListtextbooks.addView(TBView);

    }

    private void removeView(View view){

        layoutListtextbooks.removeView(view);

    }
}