package by.htp.teploukhava.testing.dao.impl;


import by.htp.telpoukhava.testing.entities.Subject;
import by.htp.telpoukhava.testing.entities.SubjectDTO;
import by.htp.telpoukhava.testing.entities.User;
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
import org.hibernate.transform.Transformers;

import java.sql.Connection;
import java.util.List;

/**
 * Class implements methods of interface UserDAO, ovveride this, contains
 * constructor
 */

public class UserDAOImpl implements AbstractDAO<User> {

	private static final Logger logger= LogManager.getLogger(UserDAOImpl.class);
	private final static String SQL_SELECT_USERS = "SELECT * FROM user";
	private final static String SQL_SELECT_USERS_SUBJECTS = "SELECT subject.name , subject.subject_id FROM student_to_subject "
			+ "JOIN subject USING (subject_id) WHERE student_to_subject.user_id=?";
	private final static String SQL_SELECT_USER_BY_LOGIN = "SELECT * FROM user WHERE user.login=?";
	private Connection connection;
	private static UserDAOImpl instance;

	private UserDAOImpl(){

	}

	public static synchronized UserDAOImpl getInstance(){
		if(instance==null){
			instance=new UserDAOImpl();
		}
		return instance;
	}
	public List<User> findAll() throws DAOException {
        List<User> listUsers=null;
        try{}catch (HibernateException e){
            throw new DAOException("Exception in findall dao user "+ e.getMessage());
        }
		Session session=HibernateUtil.getSession();
		Query query=session.createQuery("FROM User ");
		System.out.println( " find all dao " +User.class+"  " + session.hashCode());
		listUsers=query.list();
		return listUsers;
	}

	@Override
	public boolean delete(int id) throws DAOException {
		boolean flag=false;
		try{
            Session session = HibernateUtil.getSession();
            User user=(User) session.get(User.class,id);
            System.out.println( " delete dao " +user.getClass().getName()+"  " + session.hashCode());
            session.delete(user);
            flag = true;
        }catch (HibernateException e){
            throw new DAOException("Exception in delete dao user "+ e.getMessage());
        }
		return flag;
	}

	@Override
	public boolean create(User entity) throws DAOException {
		boolean flag=false;
        try{
            Session session = HibernateUtil.getSession();
            System.out.println( "create dao " +entity.getClass()+"   " + session.hashCode());
            session.save(entity);
            flag = true;
        }catch (HibernateException e){
            throw new DAOException("Exception in create dao user "+ e.getMessage());
        }
		return flag;
	}

	@Override
	public User update(User entity) throws DAOException {
	    try{
            Session session = HibernateUtil.getSession();
            System.out.println( " update dao " +entity.getClass().getName()+"  " + session.hashCode());
            session.update(entity);
        }catch (HibernateException e){
            throw new DAOException("Exception in create dao user "+ e.getMessage());
        }
		return entity;
	}

	@Override
	public User find(int id) throws DAOException {
	    User user=null;
        try{
            Session session=HibernateUtil.getSession();
            System.out.println( " find dao " +User.class+"  " + session.hashCode());
            user= (User) session.get(User.class,id);
        }catch (HibernateException e){
            throw new DAOException("Exception in find dao user "+ e.getMessage());
        }
        return  user;
	}

	public List<Subject> findUserSubject(int userId) throws DAOException {
		List<Subject> subjectList=null;
        try{
            Session session=HibernateUtil.getSession();
            System.out.println( "findUserSubject dao " +User.class+"  " + session.hashCode());
            String hql="SELECT S.subject FROM SubjectToStudent S WHERE S.user.userId=:userId";
            Query query=session.createQuery(hql);
            query.setParameter("userId",userId);
            subjectList=query.list();
        }catch (HibernateException e){
            throw new DAOException("Exception in findUserSubject dao user "+ e.getMessage());
        }
		return subjectList;
	}

	public User findUserByLogin(String login) throws DAOException {
		User user =null;
		try{
            Session session=HibernateUtil.getSession();
            System.out.println( " findUserByLogin dao user" +"  " + session.hashCode());
            Criteria criteria=session.createCriteria(User.class);
            user= (User) criteria.add(Restrictions.eq("login",login)).uniqueResult();
        }catch (HibernateException e){
            throw new DAOException("Exception in findUserByLogin dao user "+ e.getMessage());
        }
		return user;
	}
    public List<SubjectDTO> findUserSubjectPage(int userId,int numberPage,int recordsPerPage) throws DAOException {
        List<SubjectDTO> subjectList=null;
        try{
            Session session=HibernateUtil.getSession();
            System.out.println( "findUserSubject dao " +User.class+"  " + session.hashCode());
            String hql="SELECT S.subject.subjectId as subjectId," +
                    "S.subject.name as name FROM SubjectToStudent S WHERE S.user.userId=:userId";
            Query query=session.createQuery(hql);
            query.setParameter("userId",userId);
            query.setResultTransformer(Transformers.aliasToBean(SubjectDTO.class));
            query.setFirstResult((numberPage-1)*recordsPerPage);
            query.setMaxResults(recordsPerPage);
            subjectList=query.list();
        }catch (HibernateException e){
            throw new DAOException("Exception in findUserSubject dao user "+ e.getMessage());
        }
        return subjectList;
    }
}
