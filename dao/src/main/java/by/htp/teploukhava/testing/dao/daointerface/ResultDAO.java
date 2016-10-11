package by.htp.teploukhava.testing.dao.daointerface;
import by.htp.telpoukhava.testing.entities.Result;
import by.htp.teploukhava.testing.exception.DAOException;

/**Interface contains methods for access to table Result*/

public interface ResultDAO extends AbstractDAO<Result>{

	public Result findResultByTestUser(int testId, int userId) throws DAOException;
	
}
