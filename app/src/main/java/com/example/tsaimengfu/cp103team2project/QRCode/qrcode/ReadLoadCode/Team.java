package com.example.tsaimengfu.cp103team2project.QRCode.qrcode.ReadLoadCode;


public class Team {
    private int id;
    private String teamname;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeamname() {
        return teamname;
    }

    public void setTeamname(String teamname) {
        this.teamname = teamname;
    }

    public Team(int id, String teamname) {

        this.id = id;
        this.teamname = teamname;
    }
}


