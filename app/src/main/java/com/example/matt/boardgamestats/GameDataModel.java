package com.example.matt.boardgamestats;

/**
 * Created by Matt on 8/16/2015.
 */
public abstract class GameDataModel {
    protected String name;
    protected String winner;
    protected int flag = 0;


    public abstract String getName();


    public String getWinner()
    {
        return winner;
    }

}
