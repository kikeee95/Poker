package com.company;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
    private ArrayList<Card> cards;

    public Deck() {
        cards = new ArrayList<Card>();
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

    public Card dealCard(){
        Random rand = new Random();
        int i = rand.nextInt(this.cards.size());
        Card card = this.cards.get(i);
        this.cards.remove(i);
        return card;
    }
}

