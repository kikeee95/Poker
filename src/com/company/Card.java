package com.company;

public class Card {
    private String name;
    private int value;
    private String suit;
    private int primeValue;

    public Card(String name) {
        for(int i = 0; i < Constants.cardPrimes.length; i++){
            if(name.equals(Constants.cards[i])){
                this.name = name;
                this.value = i;
                this.primeValue = Constants.cardPrimes[i];
                this.suit = suitDefine(name);
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public int getPrimeValue() {
        return primeValue;
    }

    public void setPrimeValue(int primeValue) {
        this.primeValue = primeValue;
    }

    private String suitDefine(String name){
        if('h' == name.charAt(1)){
            return "Hearts";
        }else if('s' == name.charAt(1)){
            return "Spades";
        }else if('d' == name.charAt(1)){
            return "Diamonds";
        }else if('c' == name.charAt(1)){
            return "Spades";
        }else{
            return "Invalid Suit";
        }
    }

    @Override
    public String toString() {
        return "Card{" +
                "name='" + name + '\'' +
                ", value=" + value +
                ", suit='" + suit + '\'' +
                ", primeValue=" + primeValue +
                '}';
    }
}
