package com.example.matt.boardgamestats;

import java.util.ArrayList;

/**
 * Created by Matt on 8/16/2015.
 */
public abstract class GameDataModel {
    protected String name;
    protected String winner;
    protected ArrayList<PlayerModel> playerList;
    protected int flag = 0;

    public abstract String getName();

    public ArrayList<PlayerModel> getPlayers(){
        return this.playerList;
    }

    public void setPlayers(ArrayList<PlayerModel> players){
        this.playerList = players;
    }

    public String getWinner()
    {
        return winner;
    }

    public void setFlag(int number)
    {
        this.flag =number;
    }

}
