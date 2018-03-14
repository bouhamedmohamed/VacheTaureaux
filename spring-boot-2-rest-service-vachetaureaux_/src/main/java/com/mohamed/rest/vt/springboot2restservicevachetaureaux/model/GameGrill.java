package com.mohamed.rest.vt.springboot2restservicevachetaureaux.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class GameGrill {
    @Id
    @GeneratedValue
    private long idGameSolution;
    private  int settedNumberGameSolution;
    private  User playerGameSolution;

    public GameGrill() {
    }

    public User getPlayerGameSolution() {
        return playerGameSolution;
    }

    public void setPlayerGameSolution(User playerGameSolution) {
        this.playerGameSolution = playerGameSolution;
    }

    public int getSettedNumberGameSolution() {
        return settedNumberGameSolution;
    }

    public void setSettedNumberGameSolution(int settedNumberGameSolution) {
        this.settedNumberGameSolution = settedNumberGameSolution;
    }

    public long getIdGameSolution() {
        return idGameSolution;
    }

    public void setIdGameSolution(long idGameSolution) {
        this.idGameSolution = idGameSolution;
    }
}
