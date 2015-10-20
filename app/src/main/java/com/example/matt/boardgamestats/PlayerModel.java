package com.example.matt.boardgamestats;

/**
 * Created by Matt on 10/17/2015.
 */
public class PlayerModel {
    protected String name;
    protected String[] aliasList = new String[50];

    PlayerModel(String name){
        setName(name);
    }

    public String getName(){
       return name;
    }

    public void setName(String newName){
        this.name = newName;
    }




}
