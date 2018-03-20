package com.mohamed.rest.vt.springboot2restservicevachetaureaux.service;

import com.mohamed.rest.vt.springboot2restservicevachetaureaux.model.Game;
import com.mohamed.rest.vt.springboot2restservicevachetaureaux.model.GameGrill;
import com.mohamed.rest.vt.springboot2restservicevachetaureaux.model.HistoryGame;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface GameService {

    Game createGame(GameGrill gameGrill);

    Game jointGame(long id, GameGrill gameGrill);

    boolean isJoinableGame(long id);

    boolean isCloseGame(long id);

    void addIteration(HistoryGame historyGame, long id);

    List<Game> getAllGames();

    Optional<Game> getGameById(long gameId);

    void updateGame(Game game);

}
