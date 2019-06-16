package db;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class MyHibernateSessionFactory {
    //绘画工厂属性
    private static SessionFactory sessionFactory;
    //单例模式
    private MyHibernateSessionFactory(){

    }
    //公有静态工厂方法
    public static SessionFactory getSessionFactory(){
        if(sessionFactory == null){
            synchronized(MyHibernateSessionFactory.class) {
                if(sessionFactory == null){
                    Configuration configuration = new Configuration().configure();
                    ServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
                    sessionFactory = configuration.buildSessionFactory(registry);
                    return sessionFactory;
                }
            }
        }
        return sessionFactory;
    }
}
