package com.soebes.casestudy;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.soebes.casestudy.bo.CategoryBO;

public class CategoryBOTest extends BOTestBase {
    private static Logger LOGGER = Logger.getLogger(CategoryBOTest.class);

    @Test
    public void testGet() {
        CriteriaQuery<CategoryBO> criteria = getEm().getCriteriaBuilder().createQuery(CategoryBO.class);
        criteria.from(CategoryBO.class);
        List<CategoryBO> categories = getEm().createQuery(criteria).getResultList();
        
        LOGGER.info("Number of entries:" + categories.size());

        for (CategoryBO categoryBO : categories) {
            if (categoryBO.hasSubCategories()) {
                StringBuilder sb = new StringBuilder("Id:" + categoryBO.getId() + " category name:" + categoryBO.getCategoryName());
                sb.append("\n");
                for (CategoryBO subCatName : categoryBO.getSubCategories()) {
                    sb.append("    " + subCatName.getCategoryName() + "\n");
                }
                LOGGER.info(sb.toString());
            } else {
                LOGGER.info("Id:" + categoryBO.getId() + " category name:" + categoryBO.getCategoryName());
            }
        }
    }

}
