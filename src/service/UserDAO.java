package service;

import entity.UserEntity;

//用户业务逻辑接口
public interface UserDAO {

    //用户登录
    public boolean userLogin(UserEntity user);
}
