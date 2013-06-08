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
    
        // EntityManagerFactory emf =
        // cfg.addProperties( properties ) //add some properties
        // .setInterceptor( myInterceptorImpl ) // set an interceptor
        // .addAnnotatedClass( MyAnnotatedClass.class ) //add a class to be
        // mapped
        // .addClass( NonAnnotatedClass.class ) //add an hbm.xml file using the
        // Hibernate convention
        // .addRerousce( "mypath/MyOtherCLass.hbm.xml ) //add an hbm.xml file
        // .addRerousce( "mypath/orm.xml ) //add an EJB3 deployment descriptor
        // .configure("/mypath/hibernate.cfg.xml") //add a regular
        // hibernate.cfg.xml
        // .buildEntityManagerFactory(); //Create the entity manager factory
        em = emf.createEntityManager();
        LOGGER.debug("beforeClass()");
    
        LOGGER.debug("beforeClass(done)");
    }

}
