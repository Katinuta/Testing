package by.htp.teploukhava.testing.dao.daointerface;

import by.htp.telpoukhava.testing.abst.Entity;
import by.htp.teploukhava.testing.exception.DAOException;

import java.util.List;

/**Interface for access to information of database*/

public interface AbstractDAO <T extends Entity>{
	
	public abstract List<T> findAll() throws DAOException;
	public abstract boolean delete(int id) throws DAOException;
	public abstract boolean create(T entity) throws DAOException;
	public abstract T update(T entity) throws DAOException;

}
