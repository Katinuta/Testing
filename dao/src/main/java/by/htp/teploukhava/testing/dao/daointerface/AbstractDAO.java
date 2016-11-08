package by.htp.teploukhava.testing.dao.daointerface;

import by.htp.telpoukhava.testing.abst.EntityAbs;
import by.htp.teploukhava.testing.exception.DAOException;

import java.util.List;

/**Interface for access to information of database*/

public interface  AbstractDAO <T extends EntityAbs>{
//   protected final Class<T> persistentClass=(Class<T>) ((ParameterizedType) getClass()
//            .getGenericSuperclass()).getActualTypeArguments()[0];
//    private EntityAbs entityAbs;
//
//    public AbstractDAO(EntityAbs entityAbs){
//        this.entityAbs=entityAbs;
//    }
//
//    public Class<T> getType(){
//        return (Class<T>) entityAbs.getClass();
//    }

    public  List<T> findAll() throws DAOException;

	public  boolean delete(int id) throws DAOException;
//    {
//	boolean flag=false;
//	Session session = HibernateUtil.getSession();
//		T t=(T) session.get(getType(),id);
//        System.out.println( " delete dao " +t.getClass()+"  " + session.hashCode());
//		session.delete(t);
//		flag = true;
//		return flag;
//	}

	public  boolean create(T entity) throws DAOException;
	//{
//		boolean flag=false;
//		Session session = HibernateUtil.getSession();
//	System.out.println( " create dao " +entity.getClass()+"  " + session.hashCode());
//		session.save(entity);
//		flag = true;
//		return flag;flag
//	}

	public  T update(T entity) throws DAOException;
//	{
//		Session session = HibernateUtil.getSession();
//		System.out.println( " update dao " +entity.getClass().getName()+"  " + session.hashCode());
//		session.saveOrUpdate(entity);
//		return entity;entity
//	}
	 public  T find(int id) throws DAOException;
//	 {
//		 T t=null;
//		 Session session=HibernateUtil.getSession();
//		 //t=session.get(get)
//		 return t;
//	 }

}
