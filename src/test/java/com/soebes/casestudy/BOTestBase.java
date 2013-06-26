package com.soebes.casestudy;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;

public class BOTestBase {
    private static Logger LOGGER = Logger.getLogger(BOTestBase.class);

    private EntityManagerFactory emf;
    private EntityManager em;

    public EntityManagerFactory getEmf() {
        return emf;
    }

    public EntityManager getEm() {
        return em;
    }

    @BeforeClass
    public void beforeClass() {
        emf = Persistence.createEntityManagerFactory("hibernate-search-example");
        em = emf.createEntityManager();
        LOGGER.debug("beforeClass()");
    
        LOGGER.debug("beforeClass(done)");
    }

}
