package com.mohamed.rest.vt.springboot2restservicevachetaureaux.service;

import com.mohamed.rest.vt.springboot2restservicevachetaureaux.Repo.CrudRepositoryGame;
import com.mohamed.rest.vt.springboot2restservicevachetaureaux.model.Game;
import com.mohamed.rest.vt.springboot2restservicevachetaureaux.model.GameGrill;
import com.mohamed.rest.vt.springboot2restservicevachetaureaux.model.HistoryGame;
import com.mohamed.rest.vt.springboot2restservicevachetaureaux.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {
    @Autowired
    CrudRepositoryGame crudRepositoryGame;

    @Override
    public Game createGame(GameGrill gameGrill) {
        Game game = new Game();
        game = game.createGame(gameGrill);
        crudRepositoryGame.save(game);
        return game;
    }

    @Override
    public Game jointGame(long gameId, GameGrill gameGrill) {

        Optional<Game> gameById = crudRepositoryGame.findById(gameId);
        if (gameById.isPresent()) {
            gameById = Optional.ofNullable(gameById.get().jointGame(gameGrill));
        }
        return gameById.get();

    }

    @Override
    public boolean isJoinableGame(long gameId) {
        Optional<Game> gameById = crudRepositoryGame.findById(gameId);
        if (gameById.isPresent()) {
            return gameById.get().getStatGame().isWait();

        }
        return false;
    }

    @Override
    public boolean isCloseGame(long gameId) {
        Optional<Game> gameById = crudRepositoryGame.findById(gameId);
        if (gameById.isPresent()) {
            return gameById.get().getStatGame().isEnd();
        }
        return false;
    }

    @Override
    public void addIteration(HistoryGame historyGame, long gameId) {
        Optional<Game> gameById = crudRepositoryGame.findById(gameId);
        if (gameById.isPresent()) {
            gameById.get().addGameTour(historyGame);
            crudRepositoryGame.save(gameById.get());
        }
    }

    @Override
    public List<Game> getAllGames() {
        return crudRepositoryGame.findAll();
    }

    @Override
    public Optional<Game> getGameById(long gameId) {
        return crudRepositoryGame.findById(gameId);
    }

    @Override
    public void updateGame(Game game) {
        crudRepositoryGame.save(game);
    }

    @Override
    public int countWinGames(User user) {
        return crudRepositoryGame.countWin(user);
    }

    @Override
    public int countGames(User user) {
        return crudRepositoryGame.countGames(user);
    }
}
