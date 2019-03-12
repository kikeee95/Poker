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

public class Main {

    public static void main(String args[]) throws
            AWTException, IOException {

        for (; ; ) {

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

            //Saját nevünk megtalálása

            BufferedImage screencapturePlayer1Name = new Robot().createScreenCapture(
                    new Rectangle(rect.x + 360, rect.y + 598, 100, 15)); // 387, 598, 30, 12

            //Saját pénzösszegünk megtalálása

            BufferedImage screencapturePlayer1Money = new Robot().createScreenCapture(
                    new Rectangle(rect.x + 368, rect.y + 612, 68, 16)); // 385, 610, 40, 18

            BufferedImage screencaptureResized = resizeImage(screencapturePlayer1Money, 2);

            //második játékos

            BufferedImage screencapturePlayer2Name = new Robot().createScreenCapture(
                    new Rectangle(rect.x + 200, rect.y + 593, 100, 15)); // 387, 598, 30, 12

            //Saját pénzösszegünk megtalálása

            BufferedImage screencapturePlayer2Money = new Robot().createScreenCapture(
                    new Rectangle(rect.x + 200, rect.y + 607, 68, 16)); // 385, 610, 40, 18

            BufferedImage screencaptureResized2 = resizeImage(screencapturePlayer2Money, 2);

            //Action

            BufferedImage screencapturePlayer2Action = new Robot().createScreenCapture(
                    new Rectangle(rect.x + 192, rect.y + 621, 75, 16)); // 385, 610, 40, 18

            BufferedImage screencaptureResizedAction2 = resizeImage(screencapturePlayer2Action, 2);

            //harmadik játékos

            BufferedImage screencapturePlayer3Name = new Robot().createScreenCapture(
                    new Rectangle(rect.x + 30, rect.y + 543, 100, 15)); // 387, 598, 30, 12

            //Saját pénzösszegünk megtalálása

            BufferedImage screencapturePlayer3Money = new Robot().createScreenCapture(
                    new Rectangle(rect.x + 34, rect.y + 555, 68, 16)); // 385, 610, 40, 18

            BufferedImage screencaptureResized3 = resizeImage(screencapturePlayer3Money, 2);

            //negyedik játékos

            BufferedImage screencapturePlayer4Name = new Robot().createScreenCapture(
                    new Rectangle(rect.x + 22, rect.y + 173, 100, 15)); // 387, 598, 30, 12

            //Saját pénzösszegünk megtalálása

            BufferedImage screencapturePlayer4Money = new Robot().createScreenCapture(
                    new Rectangle(rect.x + 33, rect.y + 186, 68, 16)); // 385, 610, 40, 18

            BufferedImage screencaptureResized4 = resizeImage(screencapturePlayer4Money, 2);

            //ötödik játékos

            BufferedImage screencapturePlayer5Name = new Robot().createScreenCapture(
                    new Rectangle(rect.x + 178, rect.y + 109, 100, 15)); // 387, 598, 30, 12

            //Saját pénzösszegünk megtalálása

            BufferedImage screencapturePlayer5Money = new Robot().createScreenCapture(
                    new Rectangle(rect.x + 195, rect.y + 122, 68, 16)); // 385, 610, 40, 18

            BufferedImage screencaptureResized5 = resizeImage(screencapturePlayer5Money, 2);

            //hatodik játékos

            BufferedImage screencapturePlayer6Name = new Robot().createScreenCapture(
                    new Rectangle(rect.x + 358, rect.y + 105, 100, 15)); // 387, 598, 30, 12

            //Saját pénzösszegünk megtalálása

            BufferedImage screencapturePlayer6Money = new Robot().createScreenCapture(
                    new Rectangle(rect.x + 368, rect.y + 118, 68, 16)); // 385, 610, 40, 18

            BufferedImage screencaptureResized6 = resizeImage(screencapturePlayer6Money, 2);


            //első lapunk
            BufferedImage screencapturePlayer1Card1 = new Robot().createScreenCapture(
                    new Rectangle(rect.x + 370, rect.y + 488, 13, 15)); // 387, 598, 30, 12

            int color = screencapture.getRGB(377, 508);
            String cardColor1 = "";

            if (color == -16751867) {
                cardColor1 = "Clubs";
            } else if (color == -16777216) {
                cardColor1 = "Spades";
            } else if (color == -908544) {
                cardColor1 = "Hearts";
            } else if (color == -16776961) {
                cardColor1 = "Diamonds";
            }


            //második lapunk
            BufferedImage screencapturePlayer1Card2 = new Robot().createScreenCapture(
                    new Rectangle(rect.x + 386, rect.y + 497, 16, 17)); // 387, 598, 30, 12

            int color2 = screencapture.getRGB(393, 520);
            System.out.println(color2);
            String cardColor2 = "";

            if (color2 == -165751867) {
                cardColor2 = "clubs";
            } else if (color2 == -16777216) {
                cardColor2 = "spades";
            } else if (color2 == -908544) {
                cardColor2 = "Hearts";
            } else if (color2 == -16776961) {
                cardColor2 = "Diamonds";
            }


           // Save as JPEG
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

            Tesseract tesseract = new Tesseract();
            tesseract.setDatapath("D:/Tesseract/");
            tesseract.setTessVariable("tessedit_char_whitelist", "0123456789,$.");


            try {
                String Player1Name = tesseract.doOCR(screencapturePlayer1Name);
                tesseract.setOcrEngineMode(ITessAPI.TessOcrEngineMode.OEM_TESSERACT_ONLY);
                String Player1Money = tesseract.doOCR(screencaptureResized);
                String Player1MoneyOG = tesseract.doOCR(screencapturePlayer1Money);
                String Player2MoneyOG = tesseract.doOCR(screencapturePlayer2Money);
                String Player2Money = tesseract.doOCR(screencaptureResized2);
                String Player3Money = tesseract.doOCR(screencaptureResized3);
                String Player3MoneyOG = tesseract.doOCR(screencapturePlayer3Money);
                String Player4Money = tesseract.doOCR(screencaptureResized4);
                String Player4MoneyOG = tesseract.doOCR(screencapturePlayer4Money);
                String Player5Money = tesseract.doOCR(screencaptureResized5);
                String Player5MoneyOG = tesseract.doOCR(screencapturePlayer5Money);
                String Player6Money = tesseract.doOCR(screencaptureResized6);
                String Player6MoneyOG = tesseract.doOCR(screencapturePlayer6Money);
                tesseract.setOcrEngineMode(ITessAPI.TessOcrEngineMode.OEM_DEFAULT);
                String Player1Card1 = tesseract.doOCR(screencapturePlayer1Card1);
                String Player1Card2 = tesseract.doOCR(screencapturePlayer1Card2);
                String Player2Name = tesseract.doOCR(screencapturePlayer2Name);
                String Player3Name = tesseract.doOCR(screencapturePlayer3Name);
                String Player4Name = tesseract.doOCR(screencapturePlayer4Name);
                String Player5Name = tesseract.doOCR(screencapturePlayer5Name);
                String Player6Name = tesseract.doOCR(screencapturePlayer6Name);
                tesseract.setOcrEngineMode(ITessAPI.TessOcrEngineMode.OEM_TESSERACT_ONLY);
                tesseract.setTessVariable("tessedit_char_whitelist", "ABCILETINDabciletind0123456789,.$");
                String Player2Action = tesseract.doOCR(screencaptureResizedAction2);



                System.out.println("Nevünk: " + Player1Name);
                System.out.println("Pénz: " + Player1Money);
                System.out.println("Pénz(og): " + Player1MoneyOG);
                System.out.println("Kártya1: \n" + Player1Card1 + cardColor1);
                System.out.println("Kártya2: \n" + Player1Card2 + cardColor2);
                System.out.println("Player2Name: " + Player2Name);
                System.out.println("Player2Money: " + Player2Money);
                System.out.println("Player2MoneyOG: " + Player2MoneyOG);
                System.out.println("Player3Name: " + Player3Name);
                System.out.println("Player3Money: " + Player3Money);
                System.out.println("Player3MoneyOG: " + Player3MoneyOG);
                System.out.println("Player4Name: " + Player4Name);
                System.out.println("Player4Money: " + Player4Money);
                System.out.println("Player4MoneyOG: " + Player4MoneyOG);
                System.out.println("Player5Name: " + Player5Name);
                System.out.println("Player5Money: " + Player5Money);
                System.out.println("Player5MoneyOG: " + Player5MoneyOG);
                System.out.println("Player6Name: " + Player6Name);
                System.out.println("Player6Money: " + Player6Money);
                System.out.println("Player6MoneyOG: " + Player6MoneyOG);
                System.out.println(Player2Action);
            } catch (TesseractException e) {
                System.err.println(e.getMessage());
            }


        }
    }

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

}








