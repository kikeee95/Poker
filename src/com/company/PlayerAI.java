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
        this.call = 1;
        this.raise = 1;
        this.check = 1;
        this.bet = 1;
        this.handsPlayed = 0;
    }


    public ArrayList<Hand> getRange() {
        return range;
    }

    public void setRange(int[] range) {
        this.range.clear();
        for (int aRange : range) {
            this.range.add(new Hand(aRange));
        }
    }

    public void setRange(ArrayList<Hand> range) {
        this.range.clear();
        this.range.addAll(range);
    }

    public void setAction(String action, Board board) {
        if (action.toLowerCase().equals("raise") || action.toLowerCase().equals("bet") || action.toLowerCase().equals("call") || action.toLowerCase().equals("check")) {
            this.action = action.toLowerCase();
            if (board.getGameState().equalsIgnoreCase("preflop")) {
                this.preflopAction = action.toLowerCase();
            } else if (board.getGameState().equalsIgnoreCase("flop")) {
                this.flopAction = action.toLowerCase();
            } else if (board.getGameState().equalsIgnoreCase("turn")) {
                this.turnAction = action.toLowerCase();
            } else if (board.getGameState().equalsIgnoreCase("river")) {
                this.riverAction = action.toLowerCase();
            }
        }

        if (board.getGameState().equalsIgnoreCase("flop") || board.getGameState().equalsIgnoreCase("turn") || board.getGameState().equalsIgnoreCase("river")) {
            if (action.equalsIgnoreCase("call")) {
                this.call++;
            } else if (action.equalsIgnoreCase("raise")) {
                this.raise++;
            } else if (action.equalsIgnoreCase("bet")) {
                this.bet++;
            } else if (action.equalsIgnoreCase("check")) {
                this.check++;
            }

        }
        calcPlayerType();
    }

    public void setPreflopAction(String preflopAction) {
        if (preflopAction.equalsIgnoreCase("bet") || preflopAction.equalsIgnoreCase("raise") || preflopAction.equalsIgnoreCase("call")) {
            this.vpip++;
        }
        this.preflopAction = preflopAction;
    }

    private void calcPlayerType() {
        this.vpipPercent = this.vpip / (double) this.handsPlayed;
        this.aggression = (this.raise + this.bet) / (double) this.call;

        if (this.handsPlayed > 50) {

            if (this.vpipPercent < 0.12) {
                this.playerType = "tight";
            } else if (this.vpipPercent >= 0.12 && this.vpipPercent < 0.22) {
                this.playerType = "standard";
            } else if (this.vpipPercent >= 0.22 && this.vpipPercent < 0.35) {
                this.playerType = "loose";
            } else {
                this.playerType = "fish";
            }
        }

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
