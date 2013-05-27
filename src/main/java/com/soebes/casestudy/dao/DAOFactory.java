/**
 * (c) 2008, 2009 T-Mobile Deutschland GmbH
 */
package com.soebes.casestudy.dao;

import com.soebes.casestudy.bo.EntriesBO;

/**
 * This is factory class to create the different DAO instances.
 *
 * @author Karl Heinz Marbase
 *
 */
public final class DAOFactory {

    public static IdDAO<EntriesBO> getBestellung() {
        return new IdDAO<EntriesBO>(EntriesBO.class);
    }
}
