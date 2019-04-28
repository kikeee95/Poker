package com.company;

import java.util.ArrayList;

public final class GameLogic {


    public static void preflopAction(Board board) {
        PlayerPlayed player = (PlayerPlayed) board.getPlayers().get(0);

        // saját kártyáink értéke
        int holeCardValue = -1;
        if (player.getHand() != null) {
            holeCardValue = player.getHand().getHandId();
        }
        int raises = 0;

        if (holeCardValue > 0) {


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
                } else {
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
                    if (holeCardValue == vsRaise3Bet.get(raisePosition)[i]) {
                        do3bet = true;
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
                    } else {
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
                        } else {
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
                        board.getPlayers().get(i).getAction().equalsIgnoreCase("raise");

                        // hogy a saját emelésünk ne nézze
                        if (i != 0) {


                            // hogy az utolsó emelőt kapjuk
                            if (raisePosition < board.getPlayers().get(i).getPosition()) {
                                raisePosition = board.getPlayers().get(i).getPosition();
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
                } else {
                    System.out.println("Fold");
                    board.getPlayers().get(0).setPreflopAction("fold");
                    board.getPlayers().get(0).setAction("fold", board);
                }

            }

        }
    }

    public static void flopAction(Board board) {
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


        // megnézzük hogy volt-e már raise

    }
}
