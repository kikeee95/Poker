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

    public Board() {
        this.players.add(new PlayerPlayed());
        this.players.add(new PlayerAI());
        this.players.add(new PlayerAI());
        this.players.add(new PlayerAI());
        this.players.add(new PlayerAI());
        this.players.add(new PlayerAI());
        this.players.add(new PlayerAI());
        this.players.add(new PlayerAI());
        this.players.add(new PlayerAI());
        this.gameState = "Unknown";
        this.pot = 0;
        this.buttonPosition = -1;
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
        for (Card card1 : this.cards) {
            if (card.getPrimeValue() == card1.getPrimeValue()) {
                exists = true;
            }
        }
        if (!exists) {
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

        for (Card card : this.cards) {
            int rankhelper = -1;
            int suithelper = -1;
            for (int j = 0; j < Constants.cards.length; j++) {
                if (card.getName().equalsIgnoreCase(Constants.cards[j])) {
                    rankhelper = j / 4;
                    suithelper = j % 4;
                    ranks[rankhelper]++;
                    suits[suithelper]++;
                }
            }

        }
        for (int i = 0; i < ranks.length; i++) {
            if (i != 0) {
                if (ranks[i] >= 1 && ranks[i - 1] >= 1) {
                    wet = true;
                }
            }
        }
        for (int suit : suits) {
            if (suit >= 2) {
                wet = true;
            }
        }
        this.isWetBoard = wet;

    }

    public String getPotType() {
        return potType;
    }

    public void setPlayerActions() {
        for (int i = 0; i < this.players.size(); i++) {
            if (this.players.get(i).hasCards) {
                if (this.getGameState().equalsIgnoreCase("flop")) {
                    if (this.players.get(i).getPreflopAction().equalsIgnoreCase("No action")) {
                        this.getPlayers().get(i).setPreflopAction("call");
                    }
                }
                if (this.getGameState().equalsIgnoreCase("turn")) {
                    if (this.players.get(i).getFlopAction().equalsIgnoreCase("No action")) {
                        this.getPlayers().get(i).setFlopAction("call");
                    }
                }
                if (this.getGameState().equalsIgnoreCase("river")) {
                    if (this.players.get(i).getTurnAction().equalsIgnoreCase("No action")) {
                        this.getPlayers().get(i).setTurnAction("call");
                    }
                }
            }
        }
    }

    public void setPotType() {
        int raises = 0;
        if (this.getGameState().equalsIgnoreCase("preflop")) {
            for (int i = 0; i < this.getPlayers().size(); i++) {
                if (this.getPlayers().get(i).getPreflopAction().toLowerCase().equals("raise")) {
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
