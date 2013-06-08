package com.soebes.casestudy;

import java.text.SimpleDateFormat;
import java.util.List;

import com.soebes.casestudy.bo.EntriesBO;

public class CreatePostsFromEntries {
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private List<EntriesBO> entries;

    public List<EntriesBO> getEntries() {
        return entries;
    }

    public void setEntries(List<EntriesBO> entries) {
        this.entries = entries;
    }

}
