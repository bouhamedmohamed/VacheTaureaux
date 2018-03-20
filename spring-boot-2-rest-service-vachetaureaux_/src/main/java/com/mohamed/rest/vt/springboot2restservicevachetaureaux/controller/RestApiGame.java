package com.mohamed.rest.vt.springboot2restservicevachetaureaux.controller;

import com.mohamed.rest.vt.springboot2restservicevachetaureaux.model.Game;
import com.mohamed.rest.vt.springboot2restservicevachetaureaux.model.GameGrill;
import com.mohamed.rest.vt.springboot2restservicevachetaureaux.model.HistoryGame;
import com.mohamed.rest.vt.springboot2restservicevachetaureaux.model.User;
import com.mohamed.rest.vt.springboot2restservicevachetaureaux.service.GameService;
import com.mohamed.rest.vt.springboot2restservicevachetaureaux.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/vt")
public class RestApiGame {

    public static final Logger logger = LoggerFactory.getLogger(RestApiGame.class);

    @Autowired
    GameService gameService; //Service which will do all data retrieval/manipulation work

    // -------------------Retrieve All Games---------------------------------------------

    @RequestMapping(value = "/games/", method = RequestMethod.GET)
    public ResponseEntity<List<Game>> listAllGames() {
        List<Game> games = gameService.getAllGames();
        if (games.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<>(games, HttpStatus.OK);
    }

    // -------------------Retrieve Single Game------------------------------------------

    @RequestMapping(value = "/games/{gameId}", method = RequestMethod.GET)
    public ResponseEntity<?> getGame(@PathVariable("gameId") long gameId) {
        logger.info("Fetching game with id{}", gameId);

        Optional<Game> game = gameService.getGameById(gameId);
        if (!game.isPresent()) {
            logger.error("game   {} not found.", gameId);
            return new ResponseEntity(new CustomErrorType("game with id " + gameId
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Game>(game.get(), HttpStatus.OK);
    }

    // -------------------Create a Game-------------------------------------------

    @RequestMapping(value = "/game/", method = RequestMethod.POST)
    public ResponseEntity<?> createGame(@RequestBody GameGrill gameGrill, UriComponentsBuilder ucBuilder) {
        logger.info("Creating game : {}");

        Game game = gameService.createGame(gameGrill);
        if (!game.getStatGame().isWait()) {
            logger.error("We cant create a new game");
            return new ResponseEntity(new CustomErrorType("We cant create a new game" ), HttpStatus.CONFLICT);
        }


        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/user/{gameId}").buildAndExpand(
                game.getIdGame()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // ------------------- Join  a Game ------------------------------------------------

    @RequestMapping(value = "/game/joint/{gameId}", method = RequestMethod.PUT)
    public ResponseEntity<?> jointGame(@PathVariable("gameId") long gameId, @RequestBody GameGrill gameGrill) {
        logger.info("joint game {}");

        Optional<Game> game = gameService.getGameById(gameId);
        if (!game.isPresent()) {
            logger.error("game {} not found.", gameId);
            return new ResponseEntity(new CustomErrorType("game with id " + gameId
                    + " not found"), HttpStatus.NOT_FOUND);
        }

        Game updatedGame = game.get().jointGame(gameGrill);
        gameService.updateGame(updatedGame);
        return new ResponseEntity<>(updatedGame, HttpStatus.OK);
    }

    // ------------------- Join  a Game ------------------------------------------------

    @RequestMapping(value = "/game/tour/{gameId}", method = RequestMethod.PUT)
    public ResponseEntity<?> addTour(@PathVariable("gameId") long gameId, @RequestBody HistoryGame historyGame) {
        logger.info("joint game {}");

        Optional<Game> game = gameService.getGameById(gameId);
        if (!game.isPresent()) {
            logger.error("game {} not found.", gameId);
            return new ResponseEntity(new CustomErrorType("game with id " + gameId
                    + " not found"), HttpStatus.NOT_FOUND);
        }

        Game updatedGame = game.get().addGameTour(historyGame);
        gameService.updateGame(updatedGame);
        return new ResponseEntity<>(updatedGame, HttpStatus.OK);
    }
}