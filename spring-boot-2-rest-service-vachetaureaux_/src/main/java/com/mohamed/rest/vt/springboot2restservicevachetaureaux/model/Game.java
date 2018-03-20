package com.mohamed.rest.vt.springboot2restservicevachetaureaux.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Game {
    public static final String END_OF_GAME = "0 Vache(s) 4 Taureau(x)";
    @Id
    @GeneratedValue
    private long idGame;
    @OneToOne(cascade = {CascadeType.ALL})
    private User winnerGame;
    @Enumerated(EnumType.STRING)
    private Stat statGame;
    @OneToOne(cascade = {CascadeType.ALL})
    private GameGrill gameGrillPlayerOne;
    @OneToOne(cascade = {CascadeType.ALL})
    private GameGrill gameGrillPlayerTwo;
    @OneToMany(cascade = {CascadeType.ALL})
    private List<HistoryGame> historyGames;

    public static final Logger logger = LoggerFactory.getLogger(Game.class);

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

    public List<HistoryGame> getHistoryGame() {
        return historyGames;
    }

    public void setHistoryGame(List<HistoryGame> historyGames) {
        this.historyGames = historyGames;
    }

    public Game addGameTour(HistoryGame historyGame) {
        if (this.getStatGame().inGame()) {
            historyGame = checkVacheTaureaux(historyGame);
            this.historyGames.add(historyGame);
            if (historyGame.getCorrection().equals(END_OF_GAME)) {
                this.setWinnerGame(historyGame.getGameGrill().getPlayerGameSolution());
                this.setStatGame(Stat.END);
                logger.info(historyGame.getGameGrill().getPlayerGameSolution().getLoginUser() + " win this game");
            }
        } else
            logger.info("Cannot start to play  in nan full game, wait for another player");
        return this;
    }

    public Game jointGame(GameGrill gameGrill) {
        this.setGameGrillPlayerTwo(gameGrill);
        this.setStatGame(Stat.FULL);
        logger.info(gameGrill.getPlayerGameSolution().getLoginUser() + " join this game");
        return this;
    }

    public Game createGame(GameGrill gameGrill) {
        this.setGameGrillPlayerOne(gameGrill);
        this.setStatGame(Stat.WAIT);
        this.historyGames = new ArrayList<>();
        logger.info(gameGrill.getPlayerGameSolution().getLoginUser() + " create a game");
        return this;
    }

    private HistoryGame checkVacheTaureaux(HistoryGame historyGame) {
        int vsNumber = getVSNumber(historyGame);
        int settedNumberGameProposition = historyGame.getGameGrill().getSettedNumberGameSolution();
        String correction = calculateVacheTaureaux(vsNumber, settedNumberGameProposition);
        historyGame.setCorrection(correction);
        logger.info(historyGame.getGameGrill().getPlayerGameSolution().getLoginUser() + " play make a new  proposition which contains " + correction);
        return historyGame;
    }


    public String calculateVacheTaureaux(int vsNumber, int settedNumberGameProposition) {
        int vache = 0;
        int taureaux = 0;
        String proposition = String.valueOf(settedNumberGameProposition);
        String vsNumberToFound = String.valueOf(vsNumber);
        for (int position = 0; position < proposition.length(); position++) {
            if (hasTaureauxInThisPosition(proposition, vsNumberToFound, position))
                taureaux++;
            else if (hasVacheInThisPoistion(proposition.charAt(position), vsNumberToFound))
                vache++;
        }
        return vache + " Vache(s) " + taureaux + " Taureau(x)";
    }

    private boolean hasVacheInThisPoistion(char propositionAtPosition, String vsNumberToFound) {
        return vsNumberToFound.indexOf(propositionAtPosition) > -1;
    }

    private boolean hasTaureauxInThisPosition(String proposition, String vsNumberToFound, int position) {
        char propositionAtPosition = proposition.charAt(position);
        char vsNumberAtPosition = vsNumberToFound.charAt(position);
        return String.valueOf(propositionAtPosition).equals(String.valueOf(vsNumberAtPosition));
    }

    private int getVSNumber(HistoryGame historyGame) {
        User playerGameSolution = historyGame.getGameGrill().getPlayerGameSolution();
        User playerOne = this.getGameGrillPlayerOne().getPlayerGameSolution();
        if (playerGameSolution.equals(playerOne))
            return this.getGameGrillPlayerTwo().getSettedNumberGameSolution();
        else
            return this.getGameGrillPlayerOne().getSettedNumberGameSolution();
    }

}
