package com.soebes.casestudy.bo;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = TabellenNamen.ENTRIES)
public class EntriesBO extends BaseBO {
    
    private String title;
    private String body;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
    

}
