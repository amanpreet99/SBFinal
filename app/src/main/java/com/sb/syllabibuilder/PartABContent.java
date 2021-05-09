package com.sb.syllabibuilder;

import java.io.Serializable;

public class PartABContent implements Serializable{

    public String titleedit;
    public String contentedit;
    public String hourstitle;

    public PartABContent() {

    }

    public PartABContent(String titleedit, String contentedit, String hourstitle) {
        this.titleedit=titleedit;
        this.contentedit=contentedit;
        this.hourstitle=hourstitle;
    }

    public String getTitleedit() {
        return titleedit;
    }

    public void setTitleedit(String titleedit) {
        this.titleedit = titleedit;
    }

    public String getContentedit() {
        return contentedit;
    }

    public void setContentedit(String contentedit) {
        this.contentedit = contentedit;
    }

    public String getHourstitle() {
        return hourstitle;
    }

    public void setHourstitle(String hourstitle) {
        this.hourstitle = hourstitle;
    }
}

