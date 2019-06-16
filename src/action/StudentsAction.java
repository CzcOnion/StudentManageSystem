package action;

import entity.StudentEntity;
import service.StudentDAO;
import service.impl.StudentDAOImpl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

//学生action
public class StudentsAction extends SurperAction{
    private static final long serialVersionUID = 1L;
    private String sid;
    private String sname;
    private String gender;
    private Date birthday;
    private String address;

    public void setSid(String sid) {
        this.sid = sid;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSid() {
        return sid;
    }

    public String getSname() {
        return sname;
    }

    public String getGender() {
        return gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getAddress() {
        return address;
    }

    //    private StudentDAO studentDAO;
    //查询所有学生
    public String query(){
        StudentDAO studentDAO = new StudentDAOImpl();
        List<StudentEntity> list = studentDAO.queryAllStudents();
        if(list != null && list.size() > 0)
            session.setAttribute("students_list",list);
        return "query_success";
    }

    //删除学生
    public String delete(){
        StudentDAO studentDAO = new StudentDAOImpl();
        String sid = httpServletRequest.getParameter("sid");
        if(studentDAO.deleteStudent(sid))
            return "delete_success";
        else
            return "delete_fail";
    }

    //添加学生
    public String add(){
        StudentDAO studentDAO = new StudentDAOImpl();
        StudentEntity studentEntity = new StudentEntity(null,sname,gender,new Timestamp(birthday.getTime()),address);
        studentDAO.addStudent(studentEntity);
        return "add_success";
    }

    //修改信息
    public String modify(){
        String sid = httpServletRequest.getParameter("sid");
        StudentDAO studentDAO = new StudentDAOImpl();
        StudentEntity studentEntity = studentDAO.queryStudentBySid(sid);
        session.setAttribute("modify_students",studentEntity);
        return "modify_success";
    }

    //保存修改信息
    public String save() throws Exception{
        StudentDAO studentDAO = new StudentDAOImpl();
        StudentEntity studentEntity = new StudentEntity(sid,sname,gender,new Timestamp(birthday.getTime()),address);
        studentDAO.updateStudent(studentEntity);
        return "save_success";
    }
}
