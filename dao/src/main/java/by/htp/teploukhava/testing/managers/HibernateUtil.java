package by.htp.teploukhava.testing.managers;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


/**
 * Created by Admin on 14.10.16.
 */
public class HibernateUtil {
    private final static ThreadLocal SESSION_LOCAL=new ThreadLocal();
    private final static SessionFactory SESSION_FACTORY=buildSessionFactory();

    private static SessionFactory buildSessionFactory(){
        try{
          return new Configuration().configure().buildSessionFactory();
        }catch(Throwable ex){
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory(){
        return SESSION_FACTORY;
    }
    public static Session getSession(){
        Session session= (Session) SESSION_LOCAL.get();
        if(session==null){
            session=SESSION_FACTORY.openSession();
            SESSION_LOCAL.set(session);
        }
        return session;
    }

    public static void closeSession(){
        Session session= (Session) SESSION_LOCAL.get();
        if(session!=null){
          try{
              SESSION_LOCAL.remove();
          }catch (HibernateException e){
              e.printStackTrace();;
          }
        }
    }
}
