package com.company;

import com.sun.jna.platform.WindowUtils;
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

        BufferedImage screencapturePlayer1 = new Robot().createScreenCapture(
                new Rectangle(rect.x + 387, rect.y + 598, 30, 12)); // 387, 598, 30, 12

        //Saját pénzösszegünk megtalálása

        BufferedImage screencapturePlayer2 = new Robot().createScreenCapture(
                new Rectangle(rect.x + 370, rect.y + 612, 70, 17)); // 385, 610, 40, 18

        //első lapunk
        BufferedImage screencapturePlayer3 = new Robot().createScreenCapture(
                new Rectangle(rect.x + 370, rect.y + 488, 13, 15)); // 387, 598, 30, 12

        int color = screencapture.getRGB(377,508);
        String cardColor1 = "";

        if(color == -165751867){
            cardColor1 = "clubs";
        }else if(color == -16777216){
            cardColor1 = "spades" ;
        }else if(color == -908544){
            cardColor1 = "Hearts";
        }else if(color == -16776961){
            cardColor1 = "Diamonds";
        }



        //második lapunk
        BufferedImage screencapturePlayer4 = new Robot().createScreenCapture(
                new Rectangle(rect.x + 386, rect.y + 497, 16, 17)); // 387, 598, 30, 12

      int color2 = screencapture.getRGB(393,520);
        System.out.println(color2);
        String cardColor2 = "";

        if(color2 == -165751867){
            cardColor2 = "clubs";
        }else if(color2 == -16777216){
            cardColor2 = "spades" ;
        }else if(color2 == -908544){
            cardColor2 = "Hearts";
        }else if(color2 == -16776961){
            cardColor2 = "Diamonds";
        }



        // Save as JPEG
        File file = new File("D:/screencapture.jpg");
        ImageIO.write(screencapture, "jpg", file);
        File file1 = new File("D:/screencapture1.png");
        ImageIO.write(screencapturePlayer1, "png", file1);
        File file2 = new File("D:/screencapture2.png");
        ImageIO.write(screencapturePlayer2, "png", file2);
        File file3 = new File("D:/screencapture3.png");
        ImageIO.write(screencapturePlayer3, "png", file3);
        File file4 = new File("D:/screencapture4.png");
        ImageIO.write(screencapturePlayer4, "png", file4);

        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("D:/Tesseract/");
        tesseract.setTessVariable("Digits","0123456789");







        try {
            String name = tesseract.doOCR(screencapturePlayer1);
            String name2 = tesseract.doOCR(new File("D:/nagy.png"));
            String name3 = tesseract.doOCR(screencapturePlayer3);
            String name4 = tesseract.doOCR(screencapturePlayer4);

            System.out.println(name);
            System.out.println(name2);
           System.out.println(name3);
           System.out.println(name4);
            System.out.println(color);
            System.out.println("Zold -16751867");
            System.out.println("Fekete -16777216");
        }catch (TesseractException e){
            System.err.println(e.getMessage());
        }

        System.out.println("Első lap színe: " + cardColor1);
       System.out.println("Második lap színe: " + cardColor2);


    }
}








