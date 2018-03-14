package com.mohamed.rest.vt.springboot2restservicevachetaureaux.service;

import com.mohamed.rest.vt.springboot2restservicevachetaureaux.Repo.CrudRepositoryUser;
import com.mohamed.rest.vt.springboot2restservicevachetaureaux.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    CrudRepositoryUser repoUser;

    @Override
    public Optional<User> findById(long id) {
        return repoUser.findById(id);
    }



    @Override
    public void updateUser(User currentUser) {
        repoUser.save(currentUser);
    }

    @Override
    public void deleteUserById(long id) {
        Optional<User> userFromDB = findById(id);
        if (userFromDB.isPresent())
            repoUser.delete(userFromDB.get());
    }

    @Override
    public Optional<User> findByLoginUser(String userLogin) {
        return repoUser.findByLoginUser(userLogin);
    }

    @Override
    public void saveUser(User user) {
        repoUser.save(user);
    }

    @Override
    public List<User> findAllUsers() {
        return repoUser.findAll();
    }
}
