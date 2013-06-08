package com.soebes.casestudy;


import static org.fest.assertions.Assertions.assertThat;

import java.util.Calendar;
import java.util.TimeZone;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.soebes.casestudy.bo.EntriesBO;

public class CreatePostFromEntryTest extends BOTestBase {

    private EntriesBO entry;

    @BeforeClass
    public void beforeClass() {
        entry = new EntriesBO();
        entry.setBody("This is a Test Message");
        entry.setId(Long.valueOf(1L));
        entry.setIsDraft("false");
        
        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        cal.set(2013, 3, 12, 15, 34, 12);

        entry.setTimestamp(cal.getTimeInMillis() / 1000L);
        entry.setTitle("This is the Title");
    }

//    @Test
//    public void firstTest() {
//        CreatePostFromEntry cpfe = new CreatePostFromEntry(entry);
//        
//        String fileName = cpfe.createFileName();
//        assertThat(fileName).isEqualTo("2013-04-12-This-Is-The-Title.md");
//    }

    @Test
    public void shouldReturnSingleSpaceDelimitedFileName() {
        CreatePostFromEntry cpfe = new CreatePostFromEntry(entry);
        String result = cpfe.convertTitleToFileName("this   is the     best    thing");
        assertThat(result).isEqualTo("this-is-the-best-thing");
    }

    @Test
    public void shouldReturnCorrectFileNameFromTitle() {
        CreatePostFromEntry cpfe = new CreatePostFromEntry(entry);
        String result = cpfe.convertTitleToFileName("This is the best");
        assertThat(result).isEqualTo("this-is-the-best");
    }

    @Test
    public void shouldReturnConvertedUmlautFromTitle() {
        CreatePostFromEntry cpfe = new CreatePostFromEntry(entry);
        String result = cpfe.convertTitleToFileName("Das ist ö ä ü ß");
        assertThat(result).isEqualTo("das-ist-oe-ae-ue-ss");
    }

    @Test
    public void shouldReturnConvertedSpecialCharactersFromTitle() {
        CreatePostFromEntry cpfe = new CreatePostFromEntry(entry);
        String result = cpfe.convertTitleToFileName("Das-ist: noch mehr als man denkt? hier !$%&/() noch mehr");
        assertThat(result).isEqualTo("das-ist-noch-mehr-als-man-denkt-hier-noch-mehr");
    }
}