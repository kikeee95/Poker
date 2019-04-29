package com.company;

import java.util.ArrayList;

public class PlayerAI extends Player {

    private ArrayList<Hand> range;
    private int vpip;
    private int call;
    private int raise;
    private int check;
    private int bet;
    private double aggression;
    private int handsPlayed;
    private double vpipPercent;
    private String playerType;

    public PlayerAI() {
        this.range = new ArrayList<Hand>();
        this.name = "Default Name";
        this.money = 200;
        this.action = "No action";
        this.preflopAction = "No action";
        this.flopAction = "No action";
        this.turnAction = "No action";
        this.riverAction = "No action";
        this.position = -1;
        this.playerType = "standard";
    }


    public ArrayList<Hand> getRange() {
        return range;
    }

    public void setRange(int[] range) {
        this.range.clear();
        for (int i = 0; i < range.length; i++) {
            this.range.add(new Hand(range[i]));
        }
    }

    public void setRange(ArrayList<Hand> range) {
        this.range.clear();
        this.range.addAll(range);
    }

    public int getVpip() {
        return vpip;
    }

    public void setVpip(int vpip) {
        this.vpip = vpip;
    }

    public int getCall() {
        return call;
    }

    public void setCall(int call) {
        this.call = call;
    }

    public int getRaise() {
        return raise;
    }

    public void setRaise(int raise) {
        this.raise = raise;
    }

    public int getCheck() {
        return check;
    }

    public void setCheck(int check) {
        this.check = check;
    }

    public int getBet() {
        return bet;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }

    public double getAggression() {
        return aggression;
    }

    public void setAggression(double aggression) {
        this.aggression = aggression;
    }

    public int getHandsPlayed() {
        return handsPlayed;
    }

    public void setHandsPlayed(int handsPlayed) {
        this.handsPlayed = handsPlayed;
    }

    public double getVpipPercent() {
        return vpipPercent;
    }

    public void setVpipPercent(double vpipPercent) {
        this.vpipPercent = vpipPercent;
    }

    public String getPlayerType() {
        return playerType;
    }

    public void setPlayerType(String playerType) {
        this.playerType = playerType;
    }
}
