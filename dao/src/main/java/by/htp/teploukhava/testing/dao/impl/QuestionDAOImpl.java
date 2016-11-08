package by.htp.teploukhava.testing.dao.impl;


import by.htp.telpoukhava.testing.entities.Question;
import by.htp.teploukhava.testing.dao.daointerface.AbstractDAO;
import by.htp.teploukhava.testing.exception.DAOException;
import by.htp.teploukhava.testing.managers.HibernateUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.sql.Connection;
import java.util.List;

/**
 * Class implements methods of interface QuestionDAO, ovveride this, contains
 * constructor
 */

public class QuestionDAOImpl implements AbstractDAO<Question>{
	private static final Logger logger= LogManager.getLogger(QuestionDAOImpl.class);
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
	private QuestionDAOImpl(){}

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

	public static synchronized QuestionDAOImpl getInstance(){
		if(instance==null){
			instance=new QuestionDAOImpl();
		}
		return instance;
	}
	public List<Question> findAll() {
		return null;
	}

	public boolean delete(int id) throws DAOException {

		boolean flag=false;
		try{
			Session session = HibernateUtil.getSession();
			Question question=(Question) session.get(Question.class,id);
			System.out.println( " delete dao " +question.getClass().getName()+"  " + session.hashCode());
			session.delete(question);
			flag = true;
		}catch (HibernateException e){
			throw new DAOException("Exception in delete dao question "+ e.getMessage());
		}
		return flag;
	}

	@Override
	public boolean create(Question entity) throws DAOException {
		boolean flag=false;
		try{
			Session session = HibernateUtil.getSession();
			System.out.println( "create  dao " +entity.getClass()+"   " + session.hashCode());
			session.save(entity);
			flag = true;
		}catch (HibernateException e){
			throw new DAOException("Exception in create dao question "+ e.getMessage());
		}
		return flag;
	}

	@Override
	public Question update(Question entity) throws DAOException {
		try{
			Session session = HibernateUtil.getSession();
			System.out.println( " update dao " +entity.getClass().getName()+"  " + session.hashCode());
			session.saveOrUpdate(entity);
		}catch (HibernateException e){
			throw new DAOException("Exception in update dao question "+ e.getMessage());
		}
		return entity;
	}

	@Override
	public Question find(int id) throws DAOException {
		Question question=null;
		try{
			Session session=HibernateUtil.getSession();
			System.out.println( " find dao " +Question.class+"  " + session.hashCode());
			question= (Question) session.get(Question.class,id);
		}catch (HibernateException e){
			throw new DAOException("Exception in update dao question "+ e.getMessage());
		}
		return question;
	}


	public List<Question> findTestQuestions(int testId) throws DAOException {
		List<Question> questionList=null;
		try{
			Session session = HibernateUtil.getSession();
			System.out.println( "  findTestQuestions dao " +Question.class+"  " + session.hashCode());
			Criteria criteria=session.createCriteria(Question.class);
			criteria.add(Restrictions.eq("test.testId",testId));
			questionList=criteria.list();
		}catch (HibernateException e){
			throw new DAOException("Exception in findTestQuestions dao question "+ e.getMessage());
		}
		return questionList;
	}
}
