package com.example.matt.boardgamestats;

import java.util.ArrayList;

/**
 * Created by Matt on 8/16/2015.
 */
public  class GameDataModel {
    protected String name;
    protected String winner;
    protected ArrayList<PlayerModel> playerList; //TODO: this needs to be made a reference so that player stats can be implemented
    protected int flag = 0;

    GameDataModel (String name){
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

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
