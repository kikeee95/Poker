package com.company;

import java.util.ArrayList;

public class Board {
    private String gameState;
    private ArrayList<Card> cards = new ArrayList<Card>();
    private ArrayList<Player> players = new ArrayList<Player>();
    private double pot;
    private int buttonPosition;
    private String potType;
    private boolean isWetBoard;

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
        this.potType = "normal";
        this.isWetBoard = false;
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

    public void addCard(Card card) {

        boolean exists = false;
        for (int i = 0; i < this.cards.size(); i++) {
            if (card.getPrimeValue() == cards.get(i).getPrimeValue()) {
                exists = true;
            }
        }
        if (exists == false) {
            cards.add(card);
        }
    }

    public void removeCards() {
        cards.clear();
    }

    public void setPlayerPositions() {
        if (buttonPosition == 0) {
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
        if (buttonPosition == 1) {
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
        if (buttonPosition == 2) {
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
        if (buttonPosition == 3) {
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
        if (buttonPosition == 4) {
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
        if (buttonPosition == 5) {
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
        if (buttonPosition == 6) {
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
        if (buttonPosition == 7) {
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
        if (buttonPosition == 8) {
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


    public boolean isWetBoard() {
        return isWetBoard;
    }

    public void setBoardType() {
        int[] ranks = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        // s  h  d  c
        int[] suits = new int[]{0, 0, 0, 0};

        boolean wet = false;

        for (int i = 0; i < this.cards.size(); i++) {
            int rankhelper = -1;
            int suithelper = -1;
            for (int j = 0; j < Constants.cards.length; j++) {
                if (this.cards.get(i).getName().equalsIgnoreCase(Constants.cards[j])) {
                    rankhelper = j / 4;
                    suithelper = j % 4;
                    ranks[rankhelper]++;
                    suits[suithelper]++;
                }
            }

        }
        for(int i = 0; i < ranks.length; i++ ){
            if(i != 0){
                if(ranks[i] >= 1 && ranks[i-1] >= 1){
                    wet = true;
                }
            }
        }
        for(int i = 0; i < suits.length; i++){
            if(suits[i] >= 2){
                wet = true;
            }
        }
        this.isWetBoard = wet;

    }

    public String getPotType() {
        return potType;
    }

    public void setPlayerActions() {
        if (this.getGameState().equalsIgnoreCase("flop")) {
            for (int i = 0; i < this.getPlayers().size(); i++) {
                if (players.get(i).getPreflopAction().equalsIgnoreCase("No action")) {
                    this.getPlayers().get(i).setPreflopAction("call");
                }
            }
        }
    }

    public void setPotType() {
        int raises = 0;
        if (this.getGameState().equalsIgnoreCase("preflop")) {
            for (int i = 0; i < this.getPlayers().size(); i++) {
                if (this.getPlayers().get(i).getAction().toLowerCase().equals("raise")) {
                    raises++;
                }
            }
            if (raises <= 1) {
                this.potType = "normal";
            } else if (raises == 2) {
                this.potType = "3bet";
            } else if (raises >= 3) {
                this.potType = "4bet";
            }
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
