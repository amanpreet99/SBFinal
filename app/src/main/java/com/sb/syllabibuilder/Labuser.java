package com.sb.syllabibuilder;

import java.io.Serializable;

public class Labuser implements Serializable{

    public String pracnumber;
    public String pracname;

    public Labuser() {

    }

    public Labuser(String pracname,String pracnumber) {
        this.pracname=pracname;
        this.pracnumber=pracnumber;
    }

    public String getPracnumber() {
        return pracnumber;
    }

    public void setPracnumber(String pracnumber) {
        this.pracnumber = pracnumber;
    }

    public String getPracname() {
        return pracname;
    }

    public void setPracname(String pracname) {
        this.pracname = pracname;
    }
}

