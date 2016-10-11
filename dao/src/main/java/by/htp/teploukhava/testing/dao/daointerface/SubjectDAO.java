package by.htp.teploukhava.testing.dao.daointerface;

import by.htp.telpoukhava.testing.entities.Subject;
import by.htp.teploukhava.testing.exception.DAOException;

/** Interface contains methods for access to table Subject */

public interface SubjectDAO extends AbstractDAO<Subject> {

	public Subject findSubjectById(int subjectId) throws DAOException;

}
