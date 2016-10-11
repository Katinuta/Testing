package by.htp.teploukhava.testing.dao.impl;

import by.htp.telpoukhava.testing.entities.User;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.assertEquals;
/**
 * Created by Admin on 06.10.16.
 */
public class UserDAOImplTest {
    User user=new User();
    Connection connection;
    private  UserDAOImpl dao;
    @Before
    public void setUp() throws Exception {
        user.setUserId(1);
        user.setName("Сергей");
        user.setSurname("Макар");
        user.setLogin("cat");
        user.setPassword("5e56e9616c42b559f659e942d6d25aa9");
        user.setAccess(true);
        ComboPooledDataSource cpds=new ComboPooledDataSource();
        cpds.setDriverClass("com.mysql.jdbc.Driver");
        cpds.setJdbcUrl("jdbc:mysql://localhost:3306/testing"+"?" + "useUnicode="+"true" + "&"
                + "characterEncoding=" +"UTF-8");
        cpds.setUser("root");
        cpds.setPassword("15022014");
        cpds.setMinPoolSize(5);
        cpds.setAcquireIncrement(5);
        cpds.setMaxPoolSize(15);
        cpds.setMaxStatements(180);
        connection=cpds.getConnection();
        dao=UserDAOImpl.getInstance(connection);
    }

    @After
    public void tearDown() throws Exception {
        user=null;
        connection.close();
    }

        @Test
    public void findUserByLoginIsTrue() throws Exception {
        User userActual=dao.findUserByLogin("cat");
        assertEquals(user,userActual);
    }

}