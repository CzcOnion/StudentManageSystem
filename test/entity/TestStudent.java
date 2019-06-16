package entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.EnumSet;

public class TestStudent {
    @Test
    public void testSchemaExport(){
        ServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        Metadata metadata = new MetadataSources(registry).buildMetadata();
        SchemaExport export = new SchemaExport();
        export.create(EnumSet.of(TargetType.DATABASE),metadata);
    }

    @Test
    public void testSaveStudents(){
        //创建配置对象
        Configuration configuration = new Configuration().configure();
        //创建服务注册对象
        ServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        //创建sessionFactory
        SessionFactory sessionFactory = configuration.buildSessionFactory(registry);
        //创建session对象
        Session session = sessionFactory.getCurrentSession();
        //创建事务对象
        Transaction tx = session.beginTransaction();
        StudentEntity studentEntity1 = new StudentEntity("160313","张泽新","男",Timestamp.valueOf("1998-6-18 11:11:11"),"山西");
        StudentEntity studentEntity2 = new StudentEntity("160314","祝志豪","男",Timestamp.valueOf("1998-1-10 11:11:11"),"湖南");
        StudentEntity studentEntity3 = new StudentEntity("160315","陈至聪","男",Timestamp.valueOf("1998-7-25 11:11:11"),"广东");
        StudentEntity studentEntity4 = new StudentEntity("160316","董金霖","男",Timestamp.valueOf("1998-2-14 11:11:11"),"山东");
        StudentEntity studentEntity5 = new StudentEntity("160311","靳毅凡","女",Timestamp.valueOf("1999-4-28 11:11:11"),"河南");
        StudentEntity studentEntity6 = new StudentEntity("160320","燕熠新","女",Timestamp.valueOf("1998-2-11 11:11:11"),"广西");
        session.save(studentEntity1);
        session.save(studentEntity2);
        session.save(studentEntity3);
        session.save(studentEntity4);
        session.save(studentEntity5);
        session.save(studentEntity6);
        tx.commit();
        sessionFactory.close();
    }
}
