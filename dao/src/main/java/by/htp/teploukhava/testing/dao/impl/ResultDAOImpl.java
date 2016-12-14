package by.htp.teploukhava.testing.dao.impl;

import by.htp.teploukhava.testing.dao.daointerface.AbstractDAO;
import by.htp.teploukhava.testing.entities.Result;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Class implements methods of interface ResultDAO, ovveride this, contains
 * constructor
 */
@Repository
public class ResultDAOImpl implements AbstractDAO<Result> {

    private static final Logger logger= LogManager.getLogger(ResultDAOImpl.class);
	final static String SQL_INSERT_RESULT = "INSERT INTO result(test_id, user_id, result)VALUES(?,?,?)";
	final static String SQL_SELECT_RESULT_BY_TEST_USER = "SELECT R FROM Result R WHERE idResult=:id";

	private SessionFactory sessionFactory;

	public ResultDAOImpl(){}

	@Autowired
	public ResultDAOImpl(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}

	public List<Result> findAll() {
		return null;
	}

	public boolean delete(int id) {
		return false;
	}

	@Override
	public boolean create(Result entity)  {
		boolean flag=false;
		sessionFactory.getCurrentSession().save(entity);
		flag = true;
		return flag;
	}

	@Override
	public Result update(Result entity)  {
		return null;
	}

	@Override
	public Result find(int id) {
		return null;
	}


	public Result findResultByTestUser(int testId, int userId)  {
		Result.IdResult id=new Result.IdResult(testId,userId);
		Query query=sessionFactory.getCurrentSession().createQuery(SQL_SELECT_RESULT_BY_TEST_USER);
		query.setParameter("id",id);
		Result result= (Result) query.uniqueResult();
		return result;
	}

}
