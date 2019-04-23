package com.company;

import java.util.ArrayList;

public final class GameLogic {


    public static void preflopAction(Board board){

        // saját kártyáink értéke
        int holeCardValue = board.getPlayers().get(0).getHand().getHandId();
        int raises = 0;


        //emelések száma előttünk
        for(int i = 0; i < board.getPlayers().size(); i++){
            if(board.getPlayers().get(i).getAction().equalsIgnoreCase("raise")){
                raises++;
            }
        }
        // ha nem volt emelés
        if(raises == 0){

            boolean raise = false;

            ArrayList<int[]>openraise = new ArrayList<int[]>();
            openraise.add(Ranges.tightPos1OR);
            openraise.add(Ranges.tightPos2OR);
            openraise.add(Ranges.tightPos3OR);
            openraise.add(Ranges.tightPos4OR);
            openraise.add(Ranges.tightPos5OR);
            openraise.add(Ranges.tightPos6OR);
            openraise.add(Ranges.tightPos7OR);
            openraise.add(Ranges.tightPos8OR);
            openraise.add(Ranges.tightPos9OR);

            for(int i = 0; i < openraise.get(board.getPlayers().get(0).getPosition()).length; i++){
                if(holeCardValue == openraise.get(board.getPlayers().get(0).getPosition())[i]){
                    raise = true;
                }
            }

            if(raise){
                System.out.println("Raise");
            }else{
                System.out.println("Fold");
            }

        }

    }
}
