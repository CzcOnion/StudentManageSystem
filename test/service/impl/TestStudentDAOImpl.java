package service.impl;

import entity.StudentEntity;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.List;

public class TestStudentDAOImpl {
    @Test
    public void testQueryAllStudent(){
        List<StudentEntity> list = new StudentDAOImpl().queryAllStudents();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    @Test
    public void TestAddStudent(){
        StudentEntity studentEntity3 = new StudentEntity(null,"陈至聪","男",Timestamp.valueOf("1998-7-25 11:11:11"),"广东");
        System.out.println(new StudentDAOImpl().addStudent(studentEntity3));
    }

    @Test
    public void TestGetNewSid(){
//        System.out.println(new StudentDAOImpl().getNewSid());
    }
}
