package by.htp.teploukhava.testing.serviceimpl;


import by.htp.telpoukhava.testing.entities.Subject;
import by.htp.teploukhava.testing.AbstractService;
import by.htp.teploukhava.testing.dao.impl.SubjectDAOImpl;
import by.htp.teploukhava.testing.exception.DAOException;
import by.htp.teploukhava.testing.managers.ConnectorDB;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Admin on 01.10.16.
 */
public class SubjectService implements AbstractService<Subject> {

    private static SubjectService instance;

    private SubjectService(){}

    public static synchronized SubjectService getInstance(){
        if(instance==null){
            instance=new SubjectService();
        }
        return instance;
    }

    @Override
    public boolean create(Subject entity) throws SQLException, ServiceException {
        boolean flag=false;
        Connection connection =  null;
        try {
            connection= ConnectorDB.getInstance().getConnection();
          //  connection.setAutoCommit(false);
            SubjectDAOImpl.getInstance(connection).create(entity);
          //  connection.commit();
            flag=true;
        } catch (SQLException |PropertyVetoException | DAOException e) {
         //   connection.rollback();
            throw new ServiceException(e.getMessage());
        } finally {
            connection.close();
        }
        return flag;
    }

    @Override
    public List<Subject> findAll() throws SQLException, ServiceException {
        Connection connection =  null;
        List<Subject> list;
        try {
            connection= ConnectorDB.getInstance().getConnection();
            connection.setAutoCommit(false);
            list=SubjectDAOImpl.getInstance(connection).findAll();
            connection.commit();
        } catch (SQLException |PropertyVetoException | DAOException e) {
            connection.rollback();
            throw new ServiceException(e.getMessage());
        } finally {
            connection.close();
        }
        return list;
    }

    @Override
    public void delete(int id) {}

    @Override
    public Subject update(Subject entity) {
        return null;
    }

    public Subject findSubjectById(int subjectId) throws SQLException, ServiceException {
        Connection connection =  null;
        Subject subject;

        try {

            connection= ConnectorDB.getInstance().getConnection();
            connection.setAutoCommit(false);
            subject=SubjectDAOImpl.getInstance(connection).findSubjectById(subjectId);
            connection.commit();
        } catch (SQLException |PropertyVetoException | DAOException e) {
            connection.rollback();
            throw new ServiceException(e.getMessage());
        } finally {
            connection.close();
        }
        return subject;
    }
}
