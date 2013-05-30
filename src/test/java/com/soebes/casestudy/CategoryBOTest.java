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
        for (CategoryBO categroyBO : categories) {
            LOGGER.info("Id:" + categroyBO.getId() + " category name:" + categroyBO.getCategoryName() + " parent: " + categroyBO.getParentId() );
        }
    }


}
