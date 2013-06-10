package com.soebes.casestudy;

import java.text.SimpleDateFormat;
import java.util.List;

import com.soebes.casestudy.bo.EntryBO;

public class CreatePostsFromEntries {
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private List<EntryBO> entries;

    public List<EntryBO> getEntries() {
        return entries;
    }

    public void setEntries(List<EntryBO> entries) {
        this.entries = entries;
    }

}
