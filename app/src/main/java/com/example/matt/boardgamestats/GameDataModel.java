package com.example.matt.boardgamestats;

/**
 * Created by Matt on 8/16/2015.
 */
public class GameDataModel {
    protected String name;
    protected String winner;
    protected int flag = 0;


    public String getName(){
        return name;
    }

    public String getWinner(){

        return winner;
    }

    public void setFlag(int value){
        this.flag = value;
    }
}
