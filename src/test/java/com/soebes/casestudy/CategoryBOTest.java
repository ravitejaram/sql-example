package com.soebes.casestudy;

import java.util.List;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.soebes.casestudy.bo.CategoryBO;
import com.soebes.casestudy.dao.DAOFactory;
import com.soebes.casestudy.dao.IdDAO;

public class CategoryBOTest extends BOTestBase {
    private static Logger LOGGER = Logger.getLogger(CategoryBOTest.class);

    @BeforeClass
    public void beforeClass() {
        LOGGER.debug("beforeClass()");

        LOGGER.debug("beforeClass(done)");
    }

    @Test(enabled = true)
    public void testGet() {
        IdDAO<CategoryBO> dao = DAOFactory.getCategory();
        List<CategoryBO> categories = dao.get();
        for (CategoryBO categoryBO : categories) {
            if (categoryBO.hasSubCategories()) {
        	StringBuilder sb = new StringBuilder("Id:" + categoryBO.getId() + " category name:" + categoryBO.getCategoryName());
        	sb.append("\n");
        	for (CategoryBO subCatName : categoryBO.getSubCategories()) {
		    sb.append("    " + subCatName.getCategoryName() + "\n");
		}
        	LOGGER.info(sb.toString());
            } else {
        	LOGGER.info("Id:" + categoryBO.getId() + " category name:" + categoryBO.getCategoryName() );
            }
        }
    }


}
