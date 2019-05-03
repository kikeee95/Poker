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
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String args[]) throws
            AWTException, IOException, InterruptedException {

        int timerSec = 1;
        TimerTask timerObj = new TimerObj();

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(timerObj, 0, timerSec * 1000);


    }
}


class TimerObj extends TimerTask {
    Gui gui = new Gui();
    Board board = new Board();
    boolean hadRun = false;


    @Override
    public void run() {

        try{
            Connection conn = DriverManager.getConnection("jdbc:sqlite:D:\\players.db");
            Statement statement = conn.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS" +
                    " players (name TEXT, call INTEGER, raise INTEGER, bet INTEGER, checks INTEGER, vpip INTEGER, hands INTEGER)");
            statement.close();
            conn.close();
        }catch (SQLException e){
            gui.textAppend(e.getMessage());
        }

        while (gui.isRunIt()) {
            try {
                ScreenReader.readScreen(board);
                hadRun = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(hadRun && !gui.isRunIt()){
            System.out.println("asd");
            try{
                Connection conn = DriverManager.getConnection("jdbc:sqlite:D:\\players.db");
                Statement statement = conn.createStatement();
                ArrayList<PlayerAI> opponents = new ArrayList<PlayerAI>();

                for(int i = 1; i < board.getPlayers().size(); i++ ){
                    opponents.add((PlayerAI)board.getPlayers().get(i));
                }

                for(int i = 0; i < opponents.size(); i++) {
                   statement.execute("UPDATE players SET call=" + opponents.get(i).getCall() + ", raise=" + opponents.get(i).getRaise() +
                   ", bet=" + opponents.get(i).getBet() + ", checks=" + opponents.get(i).getCheck() + ", vpip=" + opponents.get(i).getVpip() +
                   ", hands=" + opponents.get(i).getHandsPlayed() + " WHERE name='" + opponents.get(i).getName() + "'");
                }
                statement.close();
                conn.close();


            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}




