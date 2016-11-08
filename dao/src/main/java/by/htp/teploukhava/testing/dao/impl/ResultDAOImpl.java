package by.htp.teploukhava.testing.dao.impl;

import by.htp.telpoukhava.testing.entities.Result;
import by.htp.teploukhava.testing.dao.daointerface.AbstractDAO;
import by.htp.teploukhava.testing.exception.DAOException;
import by.htp.teploukhava.testing.managers.HibernateUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import java.sql.Connection;
import java.util.List;

/**
 * Class implements methods of interface ResultDAO, ovveride this, contains
 * constructor
 */

public class ResultDAOImpl implements AbstractDAO<Result> {

    private static final Logger logger= LogManager.getLogger(ResultDAOImpl.class);
	final static String SQL_INSERT_RESULT = "INSERT INTO result(test_id, user_id, result)VALUES(?,?,?)";
	final static String SQL_SELECT_RESULT_BY_TEST_USER = "SELECT * FROM result WHERE test_id=? and user_id=?";
	private Connection connection;
	private static ResultDAOImpl instance;

	private ResultDAOImpl(){

	}

	public static synchronized ResultDAOImpl getInstance(){
		if(instance==null){
			instance=new ResultDAOImpl();
		}
		return instance;
	}

	public List<Result> findAll() {
		return null;
	}

	public boolean delete(int id) {
		return false;
	}

	@Override
	public boolean create(Result entity) throws DAOException {
		boolean flag=false;
		try{
			Session session = HibernateUtil.getSession();
			System.out.println( " create  dao " +entity.getClass()+"  " + session.hashCode());
			session.save(entity);
			flag = true;
		}catch (HibernateException e){
			throw new DAOException("Exception in update dao test "+ e.getMessage());
		}
		return flag;
	}

	@Override
	public Result update(Result entity) throws DAOException {
		return null;
	}

	@Override
	public Result find(int id) {
		return null;
	}


	public Result findResultByTestUser(int testId, int userId) throws  DAOException {
		Result result = new Result();
		try{
			Session session=HibernateUtil.getSession();
			Result.IdResult id=new Result.IdResult(testId,userId);
			String hql="SELECT R FROM Result R WHERE idResult=:id";
			Query query=session.createQuery(hql);
			query.setParameter("id",id);
			result= (Result) query.uniqueResult();
		}catch (HibernateException e){
			throw new DAOException("Exception in findResultByTestUser dao test "+ e.getMessage());
		}
		return result;
	}

}
