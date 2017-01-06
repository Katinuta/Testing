package by.htp.teploukhava.testing.dao.impl;

import by.htp.teploukhava.testing.dao.daointerface.AbstractDAO;
import by.htp.teploukhava.testing.entities.Test;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Class implements methods of interface UserDAO, ovveride this, contains
 * constructor
 */

@Repository
public class TestDAOImpl implements AbstractDAO<Test> {

	private static final Logger logger= LogManager.getLogger(TestDAOImpl.class);

	private SessionFactory sessionFactory;

	public TestDAOImpl(){}

	@Autowired
	public TestDAOImpl(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}

	@Override
	public List<Test> findAll() {
		return null;
	}

	@Override
	public boolean delete(int id)  {
		boolean flag=false;
		Test test=(Test) sessionFactory.getCurrentSession().get(Test.class,id);
		sessionFactory.getCurrentSession().delete(test);
		flag = true;
		return flag;
	}

	@Override
	public boolean create(Test entity)  {
		boolean flag=false;
		sessionFactory.getCurrentSession().persist(entity);
		flag = true;
		return flag;
	}

	@Override
	public Test update(Test entity)  {
		sessionFactory.getCurrentSession().merge(entity);
		return entity;
	}

	@Override
	public Test find(int id) {
		String hql="SELECT t FROM Test t WHERE t.testId=:id";
		Test test= (Test) sessionFactory.getCurrentSession().createQuery(hql).setParameter("id",id).uniqueResult();
		return test;
	}

	public List<Test> findTestBySubjectId(int subjectId)  {
		String hql="SELECT S.tests FROM Subject S WHERE S.subjectId=:subjectId";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("subjectId",subjectId);
		List<Test> list= query.list();
		return list;
	}
	public List<Test> findSubjectTestByPage(int subjectId,int recordsPerPage, int numberPage) {
		Query query = sessionFactory.getCurrentSession()
				.createQuery("SELECT S.tests FROM Subject S WHERE S.subjectId=:subjectId");
		query.setParameter("subjectId",subjectId);
		query.setFirstResult((numberPage - 1) * recordsPerPage);
		query.setMaxResults(recordsPerPage);
		List<Test> list = query.list();
		return list;
	}
	public long countRecords(int subjectId){
		Query query=sessionFactory.getCurrentSession().createQuery("Select count(*) from Test T WHERE T.subject.subjectId=:subjectId");
		query.setParameter("subjectId",subjectId);
		long countPages= (long) query.uniqueResult();
		return countPages;
	}
}
