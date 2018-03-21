package com.mohamed.rest.vt.springboot2restservicevachetaureaux.service;

import com.mohamed.rest.vt.springboot2restservicevachetaureaux.SpringBoot2RestServiceVachetaureauxApplication;
import com.mohamed.rest.vt.springboot2restservicevachetaureaux.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringBoot2RestServiceVachetaureauxApplication.class, loader = AnnotationConfigContextLoader.class)
public class UserServiceImplTest {


    @Autowired
    private UserService userService;


    @Test
    public void should_create_user_and_deleted_after() throws Exception {
        User user = Tools.createUser(1);
        userService.saveUser(user);
        Long userId = checkExist(true);
        userService.deleteUserById(userId);
        checkExist(false);
    }


    private Long checkExist(boolean existing) {
        Optional<User> byLoginUser = userService.findByLoginUser(Tools.LOGIN_USER);
        if (existing) {
            Optional<User> byUserID = userService.findById(byLoginUser.get().getIdUser());
            Assert.assertTrue(byLoginUser.isPresent());
            Assert.assertTrue(byUserID.isPresent());
            Assert.assertEquals(byLoginUser, byUserID);
            return byUserID.get().getIdUser();
        }
        Assert.assertFalse(byLoginUser.isPresent());
        return null;
    }


}
