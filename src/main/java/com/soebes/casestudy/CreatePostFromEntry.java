package com.soebes.casestudy;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import com.google.common.base.Joiner;
import com.soebes.casestudy.bo.EntryBO;

public class CreatePostFromEntry {
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat DATE_FORMAT_YAML = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private EntryBO entry;

    public CreatePostFromEntry(EntryBO entry) {
        this.entry = entry;
    }

    public String createFileName() {
        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        cal.setTimeInMillis(getEntry().getTimestamp() * 1000);
        String result = DATE_FORMAT.format(cal.getTime()) + "-" + convertTitleToFileName(getEntry().getTitle()) + ".md";
        return result;
    }

    private String convertTitleToFileName(String title) {
        String result = title.replaceAll("[+,:/&%$§\"!()?]", "");
        result = result.replaceAll("[\\.]", " ");
        result = result.replaceAll("[ ]+", "-");
        result = result.replaceAll("[-]+", "-");
        result = result.replaceAll("ö", "oe");
        result = result.replaceAll("ä", "ae");
        result = result.replaceAll("ü", "ue");
        result = result.replaceAll("ß", "ss");
        result = result.replaceAll("ã¼", "ue");
        result = result.replaceAll("&#223;", "ss");

        result = result.toLowerCase();
        return result;
    }

    public StringBuilder createBodyText() {
        StringBuilder result = new StringBuilder();

        result.append(getEntry().getBody());
        return result;
    }

    public StringBuilder createYAMLHeader() {
        StringBuilder result = new StringBuilder();
        result.append("---" + "\n");
        result.append("layout: post\n");
        result.append("title: \"" + getEntry().getTitle() + "\"\n");

        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        cal.setTimeInMillis(getEntry().getTimestamp() * 1000);

        result.append("date: " + DATE_FORMAT_YAML.format(cal.getTime()) + "\n");

        result.append("tags: " + Joiner.on(",").join(getEntry().getCategories()) + "\n");

        result.append("categories: " + Joiner.on(",").join(getEntry().getCategories()) + "\n");

        result.append("post-type: blog\n");

        result.append("---\n");

        return result;
    }

    public void writeYAMLFile(File path) throws IOException {
        StringBuilder createYAMLHeader = createYAMLHeader();
        createYAMLHeader.append(createBodyText());

        File yamlFile = new File(path, createFileName());

        FileWriter fw = new FileWriter(yamlFile);
        fw.write(createYAMLHeader.toString());

        fw.close();
    }

    public EntryBO getEntry() {
        return entry;
    }

    public void setEntry(EntryBO entry) {
        this.entry = entry;
    }

}
