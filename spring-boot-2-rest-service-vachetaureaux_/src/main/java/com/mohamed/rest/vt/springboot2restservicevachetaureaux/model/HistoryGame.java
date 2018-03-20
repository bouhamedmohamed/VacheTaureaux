package com.mohamed.rest.vt.springboot2restservicevachetaureaux.model;

import javax.persistence.*;

@Entity
public class HistoryGame {

    @Id
    @GeneratedValue
    private long idHistory;
    @OneToOne(cascade = {CascadeType.ALL})
    private GameGrill gameGrill;
    private String correction;

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

}
