package com.company;

import com.sun.jna.platform.WindowUtils;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import net.sourceforge.tess4j.ITessAPI;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

public final class ScreenReader {

    private static boolean hadNameInit = false;


    public static void readScreen(Board board) throws Exception {
        Robot robot = new Robot();
        boolean[] hasCards = new boolean[]{true, true, true, true, true, true, true, true};
        String PlayerButton1 = "";
        String PlayerButton2 = "";
        String PlayerButton3 = "";
        BufferedImage[] playerNames = new BufferedImage[9];
        BufferedImage[] playerMoney = new BufferedImage[9];
        BufferedImage[] playerAction = new BufferedImage[8];


        //Poker Genius ablakának megtalálása
        Rectangle rect = findWindow();


        // screenshot az egész asztalról
        BufferedImage screencapture = robot.createScreenCapture(
                new Rectangle(rect));

        boardVisibilityCheck(screencapture);

        if (Gui.isRunIt()) {


            //aktív játékosok keresése

            hasCards = hasCardsFunction(Constants.playersCardPosX, Constants.playersCardPosY, hasCards, screencapture);
            assignCard(board, hasCards);

            if (isActive(screencapture)) {
                canRaise(screencapture, board);

                //játék állapotának lekérdezése

                board.setGameState(gameState(screencapture));


                //játékosok adatainak begyűjtése

                for (int i = 0; i <= Constants.playersCardPosX.length; i++) {


                    //név

                    playerNames[i] = robot.createScreenCapture(
                            new Rectangle(rect.x + Constants.playersPosX[i] + Constants.posXhelper, rect.y + Constants.playersPosY[i] + Constants.posYname, Constants.boxWidth, Constants.nameHeight));

                    //pénz

                    BufferedImage money = robot.createScreenCapture(
                            new Rectangle(rect.x + Constants.playersPosX[i] + Constants.posXhelper, rect.y + Constants.playersPosY[i] + Constants.posYmoney, Constants.boxWidth, Constants.moneyHeight)); // 385, 610, 40, 18

                    playerMoney[i] = resizeImage(money, 2);
                    if (i != 0) {
                        BufferedImage action = robot.createScreenCapture(
                                new Rectangle(rect.x + Constants.playersPosX[i] + Constants.posXhelper, rect.y + Constants.playersPosY[i] + Constants.posYaction, Constants.boxWidth, Constants.actionHeight));

                        playerAction[i - 1] = resizeImage(action, 2);
                    }

                }


                //első lapunk
                BufferedImage screencapturePlayer1Card1 = robot.createScreenCapture(
                        new Rectangle(rect.x + Constants.holeCard1X, rect.y + Constants.holeCard1Y, Constants.CardWidth, Constants.CardHeight)); // 387, 598, 30, 12

                int holeCardColor1 = screencapture.getRGB(Constants.holeCard1X + Constants.CardhelperX, Constants.holeCard1Y + Constants.CardhelperY);
                String holeCardColor1name = "";

                if (holeCardColor1 == Constants.clubsRGB) {
                    holeCardColor1name = "c";
                } else if (holeCardColor1 == Constants.spadesRGB) {
                    holeCardColor1name = "s";
                } else if (holeCardColor1 == Constants.heartsRGB) {
                    holeCardColor1name = "h";
                } else if (holeCardColor1 == Constants.diamondsRGB) {
                    holeCardColor1name = "d";
                }


                //második lapunk
                BufferedImage screencapturePlayer1Card2 = robot.createScreenCapture(
                        new Rectangle(rect.x + Constants.holeCard2X, rect.y + Constants.holeCard2Y, Constants.CardWidth, Constants.CardHeight)); // 387, 598, 30, 12

                int holeCardColor2 = screencapture.getRGB(Constants.holeCard2X + Constants.CardhelperX, Constants.holeCard2Y + Constants.CardhelperY);
                String holeCardColor2name = "";

                if (holeCardColor2 == Constants.clubsRGB) {
                    holeCardColor2name = "c";
                } else if (holeCardColor2 == Constants.spadesRGB) {
                    holeCardColor2name = "s";
                } else if (holeCardColor2 == Constants.heartsRGB) {
                    holeCardColor2name = "h";
                } else if (holeCardColor2 == Constants.diamondsRGB) {
                    holeCardColor2name = "d";
                }

                // Board lapjai

                //első 3 lap az asztalon leolvasása, csak ha lent vannak

                String boardCardColor1name = "";
                String boardCardColor2name = "";
                String boardCardColor3name = "";
                String boardCardColor4name = "";
                String boardCardColor5name = "";


                BufferedImage screencaptureBoardCard1 = robot.createScreenCapture(
                        new Rectangle(rect.x + Constants.boardCarsdX[0], rect.y + Constants.boardCardsY, Constants.CardWidth, Constants.CardHeight));

                BufferedImage screencaptureBoardCard2 = robot.createScreenCapture(
                        new Rectangle(rect.x + Constants.boardCarsdX[1], rect.y + Constants.boardCardsY, Constants.CardWidth, Constants.CardHeight));

                BufferedImage screencaptureBoardCard3 = robot.createScreenCapture(
                        new Rectangle(rect.x + Constants.boardCarsdX[2], rect.y + Constants.boardCardsY, Constants.CardWidth, Constants.CardHeight));

                BufferedImage screencaptureBoardCard4 = robot.createScreenCapture(
                        new Rectangle(rect.x + Constants.boardCarsdX[3], rect.y + Constants.boardCardsY, Constants.CardWidth, Constants.CardHeight));

                BufferedImage screencaptureBoardCard5 = robot.createScreenCapture(
                        new Rectangle(rect.x + Constants.boardCarsdX[4], rect.y + Constants.boardCardsY, Constants.CardWidth, Constants.CardHeight));

                if (board.getGameState().equalsIgnoreCase("flop") || board.getGameState().equalsIgnoreCase("turn") || board.getGameState().equalsIgnoreCase("river")) {


                    int boardCardColor1 = screencapture.getRGB(Constants.boardCarsdX[0] + Constants.CardhelperX, Constants.boardCardsY + Constants.CardhelperY);

                    if (boardCardColor1 == Constants.clubsRGB) {
                        boardCardColor1name = "c";
                    } else if (boardCardColor1 == Constants.spadesRGB) {
                        boardCardColor1name = "s";
                    } else if (boardCardColor1 == Constants.heartsRGB) {
                        boardCardColor1name = "h";
                    } else if (boardCardColor1 == Constants.diamondsRGB) {
                        boardCardColor1name = "d";
                    }


                    int boardCardColor2 = screencapture.getRGB(Constants.boardCarsdX[1] + Constants.CardhelperX, Constants.boardCardsY + Constants.CardhelperY);


                    if (boardCardColor2 == Constants.clubsRGB) {
                        boardCardColor2name = "c";
                    } else if (boardCardColor2 == Constants.spadesRGB) {
                        boardCardColor2name = "s";
                    } else if (boardCardColor2 == Constants.heartsRGB) {
                        boardCardColor2name = "h";
                    } else if (boardCardColor2 == Constants.diamondsRGB) {
                        boardCardColor2name = "d";
                    }

                    int boardCardColor3 = screencapture.getRGB(Constants.boardCarsdX[2] + Constants.CardhelperX, Constants.boardCardsY + Constants.CardhelperY);

                    if (boardCardColor3 == Constants.clubsRGB) {
                        boardCardColor3name = "c";
                    } else if (boardCardColor3 == Constants.spadesRGB) {
                        boardCardColor3name = "s";
                    } else if (boardCardColor3 == Constants.heartsRGB) {
                        boardCardColor3name = "h";
                    } else if (boardCardColor3 == Constants.diamondsRGB) {
                        boardCardColor3name = "d";
                    }

                }

                // 4. lap leolvasása turn és river esetén

                if (board.getGameState().equalsIgnoreCase("turn") || board.getGameState().equalsIgnoreCase("river")) {

                    int boardCardColor4 = screencapture.getRGB(Constants.boardCarsdX[3] + Constants.CardhelperX, Constants.boardCardsY + Constants.CardhelperY);


                    if (boardCardColor4 == Constants.clubsRGB) {
                        boardCardColor4name = "c";
                    } else if (boardCardColor4 == Constants.spadesRGB) {
                        boardCardColor4name = "s";
                    } else if (boardCardColor4 == Constants.heartsRGB) {
                        boardCardColor4name = "h";
                    } else if (boardCardColor4 == Constants.diamondsRGB) {
                        boardCardColor4name = "d";
                    }

                }

                // 5. lap leolvasása river esetén

                if (board.getGameState().equalsIgnoreCase("river")) {


                    int boardCardColor5 = screencapture.getRGB(Constants.boardCarsdX[4] + Constants.CardhelperX, Constants.boardCardsY + Constants.CardhelperY);

                    if (boardCardColor5 == Constants.clubsRGB) {
                        boardCardColor5name = "c";
                    } else if (boardCardColor5 == Constants.spadesRGB) {
                        boardCardColor5name = "s";
                    } else if (boardCardColor5 == Constants.heartsRGB) {
                        boardCardColor5name = "h";
                    } else if (boardCardColor5 == Constants.diamondsRGB) {
                        boardCardColor5name = "d";
                    }

                }


                for (int i = 0; i < 9; i++) {

                    if (Constants.buttonRGB == (screencapture.getRGB(Constants.buttonPosX[i], Constants.buttonPosY[i]))) {
                        if (i != board.getButtonPosition()) {
                            board.setButtonPosition(i);
                            System.out.println("New Hand");

                            //új leosztás ha a btn helyzete megváltozott
                            board.removeCards();


                            for (int j = 0; j < board.getPlayers().size(); j++) {
                                board.getPlayers().get(j).setAction("No action", board);
                                board.getPlayers().get(j).setPreflopAction("No action");
                                board.getPlayers().get(j).setFlopAction("No action");
                                board.getPlayers().get(j).setTurnAction("No action");
                                board.getPlayers().get(j).setRiverAction("No action");
                                if (j != 0) {
                                    PlayerAI opponentPlayer = (PlayerAI) board.getPlayers().get(j);
                                    opponentPlayer.setHandsPlayed(opponentPlayer.getHandsPlayed() + 1);
                                }
                            }
                            board.setPotType();
                        }
                    }
                }


                // pot összegének megállapítása

                BufferedImage screenCapturePot = robot.createScreenCapture(
                        new Rectangle(rect.x + Constants.potTextX, rect.y + Constants.potTextY, Constants.potWidth, Constants.potHeight)); // 385, 610, 40, 18

                BufferedImage screenCapturePotResized = resizeImage(screenCapturePot, 2);

                // Játékos gombjai

                BufferedImage screenCapturePlayerButton1 = robot.createScreenCapture(
                        new Rectangle(rect.x + Constants.playerButton1PosX, rect.y + Constants.playerButtonsPosY, Constants.buttonWidth, Constants.buttonHeigth)); // 385, 610, 40, 18


                BufferedImage screenCapturePlayerButton2 = robot.createScreenCapture(
                        new Rectangle(rect.x + Constants.playerButton2PosX, rect.y + Constants.playerButtonsPosY, Constants.buttonWidth, Constants.buttonHeigth)); // 385, 610, 40, 18


                BufferedImage screenCapturePlayerButton3 = robot.createScreenCapture(
                        new Rectangle(rect.x + Constants.playerButton3PosX, rect.y + Constants.playerButtonsPosY, Constants.buttonWidth, Constants.buttonHeigth)); // 385, 610, 40, 18


                File file = new File("D:/screencapture.jpg");
                ImageIO.write(screencapture, "jpg", file);
/*
              //Save as PNG
                File file = new File("D:/screencapture.jpg");
                ImageIO.write(screencapture, "jpg", file);
                File file1 = new File("D:/screencapture1.png");
                ImageIO.write(playerNames[0], "png", file1);
                File file2 = new File("D:/screencapture2.png");
                ImageIO.write(playerMoney[0], "png", file2);
                File file3 = new File("D:/screencapture3.png");
                ImageIO.write(screencapturePlayer1Card1, "png", file3);
                File file4 = new File("D:/screencapture4.png");
                ImageIO.write(screencapturePlayer1Card2, "png", file4);
                File file5 = new File("D:/screencapturePlayer2name.png");
                ImageIO.write(playerNames[1], "png", file5);
                File file6 = new File("D:/screencapturePlayer2Money.png");
                ImageIO.write(playerMoney[1], "png", file6);
                File file7 = new File("D:/screencapturePlayer3name.png");
                ImageIO.write(playerNames[2], "png", file7);
                File file8 = new File("D:/screencapturePlayer3money.png");
                ImageIO.write(playerMoney[2], "png", file8);
                File file9 = new File("D:/screencapturePlayer4name.png");
                ImageIO.write(playerNames[3], "png", file9);
                File file10 = new File("D:/screencapturePlayer4money.png");
                ImageIO.write(playerMoney[3], "png", file10);
                File file11 = new File("D:/screencapturePlayer5name.png");
                ImageIO.write(playerNames[4], "png", file11);
                File file12 = new File("D:/screencapturePlayer5money.png");
                ImageIO.write(playerMoney[4], "png", file12);
                File file13 = new File("D:/screencapturePlayer6name.png");
                ImageIO.write(playerNames[5], "png", file13);
                File file14 = new File("D:/screencapturePlayer6money.png");
                ImageIO.write(playerMoney[5], "png", file14);
                File file15 = new File("D:/screencapturePlayer2action.png");
               ImageIO.write(screencaptureResizedAction2, "png", file15);
                ImageIO.write(screencapturePlayer6Name, "png", file13);
                File file16 = new File("D:/screencapturePlayer7name.png");
                ImageIO.write(playerNames[6], "png", file16);
                File file17 = new File("D:/screencapturePlayer7money.png");
                ImageIO.write(playerMoney[6], "png", file17);
                File file18 = new File("D:/screencapturePlayer8name.png");
                ImageIO.write(playerNames[7], "png", file18);
                File file19 = new File("D:/screencapturePlayer8money.png");
                ImageIO.write(playerMoney[7], "png", file19);
                File file20 = new File("D:/screencapturePlayer9name.png");
                ImageIO.write(playerNames[8], "png", file20);
                File file21 = new File("D:/screencapturePlayer9money.png");
                ImageIO.write(playerMoney[8], "png", file21);
                File file22 = new File("D:/screencapturePlayer3action.png");
                ImageIO.write(screencapturePlayer3Action, "png", file22);
                File file23 = new File("D:/screencapturePlayer4action.png");
                ImageIO.write(screencapturePlayer4Action, "png", file23);
                File file24 = new File("D:/screencapturePlayer5action.png");
                ImageIO.write(screencapturePlayer5Action, "png", file24);
                File file25 = new File("D:/screencapturePlayer6action.png");
                ImageIO.write(screencapturePlayer6Action, "png", file25);
                File file26 = new File("D:/screencapturePlayer7action.png");
                ImageIO.write(screencapturePlayer7Action, "png", file26);
                File file27 = new File("D:/screencapturePlayer8action.png");
                ImageIO.write(screencapturePlayer8Action, "png", file27);
                File file28 = new File("D:/screencapturePlayer9action.png");
                ImageIO.write(screencapturePlayer9Action, "png", file28);
                File file29 = new File("D:/screencaptureCard1.png");
                ImageIO.write(screencapturePlayer1Card1, "png", file29);
                File file30 = new File("D:/screencaptureCard2.png");
                ImageIO.write(screencapturePlayer1Card2, "png", file30);
                File file31 = new File("D:/screencaptureCard3.png");
                ImageIO.write(screencaptureBoardCard1, "png", file31);
                File file32 = new File("D:/screencaptureCard4.png");
                ImageIO.write(screencaptureBoardCard2, "png", file32);
                File file33 = new File("D:/screencaptureCard5.png");
                ImageIO.write(screencaptureBoardCard3, "png", file33);
                File file34 = new File("D:/screencaptureCard6.png");
                ImageIO.write(screencaptureBoardCard4, "png", file34);
                File file35 = new File("D:/screencaptureCard7.png");
                ImageIO.write(screencaptureBoardCard5, "png", file35);

*/
                Tesseract tesseract = new Tesseract();
                tesseract.setDatapath("D:/Tesseract/");
                tesseract.setTessVariable("tessedit_char_whitelist", "0123456789$.,");


                board.getPlayers().get(0).setName(tesseract.doOCR(playerNames[0]));
                PlayerButton2 = tesseract.doOCR(screenCapturePlayerButton2);
                String[] button = PlayerButton2.split(" ");
                String buttonContainer;
                buttonContainer = button[0];
                buttonContainer = buttonContainer.replaceAll("\\s+", "");
                PlayerPlayed player = (PlayerPlayed) board.getPlayers().get(0);
                player.setAvailableAction(buttonContainer);

                if (button.length == 2) {
                    buttonContainer = button[1];
                    buttonContainer = buttonContainer.replaceAll("[$]", "");
                    buttonContainer = buttonContainer.replaceAll("\\s+", "");
                    try {
                        player.setAmountToCall(Double.parseDouble(buttonContainer));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    player.setAmountToCall(0);
                }

                tesseract.setOcrEngineMode(ITessAPI.TessOcrEngineMode.OEM_TESSERACT_ONLY);

                String container = tesseract.doOCR(screenCapturePotResized);
                container = container.replaceAll("[$]", "");
                container = container.replaceAll(",", ".");
                container = container.replaceAll("\\s+", "");
                board.setPot(Double.parseDouble(container));

                container = tesseract.doOCR(playerMoney[0]);
                container = container.replaceAll("[$]", "");
                container = container.replaceAll(",", ".");
           /*
            board.getPlayers().get(0).setMoney(Double.parseDouble(container));
            if (hasCards[0]) {
                container = tesseract.doOCR(playerMoney[1]);
                container = container.replaceAll("[$]", "");
                container = container.replaceAll(",", ".");
                board.getPlayers().get(1).setMoney(Double.parseDouble(container));
            }
            if (hasCards[1]) {
                container = tesseract.doOCR(playerMoney[2]);
                container = container.replaceAll("[$]", "");
                container = container.replaceAll(",", ".");
                board.getPlayers().get(2).setMoney(Double.parseDouble(container));
            }
            if (hasCards[2]) {
                container = tesseract.doOCR(playerMoney[3]);
                container = container.replaceAll("[$]", "");
                container = container.replaceAll(",", ".");
                board.getPlayers().get(3).setMoney(Double.parseDouble(container));
            }
            if (hasCards[3]) {
                container = tesseract.doOCR(playerMoney[4]);
                container = container.replaceAll("[$]", "");
                container = container.replaceAll(",", ".");
                board.getPlayers().get(4).setMoney(Double.parseDouble(container));
            }
            if (hasCards[4]) {
                container = tesseract.doOCR(playerMoney[5]);
                container = container.replaceAll("[$]", "");
                container = container.replaceAll(",", ".");
                board.getPlayers().get(5).setMoney(Double.parseDouble(container));
            }
            if (hasCards[5]) {
                container = tesseract.doOCR(playerMoney[6]);
                container = container.replaceAll("[$]", "");
                container = container.replaceAll(",", ".");
                board.getPlayers().get(6).setMoney(Double.parseDouble(container));
            }
            if (hasCards[6]) {
                container = tesseract.doOCR(playerMoney[7]);
                container = container.replaceAll("[$]", "");
                container = container.replaceAll(",", ".");
                board.getPlayers().get(7).setMoney(Double.parseDouble(container));
            }
            if (hasCards[7]) {
                container = tesseract.doOCR(playerMoney[8]);
                container = container.replaceAll("[$]", "");
                container = container.replaceAll(",", ".");
                board.getPlayers().get(8).setMoney(Double.parseDouble(container));
            }
            */
                tesseract.setOcrEngineMode(ITessAPI.TessOcrEngineMode.OEM_DEFAULT);
                String cardContainer1 = tesseract.doOCR(screencapturePlayer1Card1);

                cardContainer1 = cardContainer1.replaceAll("\\s+", "").concat(holeCardColor1name);


                String cardContainer2 = tesseract.doOCR(screencapturePlayer1Card2);
                cardContainer2 = cardContainer2.replaceAll("\\s+", "").concat(holeCardColor2name);


                player.setHand(new Hand(new Card(cardContainer1), new Card(cardContainer2)));

                board.getPlayers().set(0, player);


                if (board.getGameState().equalsIgnoreCase("flop") && board.getCards().size() < 3) {
                    container = tesseract.doOCR(screencaptureBoardCard1);
                    board.addCard(new Card(container.replaceAll("\\s+", "").concat(boardCardColor1name)));
                    container = tesseract.doOCR(screencaptureBoardCard2);
                    board.addCard(new Card(container.replaceAll("\\s+", "").concat(boardCardColor2name)));
                    container = tesseract.doOCR(screencaptureBoardCard3);
                    board.addCard(new Card(container.replaceAll("\\s+", "").concat(boardCardColor3name)));
                }
                if (board.getGameState().equalsIgnoreCase("turn") && board.getCards().size() < 4) {
                    container = tesseract.doOCR(screencaptureBoardCard4);
                    board.addCard(new Card(container.replaceAll("\\s+", "").concat(boardCardColor4name)));
                }
                if (board.getGameState().equalsIgnoreCase("river") && board.getCards().size() < 5) {
                    container = tesseract.doOCR(screencaptureBoardCard5);
                    board.addCard(new Card(container.replaceAll("\\s+", "").concat(boardCardColor5name)));
                }


                if (!hadNameInit) {
                    ArrayList<PlayerAI> opponentPlayers = new ArrayList<PlayerAI>();
                    for (int i = 0; i < board.getPlayers().size(); i++) {
                        String nameContainer = tesseract.doOCR(playerNames[i]);
                        nameContainer = nameContainer.replaceAll("\\s", "");
                        board.getPlayers().get(i).setName(nameContainer);
                        if (i != 0) {
                            opponentPlayers.add((PlayerAI) board.getPlayers().get(i));
                        }
                    }
                    hadNameInit = true;

                    try {
                        Connection conn = DriverManager.getConnection("jdbc:sqlite:D:\\players.db");
                        Statement statement = conn.createStatement();

                        for (PlayerAI opponentPlayer : opponentPlayers) {
                            statement.execute("SELECT * FROM players WHERE name= '" + opponentPlayer.getName() + "'");
                            ResultSet results = statement.getResultSet();

                            if (results.next()) {
                                opponentPlayer.setHandsPlayed(results.getInt("hands"));
                                opponentPlayer.setVpip(results.getInt("vpip"));
                                opponentPlayer.setCall(results.getInt("call"));
                                opponentPlayer.setBet(results.getInt("bet"));
                                opponentPlayer.setCheck(results.getInt("checks"));
                                opponentPlayer.setRaise(results.getInt("raise"));
                            } else {
                                System.out.println("INSERT INTO players (name, call, raise, bet, checks, vpip, hands) " +
                                        "VALUES ('" + opponentPlayer.getName() + "' , " + opponentPlayer.getCall() + ", " + opponentPlayer.getRaise() + ", " +
                                        opponentPlayer.getBet() + ", " + opponentPlayer.getCheck() + ", " + opponentPlayer.getVpip() + ", " + opponentPlayer.getHandsPlayed() + ")");
                                //statement.execute("INSERT INTO players (name, call, raise, bet, checks, vpip, hands) VALUES ('AlInne' , 0, 0, 0, 0, 0, 1)");
                                statement.execute("INSERT INTO players (name, call, raise, bet, checks, vpip, hands) " +
                                        "VALUES ('" + opponentPlayer.getName() + "', " + opponentPlayer.getCall() + ", " + opponentPlayer.getRaise() + ", " +
                                        opponentPlayer.getBet() + ", " + opponentPlayer.getCheck() + ", " + opponentPlayer.getVpip() + ", " + opponentPlayer.getHandsPlayed() + ")");
                            }
                        }
                        statement.close();
                        conn.close();


                    } catch (SQLException e) {
                        e.printStackTrace();
                    }


                }


                tesseract.setOcrEngineMode(ITessAPI.TessOcrEngineMode.OEM_TESSERACT_ONLY);

                tesseract.setTessVariable("tessedit_char_whitelist", "Raise Call Bet Blind Check");

                String[] containerArray;
                if (hasCards[0]) {
                    container = tesseract.doOCR(playerAction[0]);
                    containerArray = container.split(" ");
                    board.getPlayers().get(1).setAction(containerArray[0].replaceAll("\\s+", ""), board);


                }
                if (hasCards[1]) {
                    container = tesseract.doOCR(playerAction[1]);
                    containerArray = container.split(" ");
                    board.getPlayers().get(2).setAction(containerArray[0].replaceAll("\\s+", ""), board);

                }
                if (hasCards[2]) {
                    container = tesseract.doOCR(playerAction[2]);
                    containerArray = container.split(" ");
                    board.getPlayers().get(3).setAction(containerArray[0].replaceAll("\\s+", ""), board);

                }
                if (hasCards[3]) {
                    container = tesseract.doOCR(playerAction[3]);
                    containerArray = container.split(" ");
                    board.getPlayers().get(4).setAction(containerArray[0].replaceAll("\\s+", ""), board);

                }
                if (hasCards[4]) {
                    container = tesseract.doOCR(playerAction[4]);
                    containerArray = container.split(" ");
                    board.getPlayers().get(5).setAction(containerArray[0].replaceAll("\\s+", ""), board);

                }
                if (hasCards[5]) {
                    container = tesseract.doOCR(playerAction[5]);
                    containerArray = container.split(" ");
                    board.getPlayers().get(6).setAction(containerArray[0].replaceAll("\\s+", ""), board);

                }
                if (hasCards[6]) {
                    container = tesseract.doOCR(playerAction[6]);
                    containerArray = container.split(" ");
                    board.getPlayers().get(7).setAction(containerArray[0].replaceAll("\\s+", ""), board);

                }
                if (hasCards[7]) {
                    container = tesseract.doOCR(playerAction[7]);
                    containerArray = container.split(" ");
                    board.getPlayers().get(8).setAction(containerArray[0].replaceAll("\\s+", ""), board);
                }
                board.setPlayerPositions();
                board.setPotType();
                board.setPlayerActions();
                board.setBoardType();
                assignCard(board, hasCards);

                if (board.getCards().size() > 0) {
                    String boardCards = "Board: ";
                    for (int j = 0; j < board.getCards().size(); j++) {
                        boardCards = boardCards.concat(board.getCards().get(j).getName() + " ");
                    }
                    Gui.textAppend(boardCards);
                }

                for (int i = 0; i < board.getPlayers().size(); i++) {
                    if (board.getPlayers().get(i).hasCards && !board.getPlayers().get(i).getAction().equalsIgnoreCase("No action")) {
                        if(board.getGameState().equalsIgnoreCase("preflop") && !board.getPlayers().get(i).getPreflopAction().equalsIgnoreCase("No action")) {
                            Gui.textAppend(board.getPlayers().get(i).getName() + ":  " + board.getPlayers().get(i).getPreflopAction() );
                        }else if(board.getGameState().equalsIgnoreCase("flop") && !board.getPlayers().get(i).getFlopAction().equalsIgnoreCase("No action")){
                            Gui.textAppend(board.getPlayers().get(i).getName() + ":  " + board.getPlayers().get(i).getFlopAction());
                        }else if(board.getGameState().equalsIgnoreCase("turn") && !board.getPlayers().get(i).getTurnAction().equalsIgnoreCase("No action")){
                            Gui.textAppend(board.getPlayers().get(i).getName() + ":  " + board.getPlayers().get(i).getTurnAction());
                        }else if(board.getGameState().equalsIgnoreCase("river") && !board.getPlayers().get(i).getRiverAction().equalsIgnoreCase("No action")){
                            Gui.textAppend(board.getPlayers().get(i).getName() + ":  " + board.getPlayers().get(i).getRiverAction());
                        }
                    }
                }

                try {
                    GameLogic.start(board, rect);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                DecimalFormat df = new DecimalFormat("#.##");
                if(!board.getGameState().equalsIgnoreCase("preflop")){
                    Gui.textAppend("Equity: " + df.format(player.getEquity()));
                    Gui.textAppend("Pot odds: " + df.format(player.getPotOdds()));
                }
            }
        }

    }


    //System.out.println("Execution time in seconds: " +(float)timeElapsed/1000000000);


    private static boolean isActive(BufferedImage screenCapture) {
        return screenCapture.getRGB(Constants.playerButton1PosX, Constants.playerButtonsPosY + 13) == -29152;
    }

    private static void canRaise(BufferedImage screenCapture, Board board) {
        PlayerPlayed player = (PlayerPlayed) board.getPlayers().get(0);
        if (screenCapture.getRGB(Constants.playerButton3PosX, Constants.playerButtonsPosY + 13) == -29152) {
            player.setCanRaise(true);
        } else {
            player.setCanRaise(false);
        }
    }

    private static BufferedImage resizeImage(BufferedImage originalImage, int scale) {
        BufferedImage original = originalImage;
        int height = original.getHeight();
        int width = original.getWidth();
        BufferedImage resized = new BufferedImage(width * scale, height * scale, original.getType());
        Graphics2D g2 = resized.createGraphics();
        g2.drawImage(original, 0, 0, width * scale, height * scale, null);
        g2.dispose();
        return resized;
    }

    // Játék állapotát megállapító fv.

    private static String gameState(BufferedImage screenCapture) {
        if (screenCapture.getRGB(Constants.boardCarsdX[4], Constants.boardCardsY) == Constants.cardColor) {
            return "river";
        } else if (screenCapture.getRGB(Constants.boardCarsdX[3], Constants.boardCardsY) == Constants.cardColor) {
            return "turn";
        } else if (screenCapture.getRGB(Constants.boardCarsdX[2], Constants.boardCardsY) == Constants.cardColor) {
            return "flop";
        } else {
            return "preflop";
        }
    }

    // Aktív játékosokat kereső fv.

    private static boolean[] hasCardsFunction(int[] cardsX, int[] cardsY, boolean[] hasCards, BufferedImage screenCapture) {
        for (int i = 0; i < cardsX.length; i++) {
            if (screenCapture.getRGB(cardsX[i], cardsY[i]) == Constants.cardRGB) {
                hasCards[i] = true;
            } else {
                hasCards[i] = false;
            }
        }
        return hasCards;
    }

    public static boolean inRange(Board board) {
        PlayerPlayed player = new PlayerPlayed();
        for (int i = 0; i < board.getPlayers().size(); i++) {
            if (board.getPlayers().get(i).getClass() == PlayerPlayed.class) {
                player = (PlayerPlayed) board.getPlayers().get(i);
            }
        }
        boolean inRange = false;
        for (int i = 0; i < Ranges.tightPos9OR.length; i++) {
            if (player.getHand().getHandId() == Ranges.tightPos9OR[i]) {
                inRange = true;

            }
        }
        return inRange;
    }

    private static Rectangle findWindow() {
        final Rectangle rect = new Rectangle(0, 0, 0, 0); //needs to be final or effectively final for lambda
        WindowUtils.getAllWindows(true).forEach(desktopWindow -> {
            if (desktopWindow.getTitle().contains("Genius")) {
                rect.setRect(desktopWindow.getLocAndSize());
            }
        });

        return rect;
    }

    private static void assignCard(Board board, boolean[] hasCards) {
        for (int i = 0; i < hasCards.length; i++) {
            if (hasCards[i]) {
                board.getPlayers().get(i + 1).setHasCards(true);
            } else {
                board.getPlayers().get(i + 1).setHasCards(false);
            }
        }
    }

    private static void boardVisibilityCheck(BufferedImage screencapture) {
        for (int i = 0; i < Constants.visibilityCheckX.length; i++) {
            if (Constants.visibilityCheckRGB[i] != screencapture.getRGB(Constants.visibilityCheckX[i], Constants.visibilityCheckY[i])) {
                System.out.println(Constants.visibilityCheckX[i] + "   " + Constants.visibilityCheckY[i]);
                Gui.errorDialog();
                return;
            }
        }
    }

}
