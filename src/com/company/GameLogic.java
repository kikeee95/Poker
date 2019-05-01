package com.company;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public final class GameLogic {


    public static void start(Board board, Rectangle rect)throws AWTException {
        if (board.getGameState().equalsIgnoreCase("preflop")) {
            preflopAction(board, rect);
        } else if (board.getGameState().equalsIgnoreCase("flop")) {
            flopAction(board, rect);
        } else if (board.getGameState().equalsIgnoreCase("turn")) {
            turnAction(board, rect);
        } else if (board.getGameState().equalsIgnoreCase("river")) {
            riverAction(board, rect);
        }
    }


    public static void preflopAction(Board board, Rectangle rect) throws AWTException {
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
                        Actions.callOrCheck(rect);
                    }
                }
            }


            //emelések száma előttünk
            for (int i = 0; i < board.getPlayers().size(); i++) {
                if (i != 0) {
                    if (board.getPlayers().get(i).getPreflopAction().equalsIgnoreCase("raise")) {
                        raises++;
                        System.out.println(board.getPlayers().get(i).getName());
                    }
                }
            }
            System.out.println("Preflop raises: " + raises);
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
                    board.setPotType();
                    Actions.raisePot(rect);
                } else if (player.getAvailableAction().equalsIgnoreCase("check")) {
                    System.out.println("Check");
                    board.getPlayers().get(0).setPreflopAction("check");
                    board.getPlayers().get(0).setAction("check", board);
                    Actions.callOrCheck(rect);
                } else {
                    System.out.println("Fold");
                    board.getPlayers().get(0).setPreflopAction("fold");
                    board.getPlayers().get(0).setAction("fold", board);
                    Actions.fold(rect);
                }

                // ha volt 1 emelés
            } else if (raises == 1 && !board.getPlayers().get(0).getPreflopAction().equalsIgnoreCase("raise")) {
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
                    board.setPotType();
                    Actions.raisePot(rect);
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
                        Actions.callOrCheck(rect);
                    } else if (player.getAvailableAction().equalsIgnoreCase("check")) {
                        System.out.println("Check");
                        board.getPlayers().get(0).setPreflopAction("check");
                        board.getPlayers().get(0).setAction("check", board);
                        Actions.callOrCheck(rect);
                    } else {
                        System.out.println("Fold");
                        board.getPlayers().get(0).setPreflopAction("fold");
                        board.getPlayers().get(0).setAction("fold", board);
                        Actions.fold(rect);
                    }
                }

                //ha 2 emelés volt
            } else if (raises == 2 || (raises == 1 && board.getPlayers().get(0).getPreflopAction().equalsIgnoreCase("raise"))) {

                // megnézzük hogy ránk emeltek-e


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
                    board.setPotType();
                    Actions.raisePot(rect);
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
                        Actions.callOrCheck(rect);
                    } else if (player.getAvailableAction().equalsIgnoreCase("check")) {
                        System.out.println("Check");
                        board.getPlayers().get(0).setPreflopAction("check");
                        board.getPlayers().get(0).setAction("check", board);
                        Actions.callOrCheck(rect);
                    } else {
                        System.out.println("Fold");
                        board.getPlayers().get(0).setPreflopAction("fold");
                        board.getPlayers().get(0).setAction("fold", board);
                        Actions.fold(rect);
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
                    board.setPotType();
                    Actions.allIn(rect);
                } else if (player.getAvailableAction().equalsIgnoreCase("check")) {
                    System.out.println("Check");
                    board.getPlayers().get(0).setPreflopAction("check");
                    board.getPlayers().get(0).setAction("check", board);
                    Actions.callOrCheck(rect);
                } else {
                    System.out.println("Fold");
                    board.getPlayers().get(0).setPreflopAction("fold");
                    board.getPlayers().get(0).setAction("fold", board);
                    Actions.fold(rect);
                }

            }

        }
    }

    public static void flopAction(Board board, Rectangle rect)throws AWTException {
        // rangek kiosztása
        int opponentsNumb = 0;

        ArrayList<PlayerAI> opponents = new ArrayList<PlayerAI>();

        for (int i = 0; i < board.getPlayers().size(); i++) {
            if (i != 0) {
                if (board.getPlayers().get(i).hasCards) {
                    opponents.add((PlayerAI) board.getPlayers().get(i));
                    opponentsNumb++;
                }
            }
        }
        // ha mi emeltünk preflop akkor mindenki más callolt
        if (board.getPlayers().get(0).getPreflopAction().equalsIgnoreCase("raise")) {
            for (int i = 0; i < opponents.size(); i++) {
                opponents.get(i).setPreflopAction("call");
            }
        }


        System.out.println("Ellenfelek száma" + opponentsNumb);

        for (int i = 0; i < opponents.size(); i++) {
            if (opponents.get(i).getPlayerType().equalsIgnoreCase("fish")) {
                opponents.get(i).setRange(Ranges.fish);
            } else if (board.getPotType().equalsIgnoreCase("normal")) {
                if (opponents.get(i).getPreflopAction().equalsIgnoreCase("raise")) {
                    if (opponents.get(i).getPlayerType().equalsIgnoreCase("standard")) {
                        if (opponents.get(i).getPosition() == 0) {
                            opponents.get(i).setRange(Ranges.standardPos1OR);
                            System.out.println("Range set to: StandardPos1OR");
                        }
                        if (opponents.get(i).getPosition() == 1) {
                            opponents.get(i).setRange(Ranges.standardPos2OR);
                            System.out.println("Range set to: StandardPos2OR");
                        }
                        if (opponents.get(i).getPosition() == 2) {
                            opponents.get(i).setRange(Ranges.standardPos3OR);
                            System.out.println("Range set to: StandardPos3OR");
                        }
                        if (opponents.get(i).getPosition() == 3) {
                            opponents.get(i).setRange(Ranges.standardPos4OR);
                            System.out.println("Range set to: StandardPos4OR");
                        }
                        if (opponents.get(i).getPosition() == 4) {
                            opponents.get(i).setRange(Ranges.standardPos5OR);
                            System.out.println("Range set to: StandardPos5OR");
                        }
                        if (opponents.get(i).getPosition() == 5) {
                            opponents.get(i).setRange(Ranges.standardPos6OR);
                            System.out.println("Range set to: StandardPos6OR");
                        }
                        if (opponents.get(i).getPosition() == 6) {
                            opponents.get(i).setRange(Ranges.standardPos7OR);
                            System.out.println("Range set to: StandardPos7OR");
                        }
                        if (opponents.get(i).getPosition() == 7) {
                            opponents.get(i).setRange(Ranges.standardPos8OR);
                            System.out.println("Range set to: StandardPos8OR");
                        }
                        if (opponents.get(i).getPosition() == 8) {
                            opponents.get(i).setRange(Ranges.standardPos9OR);
                            System.out.println("Range set to: StandardPos9OR");
                        }

                    }
                    if (opponents.get(i).getPlayerType().equalsIgnoreCase("tight")) {
                        if (opponents.get(i).getPosition() == 0) {
                            opponents.get(i).setRange(Ranges.tightPos1OR);
                            System.out.println("Range set to: tightPos1OR");
                        }
                        if (opponents.get(i).getPosition() == 1) {
                            opponents.get(i).setRange(Ranges.tightPos2OR);
                            System.out.println("Range set to: tightPos2OR");
                        }
                        if (opponents.get(i).getPosition() == 2) {
                            opponents.get(i).setRange(Ranges.tightPos3OR);
                            System.out.println("Range set to: tightPos3OR");
                        }
                        if (opponents.get(i).getPosition() == 3) {
                            opponents.get(i).setRange(Ranges.tightPos4OR);
                            System.out.println("Range set to: tightPos4OR");
                        }
                        if (opponents.get(i).getPosition() == 4) {
                            opponents.get(i).setRange(Ranges.tightPos5OR);
                            System.out.println("Range set to: tightPos5OR");
                        }
                        if (opponents.get(i).getPosition() == 5) {
                            opponents.get(i).setRange(Ranges.tightPos6OR);
                            System.out.println("Range set to: tightPos6OR");
                        }
                        if (opponents.get(i).getPosition() == 6) {
                            opponents.get(i).setRange(Ranges.tightPos7OR);
                            System.out.println("Range set to: tightPos7OR");
                        }
                        if (opponents.get(i).getPosition() == 7) {
                            opponents.get(i).setRange(Ranges.tightPos8OR);
                            System.out.println("Range set to: tightPos8OR");
                        }
                        if (opponents.get(i).getPosition() == 8) {
                            opponents.get(i).setRange(Ranges.tightPos9OR);
                            System.out.println("Range set to: tightPos9OR");
                        }
                    }

                    if (opponents.get(i).getPlayerType().equalsIgnoreCase("loose")) {
                        if (opponents.get(i).getPosition() == 0) {
                            opponents.get(i).setRange(Ranges.loosePos1OR);
                            System.out.println("Range set to: loosePos1OR");
                        }
                        if (opponents.get(i).getPosition() == 1) {
                            opponents.get(i).setRange(Ranges.loosePos2OR);
                            System.out.println("Range set to: loosePos2OR");
                        }
                        if (opponents.get(i).getPosition() == 2) {
                            opponents.get(i).setRange(Ranges.loosePos3OR);
                            System.out.println("Range set to: loosePos3OR");
                        }
                        if (opponents.get(i).getPosition() == 3) {
                            opponents.get(i).setRange(Ranges.loosePos4OR);
                            System.out.println("Range set to: loosePos4OR");
                        }
                        if (opponents.get(i).getPosition() == 4) {
                            opponents.get(i).setRange(Ranges.loosePos5OR);
                            System.out.println("Range set to: loosePos5OR");
                        }
                        if (opponents.get(i).getPosition() == 5) {
                            opponents.get(i).setRange(Ranges.loosePos6OR);
                            System.out.println("Range set to: loosePos6OR");
                        }
                        if (opponents.get(i).getPosition() == 6) {
                            opponents.get(i).setRange(Ranges.loosePos7OR);
                            System.out.println("Range set to: loosePos7OR");
                        }
                        if (opponents.get(i).getPosition() == 7) {
                            opponents.get(i).setRange(Ranges.loosePos8OR);
                            System.out.println("Range set to: loosePos8OR");
                        }
                        if (opponents.get(i).getPosition() == 8) {
                            opponents.get(i).setRange(Ranges.loosePos9OR);
                            System.out.println("Range set to: loosePos9OR");
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
                            System.out.println("Range set to: VsStandardPos1ORCall");
                        }
                        if (preflopRaiserPos == 1) {
                            opponents.get(i).setRange(Ranges.VsStandardPos2ORCall);
                            System.out.println("Range set to: VsStandardPos2ORCall");
                        }
                        if (preflopRaiserPos == 2) {
                            opponents.get(i).setRange(Ranges.VsStandardPos3ORCall);
                            System.out.println("Range set to: VsStandardPos3ORCall");
                        }
                        if (preflopRaiserPos == 3) {
                            opponents.get(i).setRange(Ranges.VsStandardPos4ORCall);
                            System.out.println("Range set to: VsStandardPos4ORCall");
                        }
                        if (preflopRaiserPos == 4) {
                            opponents.get(i).setRange(Ranges.VsStandardPos5ORCall);
                            System.out.println("Range set to: VsStandardPos5ORCall");
                        }
                        if (preflopRaiserPos == 5) {
                            opponents.get(i).setRange(Ranges.VsStandardPos6ORCall);
                            System.out.println("Range set to: VsStandardPos6ORCall");
                        }
                        if (preflopRaiserPos == 6) {
                            opponents.get(i).setRange(Ranges.VsStandardPos7ORCall);
                            System.out.println("Range set to: VsStandardPos7ORCall");
                        }
                        if (preflopRaiserPos == 7) {
                            opponents.get(i).setRange(Ranges.VsStandardPos8ORCall);
                            System.out.println("Range set to: VsStandardPos8ORCall");
                        }
                        if (preflopRaiserPos == 8) {
                            opponents.get(i).setRange(Ranges.VsStandardPos8ORCall);
                            System.out.println("Range set to: VsStandardPos8ORCall");
                        }
                    }
                    if (opponents.get(i).getPlayerType().equalsIgnoreCase("standard")) {
                        if (preflopRaiserPos <= 3) {
                            opponents.get(i).setRange(Ranges.standardCallEarly);
                            System.out.println("Range set to: standardCallEarly");
                        }
                        if (preflopRaiserPos > 3) {
                            opponents.get(i).setRange(Ranges.standardCallLate);
                            System.out.println("Range set to: standardCallLate");
                        }
                    }
                    if (opponents.get(i).getPlayerType().equalsIgnoreCase("loose")) {
                        if (preflopRaiserPos <= 3) {
                            opponents.get(i).setRange(Ranges.looseCallEarly);
                            System.out.println("Range set to: looseCallEarly");
                        }
                        if (preflopRaiserPos > 3) {
                            opponents.get(i).setRange(Ranges.looseCallLate);
                            System.out.println("Range set to: looseCallLate");
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
                            System.out.println("Range set to: VsStandardPos1OR3B");
                        }
                        if (preflopRaiserPos == 1) {
                            opponents.get(i).setRange(Ranges.VsStandardPos2OR3B);
                            System.out.println("Range set to: VsStandardPos2OR3B");
                        }
                        if (preflopRaiserPos == 2) {
                            opponents.get(i).setRange(Ranges.VsStandardPos3OR3B);
                            System.out.println("Range set to: VsStandardPos3OR3B");
                        }
                        if (preflopRaiserPos == 3) {
                            opponents.get(i).setRange(Ranges.VsStandardPos4OR3B);
                            System.out.println("Range set to: VsStandardPos4OR3B");
                        }
                        if (preflopRaiserPos == 4) {
                            opponents.get(i).setRange(Ranges.VsStandardPos5OR3B);
                            System.out.println("Range set to: VsStandardPos5OR3B");
                        }
                        if (preflopRaiserPos == 5) {
                            opponents.get(i).setRange(Ranges.VsStandardPos6OR3B);
                            System.out.println("Range set to: VsStandardPos6OR3B");
                        }
                        if (preflopRaiserPos == 6) {
                            opponents.get(i).setRange(Ranges.VsStandardPos7OR3B);
                            System.out.println("Range set to: VsStandardPos7OR3B");
                        }
                        if (preflopRaiserPos == 7) {
                            opponents.get(i).setRange(Ranges.VsStandardPos8OR3B);
                            System.out.println("Range set to: VsStandardPos8OR3B");
                        }
                        if (preflopRaiserPos == 8) {
                            opponents.get(i).setRange(Ranges.VsStandardPos7OR3B);
                            System.out.println("Range set to: VsStandardPos7OR3B");
                        }
                    } else if (opponents.get(i).getPlayerType().equalsIgnoreCase("standard")) {
                        if (preflopRaiserPos <= 3) {
                            opponents.get(i).setRange(Ranges.standard3BetEarly);
                            System.out.println("Range set to: standard3BetEarly");
                        }
                        if (preflopRaiserPos > 3) {
                            opponents.get(i).setRange(Ranges.standard3BetCallLate);
                            System.out.println("Range set to: standard3BetCallLate");
                        }
                    } else if (opponents.get(i).getPlayerType().equalsIgnoreCase("loose")) {
                        if (preflopRaiserPos <= 3) {
                            opponents.get(i).setRange(Ranges.loose3BetEarly);
                            System.out.println("Range set to: loose3BetEarly");
                        } else if (preflopRaiserPos > 3) {
                            opponents.get(i).setRange(Ranges.loose3BetLate);
                            System.out.println("Range set to: loose3BetLate");
                        }
                    }
                }
                if (opponents.get(i).getPreflopAction().equalsIgnoreCase("call")) {
                    if (opponents.get(i).getPlayerType().equalsIgnoreCase("tight") || opponents.get(i).getPlayerType().equalsIgnoreCase("standard")) {
                        if (preflopRaiserPos <= 3) {
                            opponents.get(i).setRange(Ranges.standard3BetCallEarly);
                            System.out.println("Range set to: standard3BetCallEarly");
                        }
                        if (preflopRaiserPos > 3) {
                            opponents.get(i).setRange(Ranges.standard3BetCallLate);
                            System.out.println("Range set to: standard3BetCallLate");
                        }
                    } else if (opponents.get(i).getPlayerType().equalsIgnoreCase("loose")) {
                        if (preflopRaiserPos <= 3) {
                            opponents.get(i).setRange(Ranges.loose3BetCallEarly);
                            System.out.println("Range set to: loose3BetCallEarly");
                        } else if (preflopRaiserPos > 3) {
                            opponents.get(i).setRange(Ranges.loose3BetCallLate);
                            System.out.println("Range set to: loose3BetCallLate");
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
                            System.out.println("Range set to: Vs3BetEarly4Bet");
                        } else if (opponents.get(i).getPosition() > 3) {
                            opponents.get(i).setRange(Ranges.Vs3BetLate4Bet);
                            System.out.println("Range set to: Vs3BetLate4bet");
                        }
                    } else if (opponents.get(i).getPlayerType().equalsIgnoreCase("standard")) {
                        opponents.get(i).setRange(Ranges.standard4Bet);
                        System.out.println("Range set to: standard4Bet");
                    } else if (opponents.get(i).getPlayerType().equalsIgnoreCase("loose")) {
                        opponents.get(i).setRange(Ranges.loose4Bet);
                        System.out.println("Range set to: loose4Bet");
                    }
                }
                if (opponents.get(i).getPreflopAction().equalsIgnoreCase("call")) {
                    if (opponents.get(i).getPlayerType().equalsIgnoreCase("tight") || opponents.get(i).getPlayerType().equalsIgnoreCase("standard")) {
                        opponents.get(i).setRange(Ranges.standard3BetEarly);
                        System.out.println("Range set to: standard3BetEarly");
                    } else if (opponents.get(i).getPlayerType().equalsIgnoreCase("loose")) {
                        opponents.get(i).setRange(Ranges.standard3BetLate);
                        System.out.println("Range set to: standard3BetLate");
                    }
                }
            }
        }
        PlayerPlayed player = (PlayerPlayed) board.getPlayers().get(0);

        System.out.println("Ellenfelek száma: " + opponents.size());
        player.equity(opponents, board.getCards());
        player.calculatePotOdds(board);

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
        // ha mi vagyunk a preflop agressor és még nem cselekedtünk a körben vagy checkeltünk
        if (player.getPreflopAction().equalsIgnoreCase("raise") && (player.getFlopAction().equalsIgnoreCase("No action")) || player.getFlopAction().equalsIgnoreCase("check")) {
            // ha nem volt még bet
            if (!betAction) {
                // ha gyenge párunk van
                if (showdownStrenght > 3000000 && showdownStrenght < 3045000) {
                    board.getPlayers().get(0).setFlopAction("check");
                    System.out.println("Flop: check");
                    Actions.callOrCheck(rect);
                    return;
                } else if (player.getEquity() > 0.3) {
                    if (board.isWetBoard()) {
                        board.getPlayers().get(0).setFlopAction("bet");
                        System.out.println("Flop: bet Pot");
                        Actions.raisePot(rect);
                        return;
                    } else {
                        board.getPlayers().get(0).setFlopAction("bet");
                        System.out.println("Flop: bet 1/2 Pot");
                        Actions.raiseHalfPot(rect);
                        return;
                    }
                } else if (player.getAvailableAction().equalsIgnoreCase("check")) {
                    board.getPlayers().get(0).setFlopAction("check");
                    System.out.println("Flop: Check");
                    Actions.callOrCheck(rect);
                    return;
                } else {
                    board.getPlayers().get(0).setFlopAction("fold");
                    System.out.println("Flop: Fold");
                    Actions.callOrCheck(rect);
                    return;
                }
            }
            if (betAction) {
                player.calculatePotOdds(board);
                if (player.getEquity() > 0.75) {
                    board.getPlayers().get(0).setFlopAction("raise");
                    System.out.println("Flop: Raise");
                    Actions.raisePot(rect);
                    return;
                }
                if (betterAgression >= 2.0) {
                    if (player.getEquity() > (player.getPotOdds() + 0.10)) {
                        board.getPlayers().get(0).setFlopAction("call");
                        System.out.println("Flop: Call");
                        Actions.callOrCheck(rect);
                        return;
                    } else {
                        board.getPlayers().get(0).setFlopAction("fold");
                        System.out.println("Flop: Fold");
                        Actions.fold(rect);
                        return;
                    }
                }
                if (betterAgression < 2.0) {
                    if (player.getEquity() > (player.getPotOdds() + 0.25)) {
                        board.getPlayers().get(0).setFlopAction("call");
                        System.out.println("Flop: Call");
                        Actions.callOrCheck(rect);
                        return;
                    } else {
                        board.getPlayers().get(0).setFlopAction("fold");
                        System.out.println("Flop: fold");
                        Actions.fold(rect);
                        return;
                    }
                }
            }
        }
        // ha emeltek ránk
        if ((player.getFlopAction().equalsIgnoreCase("bet") || player.getFlopAction().equalsIgnoreCase("raise")) && betAction) {
            if (betterAgression >= 2.0) {
                if (player.getEquity() > 0.8) {
                    board.getPlayers().get(0).setFlopAction("raise");
                    System.out.println("Flop: ReRaise");
                    Actions.raisePot(rect);
                    return;
                } else if (player.getEquity() > (player.getPotOdds() + 0.2)) {
                    board.getPlayers().get(0).setFlopAction("call");
                    System.out.println("Flop: Call Raise");
                    Actions.callOrCheck(rect);
                    return;
                } else {
                    board.getPlayers().get(0).setFlopAction("fold");
                    System.out.println("Flop: Fold to raise");
                    Actions.fold(rect);
                    return;
                }
            } else if (betterAgression < 2.0) {
                if (player.getEquity() > 0.9) {
                    board.getPlayers().get(0).setFlopAction("raise");
                    System.out.println("Flop: ReRaise");
                    Actions.raisePot(rect);
                    return;
                } else if (player.getEquity() > (player.getPotOdds() + 0.3)) {
                    board.getPlayers().get(0).setFlopAction("call");
                    System.out.println("Flop: Call raise");
                    Actions.callOrCheck(rect);
                    return;
                } else {
                    board.getPlayers().get(0).setFlopAction("fold");
                    System.out.println("Flop: Fold to raise");
                    Actions.fold(rect);
                    return;
                }
            }
        }
        // ha nem mi vagyunk a preflop agressor
        if (player.getPreflopAction().equalsIgnoreCase("call") || player.getFlopAction().equalsIgnoreCase("No action") || player.getPreflopAction().equalsIgnoreCase("check")) {
            if (!betAction) {
                // donk bet
                if (player.getEquity() > 0.35) {
                    Random rand = new Random();
                    if (rand.nextInt(100) < 20) {
                        if (board.isWetBoard()) {
                            board.getPlayers().get(0).setFlopAction("bet");
                            System.out.println("Flop: Bet Pot");
                            Actions.raisePot(rect);
                            return;
                        } else {
                            board.getPlayers().get(0).setFlopAction("bet");
                            System.out.println("Flop: Bet 1/2 Pot");
                            Actions.raiseHalfPot(rect);
                            return;
                        }
                    } else {
                        board.getPlayers().get(0).setFlopAction("check");
                        System.out.println("Flop: Check");
                        Actions.callOrCheck(rect);
                        return;
                    }
                } else {
                    board.getPlayers().get(0).setFlopAction("check");
                    System.out.println("Flop: Check");
                    Actions.callOrCheck(rect);
                    return;
                }
            } else {
                if (betterAgression >= 2) {
                    if (player.getEquity() > 0.8) {
                        board.getPlayers().get(0).setFlopAction("raise");
                        System.out.println("Flop: Reraise");
                        Actions.raisePot(rect);
                        return;
                    } else if (player.getEquity() > (player.getPotOdds() + 0.10)) {
                        board.getPlayers().get(0).setFlopAction("call");
                        System.out.println("Flop: Call");
                        Actions.callOrCheck(rect);
                        return;
                    } else {
                        board.getPlayers().get(0).setFlopAction("fold");
                        System.out.println("Flop: Fold");
                        Actions.fold(rect);
                        return;
                    }
                } else if (betterAgression < 2) {
                    if (player.getEquity() > 0.85) {
                        board.getPlayers().get(0).setFlopAction("raise");
                        System.out.println("Flop: Reraise");
                        Actions.raisePot(rect);
                        return;
                    } else if (player.getEquity() > (player.getPotOdds() + 0.2)) {
                        board.getPlayers().get(0).setFlopAction("call");
                        System.out.println("Flop: Call");
                        Actions.callOrCheck(rect);
                        return;
                    } else {
                        board.getPlayers().get(0).setFlopAction("fold");
                        System.out.println("Flop fold");
                        Actions.fold(rect);
                        return;
                    }
                }
            }
        }

    }

    public static void turnAction(Board board, Rectangle rect)throws AWTException {
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
        player.calculatePotOdds(board);

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

        if(player.getTurnAction().equalsIgnoreCase("bet") || player.getTurnAction().equalsIgnoreCase("raise")){
            betAction = true;
        }

        for (int i = 0; i < opponents.size(); i++) {
            if (opponents.get(i).getTurnAction().equalsIgnoreCase("bet") || opponents.get(i).getTurnAction().equalsIgnoreCase("raise")) {
                betAction = true;
                betterAgression = opponents.get(i).getAggression();
            }
        }
        // ha mi vagyunk a flop agressor és még nem cselekedtünk a körben
        if ((player.getFlopAction().equalsIgnoreCase("raise") || player.getFlopAction().equalsIgnoreCase("bet")) && (player.getTurnAction().equalsIgnoreCase("No action")) || player.getTurnAction().equalsIgnoreCase("check")) {
            // ha nem volt még bet
            if (!betAction) {
                if (player.getEquity() > 0.35) {
                    if (board.isWetBoard()) {
                        board.getPlayers().get(0).setTurnAction("bet");
                        System.out.println("Turn: bet Pot");
                        Actions.raisePot(rect);
                        return;
                    } else {
                        board.getPlayers().get(0).setTurnAction("bet");
                        System.out.println("Turn: bet 1/2 Pot");
                        Actions.raiseHalfPot(rect);
                        return;
                    }
                } else {
                    board.getPlayers().get(0).setTurnAction("check");
                    System.out.println("Turn: Check");
                    Actions.callOrCheck(rect);
                    return;
                }
            }
            if (betAction) {
                player.calculatePotOdds(board);
                if (player.getEquity() > 0.80) {
                    board.getPlayers().get(0).setTurnAction("raise");
                    System.out.println("Turn: Raise");
                    Actions.raisePot(rect);
                    return;
                }
                if (betterAgression >= 2.0) {
                    if (player.getEquity() > (player.getPotOdds() + 0.15)) {
                        board.getPlayers().get(0).setTurnAction("call");
                        System.out.println("Turn: Call");
                        Actions.callOrCheck(rect);
                        return;
                    } else {
                        board.getPlayers().get(0).setTurnAction("fold");
                        System.out.println("Turn: Fold");
                        Actions.fold(rect);
                        return;
                    }
                }
                if (betterAgression < 2.0) {
                    if (player.getEquity() > (player.getPotOdds() + 0.3)) {
                        board.getPlayers().get(0).setTurnAction("call");
                        System.out.println("Turn: Call");
                        Actions.callOrCheck(rect);
                        return;
                    } else {
                        board.getPlayers().get(0).setTurnAction("fold");
                        System.out.println("Turn: fold");
                        Actions.fold(rect);
                        return;
                    }
                }
            }
        }
        // ha emeltek ránk
        if (player.getTurnAction().equalsIgnoreCase("bet") || player.getTurnAction().equalsIgnoreCase("raise") || player.getTurnAction().equalsIgnoreCase("call")) {
            if (betterAgression >= 2.0) {
                if (player.getEquity() > 0.85) {
                    board.getPlayers().get(0).setTurnAction("raise");
                    System.out.println("Turn: ReRaise");
                    Actions.raisePot(rect);
                    return;
                } else if (player.getEquity() > (player.getPotOdds() + 0.25)) {
                    board.getPlayers().get(0).setTurnAction("call");
                    System.out.println("Turn: Call Raise");
                    Actions.callOrCheck(rect);
                    return;
                } else {
                    board.getPlayers().get(0).setTurnAction("fold");
                    System.out.println("Turn: Fold to raise");
                    Actions.fold(rect);
                    return;
                }
            } else if (betterAgression < 2.0) {
                if (player.getEquity() > 0.9) {
                    board.getPlayers().get(0).setTurnAction("raise");
                    System.out.println("Turn: ReRaise");
                    Actions.raisePot(rect);
                    return;
                } else if (player.getEquity() > (player.getPotOdds() + 0.3)) {
                    board.getPlayers().get(0).setTurnAction("call");
                    System.out.println("Turn: Call raise");
                    Actions.callOrCheck(rect);
                    return;
                } else {
                    board.getPlayers().get(0).setTurnAction("fold");
                    System.out.println("Turn: Fold to raise");
                    Actions.fold(rect);
                    return;
                }
            }
        }
        // ha nem mi vagyunk a preflop agressor és nem raktunk be tétet
        if ((player.getFlopAction().equalsIgnoreCase("call") || player.getFlopAction().equalsIgnoreCase("check")) && (player.getTurnAction().equalsIgnoreCase("No action") || player.getTurnAction().equalsIgnoreCase("check"))) {
            if (!betAction && flopBetHappened) {
                // donk bet
                if (player.getEquity() > 0.55) {
                    Random rand = new Random();
                    if (rand.nextInt(100) < 20) {
                        if (board.isWetBoard()) {
                            board.getPlayers().get(0).setTurnAction("bet");
                            System.out.println("Turn: Bet Pot");
                            Actions.raisePot(rect);
                            return;
                        } else {
                            board.getPlayers().get(0).setTurnAction("bet");
                            System.out.println("Turn: Bet 1/2 Pot");
                            Actions.raiseHalfPot(rect);
                            return;
                        }
                    } else {
                        board.getPlayers().get(0).setTurnAction("check");
                        System.out.println("Turn: Check");
                        Actions.callOrCheck(rect);
                        return;
                    }
                }else{
                    board.getPlayers().get(0).setTurnAction("check");
                    System.out.println("Turn: Check");
                    Actions.callOrCheck(rect);
                    return;
                }
            } else if (!betAction && !flopBetHappened) {
                if (player.getEquity() > 0.4) {
                    if (board.isWetBoard()) {
                        board.getPlayers().get(0).setTurnAction("bet");
                        System.out.println("Turn: Bet Pot");
                        Actions.raisePot(rect);
                        return;
                    } else {
                        board.getPlayers().get(0).setTurnAction("bet");
                        System.out.println("Turn: Bet 1/2 Pot");
                        Actions.raiseHalfPot(rect);
                        return;
                    }
                } else {
                    board.getPlayers().get(0).setTurnAction("check");
                    System.out.println("Turn: Check");
                    Actions.callOrCheck(rect);
                    return;
                }
            } else if (betAction) {
                if (betterAgression >= 2) {
                    if (player.getEquity() > 0.8) {
                        board.getPlayers().get(0).setTurnAction("raise");
                        System.out.println("Turn: Reraise");
                        Actions.raisePot(rect);
                        return;
                    } else if (player.getEquity() > (player.getPotOdds() + 0.15)) {
                        board.getPlayers().get(0).setTurnAction("call");
                        System.out.println("Turn: Call");
                        Actions.callOrCheck(rect);
                        return;
                    } else {
                        board.getPlayers().get(0).setTurnAction("fold");
                        System.out.println("Turn: Fold");
                        Actions.fold(rect);
                        return;
                    }
                } else if (betterAgression < 2) {
                    if (player.getEquity() > 0.85) {
                        board.getPlayers().get(0).setTurnAction("raise");
                        System.out.println("Turn: Reraise");
                        Actions.raisePot(rect);
                        return;
                    } else if (player.getEquity() > (player.getPotOdds() + 0.25)) {
                        board.getPlayers().get(0).setTurnAction("call");
                        System.out.println("Turn: Call");
                        Actions.callOrCheck(rect);
                        return;
                    } else {
                        board.getPlayers().get(0).setTurnAction("fold");
                        System.out.println("Turn: Fold");
                        Actions.fold(rect);
                        return;
                    }
                }
            }
        }
        if ((player.getFlopAction().equalsIgnoreCase("call") || player.getFlopAction().equalsIgnoreCase("check")) && (player.getTurnAction().equalsIgnoreCase("call") || player.getTurnAction().equalsIgnoreCase("bet") || player.getTurnAction().equalsIgnoreCase("raise"))) {
            if (betterAgression >= 2) {
                if (player.getEquity() > 0.8) {
                    board.getPlayers().get(0).setTurnAction("raise");
                    System.out.println("Turn: Reraise");
                    Actions.raisePot(rect);
                    return;
                } else if (player.getEquity() > (player.getPotOdds() + 0.15)) {
                    board.getPlayers().get(0).setTurnAction("call");
                    System.out.println("Turn: Call");
                    Actions.callOrCheck(rect);
                    return;
                } else {
                    board.getPlayers().get(0).setTurnAction("fold");
                    System.out.println("Turn: Fold");
                    Actions.fold(rect);
                    return;
                }
            } else {
                if (player.getEquity() > 0.85) {
                    board.getPlayers().get(0).setTurnAction("raise");
                    System.out.println("Turn: Reraise");
                    Actions.raisePot(rect);
                    return;
                } else if (player.getEquity() > (player.getPotOdds() + 0.2)) {
                    board.getPlayers().get(0).setTurnAction("call");
                    System.out.println("Turn: Call");
                    Actions.callOrCheck(rect);
                    return;
                } else {
                    board.getPlayers().get(0).setTurnAction("fold");
                    System.out.println("Turn: Fold");
                    Actions.fold(rect);
                    return;
                }
            }
        }
    }

    public static void riverAction(Board board, Rectangle rect)throws AWTException {
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
        player.calculatePotOdds(board);
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
            if (opponents.get(i).getTurnAction().equalsIgnoreCase("bet") || opponents.get(i).getTurnAction().equalsIgnoreCase("raise")) {
                betAction = true;
                betterAgression = opponents.get(i).getAggression();
            }
        }

        // ha mi vagyunk a turn agresszor és nem cselekedtünk
        if ((player.getTurnAction().equalsIgnoreCase("raise") || player.getTurnAction().equalsIgnoreCase("bet")) && (player.getRiverAction().equalsIgnoreCase("No action")) || player.getRiverAction().equalsIgnoreCase("check")) {
            // ha nem volt még bet
            if (!betAction) {
                if (player.getEquity() > 0.65) {
                    if (board.isWetBoard()) {
                        board.getPlayers().get(0).setRiverAction("bet");
                        System.out.println("River: bet Pot");
                        Actions.raisePot(rect);
                        return;
                    } else {
                        board.getPlayers().get(0).setRiverAction("bet");
                        System.out.println("River: bet 1/2 Pot");
                        Actions.raiseHalfPot(rect);
                        return;
                    }
                }else{
                    board.getPlayers().get(0).setRiverAction("check");
                    System.out.println("River: check");
                    Actions.callOrCheck(rect);
                    return;
                }
            }
            if (betAction) {
                player.calculatePotOdds(board);
                if (player.getEquity() > 0.85) {
                    board.getPlayers().get(0).setRiverAction("raise");
                    System.out.println("River: Raise");
                    Actions.raisePot(rect);
                    return;
                }
                if (betterAgression >= 2.0) {
                    if (player.getEquity() > (player.getPotOdds() + 0.15)) {
                        board.getPlayers().get(0).setRiverAction("call");
                        System.out.println("River: Call");
                        Actions.callOrCheck(rect);
                        return;
                    } else {
                        board.getPlayers().get(0).setRiverAction("fold");
                        System.out.println("River: Fold");
                        Actions.fold(rect);
                        return;
                    }
                }
                if (betterAgression < 2.0) {
                    if (player.getEquity() > (player.getPotOdds() + 0.3)) {
                        board.getPlayers().get(0).setRiverAction("call");
                        System.out.println("River: Call");
                        Actions.callOrCheck(rect);
                        return;
                    } else {
                        board.getPlayers().get(0).setRiverAction("fold");
                        System.out.println("River: fold");
                        Actions.fold(rect);
                        return;
                    }
                }
            }
        }
        // ha emeltek ránk
        if (player.getRiverAction().equalsIgnoreCase("bet") || player.getRiverAction().equalsIgnoreCase("raise") || player.getRiverAction().equalsIgnoreCase("call")) {
            if (betterAgression >= 2.0) {
                if (player.getEquity() > 0.9) {
                    board.getPlayers().get(0).setRiverAction("raise");
                    System.out.println("River: ReRaise");
                    Actions.raisePot(rect);
                    return;
                } else if (player.getEquity() > (player.getPotOdds() + 0.3)) {
                    board.getPlayers().get(0).setRiverAction("call");
                    System.out.println("River: Call Raise");
                    Actions.callOrCheck(rect);
                    return;
                } else {
                    board.getPlayers().get(0).setRiverAction("fold");
                    System.out.println("River: Fold to raise");
                    Actions.fold(rect);
                    return;
                }
            } else if (betterAgression < 2.0) {
                if (player.getEquity() > 0.9) {
                    board.getPlayers().get(0).setRiverAction("raise");
                    System.out.println("River: ReRaise");
                    Actions.raisePot(rect);
                    return;
                } else if (player.getEquity() > (player.getPotOdds() + 0.3)) {
                    board.getPlayers().get(0).setRiverAction("call");
                    System.out.println("River: Call raise");
                    Actions.callOrCheck(rect);
                    return;
                } else {
                    board.getPlayers().get(0).setRiverAction("fold");
                    System.out.println("River: Fold to raise");
                    Actions.fold(rect);
                    return;
                }
            }
        }

        // ha nem mi vagyunk a turn agressor
        if (player.getTurnAction().equalsIgnoreCase("call") || player.getTurnAction().equalsIgnoreCase("No action") || player.getTurnAction().equalsIgnoreCase("check")) {
            if (!betAction && turnBetHappened) {
                // donk bet
                if (player.getEquity() > 0.65) {
                    Random rand = new Random();
                    if (rand.nextInt(100) < 40) {
                        if (board.isWetBoard()) {
                            board.getPlayers().get(0).setRiverAction("bet");
                            System.out.println("River: Bet Pot");
                            Actions.raisePot(rect);
                            return;
                        } else {
                            board.getPlayers().get(0).setRiverAction("bet");
                            System.out.println("River: Bet 1/2 Pot");
                            Actions.raiseHalfPot(rect);
                            return;
                        }
                    } else {
                        board.getPlayers().get(0).setRiverAction("check");
                        System.out.println("River: Check");
                        Actions.callOrCheck(rect);
                        return;
                    }
                } else {
                    board.getPlayers().get(0).setRiverAction("check");
                    System.out.println("River: Check");
                    Actions.callOrCheck(rect);
                    return;
                }
            } else if (!betAction && !turnBetHappened) {
                if (player.getEquity() > 0.4) {
                    if (board.isWetBoard()) {
                        board.getPlayers().get(0).setRiverAction("bet");
                        System.out.println("River: Bet Pot");
                        Actions.raisePot(rect);
                        return;
                    } else {
                        board.getPlayers().get(0).setRiverAction("bet");
                        System.out.println("Turn: Bet 1/2 Pot");
                        Actions.raiseHalfPot(rect);
                        return;
                    }
                } else {
                    board.getPlayers().get(0).setRiverAction("check");
                    System.out.println("Turn: Check");
                    Actions.callOrCheck(rect);
                    return;
                }
            } else if (betAction) {
                if (betterAgression >= 2) {
                    if (player.getEquity() > 0.9) {
                        board.getPlayers().get(0).setRiverAction("raise");
                        System.out.println("River: Reraise");
                        Actions.raisePot(rect);
                        return;
                    } else if (player.getEquity() > (player.getPotOdds() + 0.05)) {
                        board.getPlayers().get(0).setRiverAction("call");
                        System.out.println("River: Call");
                        Actions.callOrCheck(rect);
                        return;
                    } else {
                        board.getPlayers().get(0).setRiverAction("fold");
                        System.out.println("River: Fold");
                        Actions.fold(rect);
                        return;
                    }
                } else if (betterAgression < 2) {
                    if (player.getEquity() > 0.85) {
                        board.getPlayers().get(0).setRiverAction("raise");
                        System.out.println("River: Reraise");
                        Actions.raisePot(rect);
                        return;
                    } else if (player.getEquity() > (player.getPotOdds() + 0.1)) {
                        board.getPlayers().get(0).setRiverAction("call");
                        System.out.println("River: Call");
                        Actions.callOrCheck(rect);
                        return;
                    } else {
                        board.getPlayers().get(0).setRiverAction("fold");
                        System.out.println("Turn: Fold");
                        Actions.fold(rect);
                        return;
                    }
                }
            }
        }
    }
}
