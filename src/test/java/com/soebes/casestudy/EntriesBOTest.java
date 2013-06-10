package com.soebes.casestudy;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.google.common.base.Joiner;
import com.soebes.casestudy.bo.EntryBO;

public class EntriesBOTest extends BOTestBase {
    private static Logger LOGGER = Logger.getLogger(EntriesBOTest.class);

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");

    @Test
    public void testGet() {
        CriteriaBuilder criteriaBuilder = getEm().getCriteriaBuilder();
        CriteriaQuery<EntryBO> criteria = criteriaBuilder.createQuery(EntryBO.class);
        Root<EntryBO> from = criteria.from(EntryBO.class);

        criteria.where( criteriaBuilder.equal(from.get("isDraft"), String.valueOf("false")));
        
        List<EntryBO> resultList = getEm().createQuery(criteria).getResultList();
        
        LOGGER.info("Number of entries:" + resultList.size());
        for (EntryBO entriesBO : resultList) {
            Calendar cal = Calendar.getInstance(TimeZone.getDefault());
            cal.setTimeInMillis(entriesBO.getTimestamp() * 1000);

            StringBuilder sb = new StringBuilder("Id:" + entriesBO.getId() + " draft: " + entriesBO.getIsDraft() + " time:"
                    + DATE_FORMAT.format(cal.getTime()) + "(" + entriesBO.getTimestamp() + ") " + " Title:" + entriesBO.getTitle());
            sb.append(" Categories:" + Joiner.on(',').join(entriesBO.getCategories()));

            LOGGER.info(sb.toString());
        }
    }
    
    @Test
    public void shouldCreateYAMLFilesFromPosts() throws IOException {
        CriteriaBuilder criteriaBuilder = getEm().getCriteriaBuilder();
        CriteriaQuery<EntryBO> criteria = criteriaBuilder.createQuery(EntryBO.class);
        Root<EntryBO> from = criteria.from(EntryBO.class);

        criteria.where( criteriaBuilder.equal(from.get("isDraft"), String.valueOf("false")));
        
        List<EntryBO> resultList = getEm().createQuery(criteria).getResultList();
        
        for (EntryBO entriesBO : resultList) {
            CreatePostFromEntry cpfe = new CreatePostFromEntry(entriesBO);
            cpfe.writeYAMLFile(new File("target"));
            
        }
        
    }

    
}
