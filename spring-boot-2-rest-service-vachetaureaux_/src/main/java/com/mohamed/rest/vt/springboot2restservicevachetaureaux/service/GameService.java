package com.mohamed.rest.vt.springboot2restservicevachetaureaux.service;

import com.mohamed.rest.vt.springboot2restservicevachetaureaux.model.Game;
import com.mohamed.rest.vt.springboot2restservicevachetaureaux.model.GameGrill;
import com.mohamed.rest.vt.springboot2restservicevachetaureaux.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface GameService {

    Game createGame(GameGrill gameGrill);

    boolean isJoinableGame(long id);

    boolean isCloseGame(long id);

    List<Game> getAllGames();

    Optional<Game> getGameById(long gameId);

    void updateGame(Game game);

    int countWinGames(User user);

    int countGames(User user);

    void deletGame(long idGame);
}
