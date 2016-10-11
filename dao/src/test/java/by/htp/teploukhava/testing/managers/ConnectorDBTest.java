package by.htp.teploukhava.testing.managers;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.assertEquals;

/**
 * Created by Admin on 06.10.16.
 */
public class ConnectorDBTest {
    ComboPooledDataSource cpds;
    Connection connection;
    @Before
    public void setUp() throws Exception {
        cpds=new ComboPooledDataSource();

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
    }

    @After
    public void tearDown() throws Exception {
        cpds=null;
        connection.close();
    }
    @Ignore
    @Test
    public void getConnectionTrue() throws Exception {

        Connection connectionget= cpds.getConnection();
        assertEquals(connection,connectionget);
    }

}