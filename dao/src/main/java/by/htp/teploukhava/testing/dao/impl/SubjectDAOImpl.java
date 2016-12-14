package by.htp.teploukhava.testing.dao.impl;


import by.htp.teploukhava.testing.entities.Subject;
import by.htp.teploukhava.testing.entities.SubjectDTO;
import by.htp.teploukhava.testing.dao.daointerface.AbstractDAO;
import by.htp.teploukhava.testing.exception.DAOException;
import by.htp.teploukhava.testing.managers.HibernateUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * Class implements methods of interface SubjectDAO, ovveride this, contains
 * constructor
 */
@Repository
public class SubjectDAOImpl implements AbstractDAO<Subject> {

	private static final Logger logger= LogManager.getLogger(SubjectDAOImpl.class);

	private SessionFactory sessionFactory;

	@Autowired
	public  SubjectDAOImpl(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
//	private static SubjectDAOImpl instance;
//
//
//	public static synchronized SubjectDAOImpl getInstance(){
//		if(instance==null){
//			instance=new SubjectDAOImpl();
//		}
//		return instance;
//	}


	public List<Subject> findAll() {
		Query query=sessionFactory.getCurrentSession().createQuery("SELECT S FROM Subject S ORDER BY S.name");
		List<Subject>list=query.list();
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
	public Subject find(int id)  {
		Session session=sessionFactory.getCurrentSession();
		Subject subject=(Subject) session.get(Subject.class,id);
		return subject;
	}
	public List<SubjectDTO> findByPage(int recordsPerPage,int numberPage) {
		Query query = sessionFactory.getCurrentSession().createQuery("SELECT S.subjectId as subjectId, S.name as name" +
				" FROM Subject S ORDER BY S.name");
		query.setResultTransformer(Transformers.aliasToBean(SubjectDTO.class));
		query.setFirstResult((numberPage - 1) * recordsPerPage);
		query.setMaxResults(recordsPerPage);
		List<SubjectDTO> list = query.list();
		return list;
	}
	public long countRecords(){
		Query query=sessionFactory.getCurrentSession().createQuery("Select count(*) from Subject S ORDER BY S.name");
		long countPages= (long) query.uniqueResult();

		return countPages;
	}


}
