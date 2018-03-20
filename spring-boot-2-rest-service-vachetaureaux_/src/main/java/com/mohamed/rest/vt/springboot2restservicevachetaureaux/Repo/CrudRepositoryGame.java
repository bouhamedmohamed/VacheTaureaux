package com.mohamed.rest.vt.springboot2restservicevachetaureaux.Repo;

import com.mohamed.rest.vt.springboot2restservicevachetaureaux.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrudRepositoryGame extends JpaRepository<Game, Long> {
}
