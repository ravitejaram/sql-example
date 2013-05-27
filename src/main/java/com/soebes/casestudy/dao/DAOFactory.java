package com.soebes.casestudy.dao;

import com.soebes.casestudy.bo.EntriesBO;

/**
 * This is factory class to create the different DAO instances.
 *
 * @author Karl Heinz Marbase
 *
 */
public final class DAOFactory {

    public static IdDAO<EntriesBO> getEntries() {
        return new IdDAO<EntriesBO>(EntriesBO.class);
    }
}
