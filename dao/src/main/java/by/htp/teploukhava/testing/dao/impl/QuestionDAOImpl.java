package by.htp.teploukhava.testing.dao.impl;


import by.htp.teploukhava.testing.dao.daointerface.AbstractDAO;
import by.htp.teploukhava.testing.entities.Question;
import by.htp.teploukhava.testing.exception.DAOException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Class implements methods of interface QuestionDAO, ovveride this, contains
 * constructor
 */
@Repository
public class QuestionDAOImpl implements AbstractDAO<Question>{
	private static final Logger logger= LogManager.getLogger(QuestionDAOImpl.class);

	private SessionFactory sessionFactory;

	public QuestionDAOImpl(){}

	@Autowired
	public QuestionDAOImpl(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}

	public List<Question> findAll() {
		return null;
	}

	public boolean delete(int id)  {
		boolean flag=false;
		Question question=(Question) sessionFactory.getCurrentSession().get(Question.class,id);
		sessionFactory.getCurrentSession().delete(question);
		flag = true;
		return flag;
	}

	@Override
	public boolean create(Question entity) throws DAOException {

		boolean flag=false;
		try{	sessionFactory.getCurrentSession().save(entity);}catch (HibernateException e){
			throw  new DAOException(e.getMessage());
		}
		flag = true;
		return flag;
	}

	@Override
	public Question update(Question entity)  {
		sessionFactory.getCurrentSession().saveOrUpdate(entity);
		return entity;
	}

	@Override
	public Question find(int id)  {
		Question question= (Question) sessionFactory.getCurrentSession().get(Question.class,id);
		return question;
	}


	public List<Question> findTestQuestions(int testId) {
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(Question.class);
		criteria.add(Restrictions.eq("test.testId",testId));
		List<Question>questionList=criteria.list();
		return questionList;
	}
}
