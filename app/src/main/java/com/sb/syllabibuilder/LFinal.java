package com.sb.syllabibuilder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import java.util.ArrayList;

public class LFinal extends AppCompatActivity {
    RecyclerView recyclerprac,recyclertextbook,recyclerreference,recyclerebook,recycleronlinevideo;
    ArrayList<Content> TList= new ArrayList<>();
    ArrayList<Content> RBList= new ArrayList<>();
    ArrayList<Content> EBList= new ArrayList<>();
    ArrayList<Content> OVList= new ArrayList<>();
    ArrayList<Labuser> LVList= new ArrayList<>();
    TextView line2,line3,line4,line5,line6a,line6b,line7a,line7b,line8a,line8b,line9a,line9b,line10a,line10b,line11a,line11b,prerequisite,special,minor,co1,co2,co3,co4,co5,co6;
    String department,scheme,semester,course,thpr,practical,subname,subcode,credit,elective,numerical,prereq,sp,mp,c1,c2,c3,c4,c5,c6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_l_final);
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
       special=(TextView)findViewById(R.id.special);
        minor=(TextView)findViewById(R.id.minorproject);
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
        practical=sharedPreferences.getString("practicalkey","");
        subname=sharedPreferences.getString("subnamelabkey","");
        subcode=sharedPreferences.getString("subcodelabkey","");
        credit=sharedPreferences.getString("creditlabkey","");
        elective=sharedPreferences.getString("electivelabkey","");
        numerical=sharedPreferences.getString("numericallabkey","");
        prereq=sharedPreferences.getString("prelabkey","");
        sp=sharedPreferences.getString("specialkey","");
        mp=sharedPreferences.getString("pnkey","");
        c1=sharedPreferences.getString("co1lkey","");
        c2=sharedPreferences.getString("co2lkey","");
        c3=sharedPreferences.getString("co3lkey","");
        c4=sharedPreferences.getString("co4lkey","");
        c5=sharedPreferences.getString("co5lkey","");
        c6=sharedPreferences.getString("co6lkey","");

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
        String im = "<b>" + "Internal Marks: " + "</b> " + "30";
        line9a.setText(Html.fromHtml(im));
        String em = "<b>" + "External Marks: " + "</b> " + "20";
        line10a.setText(Html.fromHtml(em));
        String tm = "<b>" + "Total Marks: " + "</b> " + "50";
        line11a.setText(Html.fromHtml(tm));
        String ltp = "<b>" + "L: " + "</b> " +"0"+"<b>" + " T: " + "</b> "+"0"+"<b>" + " P: " + "</b> "+practical;
        line6b.setText(Html.fromHtml(ltp));
        String th = "<b>" + "Teaching Hours: " + "</b> " + "26";
        line7b.setText(Html.fromHtml(th));
        String c = "<b>" + "Credits: " + "</b> " + credit;
        line8b.setText(Html.fromHtml(c));
        String pnum = "<b>" + "Percentage of numerical/design problems: " + "</b> " + numerical+"%";
        line9b.setText(Html.fromHtml(pnum));
        String dur = "<b>" + "Duration of end semester examination(ESE): " + "</b> " + "1.5 Hours";
        line10b.setText(Html.fromHtml(dur));
        String es = "<b>" + "Elective Status: " + "</b> " + elective;
        line11b.setText(Html.fromHtml(es));
        String pre = "<b>" + "Prerequisites: " + "</b> " + prereq;
        prerequisite.setText(Html.fromHtml(pre));
        co1.setText(c1);
        co2.setText(c2);
        co3.setText(c3);
        co4.setText(c4);
        co5.setText(c5);
        co6.setText(c6);
        String spect = "<b>" + "Special Instruction Related to resources requirement: " + "</b> " + sp;
        special.setText(Html.fromHtml(spect));
        minor.setText(Html.fromHtml(mp));

        recyclerprac = findViewById(R.id.recycler_prac);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerprac.setLayoutManager(layoutManager);
        LVList = (ArrayList<Labuser>) getIntent().getExtras().getSerializable("practical");
        recyclerprac.setAdapter(new LabAdapter(LVList));


        recyclertextbook = findViewById(R.id.recycler_textbook);
        LinearLayoutManager layoutManagerT = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclertextbook.setLayoutManager(layoutManagerT);
        TList = (ArrayList<Content>) getIntent().getExtras().getSerializable("ltextbooks");
        recyclertextbook.setAdapter(new TextbookAdapter(TList));

        recyclerreference = findViewById(R.id.recycler_reference);
        LinearLayoutManager layoutManagerR = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerreference.setLayoutManager(layoutManagerR);
        RBList = (ArrayList<Content>) getIntent().getExtras().getSerializable("lreference");
        recyclerreference.setAdapter(new ReferenceAdapter(RBList));

        recyclerebook = findViewById(R.id.recycler_ebook);
        LinearLayoutManager layoutManagerE = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerebook.setLayoutManager(layoutManagerE);
        EBList = (ArrayList<Content>) getIntent().getExtras().getSerializable("lebooks");
        recyclerebook.setAdapter(new ebooksAdapter(EBList));

        recycleronlinevideo = findViewById(R.id.recycler_online);
        LinearLayoutManager layoutManagerO = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recycleronlinevideo.setLayoutManager(layoutManagerO);
        OVList = (ArrayList<Content>) getIntent().getExtras().getSerializable("lonlinevideos");
        recycleronlinevideo.setAdapter(new OnlineVideosAdapter(OVList));

    }
}