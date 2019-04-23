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

    public void setPlayerPositions(){
        if(buttonPosition == 0){
            this.players.get(0).setPosition(6);
            this.players.get(1).setPosition(7);
            this.players.get(2).setPosition(8);
            this.players.get(3).setPosition(0);
            this.players.get(4).setPosition(1);
            this.players.get(5).setPosition(2);
            this.players.get(6).setPosition(3);
            this.players.get(7).setPosition(4);
            this.players.get(8).setPosition(5);
        }
        if(buttonPosition == 1){
            this.players.get(1).setPosition(6);
            this.players.get(2).setPosition(7);
            this.players.get(3).setPosition(8);
            this.players.get(4).setPosition(0);
            this.players.get(5).setPosition(1);
            this.players.get(6).setPosition(2);
            this.players.get(7).setPosition(3);
            this.players.get(8).setPosition(4);
            this.players.get(0).setPosition(5);
        }
        if(buttonPosition == 2){
            this.players.get(2).setPosition(6);
            this.players.get(3).setPosition(7);
            this.players.get(4).setPosition(8);
            this.players.get(5).setPosition(0);
            this.players.get(6).setPosition(1);
            this.players.get(7).setPosition(2);
            this.players.get(8).setPosition(3);
            this.players.get(0).setPosition(4);
            this.players.get(1).setPosition(5);
        }
        if(buttonPosition == 3) {
            this.players.get(3).setPosition(6);
            this.players.get(4).setPosition(7);
            this.players.get(5).setPosition(8);
            this.players.get(6).setPosition(0);
            this.players.get(7).setPosition(1);
            this.players.get(8).setPosition(2);
            this.players.get(0).setPosition(3);
            this.players.get(1).setPosition(4);
            this.players.get(2).setPosition(5);
        }
        if(buttonPosition == 4) {
            this.players.get(4).setPosition(6);
            this.players.get(5).setPosition(7);
            this.players.get(6).setPosition(8);
            this.players.get(7).setPosition(0);
            this.players.get(8).setPosition(1);
            this.players.get(0).setPosition(2);
            this.players.get(1).setPosition(3);
            this.players.get(2).setPosition(4);
            this.players.get(3).setPosition(5);
        }
        if(buttonPosition == 5) {
            this.players.get(5).setPosition(6);
            this.players.get(6).setPosition(7);
            this.players.get(7).setPosition(8);
            this.players.get(8).setPosition(0);
            this.players.get(0).setPosition(1);
            this.players.get(1).setPosition(2);
            this.players.get(2).setPosition(3);
            this.players.get(3).setPosition(4);
            this.players.get(4).setPosition(5);
        }
        if(buttonPosition == 6) {
            this.players.get(6).setPosition(6);
            this.players.get(7).setPosition(7);
            this.players.get(8).setPosition(8);
            this.players.get(0).setPosition(0);
            this.players.get(1).setPosition(1);
            this.players.get(2).setPosition(2);
            this.players.get(3).setPosition(3);
            this.players.get(4).setPosition(4);
            this.players.get(5).setPosition(5);
        }
        if(buttonPosition == 7) {
            this.players.get(7).setPosition(6);
            this.players.get(8).setPosition(7);
            this.players.get(0).setPosition(8);
            this.players.get(1).setPosition(0);
            this.players.get(2).setPosition(1);
            this.players.get(3).setPosition(2);
            this.players.get(4).setPosition(3);
            this.players.get(5).setPosition(4);
            this.players.get(6).setPosition(5);
        }
        if(buttonPosition == 8) {
            this.players.get(8).setPosition(6);
            this.players.get(0).setPosition(7);
            this.players.get(1).setPosition(8);
            this.players.get(2).setPosition(0);
            this.players.get(3).setPosition(1);
            this.players.get(4).setPosition(2);
            this.players.get(5).setPosition(3);
            this.players.get(6).setPosition(4);
            this.players.get(7).setPosition(5);
        }


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
