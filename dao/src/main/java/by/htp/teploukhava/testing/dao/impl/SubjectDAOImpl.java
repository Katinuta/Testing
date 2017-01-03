package by.htp.teploukhava.testing.dao.impl;


import by.htp.teploukhava.testing.dao.daointerface.AbstractDAO;
import by.htp.teploukhava.testing.entities.Subject;
import by.htp.teploukhava.testing.entities.SubjectDTO;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
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

	public List<Subject> findAll() {
		Query query=sessionFactory.getCurrentSession().createQuery("SELECT S FROM Subject S ORDER BY S.name");
		List<Subject>list=query.list();
		return list;
	}

	public boolean delete(int id)  {
		boolean flag=false;
		Subject subject=(Subject) sessionFactory.getCurrentSession().get(Subject.class,id);
		sessionFactory.getCurrentSession().delete(subject);
		flag = true;
		return flag;
	}

	@Override
	public boolean create(Subject entity)  {
		boolean flag=false;
		sessionFactory.getCurrentSession().save(entity);
		flag = true;
		return flag;
	}

	@Override
	public Subject update(Subject entity) {
		sessionFactory.getCurrentSession().saveOrUpdate(entity);
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
