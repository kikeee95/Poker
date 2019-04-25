package com.company;

import java.util.Arrays;

public class Hand {
    private String handName;
    private Card[] cards;
    private int handId;

    public Hand(Card card1, Card card2) {
        this.handName = card1.getName()+card2.getName();
        this.cards = new Card[] {card1, card2};
        this.handId = calculateHandId(card1, card2);
    }

    public Hand(int handId){
        for(int i = 0; i < Constants.allHandsId.length; i++){
            if(handId == Constants.allHandsId[i]){
                this.handName = Constants.allHandsName[i];
                this.cards = new Card[] {new Card(this.handName.substring(0,3)), new Card(this.handName.substring(2, 4))};
                this.handId = handId;
            }
        }
    }

    public String getHandName() {
        return handName;
    }

    public void setHandName(String handName) {
        this.handName = handName;
    }

    public Card[] getCards() {
        return cards;
    }

    public void setCards(Card[] cards) {
        this.cards = cards;
    }

    public int getHandId() {
        return handId;
    }

    public void setHandId(int handId) {
        this.handId = handId;
    }

    private int calculateHandId(Card card1, Card card2){
        int value;
        value = card1.getPrimeValue()*card2.getPrimeValue();
        return value;
    }

    @Override
    public String toString() {
        return "Hand{" +
                "handName='" + handName + '\'' +
                ", cards=" + Arrays.toString(cards) +
                ", handId=" + handId +
                '}';
    }
}
