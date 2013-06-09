package com.soebes.casestudy;

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
import com.soebes.casestudy.bo.EntriesBO;

public class EntriesBOTest extends BOTestBase {
    private static Logger LOGGER = Logger.getLogger(EntriesBOTest.class);

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");

    @Test
    public void testGet() {
        CriteriaBuilder criteriaBuilder = getEm().getCriteriaBuilder();
        CriteriaQuery<EntriesBO> criteria = criteriaBuilder.createQuery(EntriesBO.class);
        Root<EntriesBO> from = criteria.from(EntriesBO.class);

        criteria.where( criteriaBuilder.equal(from.get("isDraft"), String.valueOf("false")));
        
        List<EntriesBO> resultList = getEm().createQuery(criteria).getResultList();
        
        LOGGER.info("Number of entries:" + resultList.size());
        for (EntriesBO entriesBO : resultList) {
            Calendar cal = Calendar.getInstance(TimeZone.getDefault());
            cal.setTimeInMillis(entriesBO.getTimestamp() * 1000);

            StringBuilder sb = new StringBuilder("Id:" + entriesBO.getId() + " draft: " + entriesBO.getIsDraft() + " time:"
                    + DATE_FORMAT.format(cal.getTime()) + "(" + entriesBO.getTimestamp() + ") " + " Title:" + entriesBO.getTitle());
            sb.append(" Categories:" + Joiner.on(',').join(entriesBO.getCategories()));

            LOGGER.info(sb.toString());
        }
    }
    
}
