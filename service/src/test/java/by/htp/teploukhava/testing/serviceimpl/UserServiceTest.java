package by.htp.teploukhava.testing.serviceimpl;

import by.htp.telpoukhava.testing.entities.User;
import by.htp.teploukhava.testing.dao.impl.UserDAOImpl;
import by.htp.teploukhava.testing.managers.ConnectorDB;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.assertEquals;

/**
 * Created by Admin on 06.10.16.
 */
public class UserServiceTest {
    User user=new User();
    Connection connection;
    UserDAOImpl dao;
    @Before
    public void setUp() throws Exception {

        user.setUserId(1);
        user.setName("Сергей");
        user.setSurname("Макар");
        user.setLogin("cat");
        user.setPassword("5e56e9616c42b559f659e942d6d25aa9");
        user.setAccess(true);

        connection= ConnectorDB.getInstance().getConnection();
        dao=UserDAOImpl.getInstance(connection);
    }

    @After
    public void tearDown() throws Exception {
        user=null;
        connection.close();
    }

    @Test
    public void findUserByLogin() throws Exception {


        User userActual= UserDAOImpl.getInstance(connection).findUserByLogin("cat");
        assertEquals(user,userActual);
    }



}