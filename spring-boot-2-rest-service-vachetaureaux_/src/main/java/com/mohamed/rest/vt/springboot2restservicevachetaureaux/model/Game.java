package com.mohamed.rest.vt.springboot2restservicevachetaureaux.model;

import javax.persistence.*;

@Entity
public class Game {
    @Id
    @GeneratedValue
    private long idGame;
    @OneToOne
    private User winnerGame;
    @Enumerated(EnumType.STRING)
    private Stat statGame;
    @OneToOne
    private GameGrill gameGrillPlayerOne;
    @OneToOne
    private GameGrill gameGrillPlayerTwo;


    public long getIdGame() {
        return idGame;
    }

    public void setIdGame(long idGame) {
        this.idGame = idGame;
    }

    public User getWinnerGame() {
        return winnerGame;
    }

    public void setWinnerGame(User winnerGame) {
        this.winnerGame = winnerGame;
    }

    public Stat getStatGame() {
        return statGame;
    }

    public void setStatGame(Stat statGame) {
        this.statGame = statGame;
    }

    public GameGrill getGameGrillPlayerOne() {
        return gameGrillPlayerOne;
    }

    public void setGameGrillPlayerOne(GameGrill gameGrillPlayerOne) {
        this.gameGrillPlayerOne = gameGrillPlayerOne;
    }

    public GameGrill getGameGrillPlayerTwo() {
        return gameGrillPlayerTwo;
    }

    public void setGameGrillPlayerTwo(GameGrill gameGrillPlayerTwo) {
        this.gameGrillPlayerTwo = gameGrillPlayerTwo;
    }
}
