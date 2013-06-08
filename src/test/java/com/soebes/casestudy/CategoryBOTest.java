package com.soebes.casestudy;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.soebes.casestudy.bo.CategoryBO;

public class CategoryBOTest extends BOTestBase {
    private static Logger LOGGER = Logger.getLogger(CategoryBOTest.class);

    private EntityManagerFactory emf;

    private EntityManager em;

    
    @BeforeClass
    public void beforeClass() {
        emf = Persistence.createEntityManagerFactory( "hibernate-search-example" );
        
//        EntityManagerFactory emf = 
//                cfg.addProperties( properties ) //add some properties
//                   .setInterceptor( myInterceptorImpl ) // set an interceptor
//                   .addAnnotatedClass( MyAnnotatedClass.class ) //add a class to be mapped
//                   .addClass( NonAnnotatedClass.class ) //add an hbm.xml file using the Hibernate convention
//                   .addRerousce( "mypath/MyOtherCLass.hbm.xml ) //add an hbm.xml file
//                   .addRerousce( "mypath/orm.xml ) //add an EJB3 deployment descriptor
//                   .configure("/mypath/hibernate.cfg.xml") //add a regular hibernate.cfg.xml
//                   .buildEntityManagerFactory(); //Create the entity manager factory
        em = emf.createEntityManager();

        LOGGER.debug("beforeClass()");

        LOGGER.debug("beforeClass(done)");
    }

    @Test
    public void testGet() {
        CriteriaQuery<CategoryBO> criteria = em.getCriteriaBuilder().createQuery(CategoryBO.class);
        criteria.from(CategoryBO.class);
        List<CategoryBO> categories = em.createQuery(criteria).getResultList();
        
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
