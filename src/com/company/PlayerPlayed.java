package com.company;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;

public class PlayerPlayed extends Player {
    Hand hand;
    double equity;


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
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public double equity(ArrayList<PlayerAI> opponent, ArrayList<Card> cards){
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
            return equity;




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
