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
                board.getPlayers().get(0).setPreflopAction("raise");
                board.getPlayers().get(0).setAction("raise");
            }else{
                System.out.println("Fold");
                board.getPlayers().get(0).setPreflopAction("fold");
                board.getPlayers().get(0).setAction("fold");
            }

            // ha volt 1 emelés
        }else if(raises == 1){
            int raisePosition = -1;

            for(int i = 0; i < board.getPlayers().size(); i++){
                if(board.getPlayers().get(i).getAction().equalsIgnoreCase("raise")) {
                    raisePosition = board.getPlayers().get(i).getPosition();
                }
            }

            //Megnézzük hogy 3bet-elhetünk vagy sem a lapunkkal

            ArrayList<int[]> vsRaise3Bet = new ArrayList<int[]>();
            vsRaise3Bet.add(Ranges.VsStandardPos1OR3B);
            vsRaise3Bet.add(Ranges.VsStandardPos2OR3B);
            vsRaise3Bet.add(Ranges.VsStandardPos3OR3B);
            vsRaise3Bet.add(Ranges.VsStandardPos4OR3B);
            vsRaise3Bet.add(Ranges.VsStandardPos5OR3B);
            vsRaise3Bet.add(Ranges.VsStandardPos6OR3B);
            vsRaise3Bet.add(Ranges.VsStandardPos7OR3B);
            vsRaise3Bet.add(Ranges.VsStandardPos8OR3B);

            boolean do3bet = false;

            for(int i = 0; i < vsRaise3Bet.get(raisePosition).length; i++){
                if(holeCardValue == vsRaise3Bet.get(raisePosition)[i]){
                    do3bet = true;
                }
            }

            if(do3bet){
                System.out.println("3Bet");
                board.getPlayers().get(0).setPreflopAction("raise");
                board.getPlayers().get(0).setAction("raise");
            }else{
                // Megnézzük hogy megadhatunk-e

                ArrayList<int[]> vsRaiseCall = new ArrayList<int[]>();
                vsRaiseCall.add(Ranges.VsStandardPos1ORCall);
                vsRaiseCall.add(Ranges.VsStandardPos2ORCall);
                vsRaiseCall.add(Ranges.VsStandardPos3ORCall);
                vsRaiseCall.add(Ranges.VsStandardPos4ORCall);
                vsRaiseCall.add(Ranges.VsStandardPos5ORCall);
                vsRaiseCall.add(Ranges.VsStandardPos6ORCall);
                vsRaiseCall.add(Ranges.VsStandardPos7ORCall);
                vsRaiseCall.add(Ranges.VsStandardPos8ORCall);

                boolean call = false;

                for(int i = 0; i < vsRaiseCall.get(raisePosition).length; i++){
                    if(holeCardValue == vsRaiseCall.get(raisePosition)[i]){
                        call = true;
                    }
                }

                if(call){
                    System.out.println("Call");
                    board.getPlayers().get(0).setPreflopAction("call");
                    board.getPlayers().get(0).setAction("call");
                }else{
                    System.out.println("Fold");
                    board.getPlayers().get(0).setPreflopAction("fold");
                    board.getPlayers().get(0).setAction("fold");
                }
            }

            //ha 2 emelés volt
        }else if(raises == 2){

            // megnézzük hogy ránk emeltek-e

            if(board.getPlayers().get(0).getPreflopAction().equalsIgnoreCase("raise")){
                //Megnézzük hogy 4Betelhetünk-e

                ArrayList<int[]> vs3BetRaise = new ArrayList<int[]>();

                int raisePosition = -1;

                for(int i = 0; i < board.getPlayers().size(); i++){
                    board.getPlayers().get(i).getAction().equalsIgnoreCase("raise");

                    // hogy a saját emelésünk ne nézze
                    if(i != 0) {
                        raisePosition = board.getPlayers().get(i).getPosition();
                    }
                }

                // emelő poziciója szerinti felosztás
                boolean do4bet = false;
                if(raisePosition <= 3){
                    for(int i = 0; i < Ranges.Vs3BetEarly4Bet.length; i++){
                        if(holeCardValue == Ranges.Vs3BetEarly4Bet[i]){
                            do4bet = true;
                        }

                    }

                }else if(raisePosition > 3){
                    for(int i = 0; i < Ranges.Vs3BetLate4Bet.length; i++){
                        if(holeCardValue == Ranges.Vs3BetLate4Bet[i]){
                            do4bet = true;
                        }
                    }
                }

                if(do4bet){
                    System.out.println("4Bet");
                    board.getPlayers().get(0).setPreflopAction("raise");
                    board.getPlayers().get(0).setAction("raise");
                }else{
                    boolean call = false;

                    if(raisePosition <= 3){
                        for(int i = 0; i < Ranges.Vs3BetEarlyCall.length; i++){
                            if(holeCardValue == Ranges.Vs3BetEarlyCall[i]){
                                call = true;
                            }
                        }
                    }else if(raisePosition > 3){
                        for(int i = 0; i < Ranges.Vs3BetLateCall.length; i++){
                            if(holeCardValue == Ranges.Vs3BetLateCall[i]){
                                call = true;
                            }
                        }
                    }

                    if(call){
                        System.out.println("Call");
                        board.getPlayers().get(0).setPreflopAction("call");
                        board.getPlayers().get(0).setAction("call");
                    }else{
                        System.out.println("Fold");
                        board.getPlayers().get(0).setPreflopAction("fold");
                        board.getPlayers().get(0).setAction("fold");
                    }

                }

            }
            // ha 3 emelés volt
        }else if(raises == 3){
            // megnézzük hogy ránk emeltek-e
            boolean allIn = false;
            if(board.getPlayers().get(0).getPreflopAction().equalsIgnoreCase("raise")){



                int raisePosition = -1;

                for(int i = 0; i < board.getPlayers().size(); i++){
                    board.getPlayers().get(i).getAction().equalsIgnoreCase("raise");

                    // hogy a saját emelésünk ne nézze
                    if(i != 0) {


                        // hogy az utolsó emelőt kapjuk
                        if(raisePosition < board.getPlayers().get(i).getPosition()){
                            raisePosition = board.getPlayers().get(i).getPosition();
                        }
                    }
                }
                if(raisePosition <= 3){
                    for(int i = 0; i < Ranges.Vs4BetEarlyAllIn.length; i++){
                        if(holeCardValue == Ranges.Vs4BetEarlyAllIn[i]){
                            allIn = true;
                        }
                    }
                }else if(raisePosition > 3){
                    for(int i = 0; i < Ranges.Vs4BetLateAllIn.length; i++){
                        if(holeCardValue == Ranges.Vs4BetLateAllIn[i]){
                            allIn = true;
                        }
                    }
                }

            }
            if(allIn){
                System.out.println("All In");
                board.getPlayers().get(0).setPreflopAction("raise");
                board.getPlayers().get(0).setAction("raise");
            }else{
                System.out.println("Fold");
                board.getPlayers().get(0).setPreflopAction("fold");
                board.getPlayers().get(0).setAction("fold");
            }

        }

    }
}
