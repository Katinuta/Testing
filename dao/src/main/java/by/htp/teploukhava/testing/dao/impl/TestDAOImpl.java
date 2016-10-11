package by.htp.teploukhava.testing.dao.impl;

import by.htp.telpoukhava.testing.entities.Test;
import by.htp.teploukhava.testing.dao.daointerface.TestDAO;
import by.htp.teploukhava.testing.exception.DAOException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class implements methods of interface UserDAO, ovveride this, contains
 * constructor
 */


public class TestDAOImpl implements TestDAO {

	private static final Logger logger= LogManager.getLogger(TestDAOImpl.class);
	private final static String SQL_INSERT_TEST="INSERT  INTO test (subject_id,name) VALUES(?,?)"; 
	private final static String SQL_SELECT_TEST_BY_SUBJECTID="SELECT * FROM test WHERE subject_id=?";
	private final static String SQL_SELECT_TEST_BY_NAME_SUBJECT_ID="SELECT * FROM test WHERE name=? and subject_id=?";
	private final static String SQL_SELECT_TEST_BY_ID="SELECT * FROM test WHERE test_id=?";
	private final static String SQL_UPDATE_NAME_TEST_BY_ID="UPDATE test SET test.name=? WHERE test_id=?";
	private final static String SQL_DELETE_TEST_BY_ID="DELETE FROM test WHERE test_id=?";


	private Connection connection;
    private static TestDAOImpl instance;

    private TestDAOImpl(Connection connection){

        this.connection = connection;
    }

    public static synchronized TestDAOImpl getInstance(Connection connection){
        if(instance==null){
            instance=new TestDAOImpl(connection);
        }else{
			instance.connection=connection;
		}
        return instance;
    }
	@Override
	public List<Test> findAll() {
		return null;
	}

	@Override
	public boolean delete(int id) throws DAOException {
		boolean flag=false;
	//	PreparedStatement ps=null;
	//	Connection connection =  null;
		try(PreparedStatement ps=connection.prepareStatement(SQL_DELETE_TEST_BY_ID)) {
	//		connection=ConnectorDB.getInstance().getConnection();
		//	ps=connection.prepareStatement(SQL_DELETE_TEST_BY_ID);
			ps.setInt(1,id);
			ps.executeUpdate();
			logger.info("The choose test delete");
		} catch (SQLException e) {
			logger.error("Exception in the method delete "+ TestDAOImpl.class.getName());
			throw new DAOException("Exception in the method delete "+e.getMessage());
//		} catch (PropertyVetoException e) {
//			e.printStackTrace();
//		} finally{
//			close(ps);
		}
		return flag;
	}

	public boolean create(Test entity) throws DAOException {
		boolean flag=false;
		PreparedStatement ps=null;
	//	Connection connection =  null;
		try {
		//	connection=ConnectorDB.getInstance().getConnection();
			ps=connection.prepareStatement(SQL_INSERT_TEST);
			ps.setInt(1, entity.getSubjectId());
			ps.setString(2, entity.getName());
			ps.executeUpdate();
			logger.info("A test created");
		} catch (SQLException e) {
			logger.error("Exception in the method create "+ TestDAOImpl.class.getName());
			throw new DAOException("Exception in the method create "+e.getMessage());
//		} catch (PropertyVetoException e) {
//			e.printStackTrace();
//		} finally{
//			close(ps);
		}
		return flag;
	}

	@Override
	public Test update(Test entity) throws DAOException {
	//	PreparedStatement ps=null;
	//	Connection connection =  null;
		try(PreparedStatement ps=connection.prepareStatement(SQL_UPDATE_NAME_TEST_BY_ID)) {
		//	connection=ConnectorDB.getInstance().getConnection();
		//	ps=connection.prepareStatement(SQL_UPDATE_NAME_TEST_BY_ID);
			ps.setString(1, entity.getName());
			ps.setInt(2, entity.getTestId());
			ps.executeUpdate();
			logger.info("Name test updated by id");
		} catch (SQLException e) {
			logger.error("Exception in the method update"+ TestDAOImpl.class.getName());
			throw new DAOException("Exception in the method update "+e.getMessage());
	//	} catch (PropertyVetoException e) {
	//		e.printStackTrace();
//		} finally{
//			close(ps);
		}
		return entity;
	}

	public List<Test> findTestBySubjectId(int subjectId) throws DAOException {
		List<Test> list=new ArrayList<>();
	//	PreparedStatement ps=null;
	//	Connection connection =  null;
		try (PreparedStatement ps=connection.prepareStatement(SQL_SELECT_TEST_BY_SUBJECTID)){
	//		connection=ConnectorDB.getInstance().getConnection();
	//		ps=connection.prepareStatement(SQL_SELECT_TEST_BY_SUBJECTID);
			ps.setInt(1, subjectId);
			ResultSet resultSet=ps.executeQuery();
			while(resultSet.next()){
				Test test=new Test();
				test.setTestId(resultSet.getInt("test_id"));
				test.setSubjectId(resultSet.getInt("subject_id"));
				test.setName(resultSet.getString("name"));
				list.add(test);
			}
			logger.info("Test or tests found by subject");
		} catch (SQLException e) {
			logger.error("Exception in the method findTestBySubjectId "+ TestDAOImpl.class.getName());
			throw new DAOException("Exception in the method findTestBySubjectId "+e.getMessage());
		//} catch (PropertyVetoException e) {
		//	e.printStackTrace();
//		} finally{
//			close(ps);
		}
		return list;
	}
	
	public Test findTestBySubjectIdAndName(int subjectId,String name) throws DAOException {
		Test test =new Test();
//		PreparedStatement ps=null;
//		Connection connection =  null;
		try(PreparedStatement ps=connection.prepareStatement(SQL_SELECT_TEST_BY_NAME_SUBJECT_ID)) {
//			connection=ConnectorDB.getInstance().getConnection();
//			ps=connection.prepareStatement(SQL_SELECT_TEST_BY_NAME_SUBJECT_ID);
			ps.setString(1, name);
			ps.setInt(2, subjectId);
			ResultSet resultSet=ps.executeQuery();
			while(resultSet.next()){
				test.setName( resultSet.getString("name"));
				test.setSubjectId( resultSet.getInt("subject_id"));
				test.setTestId(resultSet.getInt("test_id"));
			}
			logger.info("Test found by subject and name");
		} catch (SQLException e) {
			logger.error("Exception in the method findTestBySubjectIdAndName "+ TestDAOImpl.class.getName());
			throw new DAOException("Exception in the method findTestBySubjectIdAndName "+e.getMessage());
//		} catch (PropertyVetoException e) {
//			e.printStackTrace();
//		} finally{
//			close(ps);
		}
		return test;
	}
	public Test findTestByTestId(int testId) throws DAOException {
		Test test=new Test();
	//	PreparedStatement ps=null;
//        ResultSet resultSet;
//		Connection connection =  null;
		try(PreparedStatement ps=connection.prepareStatement(SQL_SELECT_TEST_BY_ID)) {
//			connection=ConnectorDB.getInstance().getConnection();
//			ps=connection.prepareStatement(SQL_SELECT_TEST_BY_ID);
			ps.setInt(1, testId);
			try(ResultSet resultSet=ps.executeQuery()) {
				while (resultSet.next()) {
					test.setTestId(resultSet.getInt("test_id"));
					test.setSubjectId(resultSet.getInt("subject_id"));
					test.setName(resultSet.getString("name"));
				}
			}
			logger.info("Test or tests found by id");
		} catch (SQLException e) {
			logger.error("Exception in the method findTestByTestId"+ TestDAOImpl.class.getName());
			throw new DAOException("Exception in the method findTestByTestId "+e.getMessage());
//		} catch (PropertyVetoException e) {
//			e.printStackTrace();
//		} finally{
//
//            close(ps);
		}
		return test;
	}
	
	public int updateNameTestById(Test test, String name) throws DAOException {
		int colCount=0;
// PreparedStatement ps=null;

		//Connection connection =  null;
		try (PreparedStatement ps=connection.prepareStatement(SQL_UPDATE_NAME_TEST_BY_ID)){
		//	connection=ConnectorDB.getInstance().getConnection();
//			ps=connection.prepareStatement(SQL_UPDATE_NAME_TEST_BY_ID);
			ps.setString(1, name);
			ps.setInt(2, test.getTestId());
			colCount=ps.executeUpdate();
			logger.info("Name test updated by id");
		} catch (SQLException e) {
			logger.error("Exception in the method updateNameTestById"+ TestDAOImpl.class.getName());
			throw new DAOException("Exception in the method updateNameTestById "+e.getMessage());
	//	} catch (PropertyVetoException e) {
	//		e.printStackTrace();
//		} finally{
//			close(ps);
		}
		return  colCount;
	}

	public void close(PreparedStatement ps) throws DAOException {
//		try {
//			if (ps != null) {
//				ps.close();
//				logger.info("Statement close");
//			}
//		} catch (SQLException e) {
//			logger.error("Exception close statement"+ TestDAOImpl.class.getName());
//			throw new DAOException("Exception close statement "+e.getMessage());
//		}
	}
}
