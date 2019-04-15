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

    public Player(boolean isPlayed) {
        this.isPlayed = isPlayed;
        this.name = "Default Name";
        this.money = 200;
        this.preflopAction = "No action";
        this.flopAction = "No action";
        this.turnAction = "No action";
        this.riverAction = "No action";
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
        if(action.toLowerCase().equals("fold") || action.toLowerCase().equals("raise") || action.toLowerCase().equals("bet") || action.toLowerCase().equals("call")) {
            this.action = action;
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

    public String getRiverAction() {
        return riverAction;
    }

    public void setRiverAction(String riverAction) {
        this.riverAction = riverAction;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", money=" + money +
                ", action='" + action + '\'' +
                ", isPlayed=" + isPlayed +
                ", preflopAction='" + preflopAction + '\'' +
                ", flopAction='" + flopAction + '\'' +
                ", turnAction='" + turnAction + '\'' +
                ", riverAction='" + riverAction + '\'' +
                '}';
    }
}
