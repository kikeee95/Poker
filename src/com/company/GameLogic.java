package com.company;

import java.util.ArrayList;
import java.util.Random;

public final class GameLogic {


    public static void start(Board board) {
        if (board.getGameState().equalsIgnoreCase("preflop")) {
            preflopAction(board);
        } else if (board.getGameState().equalsIgnoreCase("flop")) {
            flopAction(board);
        } else if (board.getGameState().equalsIgnoreCase("turn")) {
            turnAction(board);
        }else if(board.getGameState().equalsIgnoreCase("river")){
            riverAction(board);
        }
    }


    private static void preflopAction(Board board) {
        PlayerPlayed player = (PlayerPlayed) board.getPlayers().get(0);

        // saját kártyáink értéke
        int holeCardValue = 1;
        if (player.getHand() != null) {
            holeCardValue = player.getHand().getHandId();
        }
        int raises = 0;

        if (holeCardValue > 0) {

            if (!player.isCanRaise()) {
                for (int i = 0; i < Ranges.Vs4BetEarlyAllIn.length; i++) {
                    if (holeCardValue == Ranges.Vs4BetEarlyAllIn[i]) {
                        System.out.println("Call all in");
                        board.getPlayers().get(0).setPreflopAction("call");
                    }
                }
            }


            //emelések száma előttünk
            for (int i = 0; i < board.getPlayers().size(); i++) {
                if (board.getPlayers().get(i).getAction().equalsIgnoreCase("raise")) {
                    raises++;
                }
            }
            // ha nem volt emelés
            if (raises == 0) {

                boolean raise = false;

                ArrayList<int[]> openraise = new ArrayList<int[]>();
                openraise.add(Ranges.tightPos1OR);
                openraise.add(Ranges.tightPos2OR);
                openraise.add(Ranges.tightPos3OR);
                openraise.add(Ranges.tightPos4OR);
                openraise.add(Ranges.tightPos5OR);
                openraise.add(Ranges.tightPos6OR);
                openraise.add(Ranges.tightPos7OR);
                openraise.add(Ranges.tightPos8OR);
                openraise.add(Ranges.tightPos9OR);


                for (int i = 0; i < openraise.get(board.getPlayers().get(0).getPosition()).length; i++) {
                    if (holeCardValue == openraise.get(board.getPlayers().get(0).getPosition())[i]) {
                        raise = true;
                    }
                }

                if (raise) {
                    System.out.println("Raise");
                    board.getPlayers().get(0).setPreflopAction("raise");
                    board.getPlayers().get(0).setAction("raise", board);
                }else if(player.getAvailableAction().equalsIgnoreCase("check")) {
                    System.out.println("Check");
                    board.getPlayers().get(0).setPreflopAction("check");
                    board.getPlayers().get(0).setAction("check", board);
                }else{
                    System.out.println("Fold");
                    board.getPlayers().get(0).setPreflopAction("fold");
                    board.getPlayers().get(0).setAction("fold", board);
                }

                // ha volt 1 emelés
            } else if (raises == 1) {
                int raisePosition = -1;

                for (int i = 0; i < board.getPlayers().size(); i++) {
                    if (board.getPlayers().get(i).getAction().equalsIgnoreCase("raise")) {
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

                for (int i = 0; i < vsRaise3Bet.get(raisePosition).length; i++) {
                    if(i != 8) {
                        if (holeCardValue == vsRaise3Bet.get(raisePosition)[i]) {
                            do3bet = true;
                        }
                    }
                }

                if (do3bet) {
                    System.out.println("3Bet");
                    board.getPlayers().get(0).setPreflopAction("raise");
                    board.getPlayers().get(0).setAction("raise", board);
                } else {
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

                    for (int i = 0; i < vsRaiseCall.get(raisePosition).length; i++) {
                        if (holeCardValue == vsRaiseCall.get(raisePosition)[i]) {
                            call = true;
                        }
                    }

                    if (call) {
                        System.out.println("Call");
                        board.getPlayers().get(0).setPreflopAction("call");
                        board.getPlayers().get(0).setAction("call", board);
                    }else if(player.getAvailableAction().equalsIgnoreCase("check")) {
                        System.out.println("Check");
                        board.getPlayers().get(0).setPreflopAction("check");
                        board.getPlayers().get(0).setAction("check", board);
                    }else{
                        System.out.println("Fold");
                        board.getPlayers().get(0).setPreflopAction("fold");
                        board.getPlayers().get(0).setAction("fold", board);
                    }
                }

                //ha 2 emelés volt
            } else if (raises == 2) {

                // megnézzük hogy ránk emeltek-e

                if (board.getPlayers().get(0).getPreflopAction().equalsIgnoreCase("raise")) {
                    //Megnézzük hogy 4Betelhetünk-e

                    ArrayList<int[]> vs3BetRaise = new ArrayList<int[]>();

                    int raisePosition = -1;

                    for (int i = 0; i < board.getPlayers().size(); i++) {
                        board.getPlayers().get(i).getAction().equalsIgnoreCase("raise");

                        // hogy a saját emelésünk ne nézze
                        if (i != 0) {
                            raisePosition = board.getPlayers().get(i).getPosition();
                        }
                    }

                    // emelő poziciója szerinti felosztás
                    boolean do4bet = false;
                    if (raisePosition <= 3) {
                        for (int i = 0; i < Ranges.Vs3BetEarly4Bet.length; i++) {
                            if (holeCardValue == Ranges.Vs3BetEarly4Bet[i]) {
                                do4bet = true;
                            }

                        }

                    } else if (raisePosition > 3) {
                        for (int i = 0; i < Ranges.Vs3BetLate4Bet.length; i++) {
                            if (holeCardValue == Ranges.Vs3BetLate4Bet[i]) {
                                do4bet = true;
                            }
                        }
                    }

                    if (do4bet) {
                        System.out.println("4Bet");
                        board.getPlayers().get(0).setPreflopAction("raise");
                        board.getPlayers().get(0).setAction("raise", board);
                    } else {
                        boolean call = false;

                        if (raisePosition <= 3) {
                            for (int i = 0; i < Ranges.Vs3BetEarlyCall.length; i++) {
                                if (holeCardValue == Ranges.Vs3BetEarlyCall[i]) {
                                    call = true;
                                }
                            }
                        } else if (raisePosition > 3) {
                            for (int i = 0; i < Ranges.Vs3BetLateCall.length; i++) {
                                if (holeCardValue == Ranges.Vs3BetLateCall[i]) {
                                    call = true;
                                }
                            }
                        }

                        if (call) {
                            System.out.println("Call");
                            board.getPlayers().get(0).setPreflopAction("call");
                            board.getPlayers().get(0).setAction("call", board);
                        }else if(player.getAvailableAction().equalsIgnoreCase("check")) {
                            System.out.println("Check");
                            board.getPlayers().get(0).setPreflopAction("check");
                            board.getPlayers().get(0).setAction("check", board);
                        }else{
                            System.out.println("Fold");
                            board.getPlayers().get(0).setPreflopAction("fold");
                            board.getPlayers().get(0).setAction("fold", board);
                        }

                    }

                }
                // ha 3 emelés volt
            } else if (raises == 3) {
                // megnézzük hogy ránk emeltek-e
                boolean allIn = false;
                if (board.getPlayers().get(0).getPreflopAction().equalsIgnoreCase("raise")) {


                    int raisePosition = -1;

                    for (int i = 0; i < board.getPlayers().size(); i++) {
                        if (board.getPlayers().get(i).getAction().equalsIgnoreCase("raise")) {

                            // hogy a saját emelésünk ne nézze
                            if (i != 0) {


                                // hogy az utolsó emelőt kapjuk
                                if (raisePosition < board.getPlayers().get(i).getPosition()) {
                                    raisePosition = board.getPlayers().get(i).getPosition();
                                }
                            }
                        }
                    }
                    if (raisePosition <= 3) {
                        for (int i = 0; i < Ranges.Vs4BetEarlyAllIn.length; i++) {
                            if (holeCardValue == Ranges.Vs4BetEarlyAllIn[i]) {
                                allIn = true;
                            }
                        }
                    } else if (raisePosition > 3) {
                        for (int i = 0; i < Ranges.Vs4BetLateAllIn.length; i++) {
                            if (holeCardValue == Ranges.Vs4BetLateAllIn[i]) {
                                allIn = true;
                            }
                        }
                    }

                }
                if (allIn) {
                    System.out.println("All In");
                    board.getPlayers().get(0).setPreflopAction("raise");
                    board.getPlayers().get(0).setAction("raise", board);
                }else if(player.getAvailableAction().equalsIgnoreCase("check")) {
                    System.out.println("Check");
                    board.getPlayers().get(0).setPreflopAction("check");
                    board.getPlayers().get(0).setAction("check", board);
                }else{
                    System.out.println("Fold");
                    board.getPlayers().get(0).setPreflopAction("fold");
                    board.getPlayers().get(0).setAction("fold", board);
                }

            }

        }
    }

    private static void flopAction(Board board) {
        // rangek kiosztása

        ArrayList<PlayerAI> opponents = new ArrayList<PlayerAI>();

        for (int i = 0; i < board.getPlayers().size(); i++) {
            if (i != 0) {
                if (board.getPlayers().get(i).hasCards) {
                    opponents.add((PlayerAI) board.getPlayers().get(i));
                }
            }
        }

        for (int i = 0; i < opponents.size(); i++) {
            if (opponents.get(i).getPlayerType().equalsIgnoreCase("fish")) {
                opponents.get(i).setRange(Ranges.fish);
            } else if (board.getPotType().equalsIgnoreCase("normal")) {
                if (opponents.get(i).getPreflopAction().equalsIgnoreCase("raise")) {
                    if (opponents.get(i).getPlayerType().equalsIgnoreCase("standard")) {
                        if (opponents.get(i).getPosition() == 0) {
                            opponents.get(i).setRange(Ranges.standardPos1OR);
                        }
                        if (opponents.get(i).getPosition() == 1) {
                            opponents.get(i).setRange(Ranges.standardPos2OR);
                        }
                        if (opponents.get(i).getPosition() == 2) {
                            opponents.get(i).setRange(Ranges.standardPos3OR);
                        }
                        if (opponents.get(i).getPosition() == 3) {
                            opponents.get(i).setRange(Ranges.standardPos4OR);
                        }
                        if (opponents.get(i).getPosition() == 4) {
                            opponents.get(i).setRange(Ranges.standardPos5OR);
                        }
                        if (opponents.get(i).getPosition() == 5) {
                            opponents.get(i).setRange(Ranges.standardPos6OR);
                        }
                        if (opponents.get(i).getPosition() == 6) {
                            opponents.get(i).setRange(Ranges.standardPos7OR);
                        }
                        if (opponents.get(i).getPosition() == 7) {
                            opponents.get(i).setRange(Ranges.standardPos8OR);
                        }
                        if (opponents.get(i).getPosition() == 8) {
                            opponents.get(i).setRange(Ranges.standardPos9OR);
                        }

                    }
                    if (opponents.get(i).getPlayerType().equalsIgnoreCase("tight")) {
                        if (opponents.get(i).getPosition() == 0) {
                            opponents.get(i).setRange(Ranges.tightPos1OR);
                        }
                        if (opponents.get(i).getPosition() == 1) {
                            opponents.get(i).setRange(Ranges.tightPos2OR);
                        }
                        if (opponents.get(i).getPosition() == 2) {
                            opponents.get(i).setRange(Ranges.tightPos3OR);
                        }
                        if (opponents.get(i).getPosition() == 3) {
                            opponents.get(i).setRange(Ranges.tightPos4OR);
                        }
                        if (opponents.get(i).getPosition() == 4) {
                            opponents.get(i).setRange(Ranges.tightPos5OR);
                        }
                        if (opponents.get(i).getPosition() == 5) {
                            opponents.get(i).setRange(Ranges.tightPos6OR);
                        }
                        if (opponents.get(i).getPosition() == 6) {
                            opponents.get(i).setRange(Ranges.tightPos7OR);
                        }
                        if (opponents.get(i).getPosition() == 7) {
                            opponents.get(i).setRange(Ranges.tightPos8OR);
                        }
                        if (opponents.get(i).getPosition() == 8) {
                            opponents.get(i).setRange(Ranges.tightPos9OR);
                        }
                    }

                    if (opponents.get(i).getPlayerType().equalsIgnoreCase("loose")) {
                        if (opponents.get(i).getPosition() == 0) {
                            opponents.get(i).setRange(Ranges.loosePos1OR);
                        }
                        if (opponents.get(i).getPosition() == 1) {
                            opponents.get(i).setRange(Ranges.loosePos2OR);
                        }
                        if (opponents.get(i).getPosition() == 2) {
                            opponents.get(i).setRange(Ranges.loosePos3OR);
                        }
                        if (opponents.get(i).getPosition() == 3) {
                            opponents.get(i).setRange(Ranges.loosePos4OR);
                        }
                        if (opponents.get(i).getPosition() == 4) {
                            opponents.get(i).setRange(Ranges.loosePos5OR);
                        }
                        if (opponents.get(i).getPosition() == 5) {
                            opponents.get(i).setRange(Ranges.loosePos6OR);
                        }
                        if (opponents.get(i).getPosition() == 6) {
                            opponents.get(i).setRange(Ranges.loosePos7OR);
                        }
                        if (opponents.get(i).getPosition() == 7) {
                            opponents.get(i).setRange(Ranges.loosePos8OR);
                        }
                        if (opponents.get(i).getPosition() == 8) {
                            opponents.get(i).setRange(Ranges.loosePos9OR);
                        }
                    }
                }
                if (opponents.get(i).getPreflopAction().equalsIgnoreCase("call")) {
                    // eredeti emelő megtalálása:

                    int preflopRaiserPos = -1;

                    for (int j = 0; j < board.getPlayers().size(); j++) {
                        if (board.getPlayers().get(j).getPreflopAction().equalsIgnoreCase("raise")) {
                            preflopRaiserPos = board.getPlayers().get(j).getPosition();
                        }
                    }

                    if (opponents.get(i).getPlayerType().equalsIgnoreCase("tight")) {
                        if (preflopRaiserPos == 0) {
                            opponents.get(i).setRange(Ranges.VsStandardPos1ORCall);
                        }
                        if (preflopRaiserPos == 1) {
                            opponents.get(i).setRange(Ranges.VsStandardPos2ORCall);
                        }
                        if (preflopRaiserPos == 2) {
                            opponents.get(i).setRange(Ranges.VsStandardPos3ORCall);
                        }
                        if (preflopRaiserPos == 3) {
                            opponents.get(i).setRange(Ranges.VsStandardPos4ORCall);
                        }
                        if (preflopRaiserPos == 4) {
                            opponents.get(i).setRange(Ranges.VsStandardPos5ORCall);
                        }
                        if (preflopRaiserPos == 5) {
                            opponents.get(i).setRange(Ranges.VsStandardPos6ORCall);
                        }
                        if (preflopRaiserPos == 6) {
                            opponents.get(i).setRange(Ranges.VsStandardPos7ORCall);
                        }
                        if (preflopRaiserPos == 7) {
                            opponents.get(i).setRange(Ranges.VsStandardPos8ORCall);
                        }
                        if (preflopRaiserPos == 8) {
                            opponents.get(i).setRange(Ranges.VsStandardPos8ORCall);
                        }
                    }
                    if (opponents.get(i).getPlayerType().equalsIgnoreCase("standard")) {
                        if (preflopRaiserPos <= 3) {
                            opponents.get(i).setRange(Ranges.standardCallEarly);
                        }
                        if (preflopRaiserPos > 3) {
                            opponents.get(i).setRange(Ranges.standardCallLate);
                        }
                    }
                    if (opponents.get(i).getPlayerType().equalsIgnoreCase("loose")) {
                        if (preflopRaiserPos <= 3) {
                            opponents.get(i).setRange(Ranges.looseCallEarly);
                        }
                        if (preflopRaiserPos > 3) {
                            opponents.get(i).setRange(Ranges.looseCallLate);
                        }
                    }
                }
            } else if (board.getPotType().equalsIgnoreCase("3bet")) {
                int preflopRaiserPos = -1;

                for (int j = 0; j < board.getPlayers().size(); j++) {
                    if (board.getPlayers().get(j).getPreflopAction().equalsIgnoreCase("raise")) {
                        preflopRaiserPos = board.getPlayers().get(j).getPosition();
                    }
                }

                if (opponents.get(i).getPreflopAction().equalsIgnoreCase("raise")) {
                    if (opponents.get(i).getPlayerType().equalsIgnoreCase("tight")) {
                        if (preflopRaiserPos == 0) {
                            opponents.get(i).setRange(Ranges.VsStandardPos1OR3B);
                        }
                        if (preflopRaiserPos == 1) {
                            opponents.get(i).setRange(Ranges.VsStandardPos2OR3B);
                        }
                        if (preflopRaiserPos == 2) {
                            opponents.get(i).setRange(Ranges.VsStandardPos3OR3B);
                        }
                        if (preflopRaiserPos == 3) {
                            opponents.get(i).setRange(Ranges.VsStandardPos4OR3B);
                        }
                        if (preflopRaiserPos == 4) {
                            opponents.get(i).setRange(Ranges.VsStandardPos5OR3B);
                        }
                        if (preflopRaiserPos == 5) {
                            opponents.get(i).setRange(Ranges.VsStandardPos6OR3B);
                        }
                        if (preflopRaiserPos == 6) {
                            opponents.get(i).setRange(Ranges.VsStandardPos7OR3B);
                        }
                        if (preflopRaiserPos == 7) {
                            opponents.get(i).setRange(Ranges.VsStandardPos8OR3B);
                        }
                        if (preflopRaiserPos == 8) {
                            opponents.get(i).setRange(Ranges.VsStandardPos7OR3B);
                        }
                    } else if (opponents.get(i).getPlayerType().equalsIgnoreCase("standard")) {
                        if (preflopRaiserPos <= 3) {
                            opponents.get(i).setRange(Ranges.standard3BetEarly);
                        }
                        if (preflopRaiserPos > 3) {
                            opponents.get(i).setRange(Ranges.standard3BetCallLate);
                        }
                    } else if (opponents.get(i).getPlayerType().equalsIgnoreCase("loose")) {
                        if (preflopRaiserPos <= 3) {
                            opponents.get(i).setRange(Ranges.loose3BetEarly);
                        } else if (preflopRaiserPos > 3) {
                            opponents.get(i).setRange(Ranges.loose3BetLate);
                        }
                    }
                }
            } else if (board.getPotType().equalsIgnoreCase("3bet")) {
                int preflopRaiserPos = -1;

                for (int j = 0; j < board.getPlayers().size(); j++) {
                    if (board.getPlayers().get(j).getPreflopAction().equalsIgnoreCase("raise")) {
                        preflopRaiserPos = board.getPlayers().get(j).getPosition();
                    }
                }

                if (opponents.get(i).getPreflopAction().equalsIgnoreCase("raise")) {
                    if (opponents.get(i).getPlayerType().equalsIgnoreCase("tight")) {
                        if (preflopRaiserPos == 0) {
                            opponents.get(i).setRange(Ranges.VsStandardPos1OR3B);
                        }
                        if (preflopRaiserPos == 1) {
                            opponents.get(i).setRange(Ranges.VsStandardPos2OR3B);
                        }
                        if (preflopRaiserPos == 2) {
                            opponents.get(i).setRange(Ranges.VsStandardPos3OR3B);
                        }
                        if (preflopRaiserPos == 3) {
                            opponents.get(i).setRange(Ranges.VsStandardPos4OR3B);
                        }
                        if (preflopRaiserPos == 4) {
                            opponents.get(i).setRange(Ranges.VsStandardPos5OR3B);
                        }
                        if (preflopRaiserPos == 5) {
                            opponents.get(i).setRange(Ranges.VsStandardPos6OR3B);
                        }
                        if (preflopRaiserPos == 6) {
                            opponents.get(i).setRange(Ranges.VsStandardPos7OR3B);
                        }
                        if (preflopRaiserPos == 7) {
                            opponents.get(i).setRange(Ranges.VsStandardPos8OR3B);
                        }
                        if (preflopRaiserPos == 8) {
                            opponents.get(i).setRange(Ranges.VsStandardPos7OR3B);
                        }
                    } else if (opponents.get(i).getPlayerType().equalsIgnoreCase("standard")) {
                        if (preflopRaiserPos <= 3) {
                            opponents.get(i).setRange(Ranges.standard3BetEarly);
                        }
                        if (preflopRaiserPos > 3) {
                            opponents.get(i).setRange(Ranges.standard3BetCallLate);
                        }
                    } else if (opponents.get(i).getPlayerType().equalsIgnoreCase("loose")) {
                        if (preflopRaiserPos <= 3) {
                            opponents.get(i).setRange(Ranges.loose3BetEarly);
                        } else if (preflopRaiserPos > 3) {
                            opponents.get(i).setRange(Ranges.loose3BetLate);
                        }
                    }
                }
                if (opponents.get(i).getPreflopAction().equalsIgnoreCase("call")) {
                    if (opponents.get(i).getPlayerType().equalsIgnoreCase("tight") || opponents.get(i).getPlayerType().equalsIgnoreCase("standard")) {
                        if (preflopRaiserPos <= 3) {
                            opponents.get(i).setRange(Ranges.standard3BetCallEarly);
                        }
                        if (preflopRaiserPos > 3) {
                            opponents.get(i).setRange(Ranges.standard3BetCallLate);
                        }
                    } else if (opponents.get(i).getPlayerType().equalsIgnoreCase("loose")) {
                        if (preflopRaiserPos <= 3) {
                            opponents.get(i).setRange(Ranges.loose3BetCallEarly);
                        } else if (preflopRaiserPos > 3) {
                            opponents.get(i).setRange(Ranges.loose3BetCallLate);
                        }
                    }
                }
            } else if (board.getPotType().equalsIgnoreCase("4bet")) {
                int preflopRaiserPos = -1;

                for (int j = 0; j < board.getPlayers().size(); j++) {
                    if (board.getPlayers().get(j).getPreflopAction().equalsIgnoreCase("raise")) {
                        preflopRaiserPos = board.getPlayers().get(j).getPosition();
                    }
                }

                if (opponents.get(i).getPreflopAction().equalsIgnoreCase("raise")) {
                    if (opponents.get(i).getPlayerType().equalsIgnoreCase("tight")) {
                        if (opponents.get(i).getPosition() <= 3) {
                            opponents.get(i).setRange(Ranges.Vs3BetEarly4Bet);
                        } else if (opponents.get(i).getPosition() > 3) {
                            opponents.get(i).setRange(Ranges.Vs3BetLate4Bet);
                        }
                    } else if (opponents.get(i).getPlayerType().equalsIgnoreCase("standard")) {
                        opponents.get(i).setRange(Ranges.standard4Bet);
                    } else if (opponents.get(i).getPlayerType().equalsIgnoreCase("loose")) {
                        opponents.get(i).setRange(Ranges.loose4Bet);
                    }
                }
                if (opponents.get(i).getPreflopAction().equalsIgnoreCase("call")) {
                    if (opponents.get(i).getPlayerType().equalsIgnoreCase("tight") || opponents.get(i).getPlayerType().equalsIgnoreCase("standard")) {
                        opponents.get(i).setRange(Ranges.standard3BetEarly);
                    } else if (opponents.get(i).getPlayerType().equalsIgnoreCase("loose")) {
                        opponents.get(i).setRange(Ranges.standard3BetLate);
                    }
                }
            }
        }
        PlayerPlayed player = (PlayerPlayed) board.getPlayers().get(0);

        player.equity(opponents, board.getCards());

        // Jelenlegi showdown value
        ArrayList<Card> ourCombination = new ArrayList<Card>();
        ourCombination.add(player.getHand().getCards()[0]);
        ourCombination.add(player.getHand().getCards()[1]);
        ourCombination.addAll(board.getCards());

        int showdownStrenght = new HandCombination(ourCombination).getCombinationStrength();

        boolean betAction = false;
        double betterAgression = 2;


        // megnézzük volt-e előttünk bet
        for (int i = 0; i < opponents.size(); i++) {
            if (opponents.get(i).getFlopAction().equalsIgnoreCase("bet") || opponents.get(i).getFlopAction().equalsIgnoreCase("raise")) {
                betAction = true;
                betterAgression = opponents.get(i).getAggression();
            }
        }
        // ha mi vagyunk a preflop agressor és még nem cselekedtünk a körben
        if (player.getPreflopAction().equalsIgnoreCase("raise") && player.getFlopAction().equalsIgnoreCase("No action")) {
            // ha nem volt még bet
            if (!betAction) {
                // ha gyenge párunk van
                if (showdownStrenght > 3000000 && showdownStrenght < 3045000) {
                    board.getPlayers().get(0).setFlopAction("check");
                    System.out.println("Flop: check");
                } else if (player.getEquity() > 0.3) {
                    if (board.isWetBoard()) {
                        board.getPlayers().get(0).setFlopAction("bet");
                        System.out.println("Flop: bet Pot");
                    } else {
                        board.getPlayers().get(0).setFlopAction("bet");
                        System.out.println("Flop: bet 1/2 Pot");
                    }
                }
            }
            if (betAction) {
                player.calculatePotOdds(board);
                if (player.getEquity() > 0.75) {
                    board.getPlayers().get(0).setFlopAction("raise");
                    System.out.println("Flop: Raise");
                }
                if (betterAgression >= 2.0) {
                    if (player.getEquity() > (player.getPotOdds() + 0.10)) {
                        board.getPlayers().get(0).setFlopAction("call");
                        System.out.println("Flop: Call");
                    } else {
                        board.getPlayers().get(0).setFlopAction("fold");
                        System.out.println("Flop: Fold");
                    }
                }
                if (betterAgression < 2.0) {
                    if (player.getEquity() > (player.getPotOdds() + 0.25)) {
                        board.getPlayers().get(0).setFlopAction("call");
                        System.out.println("Flop: Call");
                    } else {
                        board.getPlayers().get(0).setFlopAction("fold");
                        System.out.println("Flop: fold");
                    }
                }
                // ha emeltek ránk
                if (player.getFlopAction().equalsIgnoreCase("bet") || player.getFlopAction().equalsIgnoreCase("raise")) {
                    if (betterAgression >= 2.0) {
                        if (player.getEquity() > 0.8) {
                            board.getPlayers().get(0).setFlopAction("raise");
                            System.out.println("Flop: ReRaise");
                        } else if (player.getEquity() > (player.getPotOdds() + 0.2)) {
                            board.getPlayers().get(0).setFlopAction("call");
                            System.out.println("Flop: Call Raise");
                        } else {
                            board.getPlayers().get(0).setFlopAction("fold");
                            System.out.println("Flop: Fold to raise");
                        }
                    } else if (betterAgression < 2.0) {
                        if (player.getEquity() > 0.9) {
                            board.getPlayers().get(0).setFlopAction("raise");
                            System.out.println("Flop: ReRaise");
                        } else if (player.getEquity() > (player.getPotOdds() + 0.3)) {
                            board.getPlayers().get(0).setFlopAction("call");
                            System.out.println("Flop: Call raise");
                        } else {
                            board.getPlayers().get(0).setFlopAction("fold");
                            System.out.println("Flop: Fold to raise");
                        }
                    }
                }
            }
            // ha nem mi vagyunk a preflop agressor
            if (player.getPreflopAction().equalsIgnoreCase("call") && player.getFlopAction().equalsIgnoreCase("No action")) {
                if (!betAction) {
                    // donk bet
                    if (player.getEquity() > 0.35) {
                        Random rand = new Random();
                        if (rand.nextInt(100) < 20) {
                            if (board.isWetBoard()) {
                                board.getPlayers().get(0).setFlopAction("bet");
                                System.out.println("Flop: Bet Pot");
                            } else {
                                board.getPlayers().get(0).setFlopAction("bet");
                                System.out.println("Flop: Bet 1/2 Pot");
                            }
                        } else {
                            board.getPlayers().get(0).setFlopAction("check");
                            System.out.println("Flop: Check");
                        }
                    }
                } else {
                    if (betterAgression >= 2) {
                        if (player.getEquity() > 0.8) {
                            board.getPlayers().get(0).setFlopAction("raise");
                            System.out.println("Flop: Reraise");
                        } else if (player.getEquity() > (player.getPotOdds() + 0.10)) {
                            board.getPlayers().get(0).setFlopAction("call");
                            System.out.println("Flop: Call");
                        } else {
                            board.getPlayers().get(0).setFlopAction("fold");
                            System.out.println("Flop: Fold");
                        }
                    } else if (betterAgression < 2) {
                        if (player.getEquity() > 0.85) {
                            board.getPlayers().get(0).setFlopAction("raise");
                            System.out.println("Flop: Reraise");
                        } else if (player.getEquity() > (player.getPotOdds() + 0.2)) {
                            board.getPlayers().get(0).setFlopAction("call");
                            System.out.println("Flop: Call");
                        } else {
                            board.getPlayers().get(0).setFlopAction("fold");
                            System.out.println("Flop fold");
                        }
                    }
                }
            }
        }
    }

    private static void turnAction(Board board) {
        ArrayList<PlayerAI> opponents = new ArrayList<PlayerAI>();

        for (int i = 0; i < board.getPlayers().size(); i++) {
            if (i != 0) {
                if (board.getPlayers().get(i).hasCards) {
                    opponents.add((PlayerAI) board.getPlayers().get(i));
                }
            }
        }

        boolean flopBetHappened = false;

        PlayerPlayed player = (PlayerPlayed) board.getPlayers().get(0);
        boolean betAction = false;
        double betterAgression = 2;
        player.equity(opponents, board.getCards());

        if (player.getFlopAction().equalsIgnoreCase("bet") || player.getFlopAction().equalsIgnoreCase("raise")) {
            flopBetHappened = true;
        }

        for (int i = 0; i < opponents.size(); i++) {
            if (opponents.get(i).getFlopAction().equalsIgnoreCase("bet") || opponents.get(i).getFlopAction().equalsIgnoreCase("raise")) {
                flopBetHappened = true;
            }
        }

        //rangetrim
        if (flopBetHappened) {
            for (int i = 0; i < opponents.size(); i++) {
                opponents.get(i).setRange(HandCombination.rangeTrimForTurn(opponents.get(i).getRange(), board));
            }
        }

        for (int i = 0; i < opponents.size(); i++) {
            if (opponents.get(i).getFlopAction().equalsIgnoreCase("bet") || opponents.get(i).getFlopAction().equalsIgnoreCase("raise")) {
                betAction = true;
                betterAgression = opponents.get(i).getAggression();
            }
        }
        // ha mi vagyunk a flop agressor és még nem cselekedtünk a körben
        if ((player.getFlopAction().equalsIgnoreCase("raise") || player.getFlopAction().equalsIgnoreCase("bet")) && player.getTurnAction().equalsIgnoreCase("No action")) {
            // ha nem volt még bet
            if (!betAction) {
            }
            if (player.getEquity() > 0.55) {
                if (board.isWetBoard()) {
                    board.getPlayers().get(0).setTurnAction("bet");
                    System.out.println("Turn: bet Pot");
                } else {
                    board.getPlayers().get(0).setTurnAction("bet");
                    System.out.println("Turn: bet 1/2 Pot");
                }
            }
        }
        if (betAction) {
            player.calculatePotOdds(board);
            if (player.getEquity() > 0.80) {
                board.getPlayers().get(0).setTurnAction("raise");
                System.out.println("Turn: Raise");
            }
        }
        if (betterAgression >= 2.0) {
            if (player.getEquity() > (player.getPotOdds() + 0.15)) {
                board.getPlayers().get(0).setTurnAction("call");
                System.out.println("Turn: Call");
            } else {
                board.getPlayers().get(0).setTurnAction("fold");
                System.out.println("Turn: Fold");
            }
        }
        if (betterAgression < 2.0) {
            if (player.getEquity() > (player.getPotOdds() + 0.3)) {
                board.getPlayers().get(0).setTurnAction("call");
                System.out.println("Turn: Call");
            } else {
                board.getPlayers().get(0).setTurnAction("fold");
                System.out.println("Turn: fold");
            }
        }
        // ha emeltek ránk
        if (player.getTurnAction().equalsIgnoreCase("bet") || player.getTurnAction().equalsIgnoreCase("raise")) {
            if (betterAgression >= 2.0) {
                if (player.getEquity() > 0.85) {
                    board.getPlayers().get(0).setTurnAction("raise");
                    System.out.println("Turn: ReRaise");
                } else if (player.getEquity() > (player.getPotOdds() + 0.25)) {
                    board.getPlayers().get(0).setTurnAction("call");
                    System.out.println("Turn: Call Raise");
                } else {
                    board.getPlayers().get(0).setTurnAction("fold");
                    System.out.println("Turn: Fold to raise");
                }
            } else if (betterAgression < 2.0) {
                if (player.getEquity() > 0.9) {
                    board.getPlayers().get(0).setTurnAction("raise");
                    System.out.println("Turn: ReRaise");
                } else if (player.getEquity() > (player.getPotOdds() + 0.3)) {
                    board.getPlayers().get(0).setTurnAction("call");
                    System.out.println("Turn: Call raise");
                } else {
                    board.getPlayers().get(0).setTurnAction("fold");
                    System.out.println("Turn: Fold to raise");
                }
            }
        }
        // ha nem mi vagyunk a preflop agressor
        if (player.getFlopAction().equalsIgnoreCase("call") || player.getTurnAction().equalsIgnoreCase("No action") || player.getTurnAction().equalsIgnoreCase("check")) {
            if (!betAction || flopBetHappened) {
                // donk bet
                if (player.getEquity() > 0.55) {
                    Random rand = new Random();
                    if (rand.nextInt(100) < 20) {
                        if (board.isWetBoard()) {
                            board.getPlayers().get(0).setTurnAction("bet");
                            System.out.println("Turn: Bet Pot");
                        } else {
                            board.getPlayers().get(0).setTurnAction("bet");
                            System.out.println("Turn: Bet 1/2 Pot");
                        }
                    } else {
                        board.getPlayers().get(0).setTurnAction("check");
                        System.out.println("Turn: Check");
                    }
                }
            } else if (!betAction && !flopBetHappened) {
                if (player.getEquity() > 0.4) {
                    if (board.isWetBoard()) {
                        board.getPlayers().get(0).setTurnAction("bet");
                        System.out.println("Turn: Bet Pot");
                    } else {
                        board.getPlayers().get(0).setTurnAction("bet");
                        System.out.println("Turn: Bet 1/2 Pot");
                    }
                } else {
                    board.getPlayers().get(0).setTurnAction("check");
                    System.out.println("Turn: Check");
                }
            } else if (betAction) {
                if (betterAgression >= 2) {
                    if (player.getEquity() > 0.8) {
                        board.getPlayers().get(0).setTurnAction("raise");
                        System.out.println("Turn: Reraise");
                    } else if (player.getEquity() > (player.getPotOdds() + 0.15)) {
                        board.getPlayers().get(0).setTurnAction("call");
                        System.out.println("Turn: Call");
                    } else {
                        board.getPlayers().get(0).setTurnAction("fold");
                        System.out.println("Turn: Fold");
                    }
                } else if (betterAgression < 2) {
                    if (player.getEquity() > 0.85) {
                        board.getPlayers().get(0).setTurnAction("raise");
                        System.out.println("Turn: Reraise");
                    } else if (player.getEquity() > (player.getPotOdds() + 0.25)) {
                        board.getPlayers().get(0).setTurnAction("call");
                        System.out.println("Turn: Call");
                    } else {
                        board.getPlayers().get(0).setTurnAction("fold");
                        System.out.println("Turn: Fold");
                    }
                }
            }
        }
    }

    private static void riverAction(Board board) {
        ArrayList<PlayerAI> opponents = new ArrayList<PlayerAI>();

        for (int i = 0; i < board.getPlayers().size(); i++) {
            if (i != 0) {
                if (board.getPlayers().get(i).hasCards) {
                    opponents.add((PlayerAI) board.getPlayers().get(i));
                }
            }
        }

        boolean turnBetHappened = false;

        PlayerPlayed player = (PlayerPlayed) board.getPlayers().get(0);
        boolean betAction = false;
        double betterAgression = 2;
        player.equity(opponents, board.getCards());

        if (player.getTurnAction().equalsIgnoreCase("bet") || player.getTurnAction().equalsIgnoreCase("raise")) {
            turnBetHappened = true;
        }

        for (int i = 0; i < opponents.size(); i++) {
            if (opponents.get(i).getTurnAction().equalsIgnoreCase("bet") || opponents.get(i).getTurnAction().equalsIgnoreCase("raise")) {
                turnBetHappened = true;
            }
        }

        for (int i = 0; i < opponents.size(); i++) {
            if (opponents.get(i).getFlopAction().equalsIgnoreCase("bet") || opponents.get(i).getFlopAction().equalsIgnoreCase("raise")) {
                betAction = true;
                betterAgression = opponents.get(i).getAggression();
            }
        }

        // ha mi vagyunk a turn agresszor és nem cselekedtünk
        if ((player.getTurnAction().equalsIgnoreCase("raise") || player.getTurnAction().equalsIgnoreCase("bet")) && player.getRiverAction().equalsIgnoreCase("No action")) {
            // ha nem volt még bet
            if (!betAction) {
                if (player.getEquity() > 0.65) {
                    if (board.isWetBoard()) {
                        board.getPlayers().get(0).setRiverAction("bet");
                        System.out.println("River: bet Pot");
                    } else {
                        board.getPlayers().get(0).setRiverAction("bet");
                        System.out.println("River: bet 1/2 Pot");
                    }
                }
            }
            if (betAction) {
                player.calculatePotOdds(board);
                if (player.getEquity() > 0.85) {
                    board.getPlayers().get(0).setRiverAction("raise");
                    System.out.println("River: Raise");
                }
                if (betterAgression >= 2.0) {
                    if (player.getEquity() > (player.getPotOdds() + 0.15)) {
                        board.getPlayers().get(0).setRiverAction("call");
                        System.out.println("River: Call");
                    } else {
                        board.getPlayers().get(0).setRiverAction("fold");
                        System.out.println("River: Fold");
                    }
                }
                if (betterAgression < 2.0) {
                    if (player.getEquity() > (player.getPotOdds() + 0.3)) {
                        board.getPlayers().get(0).setRiverAction("call");
                        System.out.println("River: Call");
                    } else {
                        board.getPlayers().get(0).setRiverAction("fold");
                        System.out.println("River: fold");
                    }
                }
                // ha emeltek ránk
                if (player.getRiverAction().equalsIgnoreCase("bet") || player.getRiverAction().equalsIgnoreCase("raise")) {
                    if (betterAgression >= 2.0) {
                        if (player.getEquity() > 0.9) {
                            board.getPlayers().get(0).setRiverAction("raise");
                            System.out.println("River: ReRaise");
                        } else if (player.getEquity() > (player.getPotOdds() + 0.3)) {
                            board.getPlayers().get(0).setRiverAction("call");
                            System.out.println("River: Call Raise");
                        } else {
                            board.getPlayers().get(0).setRiverAction("fold");
                            System.out.println("River: Fold to raise");
                        }
                    } else if (betterAgression < 2.0) {
                        if (player.getEquity() > 0.9) {
                            board.getPlayers().get(0).setRiverAction("raise");
                            System.out.println("River: ReRaise");
                        } else if (player.getEquity() > (player.getPotOdds() + 0.3)) {
                            board.getPlayers().get(0).setRiverAction("call");
                            System.out.println("River: Call raise");
                        } else {
                            board.getPlayers().get(0).setRiverAction("fold");
                            System.out.println("River: Fold to raise");
                        }
                    }
                }
            }
            // ha nem mi vagyunk a preflop agressor
            if (player.getTurnAction().equalsIgnoreCase("call") || player.getTurnAction().equalsIgnoreCase("No action") || player.getTurnAction().equalsIgnoreCase("check")) {
                if (!betAction || turnBetHappened) {
                    // donk bet
                    if (player.getEquity() > 0.65) {
                        Random rand = new Random();
                        if (rand.nextInt(100) < 40) {
                            if (board.isWetBoard()) {
                                board.getPlayers().get(0).setRiverAction("bet");
                                System.out.println("River: Bet Pot");
                            } else {
                                board.getPlayers().get(0).setRiverAction("bet");
                                System.out.println("River: Bet 1/2 Pot");
                            }
                        } else {
                            board.getPlayers().get(0).setRiverAction("check");
                            System.out.println("River: Check");
                        }
                    }
                } else if (!betAction && !turnBetHappened) {
                    if (player.getEquity() > 0.4) {
                        if (board.isWetBoard()) {
                            board.getPlayers().get(0).setRiverAction("bet");
                            System.out.println("River: Bet Pot");
                        } else {
                            board.getPlayers().get(0).setRiverAction("bet");
                            System.out.println("Turn: Bet 1/2 Pot");
                        }
                    } else {
                        board.getPlayers().get(0).setRiverAction("check");
                        System.out.println("Turn: Check");
                    }
                } else if (betAction) {
                    if (betterAgression >= 2) {
                        if (player.getEquity() > 0.9) {
                            board.getPlayers().get(0).setRiverAction("raise");
                            System.out.println("River: Reraise");
                        } else if (player.getEquity() > (player.getPotOdds() + 0.15)) {
                            board.getPlayers().get(0).setRiverAction("call");
                            System.out.println("River: Call");
                        } else {
                            board.getPlayers().get(0).setRiverAction("fold");
                            System.out.println("River: Fold");
                        }
                    } else if (betterAgression < 2) {
                        if (player.getEquity() > 0.85) {
                            board.getPlayers().get(0).setRiverAction("raise");
                            System.out.println("River: Reraise");
                        } else if (player.getEquity() > (player.getPotOdds() + 0.25)) {
                            board.getPlayers().get(0).setRiverAction("call");
                            System.out.println("River: Call");
                        } else {
                            board.getPlayers().get(0).setRiverAction("fold");
                            System.out.println("Turn: Fold");
                        }
                    }
                }
            }
        }
    }
}
