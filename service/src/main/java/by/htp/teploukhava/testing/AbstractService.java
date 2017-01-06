package by.htp.teploukhava.testing;

import by.htp.teploukhava.testing.serviceimpl.ServiceException;

import java.util.List;

/**
 * Created by Admin on 01.10.16.
 */
public interface AbstractService<T> {

    boolean create(T entity) throws ServiceException;
    List<T> findAll() throws ServiceException;
    void delete(int id) throws ServiceException;
    T update(T entity)throws ServiceException;
    T find(int id) throws ServiceException;
}
