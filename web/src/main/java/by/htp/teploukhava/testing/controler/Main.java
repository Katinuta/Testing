package by.htp.teploukhava.testing.managers;

import by.htp.teploukhava.testing.dao.impl.UserDAOImpl;
import by.htp.teploukhava.testing.entities.User;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Admin on 14.10.16.
 */
public class Main {
    public static  void main(String[] args) {
//
//        System.out.println("Temp Dir:"+System.getProperty("java.io.tmpdir"));
//        SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
//        Statistics stats = sessionFactory.getStatistics();
//        System.out.println("Stats enabled="+stats.isStatisticsEnabled());
//        stats.setStatisticsEnabled(true);
//        System.out.println("Stats enabled="+stats.isStatisticsEnabled());
//        String[] list=stats.getSecondLevelCacheRegionNames();
//        Session session=sessionFactory.openSession();
//        System.out.println("session "+session.hashCode());
//
//        printStat(stats);
//
//        System.out.println(" Ex 1");
//        User user= (User) session.load(User.class,1);
//        System.out.println(user.getName());
//        printStat(stats);
//
//        System.out.println(" Ex 2");
//        user= (User) session.load(User.class,1);
//        System.out.println(user.getName());
//        printStat(stats);
//
//        session.evict(user);
//
//        System.out.println(" Ex 3");
//        user= (User) session.load(User.class,1);
//        System.out.println(user.getName());
//        printStat(stats);
//
//        System.out.println(" Ex 4");
//        user= (User) session.load(User.class,1);
//        System.out.println(user.getName());
//        printStat(stats);
//
//       Session sessionOther=sessionFactory.openSession();
//        System.out.println("session "+sessionOther.hashCode());
//        System.out.println(" Ex 5");
//        user= (User) sessionOther.load(User.class,1);
//        System.out.println(user.getName());
//
//
//
//    }
//    public static void printStat(Statistics stats){
//        System.out.println("Fetch count="+stats.getEntityFetchCount());
//        System.out.println("Second Level Hit Count="+stats.getSecondLevelCacheHitCount());
//        System.out.println("Second Level Miss Count="+stats.getSecondLevelCacheMissCount());
//        System.out.println("Second Level Put Count="+stats.getSecondLevelCachePutCount());
//    }
        ApplicationContext context = new ClassPathXmlApplicationContext("WEB-INF/mvc-servlet.xml");
        SessionFactory sessionFactory= (SessionFactory) context.getBean("sessionFactory");
        BasicDataSource dataSource = (BasicDataSource) context.getBean("dataSource");
        UserDAOImpl userDAO= (UserDAOImpl) context.getBean("userService");
        User user=userDAO.find(3);
        System.out.println(user.toString());

        System.out.println(sessionFactory.getSessionFactoryOptions().toString());
        System.out.println(dataSource.getUsername());
    }
}
