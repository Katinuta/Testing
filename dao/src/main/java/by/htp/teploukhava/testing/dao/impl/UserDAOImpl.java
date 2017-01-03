package by.htp.teploukhava.testing.dao.impl;


import by.htp.teploukhava.testing.dao.daointerface.AbstractDAO;
import by.htp.teploukhava.testing.entities.Subject;
import by.htp.teploukhava.testing.entities.SubjectDTO;
import by.htp.teploukhava.testing.entities.User;
import by.htp.teploukhava.testing.exception.DAOException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Class implements methods of interface UserDAO, ovveride this, contains
 * constructor
 */
@Repository
public class UserDAOImpl implements AbstractDAO<User> {
    private static final Logger logger = LogManager.getLogger(UserDAOImpl.class);
    private final static String SQL_SELECT_USERS = "SELECT * FROM user";
    private final static String SQL_SELECT_USERS_SUBJECTS = "SELECT subject.name , subject.subject_id FROM student_to_subject "
            + "JOIN subject USING (subject_id) WHERE student_to_subject.user_id=?";
    private final static String SQL_SELECT_USER_BY_LOGIN = "SELECT * FROM user WHERE user.login=?";

    private SessionFactory sessionFactory;

    public UserDAOImpl() {
    }

    @Autowired
    public UserDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<User> findAll() {
        Query query = sessionFactory.getCurrentSession().createQuery("FROM User ");
        List<User> listUsers = query.list();
        return listUsers;
    }

    @Override
    public boolean delete(int id) throws DAOException {
        boolean flag = false;
        Session session = sessionFactory.getCurrentSession();
        User user = (User) session.get(User.class, id);
        session.delete(user);
        flag = true;
        return flag;
    }

    @Override
    public boolean create(User entity) {
        boolean flag = false;
        sessionFactory.getCurrentSession().save(entity);
        flag = true;
        return flag;
    }

    @Override
    public User update(User entity) throws DAOException {
        sessionFactory.getCurrentSession().update(entity);
        return entity;
    }

    @Override
    public User find(int id) {
        User user = (User) sessionFactory.getCurrentSession().get(User.class, id);
        return user;
    }

    public List<Subject> findUserSubject(int userId)  {

        String hql = "SELECT S.subject FROM SubjectToStudent S WHERE S.user.userId=:userId";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("userId", userId);
        List<Subject> subjectList = query.list();
        return subjectList;
    }

    public User findUserByLogin(String login) {

        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
        User user = (User) criteria.add(Restrictions.eq("login", login)).uniqueResult();
        return user;
    }

    public List<SubjectDTO> findUserSubjectPage(int userId, int numberPage, int recordsPerPage) {

        String hql = "SELECT S.subject.subjectId as subjectId," +
                "S.subject.name as name FROM SubjectToStudent S WHERE S.user.userId=:userId";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("userId", userId);
        query.setResultTransformer(Transformers.aliasToBean(SubjectDTO.class));
        query.setFirstResult((numberPage - 1) * recordsPerPage);
        query.setMaxResults(recordsPerPage);
        List<SubjectDTO> subjectList = query.list();
        return subjectList;
    }
    public long countUserSubject(int userId){
        String hql = "SELECT count (*) FROM SubjectToStudent S WHERE S.user.userId=:userId";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("userId", userId);
        long countUserSubject = (long) query.uniqueResult();
        return countUserSubject;
    }

}
