package com.soebes.casestudy;

import static org.fest.assertions.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.soebes.casestudy.bo.CategoryBO;
import com.soebes.casestudy.bo.EntryBO;

public class CreatePostFromEntryTest extends BOTestBase {

    private EntryBO entry;

    @BeforeClass
    public void beforeClass() {
        entry = new EntryBO();
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

        List<CategoryBO> categories = Lists.newArrayList(new CategoryBO("Category1"), new CategoryBO("Category2"));
        entry.setCategories(categories);
    }

    private String createPostFileNameFromEntryTitle(String title) {
        entry.setTitle(title);
        CreatePostFromEntry cpfe = new CreatePostFromEntry(entry);
        String fileName = cpfe.createFileName();
        return fileName;
    }

    private StringBuilder createPostYAMLHeaderFromTitle(String title) {
        entry.setTitle(title);
        CreatePostFromEntry cpfe = new CreatePostFromEntry(entry);
        return cpfe.createYAMLHeader();
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

    @Test
    public void shouldReturnConvertedTitle() {
        String result = createPostFileNameFromEntryTitle("Automatic Revision Control System 0.5.0 (Default) - 11.03.2007");
        assertThat(result).isEqualTo("2013-04-12-automatic-revision-control-system-0-5-0-default-11-03-2007.md");
    }

    @Test
    public void shouldReturnXXXTitle() {
        String result = createPostFileNameFromEntryTitle("Automatic Revision Control System 0.5.0 11.03.2007");
        assertThat(result).isEqualTo("2013-04-12-automatic-revision-control-system-0-5-0-11-03-2007.md");
    }
    
    @Test
    public void shouldReturnYAMLHeaderFromTitle() {
        StringBuilder sb = createPostYAMLHeaderFromTitle("This is the title");
        //@formatter:off
        assertThat(sb.toString()).isEqualTo(
          Joiner.on("\n").join(
            "---", 
            "layout: post",
            "title: \"This is the title\"",
            "date: 2013-04-12 15:34:12",
            "tags: Category1,Category2",
            "categories: Category1,Category2",
            "post-type: blog",
            "---",
            ""
          )
        );
        //@formatter:on
    }

    @Test
    public void shouldCreateYAMLFileForPost() throws IOException {
        entry.setTitle("This is the first title");
        CreatePostFromEntry cpfe = new CreatePostFromEntry(entry);
        cpfe.writeYAMLFile(new File("target"));
    }

}