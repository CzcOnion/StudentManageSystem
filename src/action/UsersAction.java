package action;

import com.opensymphony.xwork2.ModelDriven;
import entity.UserEntity;
import org.apache.struts2.interceptor.validation.SkipValidation;
import service.UserDAO;
import service.impl.UserDAOImpl;

public class UsersAction extends SurperAction implements ModelDriven<UserEntity> {

    private UserEntity user = new UserEntity();

    private static final long serialVersionUID = 1L;

    //用户登录
    public String login(){
        UserDAO userDAO = new UserDAOImpl();
        if(userDAO.userLogin(user)){
            //在session中保存登录成功的用户名
            session.setAttribute("loginUserName",user.getUsername());
            return "login_success";
        }
        else
            return "login_fail";
    }

    @SkipValidation
    //用户注销
    public String logout(){
        if(session.getAttribute("loginUserName")!=null){
            session.removeAttribute("loginUserName");
        }
        return "logout_success";
    }
    @Override
    public UserEntity getModel() {
        return this.user;
    }

    public UserEntity getUser() {
        return user;
    }

}
