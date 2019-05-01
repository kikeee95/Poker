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


        Gui gui = new Gui();
        int timerSec = 1;
        TimerTask timerObj = new TimerObj();

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(timerObj, 0, timerSec * 1000);


    }
}


class TimerObj extends TimerTask {
    Gui gui = new Gui();
    Board board = new Board();

    @Override
    public void run() {

        while (gui.isRunIt()) {
            try {
                ScreenReader.readScreen(board);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}




