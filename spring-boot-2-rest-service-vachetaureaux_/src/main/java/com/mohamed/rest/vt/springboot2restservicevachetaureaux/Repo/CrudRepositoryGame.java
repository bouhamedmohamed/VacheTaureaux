package com.mohamed.rest.vt.springboot2restservicevachetaureaux.Repo;

import com.mohamed.rest.vt.springboot2restservicevachetaureaux.model.Game;
import com.mohamed.rest.vt.springboot2restservicevachetaureaux.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CrudRepositoryGame extends JpaRepository<Game, Long> {
    @Query("SELECT count(*) FROM Game p WHERE p.winnerGame = :user")
    int countWin(@Param("user") User user);
    @Query("SELECT count(*) FROM Game p WHERE (p.gameGrillPlayerOne.playerGameSolution= :user) or (p.gameGrillPlayerTwo.playerGameSolution=:user)")
    int countGames(@Param("user") User user);

}
