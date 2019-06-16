package service.impl;

import entity.UserEntity;
import org.junit.Assert;
import org.junit.Test;
import service.UserDAO;

public class TestUserDAOImpl {
    @Test
    public void TestUserLogin(){
        UserEntity user = new UserEntity(1,"czc","123456");
        UserDAO userDAO = new UserDAOImpl();
        Assert.assertEquals(true,userDAO.userLogin(user));
    }
}
