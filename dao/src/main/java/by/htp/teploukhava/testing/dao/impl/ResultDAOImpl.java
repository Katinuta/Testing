package by.htp.teploukhava.testing.dao.impl;

import by.htp.telpoukhava.testing.entities.Result;
import by.htp.teploukhava.testing.dao.daointerface.ResultDAO;
import by.htp.teploukhava.testing.exception.DAOException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Class implements methods of interface ResultDAO, ovveride this, contains
 * constructor
 */

public class ResultDAOImpl implements ResultDAO {

    private static final Logger logger= LogManager.getLogger(ResultDAOImpl.class);
	final static String SQL_INSERT_RESULT = "INSERT INTO result(test_id, user_id, result)VALUES(?,?,?)";
	final static String SQL_SELECT_RESULT_BY_TEST_USER = "SELECT * FROM result WHERE test_id=? and user_id=?";
	private Connection connection;
	private static ResultDAOImpl instance;

	private ResultDAOImpl(Connection connection){

		this.connection = connection;
	}

	public static synchronized ResultDAOImpl getInstance(Connection connection){
		if(instance==null){
			instance=new ResultDAOImpl(connection);
		}else{
			instance.connection=connection;
		}
		return instance;
	}

	public List<Result> findAll() {
		return null;
	}

	public boolean delete(int id) {
		return false;
	}

	public boolean create(Result entity) throws DAOException {
		boolean flag = false;
//		PreparedStatement ps = null;
//		Connection connection =  null;
		try(PreparedStatement ps =connection.prepareCall(SQL_INSERT_RESULT)) {
		//	connection=ConnectorDB.getInstance().getConnection();
		//	ps = connection.prepareCall(SQL_INSERT_RESULT);
			ps.setInt(1, entity.getTestId());
			ps.setInt(2, entity.getUserId());
			ps.setInt(3, entity.getResult());
			ps.executeUpdate();
            logger.info("Result wrote");
		} catch (SQLException e) {
            logger.error("Exception in the method create"+ QuestionDAOImpl.class.getName());
            throw new DAOException("Exception in the method create "+e.getMessage());
//		} catch (PropertyVetoException e) {
//			e.printStackTrace();
//		} finally {
//			close(ps);
		}
		return flag;
	}

	public Result update(Result entity) {

		return null;
	}

	public Result findResultByTestUser(int testId, int userId) throws  DAOException {
		Result result = new Result();
//		PreparedStatement ps = null;
//		Connection connection =  null;
		try (PreparedStatement ps =connection.prepareStatement(SQL_SELECT_RESULT_BY_TEST_USER);){
		//	connection=ConnectorDB.getInstance().getConnection();
//			ps = connection.prepareStatement(SQL_SELECT_RESULT_BY_TEST_USER);
			ps.setInt(1, testId);
			ps.setInt(2, userId);
			try(ResultSet resultSet = ps.executeQuery()) {
				while (resultSet.next()) {
					result.setTestId(resultSet.getInt("test_id"));
					result.setUserId(resultSet.getInt("user_id"));
					result.setResult(resultSet.getInt("result"));
				}
			}
			logger.info("Result of the test for user found");
		} catch (SQLException e) {
            logger.error("Exception in the method findResultByTestUser"+ QuestionDAOImpl.class.getName());
            throw new DAOException("Exception in the method findResultByTestUser "+e.getMessage());
//		} catch (PropertyVetoException e) {
//			e.printStackTrace();
//		} finally {
//			close(ps);
		}
		return result;
	}

//	public void close(PreparedStatement ps) throws DAOException {
//		try {
//			if (ps != null) {
//				ps.close();
//                logger.info("Statement closed");
//			}
//		} catch (SQLException e) {
//            logger.error("Exception close statement"+ ResultDAOImpl.class.getName());
//            throw new DAOException("Exception close statement "+e.getMessage());
//		}
//	}
}
