package com.sb.syllabibuilder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import java.util.ArrayList;

public class Final extends AppCompatActivity {
    RecyclerView recyclerparta,recyclerpartb,recyclertextbook,recyclerreference,recyclerebook,recycleronlinevideo;
    ArrayList<PartABContent> partAList= new ArrayList<>();
    ArrayList<PartABContent> partBList= new ArrayList<>();
    ArrayList<Content> TList= new ArrayList<>();
    ArrayList<Content> RBList= new ArrayList<>();
    ArrayList<Content> EBList= new ArrayList<>();
    ArrayList<Content> OVList= new ArrayList<>();
    TextView line2,line3,line4,line5,line6a,line6b,line7a,line7b,line8a,line8b,line9a,line9b,line10a,line10b,line11a,line11b,prerequisite,add,co1,co2,co3,co4,co5,co6;
    String department,scheme,semester,course,lecture,thpr,tutorial,subname,subcode,credit,elective,numerical,prereq,additional,c1,c2,c3,c4,c5,c6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);
        line2 = (TextView) findViewById(R.id.secondline);
        line3 = (TextView) findViewById(R.id.thirdline);
        line4 = (TextView) findViewById(R.id.subjectcode);
        line5 = (TextView) findViewById(R.id.subjectname);
        line6a = (TextView) findViewById(R.id.table1);
        line7a = (TextView) findViewById(R.id.table2);
        line8a = (TextView) findViewById(R.id.table3);
        line9a = (TextView) findViewById(R.id.table4);
        line10a = (TextView) findViewById(R.id.table5);
        line11a = (TextView) findViewById(R.id.table6);
        line6b = (TextView) findViewById(R.id.table7);
        line7b = (TextView) findViewById(R.id.table8);
        line8b = (TextView) findViewById(R.id.table9);
        line9b = (TextView) findViewById(R.id.table10);
        line10b = (TextView) findViewById(R.id.table11);
        line11b = (TextView) findViewById(R.id.table12);
        prerequisite=(TextView)findViewById(R.id.preq);
        add=(TextView)findViewById(R.id.additional);
        co1=(TextView)findViewById(R.id.co1);
        co2=(TextView)findViewById(R.id.co2);
        co3=(TextView)findViewById(R.id.co3);
        co4=(TextView)findViewById(R.id.co4);
        co5=(TextView)findViewById(R.id.co5);
        co6=(TextView)findViewById(R.id.co6);

        SharedPreferences sharedPreferences= getSharedPreferences("mypref",MODE_PRIVATE);
        department=sharedPreferences.getString("departmentkey","");
        course=sharedPreferences.getString("coursekey","");
        scheme=sharedPreferences.getString("schemekey","2018");
        thpr=sharedPreferences.getString("thprkey","");
        semester=sharedPreferences.getString("semesterkey","");
        lecture=sharedPreferences.getString("lecturekey","");
        tutorial=sharedPreferences.getString("tutkey","");
        subname=sharedPreferences.getString("subnamekey","");
        subcode=sharedPreferences.getString("subcodekey","");
        credit=sharedPreferences.getString("creditkey","");
        elective=sharedPreferences.getString("electivekey","");
        numerical=sharedPreferences.getString("numericalkey","");
        prereq=sharedPreferences.getString("prekey","");
        additional=sharedPreferences.getString("additionalkey","");
        c1=sharedPreferences.getString("co1key","");
        c2=sharedPreferences.getString("co2key","");
        c3=sharedPreferences.getString("co3key","");
        c4=sharedPreferences.getString("co4key","");
        c5=sharedPreferences.getString("co5key","");
        c6=sharedPreferences.getString("co6key","");

        line2.setText("Department of "+department);
        line3.setText("Syllabus of "+course+"("+department+") Scheme "+scheme);
        String sc = "<b>" + "Subject Code: " + "</b> " + subcode;
        line4.setText(Html.fromHtml(sc));
        String sn = "<b>" + "Subject Name: " + "</b> " + subname;
        line5.setText(Html.fromHtml(sn));
        String prog = "<b>" + "Programme: " + "</b> " + course+"("+department+")";
        line6a.setText(Html.fromHtml(prog));
        String sem = "<b>" + "Semester: " + "</b> " + semester;
        line7a.setText(Html.fromHtml(sem));
        String tp = "<b>" + "Theory/Practical: " + "</b> " + thpr;
        line8a.setText(Html.fromHtml(tp));
        String im = "<b>" + "Internal Marks: " + "</b> " + "40";
        line9a.setText(Html.fromHtml(im));
        String em = "<b>" + "External Marks: " + "</b> " + "60";
        line10a.setText(Html.fromHtml(em));
        String tm = "<b>" + "Total Marks: " + "</b> " + "100";
        line11a.setText(Html.fromHtml(tm));
        String ltp = "<b>" + "L: " + "</b> " + lecture+"<b>" + " T: " + "</b> " + tutorial+"<b>" + " P: " + "</b> "+"0";
        line6b.setText(Html.fromHtml(ltp));
        String th = "<b>" + "Teaching Hours: " + "</b> " + "40";
        line7b.setText(Html.fromHtml(th));
        String c = "<b>" + "Credits: " + "</b> " + credit;
        line8b.setText(Html.fromHtml(c));
        String pnum = "<b>" + "Percentage of numerical/design problems: " + "</b> " + numerical+"%";
        line9b.setText(Html.fromHtml(pnum));
        String dur = "<b>" + "Duration of end semester examination(ESE): " + "</b> " + "3 Hours";
        line10b.setText(Html.fromHtml(dur));
        String es = "<b>" + "Elective Status: " + "</b> " + elective;
        line11b.setText(Html.fromHtml(es));
        String pre = "<b>" + "Prerequisites: " + "</b> " + prereq;
        prerequisite.setText(Html.fromHtml(pre));
        String addit = "<b>" + "Additional Material Allowed in ESE: " + "</b> " + additional;
        add.setText(Html.fromHtml(addit));
        co1.setText(c1);
        co2.setText(c2);
        co3.setText(c3);
        co4.setText(c4);
        co5.setText(c5);
        co6.setText(c6);

        recyclerparta = findViewById(R.id.recycler_parta);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerparta.setLayoutManager(layoutManager);
        partAList = (ArrayList<PartABContent>) getIntent().getExtras().getSerializable("partA");
        recyclerparta.setAdapter(new PartAAdapter(partAList));

        recyclerpartb = findViewById(R.id.recycler_partb);
        LinearLayoutManager layoutManagerB = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerpartb.setLayoutManager(layoutManagerB);
        partBList = (ArrayList<PartABContent>) getIntent().getExtras().getSerializable("partB");
        recyclerpartb.setAdapter(new PartBAdapter(partBList));

        recyclertextbook = findViewById(R.id.recycler_textbook);
        LinearLayoutManager layoutManagerT = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclertextbook.setLayoutManager(layoutManagerT);
        TList = (ArrayList<Content>) getIntent().getExtras().getSerializable("textbooks");
        recyclertextbook.setAdapter(new TextbookAdapter(TList));

        recyclerreference = findViewById(R.id.recycler_reference);
        LinearLayoutManager layoutManagerR = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerreference.setLayoutManager(layoutManagerR);
        RBList = (ArrayList<Content>) getIntent().getExtras().getSerializable("reference");
        recyclerreference.setAdapter(new ReferenceAdapter(RBList));

        recyclerebook = findViewById(R.id.recycler_ebook);
        LinearLayoutManager layoutManagerE = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerebook.setLayoutManager(layoutManagerE);
        EBList = (ArrayList<Content>) getIntent().getExtras().getSerializable("ebooks");
        recyclerebook.setAdapter(new ebooksAdapter(EBList));

        recycleronlinevideo = findViewById(R.id.recycler_online);
        LinearLayoutManager layoutManagerO = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recycleronlinevideo.setLayoutManager(layoutManagerO);
        OVList = (ArrayList<Content>) getIntent().getExtras().getSerializable("onlinevideos");
        recycleronlinevideo.setAdapter(new OnlineVideosAdapter(OVList));

    }
}