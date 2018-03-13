package com.mohamed.rest.vt.springboot2restservicevachetaureaux.service;

import com.mohamed.rest.vt.springboot2restservicevachetaureaux.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    Optional findById(long id);

    void updateUser(User currentUser);

    void deleteUserById(long id);

    void deleteAllUsers();

    boolean isUserExist(User user);

    void saveUser(User user);

    List<User> findAllUsers();
}
