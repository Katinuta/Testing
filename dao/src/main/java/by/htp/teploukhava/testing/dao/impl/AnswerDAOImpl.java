package by.htp.teploukhava.testing.dao.impl;

import by.htp.telpoukhava.testing.entities.Answer;
import by.htp.teploukhava.testing.dao.daointerface.AnswerDAO;
import by.htp.teploukhava.testing.exception.DAOException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**Class implements methods of interface AnswerDAO, ovveride this, contains constructor */

public class AnswerDAOImpl implements AnswerDAO {

    private static final Logger logger= LogManager.getLogger(AnswerDAOImpl.class);
	final static String SQL_INSERT_ANSWER = "INSERT INTO answer(question_id,content,right_answer) VALUES(?,?,?)";
	final static String SQL_SELECT_QUESTION_ANSWERS = "SELECT * FROM answer WHERE question_id=?";
	final static String SQL_UPDATE_ANSWER = "UPDATE answer SET answer.content=?,answer.right_answer=? WHERE answer.answer_id=?";
	final static String SQl_DELETE_ANSWER_BY_QUESTION_ID = "DELETE FROM answer WHERE question_id=?";
	private Connection connection;
	private static AnswerDAOImpl instance;

	private AnswerDAOImpl(Connection connection){

		this.connection = connection;
	}

	public static synchronized AnswerDAOImpl getInstance(Connection connection){
		if(instance==null){
			instance=new AnswerDAOImpl(connection);
		}else{
			instance.connection=connection;
		}
		return instance;
	}


	public List<Answer> findAll() {
		return null;
	}

	public boolean delete(int id) throws DAOException {
		boolean flag ;
		//PreparedStatement ps = null;
	//	Connection connection =  null;
		try(PreparedStatement ps=connection.prepareStatement(SQl_DELETE_ANSWER_BY_QUESTION_ID)) {
		//	connection=ConnectorDB.getInstance().getConnection();
		//	ps = connection.prepareStatement(SQl_DELETE_ANSWER_BY_QUESTION_ID);
			ps.setInt(1, id);
			ps.executeUpdate();
			flag = true;
            logger.info("Answers of the question deleted");
		} catch (SQLException e) {
            logger.error("Exception in the method delete"+ QuestionDAOImpl.class.getName());
            throw new DAOException("Exception in the method delete "+e.getMessage());

//		} catch (PropertyVetoException e) {
//			e.printStackTrace();
//		} finally {
//			close(ps);
		}
		return flag;
	}

	public boolean create(Answer entity) throws DAOException {
		boolean flag = false;
//		PreparedStatement ps = null;
//		Connection connection =  null;
		try (PreparedStatement ps = connection.prepareStatement(SQL_INSERT_ANSWER)){
		//	connection=ConnectorDB.getInstance().getConnection();
//			ps = connection.prepareStatement(SQL_INSERT_ANSWER);
			ps.setInt(1, entity.getQuestionId());
			ps.setString(2, entity.getContent());
			ps.setBoolean(3, entity.isRightAnswer());
			ps.executeUpdate();
			flag = true;
            logger.info("Answer for the question created");
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

	public Answer update(Answer entity) throws DAOException {
//		PreparedStatement ps = null;
//		Connection connection =  null;
		try (PreparedStatement ps=connection.prepareStatement(SQL_UPDATE_ANSWER)){
	//		connection=ConnectorDB.getInstance().getConnection();
		//	ps = connection.prepareStatement(SQL_UPDATE_ANSWER);
			ps.setString(1, entity.getContent());
			ps.setBoolean(2, entity.isRightAnswer());
			ps.setInt(3, entity.getAnswerId());
			ps.executeUpdate();
			logger.info("Answers for question updated");
		} catch (SQLException e) {
			logger.error("Exception in the method  update"+ QuestionDAOImpl.class.getName());
			throw new DAOException("Exception in the method update "+e.getMessage());
//		} catch (PropertyVetoException e) {
//			e.printStackTrace();
//		} finally {
//			close(ps);
		}
		return entity;
	}

	public List<Answer> findQuestionAnswers(int questionId) throws DAOException {
		List<Answer> list = new ArrayList<>();
	//	PreparedStatement ps = null;
	//	Connection connection =  null;
	//	ResultSet resultSet = null;
		try(PreparedStatement  ps = connection.prepareStatement(SQL_SELECT_QUESTION_ANSWERS)) {
		//	connection=ConnectorDB.getInstance().getConnection();
		//	ps = connection.prepareStatement(SQL_SELECT_QUESTION_ANSWERS);
			ps.setInt(1, questionId);
			try(ResultSet resultSet = ps.executeQuery() ){
				//resultSet = ps.executeQuery();
				while (resultSet.next()) {
					Answer answer = new Answer();
					answer.setAnswerId(resultSet.getInt("answer_id"));
					answer.setQuestionId(resultSet.getInt("question_id"));
					answer.setContent(resultSet.getString("content"));
					answer.setRightAnswer(resultSet.getBoolean("right_answer"));
					list.add(answer);
				}
			}
		} catch (SQLException e) {
            logger.error("Exception in the method  findQuestionAnswers"+ QuestionDAOImpl.class.getName());
            throw new DAOException("Exception in the method findQuestionAnswers "+e.getMessage());
//		} catch (PropertyVetoException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				resultSet.close();
//			} catch (SQLException e) {
//				e.getMessage();
//			}
		}
		return list;
	}

	public int updateAnswer(Answer answer, String content, Boolean rightAnswer) throws DAOException {

		int colCount = 0;
//		Connection connection =  null;
//		PreparedStatement ps = null;
		try(PreparedStatement ps=connection.prepareStatement(SQL_UPDATE_ANSWER)) {
	//		connection=ConnectorDB.getInstance().getConnection();
	//		ps = connection.prepareStatement(SQL_UPDATE_ANSWER);
			ps.setString(1, content);
			ps.setBoolean(2, rightAnswer);
			ps.setInt(3, answer.getAnswerId());
			colCount = ps.executeUpdate();
            logger.info("Answers for question updated");
		} catch (SQLException e) {
            logger.error("Exception in the method  updateAnswer"+ QuestionDAOImpl.class.getName());
            throw new DAOException("Exception in the method updateAnswer "+e.getMessage());
//		} catch (PropertyVetoException e) {
//			e.printStackTrace();
//		} finally {
//			close(ps);
		}
		return colCount;
	}

//	public void close(PreparedStatement ps) throws DAOException {
//
//		try {
//			if (ps != null) {
//				ps.close();
//                logger.info("Statement close");
//			}
//		} catch (SQLException e) {
//            logger.error("Exception close statement"+ QuestionDAOImpl.class.getName());
//            throw new DAOException("Exception close statement "+e.getMessage());
//		}
//
//	}
}
