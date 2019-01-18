package com.example.tsaimengfu.cp103team2project;

import java.io.Serializable;

public class Game implements Serializable {
    private String gameName;
    private String gameDate;

    public Game(String gameName, String gameDate) {
        this.gameName = gameName;
        this.gameDate = gameDate;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getGameDate() {
        return gameDate;
    }

    public void setGameDate(String gameDate) {
        this.gameDate = gameDate;
    }
}
