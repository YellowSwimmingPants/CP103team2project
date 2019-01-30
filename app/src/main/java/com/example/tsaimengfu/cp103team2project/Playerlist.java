package com.example.tsaimengfu.cp103team2project;

public class Playerlist {

    private int ImageId;
    private String name;

    public int getImageId() {
        return ImageId;
    }

    public void setImageId(int ImageId) {
        this.ImageId = ImageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Playerlist(int ImageId, String name) {


        this.ImageId = ImageId;
        this.name = name;
    }



}