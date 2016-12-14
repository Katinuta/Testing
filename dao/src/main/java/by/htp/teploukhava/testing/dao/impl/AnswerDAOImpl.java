package by.htp.teploukhava.testing.dao.impl;

import by.htp.teploukhava.testing.entities.Answer;
import by.htp.teploukhava.testing.entities.Question;
import by.htp.teploukhava.testing.dao.daointerface.AbstractDAO;
import by.htp.teploukhava.testing.exception.DAOException;
import by.htp.teploukhava.testing.managers.HibernateUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**Class implements methods of interface AnswerDAO, ovveride this, contains constructor */

public class AnswerDAOImpl implements AbstractDAO<Answer> {

    private static final Logger logger= LogManager.getLogger(AnswerDAOImpl.class);
	final static String SQL_UPDATE_ANSWER = "UPDATE answer SET answer.content=?,answer.right_answer=? WHERE answer.answer_id=?";

	private SessionFactory sessionFactory;

	public AnswerDAOImpl(){}

	@Autowired
	public AnswerDAOImpl(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}


	public List<Answer> findAll() {
		Query query=sessionFactory.getCurrentSession().createQuery("FROM Answer ");
		List<Answer> results=query.list();
		return results;
	}

	@Override
	public boolean delete(int id)  {
		boolean flag=false;
		Answer answer=(Answer) sessionFactory.getCurrentSession().get(Answer.class,id);
		sessionFactory.getCurrentSession().delete(answer);
		flag = true;
		return flag;
	}

	@Override
	public boolean create(Answer entity) {
		boolean flag=false;
		sessionFactory.getCurrentSession().save(entity);
		flag = true;
		return flag;
	}

	@Override
	public Answer update(Answer entity)  {
		Session session = HibernateUtil.getSession();
		System.out.println( " update dao " +entity.getClass().getName()+"  " + session.hashCode());
		session.saveOrUpdate(entity);
		return entity;
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
