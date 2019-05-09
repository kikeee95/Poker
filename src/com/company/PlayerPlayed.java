package com.company;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;

public class PlayerPlayed extends Player {
    private Hand hand;
    private double equity;
    private String availableAction;
    private double amountToCall;
    private double potOdds;
    private boolean canRaise;
    private boolean canCheck;


    public PlayerPlayed() {
        this.name = "Default Name";
        this.money = 200;
        this.action = "No action";
        this.preflopAction = "No action";
        this.flopAction = "No action";
        this.turnAction = "No action";
        this.riverAction = "No action";
        this.position = -1;
        this.setHand(null);
        this.equity = 0;
        this.availableAction = "check";
        this.amountToCall = 0;
        this.potOdds = 0;
        this.canRaise = true;


    }

    public boolean isCanRaise() {
        return canRaise;
    }

    public void setCanRaise(boolean canRaise) {
        this.canRaise = canRaise;
    }

    public double getPotOdds() {
        return potOdds;
    }

    public void calculatePotOdds(Board board) {
        this.potOdds = this.amountToCall / board.getPot();
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public double getEquity() {
        return equity;
    }

    public void setEquity(double equity) {
        this.equity = equity;
    }

    public String getAvailableAction() {
        return availableAction;
    }

    public void setAvailableAction(String availableAction) {
        this.availableAction = availableAction;
    }

    public double getAmountToCall() {
        return amountToCall;
    }

    public void setAmountToCall(double amountToCall) {
        this.amountToCall = amountToCall;
    }

    public boolean isCanCheck() {
        return canCheck;
    }

    public void setCanCheck(boolean canCheck) {
        this.canCheck = canCheck;
    }

    public void setAction(String action, Board board) {
        if (action.toLowerCase().equals("raise") || action.toLowerCase().equals("bet") || action.toLowerCase().equals("call") || action.toLowerCase().equals("check")) {
            this.action = action.toLowerCase();
            if (board.getGameState().equalsIgnoreCase("preflop")) {
                this.preflopAction = action.toLowerCase();
            } else if (board.getGameState().equalsIgnoreCase("flop")) {
                this.flopAction = action.toLowerCase();
            } else if (board.getGameState().equalsIgnoreCase("turn")) {
                this.turnAction = action.toLowerCase();
            } else if (board.getGameState().equalsIgnoreCase("river")) {
                this.riverAction = action.toLowerCase();
            }
        }
    }




    public void equity(ArrayList<PlayerAI> opponent, ArrayList<Card> cards) {
        int wins = 0;
        int hands = Constants.equityHandsCount;
        long timerStart;

        wins = IntStream.range(0, hands).parallel().map(i -> equityInnerCalculation(opponent, cards)).reduce(0, (x, y) -> x + y);

        double equity = (double) wins / hands;
        this.equity = equity;
    }

    public int equityInnerCalculation(ArrayList<PlayerAI> opponents, ArrayList<Card> cards) {
        ArrayList<Card> cardholder = new ArrayList<Card>();

        Deck deck = new Deck();
        deck.removeCardByname(this.hand.getCards()[0].getName());
        deck.removeCardByname(this.hand.getCards()[1].getName());
        int result = 1;

        for (Card card : cards) {
            deck.removeCardByname(card.getName());
        }

        ArrayList<Hand> opponentHands = new ArrayList<Hand>();

        //ellenfél kezek
        for (PlayerAI opponent : opponents) {
            Random random = new Random();
            Hand hand = opponent.getRange().get(random.nextInt(opponent.getRange().size()));

            opponentHands.add(hand);
            deck.removeCardByname(hand.getCards()[0].getName());
            deck.removeCardByname(hand.getCards()[1].getName());

            cardholder.clear();
            cardholder.addAll(cards);
        }

        if (cardholder.size() == 3) {
            cardholder.add(deck.dealCard());
            cardholder.add(deck.dealCard());
        }
        if (cardholder.size() == 4) {
            cardholder.add(deck.dealCard());
        }
        if (cardholder.size() == 5) {
            int ownHand = new HandCombination(this.hand, cardholder).getCombinationStrength();
            for (Hand opponentHand : opponentHands) {
                if (ownHand < new HandCombination(opponentHand, cardholder).getCombinationStrength()) {
                    result = 0;
                }
            }

        }
        return result;
    }

}