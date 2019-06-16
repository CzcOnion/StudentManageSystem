package service.impl;

import com.mysql.cj.QueryResult;
import db.MyHibernateSessionFactory;
import entity.StudentEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import service.StudentDAO;

import java.util.List;

//学生业务逻辑实现类
public class StudentDAOImpl implements StudentDAO {

    @Override
    public List<StudentEntity> queryAllStudents() {
        Transaction tx = null;
        List<StudentEntity> list = null;
        String hql = "";
        try{
            Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            hql = "from StudentEntity";
            Query query = session.createQuery(hql);
            list = query.list();
            tx.commit();
            return list;
        }catch (Exception ex){
            ex.printStackTrace();
            tx.commit();
            return list;
        }finally {
            if(tx != null)
                tx = null;
        }
    }

    @Override
    public StudentEntity queryStudentBySid(String sid) {
        Transaction tx = null;
        StudentEntity studentEntity = null;
        try{
            Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            studentEntity = (StudentEntity) session.get(StudentEntity.class,sid);
            tx.commit();
            return studentEntity;
        }catch (Exception ex){
            ex.printStackTrace();
            tx.commit();
            return studentEntity;
        }finally {
            if(tx != null)
                tx = null;
        }
    }

    @Override
    public boolean addStudent(StudentEntity studentEntity) {
        studentEntity.setSid(getNewSid());
        Transaction tx = null;
        try{
            Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            session.save(studentEntity);
            tx.commit();
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            tx.commit();
            return false;
        }finally {
            if(tx != null)
                tx = null;
        }
    }

    @Override
    public boolean updateStudent(StudentEntity studentEntity) {
        Transaction tx = null;
        try{
            Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            session.update(studentEntity);
            tx.commit();
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            tx.commit();
            return false;
        }finally {
            if(tx != null)
                tx = null;
        }
    }

    @Override
    public boolean deleteStudent(String sid) {
        Transaction tx = null;
        try{
            Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            StudentEntity studentEntity = session.get(StudentEntity.class,sid);
            session.delete(studentEntity);
            tx.commit();
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            tx.commit();
            return false;
        }finally {
            if(tx != null)
                tx = null;
        }
    }
    //获得学号
    private String getNewSid(){
        Transaction tx = null;
        String sid = null;
        String hql = "";
        try{
            Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            hql = "select max(sid) from StudentEntity";
            Query query = session.createQuery(hql);
            sid  = (String) query.uniqueResult();
            if(sid == null){
                sid = "S0000001";
            }else{
                //取后7位
                String tmp = sid.substring(1);
                int i = Integer.parseInt(tmp);
                //自增
                i++;
                tmp = String.valueOf(i);
                int len = tmp.length();
                //凑够7位
                for (int j = 0; j <7-len ; j++) {
                    tmp = "0" + tmp;
                }
                sid = "S" + tmp;
            }
            tx.commit();
            return sid;
        }catch (Exception ex){
            ex.printStackTrace();
            tx.commit();
            return null;
        }finally {
            if(tx != null)
                tx = null;
        }
    }
}
