package by.htp.teploukhava.testing.dao.impl;


import by.htp.telpoukhava.testing.entities.Subject;
import by.htp.telpoukhava.testing.entities.User;
import by.htp.teploukhava.testing.dao.daointerface.UserDAO;
import by.htp.teploukhava.testing.exception.DAOException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class implements methods of interface UserDAO, ovveride this, contains
 * constructor
 */

public class UserDAOImpl implements UserDAO {

	private static final Logger logger= LogManager.getLogger(UserDAOImpl.class);
	private final static String SQL_SELECT_USERS = "SELECT * FROM user";
	private final static String SQL_SELECT_USERS_SUBJECTS = "SELECT subject.name , subject.subject_id FROM student_to_subject "
			+ "JOIN subject USING (subject_id) WHERE student_to_subject.user_id=?";
	private final static String SQL_INSERT_USER = "INSERT INTO user(name, surname,login, password, access) VALUES(?,?,?,?,?)";
	private final static String SQL_SELECT_USER_BY_LOGIN = "SELECT * FROM user WHERE user.login=?";
	private Connection connection;
	private static UserDAOImpl instance;

	private UserDAOImpl(){

	}
	private UserDAOImpl(Connection connection){

		this.connection = connection;
	}

	public static synchronized UserDAOImpl getInstance(Connection connection){
		if(instance==null){
			instance=new UserDAOImpl(connection);

		}else{
			instance.connection=connection;
		}
		return instance;
	}

	public List<User> findAll() throws DAOException {
		List<User> list = new ArrayList<>();
//		PreparedStatement ps = null;
//		Connection connection=null;
		try (PreparedStatement ps=connection.prepareStatement(SQL_SELECT_USERS)){
//			connection=ConnectorDB.getInstance().getConnection();
//			ps = connection.prepareStatement(SQL_SELECT_USERS);
			try(ResultSet resultSet = ps.executeQuery()) {
				while (resultSet.next()) {
					User user = new User();
					user.setUserId(resultSet.getInt("user_id"));
					user.setName(resultSet.getString("name"));
					user.setSurname(resultSet.getString("surname"));
					list.add(user);
				}
			}
			logger.info("All users found");
		} catch (SQLException e) {
			logger.error("Exception in the method findAll "+ UserDAOImpl.class.getName());
			throw new DAOException("Exception in the method findAll "+UserDAOImpl.class.getName()+e.getMessage());
//		} catch (PropertyVetoException e) {
//			e.printStackTrace();
//		} finally {
//			close(ps);
		}
		return list;

	}

	public boolean delete(int id) {
		return false;
	}

	public boolean create(User entity) throws DAOException {
		boolean flag ;
		//PreparedStatement ps = null;
	//	Connection connection=null;
		try(PreparedStatement ps = connection.prepareStatement(SQL_INSERT_USER);) {
			//connection=ConnectorDB.getInstance().getConnection();
			//ps = connection.prepareStatement(SQL_INSERT_USER);
			ps.setString(1, entity.getName());
			ps.setString(2, entity.getSurname());
			ps.setString(3, entity.getLogin());
			ps.setString(4, entity.getPassword());
			ps.setBoolean(5, entity.isAccess());
			ps.executeUpdate();
			flag = true;
			logger.info("User created");
		} catch (SQLException e) {
			logger.error("Exception in the method create "+ UserDAOImpl.class.getName());
			throw new DAOException("Exception in the method create "+e.getMessage());
	//	} catch (PropertyVetoException e) {
	//		e.printStackTrace();
//		} finally {
//			close(ps);
		}
		return flag;
	}

	public User update(User entity) {
		return null;
	}

	public List<Subject> findUserSubject(int userId) throws DAOException {
		List<Subject> list = new ArrayList<>();
	//	PreparedStatement ps = null;
	//	Connection connection=null;
		try(PreparedStatement ps = connection.prepareStatement(SQL_SELECT_USERS_SUBJECTS)) {
	//		connection=ConnectorDB.getInstance().getConnection();
	//		ps = connection.prepareStatement(SQL_SELECT_USERS_SUBJECTS);
			ps.setInt(1, userId);
	//		ResultSet resultSet = ps.executeQuery();
			try(ResultSet resultSet = ps.executeQuery()){
				while (resultSet.next()) {
					Subject subject = new Subject();
					subject.setSubjectId(resultSet.getInt("subject_id"));
					subject.setName(resultSet.getString("name"));
					list.add(subject);
				}
			}
			logger.info("User's subject found");
		} catch (SQLException e) {
			logger.error("Exception in the method findUserSubject "+ UserDAOImpl.class.getName());
			throw new DAOException("Exception in the method findUserSubject " +UserDAOImpl.class.getName()+e.getMessage());
//		} catch (PropertyVetoException e) {
//			e.printStackTrace();
//		} finally {
//			close(ps);
		}
		return list;
	}

	public User findUserByLogin(String login) throws DAOException {
		User user = new User();
//		PreparedStatement ps = null;
	//	Connection connection;
		try (PreparedStatement ps=connection.prepareStatement(SQL_SELECT_USER_BY_LOGIN)){
		//	connection=ConnectorDB.getInstance().getConnection();
//			ps = connection.prepareStatement(SQL_SELECT_USER_BY_LOGIN);
			ps.setString(1, login);
			try(ResultSet resultSet = ps.executeQuery()) {
				while (resultSet.next()) {
					user.setUserId(resultSet.getInt("user_id"));
					user.setName(resultSet.getString("name"));
					user.setSurname(resultSet.getString("surname"));
					user.setLogin(resultSet.getString("login"));
					user.setPassword(resultSet.getString("password"));
					user.setAccess(resultSet.getBoolean("access"));
				}
			}
			logger.info("User founded by login");
		} catch (SQLException e) {
			logger.error("Exception in the method findUserByLogin "+ UserDAOImpl.class.getName());
			throw new DAOException("Exception in the method findUserByLogin "+e.getMessage());
//	} catch (PropertyVetoException e) {
//		e.printStackTrace();
//		} finally {
//			close(ps);
		}
		return user;
	}

//	public void close(PreparedStatement ps) {
//		try {
//			if (ps != null) {
//				ps.close();
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
}
