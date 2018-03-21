package com.mohamed.rest.vt.springboot2restservicevachetaureaux.Repo;

import com.mohamed.rest.vt.springboot2restservicevachetaureaux.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DAORepositoryUser extends JpaRepository<User, Long> {

  Optional<User> findByLoginUser(String loginUser);

}
