package com.example.matt.boardgamestats;

/**
 * Created by Matt on 10/17/2015.
 */
public class PlayerModel {
    protected String name;
    protected String[] aliasList = new String[50];
    protected int isChecked=0;
    protected int score=0;


    PlayerModel(String name){
        setName(name);
    }

    public void setScore(int score){
        this.score = score;
    }


    public int getScore(){
        return this.score;
    }

    public String getName(){
       return name;
    }

    public int getIsChecked() {
        return isChecked;
    }

    public void toggleChecked() {
        if (this.isChecked == 0){
            this.isChecked = 1;
        }
        else {
            this.isChecked =0;
        }
    }

    public void setName(String newName){
        this.name = newName;
    }




}
