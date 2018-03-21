package com.mohamed.rest.vt.springboot2restservicevachetaureaux.service;

import com.mohamed.rest.vt.springboot2restservicevachetaureaux.model.Game;
import com.mohamed.rest.vt.springboot2restservicevachetaureaux.model.GameGrill;
import com.mohamed.rest.vt.springboot2restservicevachetaureaux.model.HistoryGame;
import com.mohamed.rest.vt.springboot2restservicevachetaureaux.model.User;

public class Tools {
    public static final String LOGIN_USER = "med@test.com";

    public static User createUser(int indice) {
        User user = new User();
        user.setLoginUser(indice + LOGIN_USER);
        user.setNameUser(indice + "testUser");
        user.setPwdUser("***");
        return user;
    }

    public static GameGrill createGrill(User user, int number) {
        GameGrill gameGrill = new GameGrill();
        gameGrill.setSettedNumberGameSolution(number);
        gameGrill.setPlayerGameSolution(user);
        return gameGrill;
    }

    public static Game createGame(long gameId) {
        Game game = new Game();
        game.setIdGame(gameId);
        return game;
    }

    public static HistoryGame createHitoryGame(User user, int number) {
        HistoryGame historyGame = new HistoryGame();
        GameGrill gameGrill = createGrill(user, number);
        historyGame.setGameGrill(gameGrill);
        return historyGame;
    }
}
