package com.company;

import java.util.ArrayList;

public class Board {
    private String gameState;
    private ArrayList<Card> cards = new ArrayList<Card>();
    private ArrayList<Player> players = new ArrayList<Player>();
    private double pot;
    private int buttonPosition;

    public Board(Player player1, Player player2, Player player3, Player player4, Player player5, Player player6, Player player7, Player player8, Player player9) {
        this.players.add(player1);
        this.players.add(player2);
        this.players.add(player3);
        this.players.add(player4);
        this.players.add(player5);
        this.players.add(player6);
        this.players.add(player7);
        this.players.add(player8);
        this.players.add(player9);
        this.gameState = "Unknown";
        this.pot = 0;
        this.buttonPosition = 0;
    }

    public String getGameState() {
        return gameState;
    }

    public void setGameState(String gameState) {
        this.gameState = gameState;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public double getPot() {
        return pot;
    }

    public void setPot(double pot) {
        this.pot = pot;
    }

    public int getButtonPosition() {
        return buttonPosition;
    }

    public void setButtonPosition(int buttonPosition) {
        this.buttonPosition = buttonPosition;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public void addCard(Card card){

        boolean exists = false;
            for(int i = 0; i < this.cards.size(); i++){
                if(card.getPrimeValue() == cards.get(i).getPrimeValue()){
                    exists = true;
                }
            }
        if(exists == false){
            cards.add(card);
        }
    }

    public void removeCards(){
        cards.clear();
    }

    @Override
    public String toString() {
        return "Board{" +
                "gameState='" + gameState + '\'' + "\n" +
                ", cards=" + cards + "\n" +
                ", players=" + players + "\n" +
                ", pot=" + pot + "\n" +
                ", buttonPosition=" + buttonPosition + "\n" +
                '}';
    }
}