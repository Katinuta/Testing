package by.htp.teploukhava.testing;

import by.htp.teploukhava.testing.serviceimpl.ServiceException;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Admin on 01.10.16.
 */
public interface AbstractService<T> {

    boolean create(T entity) throws ServiceException, SQLException, ServiceException;
    List<T> findAll() throws ServiceException, SQLException, ServiceException;
    void delete(int id) throws ServiceException, SQLException, ServiceException;
    T update(T entity)throws ServiceException;

}
