package com.company;

public class PlayerPlayed extends Player {
    Hand hand;


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
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }
}
