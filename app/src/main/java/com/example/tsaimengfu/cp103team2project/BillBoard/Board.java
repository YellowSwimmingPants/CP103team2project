package com.example.tsaimengfu.cp103team2project.BillBoard;

import java.io.Serializable;
import java.util.Date;

class Board implements Serializable {
    private int id;
    private String title, massege;

    public Board(int id, String title, String massege) {
        this.id = id;
        this.title = title;
        this.massege = massege;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMassege() {
        return massege;
    }

    public void setMassege(String massege) {
        this.massege = massege;
    }

}
