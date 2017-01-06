package by.htp.teploukhava.testing.dao.impl;

import by.htp.teploukhava.testing.dao.daointerface.AbstractDAO;
import by.htp.teploukhava.testing.entities.Answer;
import by.htp.teploukhava.testing.entities.Question;
import by.htp.teploukhava.testing.exception.DAOException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**Class implements methods of interface AnswerDAO, ovveride this, contains constructor */
@Repository
public class AnswerDAOImpl implements AbstractDAO<Answer> {

    private static final Logger logger= LogManager.getLogger(AnswerDAOImpl.class);

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
	public boolean create(Answer entity) throws DAOException {
		try{
		boolean flag=false;
		System.out.println(entity.toString());
		sessionFactory.getCurrentSession().save(entity);
		flag = true;
		return flag;}catch (HibernateException e){
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public Answer update(Answer entity)  {
		sessionFactory.getCurrentSession().saveOrUpdate(entity);
		return entity;
	}

	@Override
	public Answer find(int id)  {
        Answer answer=(Answer) sessionFactory.getCurrentSession().get(Answer.class,id);
		return answer;
	}

	public List<Answer> findQuestionAnswers(int questionId) {
        Criteria criteria=sessionFactory.getCurrentSession().createCriteria(Question.class);
        criteria.add(Restrictions.eq("question.questionId",questionId));
        List<Answer>list=criteria.list();
		return list;
	}

}
