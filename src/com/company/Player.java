package com.company;

public class Player {
    private String name;
    private double money;
    private String action;
    private boolean isPlayed;
    private String preflopAction;
    private String flopAction;
    private String turnAction;
    private String riverAction;
    private Hand hand;
    private boolean hasCards;
    private int position;

    public Player(boolean isPlayed) {
        this.isPlayed = isPlayed;
        this.name = "Default Name";
        this.money = 200;
        this.action = "No action";
        this.preflopAction = "No action";
        this.flopAction = "No action";
        this.turnAction = "No action";
        this.riverAction = "No action";
        this.position = -1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {

        if(action.toLowerCase().equals("fold") || action.toLowerCase().equals("raise") || action.toLowerCase().equals("bet") || action.toLowerCase().equals("call") || action.toLowerCase().equals("check"))  {
            this.action = action.toLowerCase();
        }else{
            this.action = "No action";
        }
    }

    public boolean isPlayed() {
        return isPlayed;
    }

    public void setPlayed(boolean played) {
        isPlayed = played;
    }

    public String getPreflopAction() {
        return preflopAction;
    }

    public void setPreflopAction(String preflopAction) {
        this.preflopAction = preflopAction;
    }

    public String getFlopAction() {
        return flopAction;
    }

    public void setFlopAction(String flopAction) {
        this.flopAction = flopAction;
    }

    public String getTurnAction() {
        return turnAction;
    }

    public void setTurnAction(String turnAction) {
        this.turnAction = turnAction;
    }

    public String getRiverAction() {
        return riverAction;
    }

    public void setRiverAction(String riverAction) {
        this.riverAction = riverAction;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public boolean isHasCards() {
        return hasCards;
    }

    public void setHasCards(boolean hasCards) {
        this.hasCards = hasCards;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String toString() {
        if (this.hasCards) {
            return "Player{" +
                    "name='" + name + '\'' + "\n" +
                    ", money=" + money + "\n" +
                    ", action='" + action + '\'' + "\n" +
                    ", hand=" + hand + "\n" +
                    '}';
        }else{
            return "";
        }
    }
}
