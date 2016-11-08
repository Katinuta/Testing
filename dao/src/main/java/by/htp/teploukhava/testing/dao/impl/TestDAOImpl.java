package by.htp.teploukhava.testing.dao.impl;

import by.htp.telpoukhava.testing.entities.Test;
import by.htp.teploukhava.testing.dao.daointerface.AbstractDAO;
import by.htp.teploukhava.testing.exception.DAOException;
import by.htp.teploukhava.testing.managers.HibernateUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

/**
 * Class implements methods of interface UserDAO, ovveride this, contains
 * constructor
 */


public class TestDAOImpl implements AbstractDAO<Test> {

	private static final Logger logger= LogManager.getLogger(TestDAOImpl.class);
    private static TestDAOImpl instance;

 	public TestDAOImpl(){

	}
	public static synchronized TestDAOImpl getInstance(){
		if(instance==null){
			instance=new TestDAOImpl();
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
		try{
			Session session = HibernateUtil.getSession();
			Test test=(Test) session.get(Test.class,id);
			System.out.println( " delete dao " +test.getClass().getName()+"  " + session.hashCode());
			session.delete(test);
			flag = true;
		}catch (HibernateException e){
			throw new DAOException("Exception in delete dao test "+ e.getMessage());
		}
		return flag;
	}

	@Override
	public boolean create(Test entity) throws DAOException {
		boolean flag=false;
		try{
			Session session = HibernateUtil.getSession();
			session.save(entity);
			System.out.println( "create dao " +entity.getClass()+"  " + session.hashCode());
			flag = true;
		}catch (HibernateException e){
			throw new DAOException("Exception in create dao test "+ e.getMessage());
		}
		return flag;
	}

	@Override
	public Test update(Test entity) throws DAOException {
		try{
			Session session = HibernateUtil.getSession();
			System.out.println( " update dao " +entity.getClass()+"  " + session.hashCode());
			session.merge(entity);
		}catch (HibernateException e){
			throw new DAOException("Exception in update dao test "+ e.getMessage());
		}
		return entity;
	}

	@Override
	public Test find(int id) throws DAOException {
		try{
			Session session=HibernateUtil.getSession();
			return (Test) session.get(Test.class,id);
		}catch (HibernateException e){
			throw new DAOException("Exception in find dao test "+ e.getMessage());
		}

	}

	public List<Test> findTestBySubjectId(int subjectId) throws DAOException {
		List<Test> list=null;
		try{
			String hql="SELECT S.tests FROM Subject S WHERE S.subjectId=:subjectId";
			Session session = HibernateUtil.getSession();
			System.out.println( " findTestBySubjectId dao " +Test.class+"  " + session.hashCode());
			Query query=session.createQuery(hql);
			query.setParameter("subjectId",subjectId);
			list= query.list();
		}catch (HibernateException e){
			throw new DAOException("Exception in findTestBySubjectId dao test "+ e.getMessage());
		}
		return list;
	}

}
