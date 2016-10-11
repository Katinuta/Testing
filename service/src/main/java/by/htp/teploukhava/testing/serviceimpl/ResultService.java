package by.htp.teploukhava.testing.serviceimpl;


import by.htp.telpoukhava.testing.entities.*;
import by.htp.teploukhava.testing.AbstractService;
import by.htp.teploukhava.testing.exception.DAOException;
import by.htp.teploukhava.testing.managers.ConnectorDB;
import by.htp.teploukhava.testing.dao.impl.*;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Admin on 02.10.16.
 */
public class ResultService implements AbstractService<Result> {

    private static ResultService instance;

    private ResultService(){}

    public static synchronized ResultService getInstance(){
        if(instance==null){
            instance=new ResultService();
        }
        return instance;
    }

    @Override
    public boolean create(Result entity) throws SQLException, ServiceException {
        boolean flag=false;
        Connection connection =  null;
        try {
            connection= ConnectorDB.getInstance().getConnection();
            connection.setAutoCommit(false);
            ResultDAOImpl.getInstance(connection).create(entity);
            connection.commit();
            flag=true;
        } catch (SQLException |PropertyVetoException | DAOException e) {
            connection.rollback();
            throw new ServiceException(e.getMessage());
        } finally {
            connection.close();
        }
        return flag;
    }

    @Override
    public List<Result> findAll()  {
        return null;
    }

    @Override
    public void delete(int id)  {

    }

    @Override
    public Result update(Result entity) {
        return null;
    }

    public Result findResultByTestUser(int testId, int userId) throws SQLException, ServiceException {
        Connection connection =  null;
        Result result;
        try {
            connection= ConnectorDB.getInstance().getConnection();
            connection.setAutoCommit(false);
            result= ResultDAOImpl.getInstance(connection).findResultByTestUser(testId, userId);
            connection.commit();
        } catch (SQLException |PropertyVetoException | DAOException e) {
            connection.rollback();
            throw new ServiceException(e.getMessage());
        } finally {
            connection.close();
        }
        return result;

    }
}
