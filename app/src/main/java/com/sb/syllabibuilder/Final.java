package com.sb.syllabibuilder;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
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
Button btn;

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
        btn = (Button)findViewById(R.id.pdf);

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


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    createpdf();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

    }
    public void createpdf()throws FileNotFoundException {
        String pdfPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
        File file = new File(pdfPath,"s.pdf");
        OutputStream outputStream = new FileOutputStream(file);
        PdfWriter writer = new PdfWriter(file);
        PdfDocument pdfDocument = new PdfDocument(writer);
        com.itextpdf.layout.Document document = new com.itextpdf.layout.Document(pdfDocument);
        pdfDocument.setDefaultPageSize(PageSize.A4);

        Paragraph p = new Paragraph("Department of "+department).setBold().setFontSize(14).setTextAlignment(TextAlignment.CENTER);
        Paragraph p1 = new Paragraph("Syllabus of "+course+"("+department+") Scheme "+scheme).setBold().setFontSize(16).setTextAlignment(TextAlignment.CENTER);

        Paragraph p2 = new Paragraph("Subject Code: ").setBold().setFontSize(14).setTextAlignment(TextAlignment.CENTER);
        p2.add(subcode);
        Paragraph p3 = new Paragraph("Subject Name: "+ subname).setBold().setFontSize(14).setTextAlignment(TextAlignment.CENTER);

        float columnWidth1[] ={120,450};
        float columnWidth2[] = {200,200,300,100};
        Table table1 = new Table(columnWidth1);
        Table table= new Table(columnWidth1);
        Table table2 = new Table(columnWidth2);

        table2.addCell(new Cell().add(new Paragraph("programme: ").setBold()));
        table2.addCell(new Cell().add(new Paragraph(course + "("+department+")")));

        table2.addCell(new Cell().add(new Paragraph("L: "+lecture+" T: "+tutorial+" P: 0").setBold()).setBorderRight(Border.NO_BORDER));
        table2.addCell(new Cell().add(new Paragraph(" ").setBorderLeft(Border.NO_BORDER)).setBorderLeft(Border.NO_BORDER));

        table2.addCell(new Cell().add(new Paragraph("Semester: ").setBold()));
        table2.addCell(new Cell().add(new Paragraph(semester).setBorderLeft(Border.NO_BORDER)));

        table2.addCell(new Cell().add(new Paragraph("Teaching Hours: ").setBold()));
        table2.addCell(new Cell().add(new Paragraph("40").setBorderLeft(Border.NO_BORDER)));

        table2.addCell(new Cell().add(new Paragraph("Theory/Practical: ").setBold()));
        table2.addCell(new Cell().add(new Paragraph(thpr).setBorderLeft(Border.NO_BORDER)));

        table2.addCell(new Cell().add(new Paragraph("Credits: ").setBold()));
        table2.addCell(new Cell().add(new Paragraph(credit).setBorderLeft(Border.NO_BORDER)));

        table2.addCell(new Cell().add(new Paragraph("Internal Marks: ").setBold()));
        table2.addCell(new Cell().add(new Paragraph("40").setBorderLeft(Border.NO_BORDER)));

        table2.addCell(new Cell().add(new Paragraph("Percentage of numercial/design problems: ").setBold()));
        table2.addCell(new Cell().add(new Paragraph(numerical).setBorderLeft(Border.NO_BORDER)));


        table2.addCell(new Cell().add(new Paragraph("External Marks: ").setBold()));
        table2.addCell(new Cell().add(new Paragraph("60").setBorderLeft(Border.NO_BORDER)));

        table2.addCell(new Cell().add(new Paragraph("Duration of end semester examination(ESE): ").setBold()));
        table2.addCell(new Cell().add(new Paragraph("3 hours").setBorderLeft(Border.NO_BORDER)));

        table2.addCell(new Cell().add(new Paragraph("Total Marks: ").setBold()));
        table2.addCell(new Cell().add(new Paragraph("100").setBorderLeft(Border.NO_BORDER)));

        table2.addCell(new Cell().add(new Paragraph("Elective Status: ").setBold()));
        table2.addCell(new Cell().add(new Paragraph(elective).setBorderLeft(Border.NO_BORDER)));

        table.addCell(new Cell().add(new Paragraph("Prerequisites:").setBold().setBorder(Border.NO_BORDER)));
        table.addCell(new Cell().add(new Paragraph(prereq).setBorder(Border.NO_BORDER)));

        table.addCell(new Cell().add(new Paragraph("Additional Material Allowed in ESE:").setBold().setBorder(Border.NO_BORDER)));
        table.addCell(new Cell().add(new Paragraph(additional).setBorder(Border.NO_BORDER)));


        table1.addCell(new Cell().add(new Paragraph("CO").setBold()));
        table1.addCell(new Cell().add(new Paragraph("COURSE OUTCOME")).setBold().setTextAlignment(TextAlignment.CENTER));

        table1.addCell(new Cell().add(new Paragraph("CO1").setBold()));
        table1.addCell(new Cell().add(new Paragraph(c1)));

        table1.addCell(new Cell().add(new Paragraph("CO2").setBold()));
        table1.addCell(new Cell().add(new Paragraph(c2)));

        table1.addCell(new Cell().add(new Paragraph("CO3").setBold()));
        table1.addCell(new Cell().add(new Paragraph(c3)));

        table1.addCell(new Cell().add(new Paragraph("CO4").setBold()));
        table1.addCell(new Cell().add(new Paragraph(c4)));

        table1.addCell(new Cell().add(new Paragraph("CO5").setBold()));
        table1.addCell(new Cell().add(new Paragraph(c5)));

        table1.addCell(new Cell().add(new Paragraph("CO6").setBold()));
        table1.addCell(new Cell().add(new Paragraph(c6)));
        Intent intent = getIntent();


        Paragraph p4 = new Paragraph("\nDetailed context").setBold();
        Paragraph p5 = new Paragraph("PART-A").setBold().setTextAlignment(TextAlignment.CENTER);
        //int i = partAList.size();
        Paragraph p6 = new Paragraph(String.valueOf(partAList));
        Paragraph p7 = new Paragraph("PART-B").setBold().setTextAlignment(TextAlignment.CENTER);
        Paragraph p8 = new Paragraph(String.valueOf(partBList));
        Paragraph p9 = new Paragraph("Textbooks").setBold();
        Paragraph p13 = new Paragraph();
        Paragraph p10 = new Paragraph("Reference Book").setBold();
        Paragraph p14 = new Paragraph(String.valueOf(RBList));
        Paragraph p11 = new Paragraph("E-books and online learning material").setBold();
        Paragraph p15 = new Paragraph(String.valueOf(EBList));
        Paragraph p12 = new Paragraph("Online courses and video lectures").setBold();
//        for(Content s: OVList){
//            Paragraph p16 = new Paragraph(String.valueOf(s));
//            document.add(p16);
//        }
        Paragraph p16 = new Paragraph(String.valueOf(recyclerreference));


        document.add(p);
        document.add(p1);
        document.add(p2);
        document.add(p3);
        document.add(table2);
        document.add(table);
        document.add(table1);
        document.add(p4);
        document.add(p5);
        document.add(p6);
        document.add(p7);
        document.add(p8);
        document.add(p9);
        document.add(p13);
        document.add(p10);
        document.add(p14);
        document.add(p11);
        document.add(p15);
        document.add(p12);
        document.add(p16);

        //document.add(p4);

        document.close();
        Toast.makeText(this,"PDF created",Toast.LENGTH_LONG);



    }
}