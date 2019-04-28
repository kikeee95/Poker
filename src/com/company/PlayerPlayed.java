package com.company;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;

public class PlayerPlayed extends Player {
    Hand hand;
    double equity;
    String availableAction;
    double amountToCall;
    double potOdds;


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


    }


    public double getPotOdds() {
        return potOdds;
    }

    public void calculatePotOdds(Board board){
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

    public void equity(ArrayList<PlayerAI> opponent, ArrayList<Card> cards){
            int wins = 0;
            int hands = 0;

            for(int i = 0; i < 25000; i++){
                if(equityInnerCalculation(opponent, cards)){
                    wins++;
                }
                hands++;
            }

            double equity = (double) wins / hands;
            this.equity = equity;
    }



    public boolean equityInnerCalculation(ArrayList<PlayerAI>opponents, ArrayList<Card> cards){
        ArrayList<Card> cardholder = new ArrayList<Card>();


        long startTime = System.nanoTime();

        Deck deck = new Deck();
        deck.removeCardByname(this.hand.getCards()[0].getName());
        deck.removeCardByname(this.hand.getCards()[1].getName());
        boolean winnerHand = true;

        ArrayList<Hand> opponentHands = new ArrayList<Hand>();

        //ellenfél kezek
        for(int i = 0; i < opponents.size(); i++) {
            Random random = new Random();
            System.out.println(opponents.get(i).getRange().size());
            Hand hand = opponents.get(i).getRange().get(random.nextInt(opponents.get(i).getRange().size()));

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
                for(int i = 0; i < opponentHands.size(); i++) {
                    if (ownHand < new HandCombination(opponentHands.get(i), cardholder).getCombinationStrength() ) {
                        winnerHand = false;
                    }
                }

            }
            return winnerHand;
    }

}
