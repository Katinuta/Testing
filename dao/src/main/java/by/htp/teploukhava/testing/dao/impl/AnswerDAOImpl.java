package by.htp.teploukhava.testing.dao.impl;

import by.htp.telpoukhava.testing.entities.Answer;
import by.htp.telpoukhava.testing.entities.Question;
import by.htp.teploukhava.testing.dao.daointerface.AbstractDAO;
import by.htp.teploukhava.testing.exception.DAOException;
import by.htp.teploukhava.testing.managers.HibernateUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**Class implements methods of interface AnswerDAO, ovveride this, contains constructor */

public class AnswerDAOImpl implements AbstractDAO<Answer> {

    private static final Logger logger= LogManager.getLogger(AnswerDAOImpl.class);
	final static String SQL_UPDATE_ANSWER = "UPDATE answer SET answer.content=?,answer.right_answer=? WHERE answer.answer_id=?";
	private Connection connection;
	private static AnswerDAOImpl instance;

	private AnswerDAOImpl(){

	}

	public static synchronized AnswerDAOImpl getInstance(){
		if(instance==null){
			instance=new AnswerDAOImpl();
		}
		return instance;
	}


	public List<Answer> findAll() {
		Session session=HibernateUtil.getSession();
		Query query=session.createQuery("FROM Answer ");
		System.out.println( " delete dao " +Answer.class+"  " + session.hashCode());
		List<Answer> results=query.list();
		return results;
	}

	@Override
	public boolean delete(int id) throws DAOException {
		boolean flag=false;
		try{
			Session session = HibernateUtil.getSession();
			Answer answer=(Answer) session.get(Answer.class,id);
			System.out.println( " delete dao " +answer.getClass().getName()+"  " + session.hashCode());
			session.delete(answer);
			flag = true;
		}catch (HibernateException e){
			throw new DAOException("Exception in delete dao answer "+ e.getMessage());
		}
		return flag;
	}

	@Override
	public boolean create(Answer entity) throws DAOException {
		boolean flag=false;
		try{
			Session session = HibernateUtil.getSession();
			System.out.println( " create dao " +entity.getClass()+"  " + session.hashCode());
			session.save(entity);
			flag = true;
		}catch (HibernateException e){
			throw new DAOException("Exception in create dao answer "+ e.getMessage());
		}
		return flag;
	}

	@Override
	public Answer update(Answer entity) throws DAOException {
		try{
			Session session = HibernateUtil.getSession();
			System.out.println( " update dao " +entity.getClass().getName()+"  " + session.hashCode());
			session.saveOrUpdate(entity);
			return entity;
		}catch (HibernateException e){
			throw new DAOException("Exception in update dao answer "+ e.getMessage());
		}
	}

	@Override
	public Answer find(int id) throws DAOException {
		Answer answer=null;
		try{
			Session session=HibernateUtil.getSession();
			answer=(Answer) session.get(Answer.class,id);
		}catch (HibernateException e){
			throw new DAOException("Exception in find dao answer "+ e.getMessage());
		}
		return answer;
	}


	public List<Answer> findQuestionAnswers(int questionId) throws DAOException {
		List<Answer> list = new ArrayList<>();
		try{
			Session session = HibernateUtil.getSession();
			System.out.println( " findQuestionAnswers dao " +Answer.class+"  " + session.hashCode());
			Criteria criteria=session.createCriteria(Question.class);
			criteria.add(Restrictions.eq("question.questionId",questionId));
			list=criteria.list();
		}catch (HibernateException e){
			throw new DAOException("Exception in findQuestionAnswers dao question "+ e.getMessage());
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

}
