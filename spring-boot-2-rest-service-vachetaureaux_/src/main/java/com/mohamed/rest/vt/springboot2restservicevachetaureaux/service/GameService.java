package com.mohamed.rest.vt.springboot2restservicevachetaureaux.service;

import com.mohamed.rest.vt.springboot2restservicevachetaureaux.model.Game;
import com.mohamed.rest.vt.springboot2restservicevachetaureaux.model.GameGrill;
import com.mohamed.rest.vt.springboot2restservicevachetaureaux.model.Stat;
import com.mohamed.rest.vt.springboot2restservicevachetaureaux.model.User;

public interface GameService {

    void createGame(Game game);

    Stat jointGame(Game game, GameGrill gameGrill);

    Stat finishGame(Game game, User winner);

}
