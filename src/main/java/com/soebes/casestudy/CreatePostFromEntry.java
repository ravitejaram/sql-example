package com.soebes.casestudy;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import com.soebes.casestudy.bo.EntriesBO;

public class CreatePostFromEntry {
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private EntriesBO entry;

    public CreatePostFromEntry(EntriesBO entry) {
        super();
        this.entry = entry;
    }

    public String createFileName() {
        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        cal.setTimeInMillis(getEntry().getTimestamp() * 1000);
        String result = DATE_FORMAT.format(cal.getTime()) + "-" + convertTitleToFileName(getEntry().getTitle() + ".md");
        return result;
    }

    private String convertTitleToFileName(String title) {
        String result = title.replaceAll("[:/&%$§\"!()?]", "");
        result = result.replaceAll("[ ]+", "-");
        result = result.replaceAll("ö", "oe");
        result = result.replaceAll("ä", "ae");
        result = result.replaceAll("ü", "ue");
        result = result.replaceAll("ß", "ss");

        result = result.toLowerCase();
        return result;
    }

    public EntriesBO getEntry() {
        return entry;
    }

    public void setEntry(EntriesBO entry) {
        this.entry = entry;
    }

}
