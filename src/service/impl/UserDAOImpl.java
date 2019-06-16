package service.impl;

import db.MyHibernateSessionFactory;
import entity.UserEntity;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import service.UserDAO;

import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public boolean userLogin(UserEntity user) {
        Transaction tx = null;
        String hql = "";
        try{
            Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            hql = "select username from entity.UserEntity where username=:username and password=:password";
            Query query = session.createQuery(hql);
//            Query query = session.createQuery("select user from UserEntity user");
            query.setParameter("username",user.getUsername());
            query.setParameter("password",user.getPassword());
//            query.setParameter(0,user.getUsername());
//            query.setParameter(1,user.getPassword());
            List list = query.list();
            tx.commit();
            if(list.size() <= 0) {
                return false;
            }else{
                return true;
            }
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }finally {
            if(tx != null){
                //tx.commit();
                tx = null;
            }
        }
    }
}
