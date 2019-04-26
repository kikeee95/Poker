package com.company;

public abstract class Player {
    protected String name;
    protected double money;
    protected String action;
    protected String preflopAction;
    protected String flopAction;
    protected String turnAction;
    protected String riverAction;
    protected int position;
    protected boolean hasCards;


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

    public void setAction(String action, Board board) {

        if(action.toLowerCase().equals("fold") || action.toLowerCase().equals("raise") || action.toLowerCase().equals("bet") || action.toLowerCase().equals("call") || action.toLowerCase().equals("check"))  {
            this.action = action.toLowerCase();
            if(board.getGameState().equalsIgnoreCase("preflop")){
                this.preflopAction = action.toLowerCase();
            }else if(board.getGameState().equalsIgnoreCase("flop")){
                this.flopAction = action.toLowerCase();
            }else if(board.getGameState().equalsIgnoreCase("turn")){
                this.turnAction = action.toLowerCase();
            }else if(board.getGameState().equalsIgnoreCase("river")){
                this.riverAction = action.toLowerCase();
            }
        }else{
            this.action = "No action";
        }
    }

    public String getPreflopAction() {
        return preflopAction;
    }


    public String getFlopAction() {
        return flopAction;
    }


    public String getTurnAction() {
        return turnAction;
    }


    public String getRiverAction() {
        return riverAction;
    }

    public void setPreflopAction(String preflopAction) {
        this.preflopAction = preflopAction;
    }

    public void setFlopAction(String flopAction) {
        this.flopAction = flopAction;
    }

    public void setTurnAction(String turnAction) {
        this.turnAction = turnAction;
    }

    public void setRiverAction(String riverAction) {
        this.riverAction = riverAction;
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


}
