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
        cal.set(Calendar.YEAR, 2013);
        cal.set(Calendar.MONTH, 3);
        cal.set(Calendar.DAY_OF_MONTH, 12);
        cal.set(Calendar.HOUR_OF_DAY, 15);
        cal.set(Calendar.MINUTE, 34);
        cal.set(Calendar.SECOND, 12);

        entry.setTimestamp(cal.getTimeInMillis() / 1000L);
        entry.setTitle("This is the Title");
    }

    private String createPostFileNameFromEntryTitle(String title) {
        entry.setTitle(title);
        CreatePostFromEntry cpfe = new CreatePostFromEntry(entry);
        String fileName = cpfe.createFileName();
        return fileName;
    }
    
    @Test
    public void shouldReturnConvertedFileNameFromTitle() {
        String fileName = createPostFileNameFromEntryTitle("This   is a file name");
        assertThat(fileName).isEqualTo("2013-04-12-this-is-a-file-name.md");
    }

    @Test
    public void shouldReturnSingleDashDelimitedFileName() {
        String result = createPostFileNameFromEntryTitle("this   is the     best    thing");
        assertThat(result).isEqualTo("2013-04-12-this-is-the-best-thing.md");
    }

    @Test
    public void shouldReturnCorrectFileNameFromTitle() {
        String result = createPostFileNameFromEntryTitle("This is the best");
        assertThat(result).isEqualTo("2013-04-12-this-is-the-best.md");
    }

    @Test
    public void shouldReturnConvertedUmlautFromTitle() {
        String result = createPostFileNameFromEntryTitle("Das ist ö ä ü ß");
        assertThat(result).isEqualTo("2013-04-12-das-ist-oe-ae-ue-ss.md");
    }

    @Test
    public void shouldReturnConvertedSpecialCharactersFromTitle() {
        String result = createPostFileNameFromEntryTitle("Das-ist: noch mehr als man denkt? hier !$%&/() noch mehr");
        assertThat(result).isEqualTo("2013-04-12-das-ist-noch-mehr-als-man-denkt-hier-noch-mehr.md");
    }
}