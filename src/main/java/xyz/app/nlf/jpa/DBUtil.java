
package xyz.app.nlf.jpa;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Lap
 */
public class DBUtil {
    
    private static SessionFactory sessionFactory;
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    public static void connect() {
        sessionFactory = new Configuration()
                .configure()
                .buildSessionFactory();
    }
    
    public static void close() {
        if(sessionFactory != null) 
            sessionFactory.close();
    }
    
}
