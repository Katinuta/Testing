package by.htp.teploukhava.testing.dao.daointerface;

import by.htp.telpoukhava.testing.entities.Subject;
import by.htp.telpoukhava.testing.entities.User;
import by.htp.teploukhava.testing.exception.DAOException;

import java.util.List;

/**Interface contains methods for access to table User*/

public interface UserDAO extends AbstractDAO<User> {

	public List<Subject> findUserSubject(int userId) throws DAOException;

	public User findUserByLogin(String login) throws DAOException;
}
