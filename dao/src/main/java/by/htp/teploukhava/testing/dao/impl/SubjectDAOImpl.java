package by.htp.teploukhava.testing.dao.impl;


import by.htp.telpoukhava.testing.entities.Subject;
import by.htp.teploukhava.testing.dao.daointerface.SubjectDAO;
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
 * Class implements methods of interface SubjectDAO, ovveride this, contains
 * constructor
 */

public class SubjectDAOImpl implements SubjectDAO {

	private static final Logger logger= LogManager.getLogger(SubjectDAOImpl.class);
	public final static String SQL_INSERT_SUBJECT="INSERT INTO subject (name) VALUES(?)";
	public final static String SQL_SELECT_SUBJECT="SELECT subject_id,name FROM subject";
	public final static String SQL_SELECT_SUBJECT_BY_ID="SELECT * FROM subject WHERE  subject_id=?";
	private Connection connection;
	private static SubjectDAOImpl instance;

	private SubjectDAOImpl(Connection connection){

		this.connection = connection;
	}

	public static synchronized SubjectDAOImpl getInstance(Connection connection){
		if(instance==null){
			instance=new SubjectDAOImpl(connection);
		}else{
			instance.connection=connection;
		}
		return instance;
	}


	public List<Subject> findAll() throws DAOException {
		List<Subject> list=new ArrayList<>();
	//	PreparedStatement ps=null;
	//	Connection connection =  null;
		try (PreparedStatement ps=connection.prepareStatement(SQL_SELECT_SUBJECT)){
	//		connection=ConnectorDB.getInstance().getConnection();
		//	ps=connection.prepareStatement(SQL_SELECT_SUBJECT);
			try(ResultSet resultSet=ps.executeQuery()) {
				while (resultSet.next()) {
					Subject subject = new Subject();
					subject.setSubjectId(resultSet.getInt("subject_id"));
					subject.setName(resultSet.getString("name"));
					list.add(subject);
				}
			}
			logger.info("All subjects found");
		} catch (SQLException e) {
			logger.error("Exception in the method findAll"+ SubjectDAOImpl.class.getName());
			throw new DAOException("Exception in the method findAll "+e.getMessage());
	//	} catch (PropertyVetoException e) {
	//		e.printStackTrace();
//		} finally{
//			close(ps);
		}
		return list;
	}

	public boolean delete(int id) {
		return false;
	}

	public boolean create(Subject entity) throws DAOException {
		boolean flag;
	//	PreparedStatement ps=null;
	//	Connection connection =  null;
		try(PreparedStatement ps=connection.prepareStatement(SQL_INSERT_SUBJECT)) {
	//		connection=ConnectorDB.getInstance().getConnection();
	//		ps=connection.prepareStatement(SQL_INSERT_SUBJECT);
		//	System.out.println(entity.getName());
			ps.setString(1, entity.getName());
			ps.executeUpdate();
			flag=true;
			logger.info("Subject created");
		} catch (SQLException e) {
			logger.error("Exception in the method create"+ SubjectDAOImpl.class.getName());
			throw new DAOException("Exception in the method create "+e.getMessage());
//		} catch (PropertyVetoException e) {
//			e.printStackTrace();
//		} finally{
//			close(ps);
		}
		return flag;
	}

	@Override
	public Subject update(Subject entity) {
		return null;
	}
	
	public Subject findSubjectById(int subjectId) throws DAOException {
		Subject subject=new Subject();
//		PreparedStatement ps=null;
//		Connection connection =  null;
		try (PreparedStatement ps=connection.prepareStatement(SQL_SELECT_SUBJECT_BY_ID)){
//			connection=ConnectorDB.getInstance().getConnection();
//			ps=connection.prepareStatement(SQL_SELECT_SUBJECT_BY_ID);
			ps.setInt(1,subjectId );
			try(ResultSet resultSet=ps.executeQuery()) {
				while (resultSet.next()) {
					subject.setSubjectId(resultSet.getInt("subject_id"));
					subject.setName(resultSet.getString("name"));
				}
			}
			logger.info("Subject founded by id");
		} catch (SQLException e) {
			logger.error("Exception in the method findSubjectById"+ SubjectDAOImpl.class.getName());
			throw new DAOException("Exception in the method findSubjectById "+e.getMessage());
//		} catch (PropertyVetoException e) {
//			e.printStackTrace();
//		} finally{
//			close(ps);
		}
		return subject;
	}
	
//	public void close(PreparedStatement ps) throws DAOException {
//		try {
//			if (ps != null) {
//				ps.close();
//			}
//		} catch (SQLException e) {
//			logger.error("Exception close statement in  "+ SubjectDAOImpl.class.getName());
//			throw new DAOException("Exception close statement in "+e.getMessage());
//		}
//	}
}
