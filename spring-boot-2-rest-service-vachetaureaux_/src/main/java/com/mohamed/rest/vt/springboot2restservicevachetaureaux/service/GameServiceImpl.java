package com.mohamed.rest.vt.springboot2restservicevachetaureaux.service;

import com.mohamed.rest.vt.springboot2restservicevachetaureaux.Repo.DAORepositoryGame;
import com.mohamed.rest.vt.springboot2restservicevachetaureaux.model.Game;
import com.mohamed.rest.vt.springboot2restservicevachetaureaux.model.GameGrill;
import com.mohamed.rest.vt.springboot2restservicevachetaureaux.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GameServiceImpl implements GameService {
    @Autowired
    private DAORepositoryGame DAORepositoryGame;

    @Override
    public Game createGame(GameGrill gameGrill) {
        Game game = new Game();
        game = game.createGame(gameGrill);
        DAORepositoryGame.save(game);
        return game;
    }


    @Override
    public boolean isJoinableGame(long gameId) {
        Optional<Game> gameById = DAORepositoryGame.findById(gameId);
        if (gameById.isPresent()) {
            return gameById.get().getStatGame().isWait();

        }
        return false;
    }

    @Override
    public boolean isCloseGame(long gameId) {
        Optional<Game> gameById = DAORepositoryGame.findById(gameId);
        if (gameById.isPresent()) {
            return gameById.get().getStatGame().isEnd();
        }
        return false;
    }


    @Override
    public List<Game> getAllGames() {
        return DAORepositoryGame.findAll();
    }

    @Override
    public Optional<Game> getGameById(long gameId) {
        return DAORepositoryGame.findById(gameId);
    }

    @Override
    public void updateGame(Game game) {
        DAORepositoryGame.save(game);
    }

    @Override
    public int countWinGames(User user) {
        return DAORepositoryGame.countWin(user);
    }

    @Override
    public int countGames(User user) {
        return DAORepositoryGame.countGames(user);
    }

    @Override
    public void deletGame(long gameId) {
        Optional<Game> gameById = DAORepositoryGame.findById(gameId);
        if (gameById.isPresent()) {
            DAORepositoryGame.delete(gameById.get());
        }
    }
}
