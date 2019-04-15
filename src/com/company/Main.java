package com.company;

import com.sun.jna.platform.WindowUtils;
import com.sun.org.apache.xpath.internal.SourceTree;
import net.sourceforge.tess4j.ITessAPI;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Main {

    static int posXhelper = 3;
    static int posYname = -4;
    static  int posYmoney = 12;
    static  int posYaction = 25;
    static  int boxWidth = 95;
    static  int nameHeight = 16;
    static  int moneyHeight = 14;
    static  int actionHeight = 16;
    static  int Player1posX = 355;
    static  int Player1posY = 604;
    static  int Player2posX = 178;
    static  int Player2posY = 597;
    static  int Player3posX = 15;
    static  int Player3posY = 545;
    static  int Player4posX = 17;
    static  int Player4posY = 176;
    static  int Player5posX = 178;
    static  int Player5posY = 112;
    static  int Player6posX = 355;
    static  int Player6posY = 107;
    static  int Player7posX = 528;
    static  int Player7posY = 112;
    static  int Player8posX = 694;
    static  int Player8posY = 545;
    static int Player9posX = 529;
    static  int Player9posY = 596;
    static  int holeCard1X = 369;
    static  int holeCard1Y = 486;
    static  int holeCard2X = 385;
    static  int holeCard2Y = 497;
    static  int CardhelperX = 8;
    static  int CardhelperY = 22;
    static  int clubsRGB = -16751867;
    static  int spadesRGB = -16777216;
    static  int heartsRGB = -908544;
    static  int diamondsRGB = -16776961;
    static  int boardCardsY = 325;
    static  int boardCard1X = 284;
    static  int boardCard2X = 339;
    static  int boardCard3X = 394;
    static  int boardCard4X = 449;
    static int boardCard5X = 504;
    static  int CardWidth = 15;
    static  int CardHeight = 17;
    static  int buttonRGB = -1184068;
    static  int[] buttonPosX = new int[]{448, 269, 146, 118, 214, 365, 507, 708, 601};
    static  int[] buttonPosY = new int[]{531, 532, 495, 267, 202, 196, 196, 445, 526};
    static  int buttonPosition = 0;
    static  int potTextX = 120;
    static  int potTextY = 347;
    static  int potHeight = 13;
    static  int potWidth = 50;
    static  int playerButtonsPosY = 681;
    static  int playerButton1PosX = 159;
    static int playerButton2PosX = 343;
    static int playerButton3PosX = 527;
    static int buttonWidth = 100;
    static int buttonHeigth = 20;
    static int cardColor = -16777216;
    static int[] playersCardPosX = new int[]{219, 122, 152, 247, 395, 537, 653, 552};
    static int[] playersCardPosY = new int[]{491, 432, 245, 200, 196, 197, 433, 491};
    static int cardRGB = -1;

    public static void main(String args[]) throws
            AWTException, IOException, InterruptedException {

        String gameState;
        String BoardCard1 = "";
        String BoardCard2 = "";
        String BoardCard3 = "";
        String BoardCard4 = "";
        String BoardCard5 = "";
        String Player1Name= "";
        String PlayerButton1= "";
        String PlayerButton2= "";
        String PlayerButton3= "";
        String Pot= "";
        String Player1Money= "";
        String Player2Money= "";
        String Player3Money= "";
        String Player4Money= "";
        String Player5Money= "";
        String Player6Money= "";
        String Player7Money= "";
        String Player8Money= "";
        String Player9Money= "";
        String Player1Card1= "";
        String Player1Card2= "";
        String Player2Name= "";
        String Player3Name= "";
        String Player4Name= "";
        String Player5Name= "";
        String Player6Name= "";
        String Player7Name= "";
        String Player8Name= "";
        String Player9Name= "";
        String Player2Action= "";
        String Player3Action= "";
        String Player4Action= "";
        String Player5Action= "";
        String Player6Action= "";
        String Player7Action= "";
        String Player8Action= "";
        String Player9Action= "";
        boolean[] hasCards = new boolean[]{true, true, true, true, true, true, true, true};



        for (; ; ) {

            long startTime = System.nanoTime();



            //Poker Genius ablakának megtalálása

            final Rectangle rect = new Rectangle(0, 0, 0, 0); //needs to be final or effectively final for lambda
            WindowUtils.getAllWindows(true).forEach(desktopWindow -> {
                if (desktopWindow.getTitle().contains("Genius")) {
                    rect.setRect(desktopWindow.getLocAndSize());
                }
            });

            // screenshot az egész asztalról
            BufferedImage screencapture = new Robot().createScreenCapture(
                    new Rectangle(rect));

            if (isActive(screencapture)) {

                //játék állapotának lekérdezése

                gameState = gameState(screencapture);

                //aktív játékosok keresése

                hasCards = hasCardsFunction(playersCardPosX, playersCardPosY, hasCards, screencapture);

                //nevünk megtalálása

                BufferedImage screencapturePlayer1Name = new Robot().createScreenCapture(
                        new Rectangle(rect.x + Player1posX + posXhelper, rect.y + Player1posY + posYname, boxWidth, nameHeight)); // 387, 598, 30, 12

                //pénzösszegünk megtalálása

                BufferedImage screencapturePlayer1Money = new Robot().createScreenCapture(
                        new Rectangle(rect.x + Player1posX + posXhelper, rect.y + Player1posY + posYmoney, boxWidth, moneyHeight)); // 385, 610, 40, 18

                BufferedImage screencaptureResized = resizeImage(screencapturePlayer1Money, 2);

                //második játékos

                BufferedImage screencapturePlayer2Name = new Robot().createScreenCapture(
                        new Rectangle(rect.x + Player2posX + posXhelper, rect.y + Player2posY + posYname, boxWidth, nameHeight)); // 387, 598, 30, 12

                //pénzösszeg megtalálása

                BufferedImage screencapturePlayer2Money = new Robot().createScreenCapture(
                        new Rectangle(rect.x + Player2posX + posXhelper, rect.y + Player2posY + posYmoney, boxWidth, moneyHeight)); // 385, 610, 40, 18

                BufferedImage screencaptureResized2 = resizeImage(screencapturePlayer2Money, 2);

                //Action

                BufferedImage screencapturePlayer2Action = new Robot().createScreenCapture(
                        new Rectangle(rect.x + Player2posX + posXhelper, rect.y + Player2posY + posYaction, boxWidth, actionHeight));

                BufferedImage screencaptureResizedAction2 = resizeImage(screencapturePlayer2Action, 2);

                //harmadik játékos

                BufferedImage screencapturePlayer3Name = new Robot().createScreenCapture(
                        new Rectangle(rect.x + Player3posX + posXhelper, rect.y + Player3posY + posYname, boxWidth, nameHeight)); // 387, 598, 30, 12

                //pénzösszeg megtalálása

                BufferedImage screencapturePlayer3Money = new Robot().createScreenCapture(
                        new Rectangle(rect.x + Player3posX + posXhelper, rect.y + Player3posY + posYmoney, boxWidth, moneyHeight)); // 385, 610, 40, 18

                BufferedImage screencaptureResized3 = resizeImage(screencapturePlayer3Money, 2);

                //Action

                BufferedImage screencapturePlayer3Action = new Robot().createScreenCapture(
                        new Rectangle(rect.x + Player3posX + posXhelper, rect.y + Player3posY + posYaction, boxWidth, actionHeight));

                BufferedImage screencaptureResizedAction3 = resizeImage(screencapturePlayer3Action, 2);

                //negyedik játékos

                BufferedImage screencapturePlayer4Name = new Robot().createScreenCapture(
                        new Rectangle(rect.x + Player4posX + posXhelper, rect.y + Player4posY + posYname, boxWidth, nameHeight)); // 387, 598, 30, 12

                //pénzösszeg megtalálása

                BufferedImage screencapturePlayer4Money = new Robot().createScreenCapture(
                        new Rectangle(rect.x + Player4posX + posXhelper, rect.y + Player4posY + posYmoney, boxWidth, moneyHeight)); // 385, 610, 40, 18

                BufferedImage screencaptureResized4 = resizeImage(screencapturePlayer4Money, 2);

                //Action

                BufferedImage screencapturePlayer4Action = new Robot().createScreenCapture(
                        new Rectangle(rect.x + Player4posX + posXhelper, rect.y + Player4posY + posYaction, boxWidth, actionHeight));

                BufferedImage screencaptureResizedAction4 = resizeImage(screencapturePlayer4Action, 2);


                //ötödik játékos

                BufferedImage screencapturePlayer5Name = new Robot().createScreenCapture(
                        new Rectangle(rect.x + Player5posX + posXhelper, rect.y + Player5posY + posYname, boxWidth, nameHeight)); // 387, 598, 30, 12

                //pénzösszeg megtalálása

                BufferedImage screencapturePlayer5Money = new Robot().createScreenCapture(
                        new Rectangle(rect.x + Player5posX + posXhelper, rect.y + Player5posY + posYmoney, boxWidth, moneyHeight)); // 385, 610, 40, 18

                BufferedImage screencaptureResized5 = resizeImage(screencapturePlayer5Money, 2);

                //Action

                BufferedImage screencapturePlayer5Action = new Robot().createScreenCapture(
                        new Rectangle(rect.x + Player5posX + posXhelper, rect.y + Player5posY + posYaction, boxWidth, actionHeight));

                BufferedImage screencaptureResizedAction5 = resizeImage(screencapturePlayer5Action, 2);


                //hatodik játékos

                BufferedImage screencapturePlayer6Name = new Robot().createScreenCapture(
                        new Rectangle(rect.x + Player6posX + posXhelper, rect.y + Player6posY + posYname, boxWidth, nameHeight)); // 387, 598, 30, 12

                //pénzösszeg megtalálása

                BufferedImage screencapturePlayer6Money = new Robot().createScreenCapture(
                        new Rectangle(rect.x + Player6posX + posXhelper, rect.y + Player6posY + posYmoney, boxWidth, moneyHeight)); // 385, 610, 40, 18

                BufferedImage screencaptureResized6 = resizeImage(screencapturePlayer6Money, 2);

                //Action

                BufferedImage screencapturePlayer6Action = new Robot().createScreenCapture(
                        new Rectangle(rect.x + Player6posX + posXhelper, rect.y + Player6posY + posYaction, boxWidth, actionHeight));

                BufferedImage screencaptureResizedAction6 = resizeImage(screencapturePlayer6Action, 2);


                //hetedik játékos

                BufferedImage screencapturePlayer7Name = new Robot().createScreenCapture(
                        new Rectangle(rect.x + Player7posX + posXhelper, rect.y + Player7posY + posYname, boxWidth, nameHeight));

                //pénzösszeg megtalálása

                BufferedImage screencapturePlayer7Money = new Robot().createScreenCapture(
                        new Rectangle(rect.x + Player7posX + posXhelper, rect.y + Player7posY + posYmoney, boxWidth, moneyHeight)); // 385, 610, 40, 18

                BufferedImage screencaptureResized7 = resizeImage(screencapturePlayer7Money, 2);

                //Action

                BufferedImage screencapturePlayer7Action = new Robot().createScreenCapture(
                        new Rectangle(rect.x + Player7posX + posXhelper, rect.y + Player7posY + posYaction, boxWidth, actionHeight));

                BufferedImage screencaptureResizedAction7 = resizeImage(screencapturePlayer7Action, 2);


                //nyolcadik játékos

                BufferedImage screencapturePlayer8Name = new Robot().createScreenCapture(
                        new Rectangle(rect.x + Player8posX + posXhelper, rect.y + Player8posY + posYname, boxWidth, nameHeight));

                //pénzösszeg megtalálása

                BufferedImage screencapturePlayer8Money = new Robot().createScreenCapture(
                        new Rectangle(rect.x + Player8posX + posXhelper, rect.y + Player8posY + posYmoney, boxWidth, moneyHeight)); // 385, 610, 40, 18

                BufferedImage screencaptureResized8 = resizeImage(screencapturePlayer8Money, 2);

                //Action

                BufferedImage screencapturePlayer8Action = new Robot().createScreenCapture(
                        new Rectangle(rect.x + Player8posX + posXhelper, rect.y + Player8posY + posYaction, boxWidth, actionHeight)); // 385, 610, 40, 18

                BufferedImage screencaptureResizedAction8 = resizeImage(screencapturePlayer8Action, 2);

                //kilencedik játékos

                BufferedImage screencapturePlayer9Name = new Robot().createScreenCapture(
                        new Rectangle(rect.x + Player9posX + posXhelper, rect.y + Player9posY + posYname, boxWidth, nameHeight));

                //pénzösszeg megtalálása

                BufferedImage screencapturePlayer9Money = new Robot().createScreenCapture(
                        new Rectangle(rect.x + Player9posX + posXhelper, rect.y + Player9posY + posYmoney, boxWidth, moneyHeight)); // 385, 610, 40, 18

                BufferedImage screencaptureResized9 = resizeImage(screencapturePlayer9Money, 2);

                //Action

                BufferedImage screencapturePlayer9Action = new Robot().createScreenCapture(
                        new Rectangle(rect.x + Player9posX + posXhelper, rect.y + Player9posY + posYaction, boxWidth, actionHeight));

                BufferedImage screencaptureResizedAction9 = resizeImage(screencapturePlayer9Action, 2);


                //első lapunk
                BufferedImage screencapturePlayer1Card1 = new Robot().createScreenCapture(
                        new Rectangle(rect.x + holeCard1X, rect.y + holeCard1Y, CardWidth, CardHeight)); // 387, 598, 30, 12

                int holeCardColor1 = screencapture.getRGB(holeCard1X + CardhelperX, holeCard1Y + CardhelperY);
                String holeCardColor1name = "";
                System.out.println(holeCardColor1);

                if (holeCardColor1 == clubsRGB) {
                    holeCardColor1name = "c";
                } else if (holeCardColor1 == spadesRGB) {
                    holeCardColor1name = "s";
                } else if (holeCardColor1 == heartsRGB) {
                    holeCardColor1name = "h";
                } else if (holeCardColor1 == diamondsRGB) {
                    holeCardColor1name = "d";
                }


                //második lapunk
                BufferedImage screencapturePlayer1Card2 = new Robot().createScreenCapture(
                        new Rectangle(rect.x + holeCard2X, rect.y + holeCard2Y, CardWidth, CardHeight)); // 387, 598, 30, 12

                int holeCardColor2 = screencapture.getRGB(holeCard2X + CardhelperX, holeCard2Y + CardhelperY);
                String holeCardColor2name = "";

                if (holeCardColor2 == clubsRGB) {
                    holeCardColor2name = "c";
                } else if (holeCardColor2 == spadesRGB) {
                    holeCardColor2name = "s";
                } else if (holeCardColor2 == heartsRGB) {
                    holeCardColor2name = "h";
                } else if (holeCardColor2 == diamondsRGB) {
                    holeCardColor2name = "d";
                }

                // Board lapjai

                //első 3 lap az asztalon leolvasása, csak ha lent vannak

                String boardCardColor1name = "";
                String boardCardColor2name = "";
                String boardCardColor3name = "";
                String boardCardColor4name = "";
                String boardCardColor5name = "";

                BufferedImage screencaptureBoardCard1 = new Robot().createScreenCapture(
                        new Rectangle(rect.x + boardCard1X, rect.y + boardCardsY, CardWidth, CardHeight));

                BufferedImage screencaptureBoardCard2 = new Robot().createScreenCapture(
                        new Rectangle(rect.x + boardCard2X, rect.y + boardCardsY, CardWidth, CardHeight));

                BufferedImage screencaptureBoardCard3 = new Robot().createScreenCapture(
                        new Rectangle(rect.x + boardCard3X, rect.y + boardCardsY, CardWidth, CardHeight));

                BufferedImage screencaptureBoardCard4 = new Robot().createScreenCapture(
                        new Rectangle(rect.x + boardCard4X, rect.y + boardCardsY, CardWidth, CardHeight));

                BufferedImage screencaptureBoardCard5 = new Robot().createScreenCapture(
                        new Rectangle(rect.x + boardCard5X, rect.y + boardCardsY, CardWidth, CardHeight));

                if(gameState.equalsIgnoreCase("flop") || gameState.equalsIgnoreCase("turn") || gameState.equalsIgnoreCase("river")) {


                    int boardCardColor1 = screencapture.getRGB(boardCard1X + CardhelperX, boardCardsY + CardhelperY);

                    if (boardCardColor1 == clubsRGB) {
                        boardCardColor1name = "c";
                    } else if (boardCardColor1 == spadesRGB) {
                        boardCardColor1name = "s";
                    } else if (boardCardColor1 == heartsRGB) {
                        boardCardColor1name = "h";
                    } else if (boardCardColor1 == diamondsRGB) {
                        boardCardColor1name = "d";
                    }


                    int boardCardColor2 = screencapture.getRGB(boardCard2X + CardhelperX, boardCardsY + CardhelperY);



                    if (boardCardColor2 == clubsRGB) {
                        boardCardColor2name = "c";
                    } else if (boardCardColor2 == spadesRGB) {
                        boardCardColor2name = "s";
                    } else if (boardCardColor2 == heartsRGB) {
                        boardCardColor2name = "h";
                    } else if (boardCardColor2 == diamondsRGB) {
                        boardCardColor2name = "d";
                    }

                    int boardCardColor3 = screencapture.getRGB(boardCard3X + CardhelperX, boardCardsY + CardhelperY);

                    if (boardCardColor3 == clubsRGB) {
                        boardCardColor3name = "c";
                    } else if (boardCardColor3 == spadesRGB) {
                        boardCardColor3name = "s";
                    } else if (boardCardColor3 == heartsRGB) {
                        boardCardColor3name = "h";
                    } else if (boardCardColor3 == diamondsRGB) {
                        boardCardColor3name = "d";
                    }

                }

                // 4. lap leolvasása turn és river esetén

                if(gameState.equalsIgnoreCase("turn") || gameState.equalsIgnoreCase("river")) {

                    int boardCardColor4 = screencapture.getRGB(boardCard4X + CardhelperX, boardCardsY + CardhelperY);

                    System.out.println(boardCardColor4);

                    if (boardCardColor4 == clubsRGB) {
                        boardCardColor4name = "c";
                    } else if (boardCardColor4 == spadesRGB) {
                        boardCardColor4name = "s";
                    } else if (boardCardColor4 == heartsRGB) {
                        boardCardColor4name = "h";
                    } else if (boardCardColor4 == diamondsRGB) {
                        boardCardColor4name = "d";
                    }

                }

                // 5. lap leolvasása river esetén

                if(gameState.equalsIgnoreCase("river")) {


                    int boardCardColor5 = screencapture.getRGB(boardCard5X + CardhelperX, boardCardsY + CardhelperY);

                    if (boardCardColor5 == clubsRGB) {
                        boardCardColor5name = "c";
                    } else if (boardCardColor5 == spadesRGB) {
                        boardCardColor5name = "s";
                    } else if (boardCardColor5 == heartsRGB) {
                        boardCardColor5name = "h";
                    } else if (boardCardColor5 == diamondsRGB) {
                        boardCardColor5name = "d";
                    }

                }

                //button keresése

                for (int i = 0; i < 8; i++) {

                    if (buttonRGB == (screencapture.getRGB(buttonPosX[i], buttonPosY[i]))) {
                        buttonPosition = i + 1;
                    }

                }

                // pot összegének megállapítása

                BufferedImage screenCapturePot = new Robot().createScreenCapture(
                        new Rectangle(rect.x + potTextX, rect.y + potTextY, potWidth, potHeight)); // 385, 610, 40, 18

                BufferedImage screenCapturePotResized = resizeImage(screenCapturePot, 2);

                // Játékos gombjai

                BufferedImage screenCapturePlayerButton1 = new Robot().createScreenCapture(
                        new Rectangle(rect.x + playerButton1PosX, rect.y + playerButtonsPosY, buttonWidth, buttonHeigth)); // 385, 610, 40, 18


                BufferedImage screenCapturePlayerButton2 = new Robot().createScreenCapture(
                        new Rectangle(rect.x + playerButton2PosX, rect.y + playerButtonsPosY, buttonWidth, buttonHeigth)); // 385, 610, 40, 18


                BufferedImage screenCapturePlayerButton3 = new Robot().createScreenCapture(
                        new Rectangle(rect.x + playerButton3PosX, rect.y + playerButtonsPosY, buttonWidth, buttonHeigth)); // 385, 610, 40, 18


              // Save as PNG
                File file = new File("D:/screencapture.jpg");
                ImageIO.write(screencapture, "jpg", file);
                File file1 = new File("D:/screencapture1.png");
                ImageIO.write(screencapturePlayer1Name, "png", file1);
                File file2 = new File("D:/screencapture2.png");
                ImageIO.write(screencaptureResized, "png", file2);
                File file3 = new File("D:/screencapture3.png");
                ImageIO.write(screencapturePlayer1Card1, "png", file3);
                File file4 = new File("D:/screencapture4.png");
                ImageIO.write(screencapturePlayer1Card2, "png", file4);
                File file5 = new File("D:/screencapturePlayer2name.png");
                ImageIO.write(screencapturePlayer2Name, "png", file5);
                File file6 = new File("D:/screencapturePlayer2Money.png");
                ImageIO.write(screencapturePlayer2Money, "png", file6);
                File file7 = new File("D:/screencapturePlayer3name.png");
                ImageIO.write(screencapturePlayer3Name, "png", file7);
                File file8 = new File("D:/screencapturePlayer3money.png");
                ImageIO.write(screencapturePlayer3Money, "png", file8);
                File file9 = new File("D:/screencapturePlayer4name.png");
                ImageIO.write(screencapturePlayer4Name, "png", file9);
                File file10 = new File("D:/screencapturePlayer4money.png");
                ImageIO.write(screencapturePlayer4Money, "png", file10);
                File file11 = new File("D:/screencapturePlayer5name.png");
                ImageIO.write(screencapturePlayer5Name, "png", file11);
                File file12 = new File("D:/screencapturePlayer5money.png");
                ImageIO.write(screencapturePlayer5Money, "png", file12);
                File file13 = new File("D:/screencapturePlayer6name.png");
                ImageIO.write(screencapturePlayer6Name, "png", file13);
                File file14 = new File("D:/screencapturePlayer6money.png");
                ImageIO.write(screencapturePlayer6Money, "png", file14);
                File file15 = new File("D:/screencapturePlayer2action.png");
                ImageIO.write(screencaptureResizedAction2, "png", file15);
                ImageIO.write(screencapturePlayer6Name, "png", file13);
                File file16 = new File("D:/screencapturePlayer7name.png");
                ImageIO.write(screencapturePlayer7Name, "png", file16);
                File file17 = new File("D:/screencapturePlayer7money.png");
                ImageIO.write(screencapturePlayer7Money, "png", file17);
                File file18 = new File("D:/screencapturePlayer8name.png");
                ImageIO.write(screencapturePlayer8Name, "png", file18);
                File file19 = new File("D:/screencapturePlayer8money.png");
                ImageIO.write(screencapturePlayer8Money, "png", file19);
                File file20 = new File("D:/screencapturePlayer9name.png");
                ImageIO.write(screencapturePlayer9Name, "png", file20);
                File file21 = new File("D:/screencapturePlayer9money.png");
                ImageIO.write(screencapturePlayer9Money, "png", file21);
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


                Tesseract tesseract = new Tesseract();
                tesseract.setDatapath("D:/Tesseract/");
                tesseract.setTessVariable("tessedit_char_whitelist", "0123456789,$.");

                try {

                     Player1Name = tesseract.doOCR(screencapturePlayer1Name);
                     PlayerButton1 = tesseract.doOCR(screenCapturePlayerButton1);
                     PlayerButton2 = tesseract.doOCR(screenCapturePlayerButton2);
                     PlayerButton3 = tesseract.doOCR(screenCapturePlayerButton3);
                    tesseract.setOcrEngineMode(ITessAPI.TessOcrEngineMode.OEM_TESSERACT_ONLY);
                     Pot = tesseract.doOCR(screenCapturePotResized);
                     Player1Money = tesseract.doOCR(screencaptureResized);
                     if(hasCards[0]) {
                         Player2Money = tesseract.doOCR(screencaptureResized2);
                     }
                     if(hasCards[1]) {
                         Player3Money = tesseract.doOCR(screencaptureResized3);
                     }
                     if(hasCards[2]) {
                         Player4Money = tesseract.doOCR(screencaptureResized4);
                     }
                     if(hasCards[3]) {
                         Player5Money = tesseract.doOCR(screencaptureResized5);
                     }
                     if(hasCards[4]) {
                         Player6Money = tesseract.doOCR(screencaptureResized6);
                     }
                     if(hasCards[5]) {
                         Player7Money = tesseract.doOCR(screencaptureResized7);
                     }
                     if(hasCards[6]) {
                         Player8Money = tesseract.doOCR(screencaptureResized8);
                     }
                     if(hasCards[7]) {
                         Player9Money = tesseract.doOCR(screencaptureResized9);
                     }
                    tesseract.setOcrEngineMode(ITessAPI.TessOcrEngineMode.OEM_DEFAULT);
                     Player1Card1 = tesseract.doOCR(screencapturePlayer1Card1);
                     Player1Card2 = tesseract.doOCR(screencapturePlayer1Card2);



                    if(gameState.equalsIgnoreCase("flop") || gameState.equalsIgnoreCase("turn") || gameState.equalsIgnoreCase("river")) {
                         BoardCard1 = tesseract.doOCR(screencaptureBoardCard1);
                         BoardCard2 = tesseract.doOCR(screencaptureBoardCard2);
                         BoardCard3 = tesseract.doOCR(screencaptureBoardCard3);
                         if(gameState.equalsIgnoreCase("turn") || gameState.equalsIgnoreCase("river")) {
                             BoardCard4 = tesseract.doOCR(screencaptureBoardCard4);
                             if(gameState.equalsIgnoreCase("river")) {
                                 BoardCard5 = tesseract.doOCR(screencaptureBoardCard5);
                             }
                         }
                    }
                    if(hasCards[0]) {
                        Player2Name = tesseract.doOCR(screencapturePlayer2Name);
                    }
                    if(hasCards[1]) {
                        Player3Name = tesseract.doOCR(screencapturePlayer3Name);
                    }
                    if(hasCards[2]) {
                        Player4Name = tesseract.doOCR(screencapturePlayer4Name);
                    }
                    if(hasCards[3]) {
                        Player5Name = tesseract.doOCR(screencapturePlayer5Name);
                    }
                    if(hasCards[4]) {
                        Player6Name = tesseract.doOCR(screencapturePlayer6Name);
                    }
                    if(hasCards[5]) {
                        Player7Name = tesseract.doOCR(screencapturePlayer7Name);
                    }
                    if(hasCards[6]) {
                        Player8Name = tesseract.doOCR(screencapturePlayer8Name);
                    }
                    if(hasCards[7]) {
                        Player9Name = tesseract.doOCR(screencapturePlayer9Name);
                    }

                    tesseract.setOcrEngineMode(ITessAPI.TessOcrEngineMode.OEM_TESSERACT_ONLY);

                    tesseract.setTessVariable("tessedit_char_whitelist", "Raise Call Bet Blind Check");

                    if(hasCards[0]) {
                        Player2Action = tesseract.doOCR(screencaptureResizedAction2);
                    }
                    if(hasCards[1]) {
                        Player3Action = tesseract.doOCR(screencaptureResizedAction3);
                    }
                    if(hasCards[2]) {
                        Player4Action = tesseract.doOCR(screencaptureResizedAction4);
                    }
                    if(hasCards[3]) {
                        Player5Action = tesseract.doOCR(screencaptureResizedAction5);
                    }
                    if(hasCards[4]) {
                        Player6Action = tesseract.doOCR(screencaptureResizedAction6);
                    }
                    if(hasCards[5]) {
                        Player7Action = tesseract.doOCR(screencaptureResizedAction7);
                    }
                    if(hasCards[6]) {
                        Player8Action = tesseract.doOCR(screencaptureResizedAction8);
                    }
                    if(hasCards[7]) {
                        Player9Action = tesseract.doOCR(screencaptureResizedAction9);
                    }


                    Player1Card1 = Player1Card1.replaceAll("\\s+", "").concat(holeCardColor1name);
                    Player1Card2 = Player1Card2.replaceAll("\\s+", "").concat(holeCardColor2name);
                    BoardCard1 = BoardCard1.replaceAll("\\s+", "").concat(boardCardColor1name);
                    BoardCard2 = BoardCard2.replaceAll("\\s+", "").concat(boardCardColor2name);
                    BoardCard3 = BoardCard3.replaceAll("\\s+", "").concat(boardCardColor3name);
                    BoardCard4 = BoardCard4.replaceAll("\\s+", "").concat(boardCardColor4name);
                    BoardCard5 = BoardCard5.replaceAll("\\s+", "").concat(boardCardColor5name);


                    PrintWriter out = new PrintWriter("D:/filename.txt");
                    out.println(Player1Card1);
                    out.close();


                    System.out.println("Nevünk: " + Player1Name);
                    System.out.println("Pénz: " + Player1Money);
                    System.out.println(PlayerButton1 + PlayerButton2 + PlayerButton3);
                    System.out.println("Kártya1: " + Player1Card1);
                    System.out.println("Kártya2: " + Player1Card2);
                    System.out.println("Player2Name: " + Player2Name);
                    System.out.println("Player2Money: " + Player2Money);
                    System.out.println(Player2Action);
                    System.out.println("Player3Name: " + Player3Name);
                    System.out.println("Player3Money: " + Player3Money);
                    System.out.println(Player3Action);
                    System.out.println("Player4Name: " + Player4Name);
                    System.out.println("Player4Money: " + Player4Money);
                    System.out.println(Player4Action);
                    System.out.println("Player5Name: " + Player5Name);
                    System.out.println("Player5Money: " + Player5Money);
                    System.out.println(Player5Action);
                    System.out.println("Player6Name: " + Player6Name);
                    System.out.println("Player6Money: " + Player6Money);
                    System.out.println(Player6Action);
                    System.out.println("Player7Name: " + Player7Name);
                    System.out.println("Player7Money: " + Player7Money);
                    System.out.println(Player7Action);
                    System.out.println("Player8Name: " + Player8Name);
                    System.out.println("Player8Money: " + Player8Money);
                    System.out.println(Player8Action);
                    System.out.println("Player9Name: " + Player9Name);
                    System.out.println("Player9Money: " + Player9Money);
                    System.out.println(Player9Action);

                    System.out.println("A board lapjai:");
                    System.out.println(BoardCard1 + " " + BoardCard2 + " " + BoardCard3 + " " + BoardCard4 + " " + BoardCard5);
                    System.out.println(screencapture.getRGB(playersCardPosX[0],playersCardPosY[0]));
                    System.out.println(Pot);
                } catch (TesseractException e) {
                    System.err.println(e.getMessage());
                }



                System.out.println("A Button poziciója: " + buttonPosition);
                System.out.println(gameState);

                for(int i = 0; i < hasCards.length; i++){
                    System.out.println(hasCards[i]);
                }


            }
            long endTime = System.nanoTime();
            long timeElapsed = endTime - startTime;

            System.out.println("Execution time in seconds: " +(float)timeElapsed/1000000000);


        }


    }

    // Aktív játékosokat kereső fv.

    public static boolean[] hasCardsFunction(int[] cardsX, int[] cardsY, boolean[] hasCards, BufferedImage screenCapture){
        for(int i = 0; i < cardsX.length; i++){
            if(screenCapture.getRGB(cardsX[i], cardsY[i]) == cardRGB){
                hasCards[i] = true;
            }else{
                hasCards[i] = false;
            }
        }
        return hasCards;
    }

    // Játék állapotát megállapító fv.

    public static String gameState(BufferedImage screenCapture){
        if(screenCapture.getRGB(boardCard5X, boardCardsY) == cardColor){
            return "river";
        }else if(screenCapture.getRGB(boardCard4X, boardCardsY) == cardColor){
            return "turn";
        }else if(screenCapture.getRGB(boardCard3X, boardCardsY) == cardColor){
            return "flop";
        }else{
            return "preflop";
        }
    }


    // A kép átméretezése a leolvasás javításához fv.

    public static BufferedImage resizeImage(BufferedImage originalImage, int scale) {
        BufferedImage original = originalImage;
        int height = original.getHeight();
        int width = original.getWidth();
        BufferedImage resized = new BufferedImage(width * scale, height * scale, original.getType());
        Graphics2D g2 = resized.createGraphics();
        g2.drawImage(original, 0, 0, width * scale, height * scale, null);
        g2.dispose();
        return resized;
    }

    // Megállapítja ha épp döntenünk kell

    public static boolean isActive(BufferedImage screenCapture) {
        if (screenCapture.getRGB(playerButton1PosX, playerButtonsPosY + 13) == -29152) {
            return true;
        } else {
            return false;
        }
    }
}

