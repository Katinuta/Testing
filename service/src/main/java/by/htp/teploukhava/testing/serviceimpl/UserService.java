package by.htp.teploukhava.testing.serviceimpl;


import by.htp.telpoukhava.testing.entities.Subject;
import by.htp.telpoukhava.testing.entities.User;
import by.htp.teploukhava.testing.AbstractService;
import by.htp.teploukhava.testing.dao.impl.UserDAOImpl;
import by.htp.teploukhava.testing.exception.DAOException;
import by.htp.teploukhava.testing.managers.ConnectorDB;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Admin on 01.10.16.
 */
public class UserService implements AbstractService<User> {
    private static UserService instance;
    private static final Logger logger= LogManager.getLogger(UserService.class);
    private UserService(){}

    public static synchronized UserService getInstance(){
        if(instance==null){
            instance=new UserService();
        }
        return instance;
    }

    @Override
    public boolean create(User entity) throws SQLException, ServiceException {
        boolean flag=false;
        Connection connection =  null;
        try {
            connection= ConnectorDB.getInstance().getConnection();
            connection.setAutoCommit(false);
            UserDAOImpl.getInstance(connection).create(entity);
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
    public List<User> findAll()  {
        return null;
    }

    @Override
    public void delete(int id)  {

    }

    @Override
    public User update(User entity) {
        return null;
    }

    public List<Subject> findUserSubject(int userId) throws SQLException, ServiceException {
        List<Subject> list;
        Connection connection =  null;
        try {
            connection= ConnectorDB.getInstance().getConnection();
           // connection.setAutoCommit(false);
            list=UserDAOImpl.getInstance(connection).findUserSubject(userId);
           // connection.commit();
        } catch (SQLException |PropertyVetoException | DAOException e) {
           // connection.rollback();
            throw new ServiceException(e.getMessage());
        } finally {
            connection.close();
        }
        return list;
    }

    public User findUserByLogin(String login) throws SQLException, ServiceException {

        Connection connection=null ;
        User user=null;
        try {
            connection= ConnectorDB.getInstance().getConnection();
          //  connection.setAutoCommit(false);
            user=UserDAOImpl.getInstance(connection).findUserByLogin(login);
           // connection.commit();
        } catch (SQLException |PropertyVetoException | DAOException e) {
           //connection.rollback();
            throw new ServiceException(e.getMessage());
        } finally {
            connection.close();

        }
        return user;
    }
}
