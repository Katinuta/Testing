package by.htp.teploukhava.testing.dao.impl;


import by.htp.telpoukhava.testing.entities.Question;
import by.htp.teploukhava.testing.dao.daointerface.QuestionDAO;
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
 * Class implements methods of interface QuestionDAO, ovveride this, contains
 * constructor
 */

public class QuestionDAOImpl implements QuestionDAO {
	private static final Logger logger= LogManager.getLogger(QuestionDAOImpl.class);
	private final static String SQL_INSERT_QUESTUION = "INSERT  INTO question (test_id,content) VALUES(?,?)";;
	private final static String SQL_SELECT_TEST_QUESTIONS = "SELECT * FROM question WHERE test_id=?";
	private final static String SQL_SELECT_TEST_BY_CONTENT_TEST_ID = "SELECT * FROM question WHERE content=? and test_id=?";
	private final static String SQL_UPDATE_CONTENT_BY_ID = "UPDATE question SET question.content=? WHERE question.question_id=?";
	private final static String SQL_DELETE_QUESTION_BY_ID = "DELETE FROM question WHERE question.question_id=?";

	private Connection connection;
//
//	public QuestionDAOImpl(Connection connection) {
//
//		this.connection = connection;
//	}
	private static QuestionDAOImpl instance;

	private QuestionDAOImpl(Connection connection){
		this.connection = connection;
	}

	public static synchronized QuestionDAOImpl getInstance(Connection connection){
		if(instance==null){
			instance=new QuestionDAOImpl(connection);
		}else{
			instance.connection=connection;
		}
		return instance;
	}

	public List<Question> findAll() {
		return null;
	}

	public boolean delete(int id) throws DAOException {
		boolean flag = false;
	//	PreparedStatement ps = null;
		try (PreparedStatement ps=connection.prepareStatement(SQL_DELETE_QUESTION_BY_ID)){
		//	ps = connection.prepareStatement(SQL_DELETE_QUESTION_BY_ID);
			ps.setInt(1, id);
			ps.executeUpdate();
			flag = true;
			logger.info("Question deleted");
		} catch (SQLException e) {
			logger.error("Exception in the method delete"+ QuestionDAOImpl.class.getName());
			throw new DAOException("Exception in the method delete "+e.getMessage());
//		} finally {
//			close(ps);
		}
		return flag;
	}

	public boolean create(Question entity) throws DAOException {
		boolean flag;
//		PreparedStatement ps = null;
//		Connection connection =  null;
		try(PreparedStatement ps=connection.prepareStatement(SQL_INSERT_QUESTUION)) {
		//	connection=ConnectorDB.getInstance().getConnection();
		//	ps = connection.prepareStatement(SQL_INSERT_QUESTUION);
			ps.setInt(1, entity.getTestId());
			ps.setString(2, entity.getContent());
			ps.executeUpdate();
			flag = true;
			logger.info("Question created");
		} catch (SQLException e) {
			logger.error("Exception in the method create question"+ QuestionDAOImpl.class.getName());
			throw new DAOException("Exception in the method create question "+e.getMessage());
//		} catch (PropertyVetoException e) {
//			e.printStackTrace();
//		} finally {
//			close(ps);
		}
		return flag;
	}

	public Question update(Question entity) throws DAOException {
//		PreparedStatement ps = null;
//		Connection connection =  null;
		try(PreparedStatement ps=connection.prepareStatement(SQL_UPDATE_CONTENT_BY_ID)) {
//			connection=ConnectorDB.getInstance().getConnection();
//			ps = connection.prepareStatement(SQL_UPDATE_CONTENT_BY_ID);
			ps.setString(1, entity.getContent());
			ps.setInt(2, entity.getQuestionId());
			ps.executeUpdate();
			logger.info("Content of question updated");
		} catch (SQLException e) {
			logger.error("Exception in the method updateContextQuestion"+ QuestionDAOImpl.class.getName());
			throw new DAOException("Exception in the method update"+e.getMessage());
//		} catch (PropertyVetoException e) {
//			e.printStackTrace();
//		} finally {
//			close(ps);
		}
		return entity;
	}

	public List<Question> findTestQuestions(int testId) throws DAOException {
		List<Question> list = new ArrayList<>();
//		PreparedStatement ps = null;
//		ResultSet resultSet=null;
//		Connection connection =  null;
		try(PreparedStatement ps=connection.prepareStatement(SQL_SELECT_TEST_QUESTIONS)) {
			//	connection=ConnectorDB.getInstance().getConnection();
			//	ps = connection.prepareStatement(SQL_SELECT_TEST_QUESTIONS);
			ps.setInt(1, testId);
			try(ResultSet resultSet = ps.executeQuery()){
				while (resultSet.next()) {
					Question question = new Question();
					question.setTestId(resultSet.getInt("test_id"));
					question.setQuestionId(resultSet.getInt("question_id"));
					question.setContent(resultSet.getString("content"));
					list.add(question);
				}
			}
			logger.info("Question of the test found");
		} catch (SQLException e) {
			logger.error("Exception in the method findTestQuestions"+ QuestionDAOImpl.class.getName());
			throw new DAOException("Exception in the method findTestQuestions "+e.getMessage());
//		} catch (PropertyVetoException e) {
//			e.printStackTrace();
//		} finally {
//
//			try {
//				resultSet.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//			close(ps);
		}
		return list;
	}

	public Question findQuestionByContentAndTest(String content, int testId) throws DAOException {
		Question question = new Question();
//		PreparedStatement ps = null;
//		Connection connection =  null;
		try(PreparedStatement ps=connection.prepareStatement(SQL_SELECT_TEST_BY_CONTENT_TEST_ID)) {
//			connection=ConnectorDB.getInstance().getConnection();
//			ps = connection.prepareStatement(SQL_SELECT_TEST_BY_CONTENT_TEST_ID);
			ps.setString(1, content);
			ps.setInt(2, testId);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				question.setQuestionId(resultSet.getInt("question_id"));
				question.setTestId(resultSet.getInt("test_id"));
				question.setContent(resultSet.getString("content"));
			}
			logger.info("Question found by test and content");
		} catch (SQLException e) {
			logger.error("Exception in the method findQuestionByContentAndTest"+ QuestionDAOImpl.class.getName());
			throw new DAOException("Exception in the method findQuestionByContentAndTest "+e.getMessage());
//		} catch (PropertyVetoException e) {
//			e.printStackTrace();
//		} finally {
//			close(ps);
		}
		return question;
	}

	public int updateContextQuestion(Question question, String content) throws DAOException {

		int colCount = 0;
//		Connection connection =  null;
//		PreparedStatement ps = null;
		try (PreparedStatement ps=connection.prepareStatement(SQL_UPDATE_CONTENT_BY_ID)){
//			connection=ConnectorDB.getInstance().getConnection();
//			ps = connection.prepareStatement(SQL_UPDATE_CONTENT_BY_ID);
			ps.setString(1, content);
			ps.setInt(2, question.getQuestionId());
			colCount = ps.executeUpdate();
			logger.info("Content of question updated");
		} catch (SQLException e) {
			logger.error("Exception in the method updateContextQuestion"+ QuestionDAOImpl.class.getName());
			throw new DAOException("Exception in the method updateContextQuestion "+e.getMessage());
//		} catch (PropertyVetoException e) {
//			e.printStackTrace();
//		} finally {
//			close(ps);
		}
		return colCount;
	}



	public void close(PreparedStatement ps) throws DAOException {
		try {
			if (ps != null) {
				ps.close();
			}
		} catch (SQLException e) {
			logger.error("Exception close statement"+ QuestionDAOImpl.class.getName());
			throw new DAOException("Exception close statement "+e.getMessage());
		}
	}
}
