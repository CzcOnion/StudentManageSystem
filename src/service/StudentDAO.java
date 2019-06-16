package service;

import entity.StudentEntity;

import java.util.List;

//学生业务逻辑接口
public interface StudentDAO {
    //查询所有学生
    public List<StudentEntity> queryAllStudents();
    //根据id查询学生
    public StudentEntity queryStudentBySid(String sid);
    //添加学生信息
    public boolean addStudent(StudentEntity studentEntity);
    //更新学生信息
    public boolean updateStudent(StudentEntity studentEntity);
    //删除学生信息
    public boolean deleteStudent(String sid);
}
