package com.mohamed.rest.vt.springboot2restservicevachetaureaux.service;

import com.mohamed.rest.vt.springboot2restservicevachetaureaux.SpringBoot2RestServiceVachetaureauxApplication;
import com.mohamed.rest.vt.springboot2restservicevachetaureaux.model.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringBoot2RestServiceVachetaureauxApplication.class, loader = AnnotationConfigContextLoader.class)
public class GameServiceImplTest {

    @Autowired
    GameService gameService;
    @Autowired
    UserService userService;

    @Test
    public void should_create_game() throws Exception {

//        create Two players
        User userOne = Tools.createUser(1);
        userService.saveUser(userOne);
        User userTwo = Tools.createUser(2);
        userService.saveUser(userTwo);

//         create game
        GameGrill grillPlayerOne = Tools.createGrill(userOne, 1234);
        Game game = Tools.createGame(100);
        game.createGame(grillPlayerOne);
        gameService.updateGame(game);

        Optional<Game> createdGame = checkGameStatus(100, Stat.WAIT);


//        join game
        GameGrill grillPlayerTwo = Tools.createGrill(userTwo, 5678);
        createdGame.get().jointGame(grillPlayerTwo);
        gameService.updateGame(createdGame.get());
        Optional<Game> joinedGame = checkGameStatus(100, Stat.FULL);


//        first play
        HistoryGame historyGame = Tools.createHitoryGame(userOne, 1234);
        joinedGame.get().addGameTour(historyGame);
        gameService.updateGame(joinedGame.get());


//        second win the game
        Optional<Game> firstTourGame = gameService.getGameById(100);
        HistoryGame historyGameOfWin = Tools.createHitoryGame(userTwo, 1234);
        firstTourGame.get().addGameTour(historyGameOfWin);
        gameService.updateGame(firstTourGame.get());


        Optional<Game> endGameTour = gameService.getGameById(100);
        Assert.assertTrue(endGameTour.get().getStatGame().isEnd());
        Assert.assertTrue(endGameTour.get().getWinnerGame().equals(userTwo));
//        delete game

        gameService.deletGame(game.getIdGame());
        Assert.assertFalse(endGameTour.isPresent());
    }

    private Optional<Game> checkGameStatus(long gameId, Stat stat) throws InterruptedException {
        Thread.sleep(1000);
        Optional<Game> gameById = gameService.getGameById(100);
        Assert.assertTrue(gameById.isPresent());
        Assert.assertTrue(gameById.get().getStatGame().equals(stat));
        return gameById;
    }


}