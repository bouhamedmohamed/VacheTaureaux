package com.mohamed.rest.vt.springboot2restservicevachetaureaux.model;

import javax.persistence.*;

@Entity
public class HistoryGame {

    @Id
    @GeneratedValue
    private long idHistory;
    @OneToOne
    private GameGrill gameGrill;
    private String correction;
    @OneToOne
    private Game game;

    public long getIdHistory() {
        return idHistory;
    }

    public void setIdHistory(long idHistory) {
        this.idHistory = idHistory;
    }

    public GameGrill getGameGrill() {
        return gameGrill;
    }

    public void setGameGrill(GameGrill gameGrill) {
        this.gameGrill = gameGrill;
    }

    public String getCorrection() {
        return correction;
    }

    public void setCorrection(String correction) {
        this.correction = correction;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
