package com.company;

import com.sun.jna.platform.WindowUtils;
import com.sun.org.apache.xpath.internal.SourceTree;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import net.sourceforge.tess4j.ITessAPI;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.pdfbox.contentstream.operator.state.Save;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String args[]) throws
            AWTException, IOException, InterruptedException {

        String PlayerButton1 = "";
        String PlayerButton2 = "";
        String PlayerButton3 = "";
        boolean[] hasCards = new boolean[]{true, true, true, true, true, true, true, true};
        BufferedImage[] playerNames = new BufferedImage[9];
        BufferedImage[] playerMoney = new BufferedImage[9];
        BufferedImage[] playerAction = new BufferedImage[8];
        PlayerPlayed player1 = new PlayerPlayed();
        PlayerAI player2 = new PlayerAI();
        PlayerAI player3 = new PlayerAI();
        PlayerAI player4 = new PlayerAI();
        PlayerAI player5 = new PlayerAI();
        PlayerAI player6 = new PlayerAI();
        PlayerAI player7 = new PlayerAI();
        PlayerAI player8 = new PlayerAI();
        PlayerAI player9 = new PlayerAI();
        Board board = new Board(player1, player2, player3, player4, player5, player6, player7, player8, player9);

/*
        player1.setHand(new Hand(new Card("jd"), new Card("jh")));
        Card card1 = new Card("7d");
        Card card2 = new Card("7h");
        Card card3 = new Card("tc");
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);

        player2.setRange(Ranges.tightPos1OR);
        player3.setRange(Ranges.loosePos7OR);

        ArrayList<PlayerAI> opponents = new ArrayList<PlayerAI>();
        opponents.add(player2);
        opponents.add(player3);
        player1.equity(opponents, cards);
       System.out.println(player1.getEquity());*/


        for (; ; ) {
            try {
                ScreenReader.readScreen(board);
            } catch (Exception e) {
                e.printStackTrace();
            }
/*
            board.setPlayerPositions();
            board.setPotType();
            board.setPlayerActions();
            board.setBoardType();
            GameLogic.flopAction(board);
            */

        }
    }


}

