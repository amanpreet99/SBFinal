package com.sb.syllabibuilder;
import java.io.Serializable;

public class Content implements Serializable{

    public String titleedit;

    public Content() {

    }

    public Content(String titleedit) {
        this.titleedit=titleedit;
    }

    public String getTitleedit() {
        return titleedit;
    }

    public void setTitleedit(String titleedit) {
        this.titleedit = titleedit;
    }

}


