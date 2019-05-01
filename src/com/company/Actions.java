package com.company;

import java.awt.*;
import java.awt.event.InputEvent;

public final class Actions {

    public static void raisePot(Rectangle rect) throws AWTException {
        Robot bot = new Robot();
        bot.mouseMove(rect.x + 750, rect.y + 662);
        bot.mouseMove(rect.x + 750, rect.y + 662);
        bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    public static void raiseHalfPot(Rectangle rect) throws AWTException {
        Robot bot = new Robot();
        bot.mouseMove(rect.x + 710, rect.y + 662);
        bot.mouseMove(rect.x + 710, rect.y + 662);
        bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    public static void callOrCheck(Rectangle rect) throws AWTException {
        Robot bot = new Robot();
        bot.mouseMove(rect.x + 408, rect.y + 690);
        bot.mouseMove(rect.x + 408, rect.y + 690);
        bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    public static void fold(Rectangle rect) throws AWTException {
        Robot bot = new Robot();
        bot.mouseMove(rect.x + 221, rect.y + 690);
        bot.mouseMove(rect.x + 221, rect.y + 690);
        bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    public static void allIn(Rectangle rect) throws AWTException {
        Robot bot = new Robot();
        bot.mouseMove(rect.x + 785, rect.y + 662);
        bot.mouseMove(rect.x + 785, rect.y + 662);
        bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }




}
