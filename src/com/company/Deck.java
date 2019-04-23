package com.company;

import java.util.ArrayList;

public class Deck {
    ArrayList<Card> cards;

    public Deck() {
        for(int i = 0; i < Constants.cards.length; i++){
            cards.add(new Card(Constants.cards[i]));
        }
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public void removeCardByname(String name){
        for(int i = this.cards.size()-1; i >= 0; i--){
            if(this.cards.get(i).getName() == name){
                this.cards.remove(i);
            }
        }
    }

    public void removeCardById(int id){
        for(int i = this.cards.size()-1; i >= 0; i--){
            if(this.cards.get(i).getPrimeValue() == id){
                this.cards.remove(i);
            }
        }
    }
}

