package com.company;

import java.util.ArrayList;

public class HandCombination {
    private String name;
    private ArrayList<Card> Cards;
    private int combinationId;
    private int combinationStrength;

    public HandCombination(ArrayList<Card> cards) {
        if(cards.size() == 5){
            this.Cards = cards;
            this.name = calcName();
            this.combinationId = calcId();

        }else{
            System.out.println("Invalid hand combination");
        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Card> getCards() {
        return Cards;
    }

    public void setCards(ArrayList<Card> cards) {
        Cards = cards;
    }

    public int getCombinationId() {
        return combinationId;
    }

    public void setCombinationId(int combinationId) {
        this.combinationId = combinationId;
    }

    public int getCombinationStrength() {
        return combinationStrength;
    }

    public void setCombinationStrength(int combinationStrength) {
        this.combinationStrength = combinationStrength;
    }

    private int calcId(){
        int id = 1;
        for(int i = 0; i < this.Cards.size(); i++){
            id = id * this.Cards.get(i).getPrimeValue();
        }
        return id;
    }

    private String calcName(){
        String name = "";
        for(int i = 0; i < this.Cards.size(); i++){
            name = name.concat(this.Cards.get(i).getName());
        }
        return name;
    }
}
