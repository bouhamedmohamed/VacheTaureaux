package com.mohamed.rest.vt.springboot2restservicevachetaureaux.Repo;

import com.mohamed.rest.vt.springboot2restservicevachetaureaux.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrudRepositoryUser extends JpaRepository<User, Long> {

}
