package by.htp.teploukhava.testing.dao.daointerface;

import by.htp.telpoukhava.testing.entities.Test;
import by.htp.teploukhava.testing.exception.DAOException;

/** Interface contains methods for access to table Test */

public interface TestDAO extends AbstractDAO<Test> {

	public Test findTestBySubjectIdAndName(int subjectId, String name) throws DAOException;

	public Test findTestByTestId(int testId)throws DAOException ;

	public int updateNameTestById(Test test, String name)throws DAOException ;
}
