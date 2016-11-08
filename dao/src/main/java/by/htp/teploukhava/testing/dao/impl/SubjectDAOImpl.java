package by.htp.teploukhava.testing.dao.impl;


import by.htp.telpoukhava.testing.entities.Subject;
import by.htp.telpoukhava.testing.entities.SubjectDTO;
import by.htp.teploukhava.testing.dao.daointerface.AbstractDAO;
import by.htp.teploukhava.testing.exception.DAOException;
import by.htp.teploukhava.testing.managers.HibernateUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import java.util.List;
/**
 * Class implements methods of interface SubjectDAO, ovveride this, contains
 * constructor
 */

public class SubjectDAOImpl implements AbstractDAO<Subject> {

	private static final Logger logger= LogManager.getLogger(SubjectDAOImpl.class);
	private static SubjectDAOImpl instance;


	public static synchronized SubjectDAOImpl getInstance(){
		if(instance==null){
			instance=new SubjectDAOImpl();
		}
		return instance;
	}


	public List<Subject> findAll() throws DAOException {
		List<Subject> list=null;
		try{
			Session session=HibernateUtil.getSession();
			Query query=session.createQuery("SELECT S FROM Subject S ORDER BY S.name");
			System.out.println( "findall " +Subject.class+"  " + session.hashCode());
			list=query.list();
		}catch (HibernateException e) {
			throw new DAOException("Exception in findall dao subject " + e.getMessage());
		}
		return list;
	}

	public boolean delete(int id) throws DAOException {
		boolean flag=false;
		try{
			Session session = HibernateUtil.getSession();
			Subject subject=(Subject) session.get(Subject.class,id);
			System.out.println( " delete dao " +subject.getClass().getName()+"  " + session.hashCode());
			session.delete(subject);
			flag = true;
		}catch (HibernateException e) {
			throw new DAOException("Exception in delete dao subject " + e.getMessage());
		}
		return flag;
	}

	@Override
	public boolean create(Subject entity) throws DAOException {
		boolean flag=false;
		try{
			Session session = HibernateUtil.getSession();
			System.out.println( "create dao " +entity.getClass()+"  " + session.hashCode());
			session.save(entity);
			flag = true;
		}catch (HibernateException e) {
			throw new DAOException("Exception in create dao subject " + e.getMessage());
		}
		return flag;
	}

	@Override
	public Subject update(Subject entity) throws DAOException {
		try{
			Session session = HibernateUtil.getSession();
			System.out.println( " update dao " +entity.getClass().getName()+"  " + session.hashCode());
			session.saveOrUpdate(entity);
		}catch (HibernateException e) {
			throw new DAOException("Exception in update dao subject " + e.getMessage());
		}
		return entity;
	}

	@Override
	public Subject find(int id) throws DAOException {
		Subject subject=null;
		try{
			Session session=HibernateUtil.getSession();
			System.out.println( " find dao " +Subject.class+"  " + session.hashCode());
			subject=(Subject) session.get(Subject.class,id);
		}catch (HibernateException e) {
			throw new DAOException("Exception in find dao subject " + e.getMessage());
		}
		return subject;
	}
	public List<SubjectDTO> findByPage(int recordsPerPage,int numberPage) throws DAOException {
		List<SubjectDTO> list=null;
		try{
			Session session=HibernateUtil.getSession();
			Query query=session.createQuery("SELECT S.subjectId as subjectId, S.name as name" +
						" FROM Subject S ORDER BY S.name");
			query.setResultTransformer(Transformers.aliasToBean(SubjectDTO.class));
			System.out.println( "findByPage " +Subject.class+"  " + session.hashCode());
			query.setFirstResult((numberPage-1)*recordsPerPage);
			query.setMaxResults(recordsPerPage);
			list=query.list();
		}catch (HibernateException e) {
			throw new DAOException("Exception in findByPage dao subject " + e.getMessage());
		}
		return list;
	}

}
