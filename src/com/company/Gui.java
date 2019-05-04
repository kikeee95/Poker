package com.company;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static javax.swing.text.DefaultCaret.ALWAYS_UPDATE;

public class Gui extends JFrame implements KeyListener {

    private static JTextArea textArea;
    private static JButton btn;
    private JPanel jp1;
    private JPanel jp2;
    private JPanel jp3;
    private JPanel jp4;
    private JPanel jp5;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private static JPanel errorPanel = new JPanel();
    private static boolean runIt;


    public Gui() {
        super("PokerBot");
        setSize(350, 300);
        runIt = false;
        setResizable(false);
        this.setLocation(250, 250);
        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        jp4 = new JPanel();
        jp5 = new JPanel();
        label1 = new JLabel("<html><span style='font-size:12px'>" + "PokerBot - Schneider Krisztián S7M4GR" + "</span></html>");
        label2 = new JLabel("The PokerGenius table must be visible at all times!");
        label3 = new JLabel("Only startable preflop!");
        jp3.add(label1);
        jp4.add(label2);
        jp5.add(label3);
        jp4.setMaximumSize(label2.getPreferredSize());
        jp5.setMaximumSize(label3.getPreferredSize());
        jp3.setMaximumSize(label1.getPreferredSize());
        jp1.setBorder(new EmptyBorder(0, 5, 0, 5));
        jp1.setLayout(new BoxLayout(jp1, BoxLayout.Y_AXIS));
        jp1.setAlignmentX(Component.CENTER_ALIGNMENT);
        textArea = new JTextArea();
        textArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn = new JButton("Start (F5)");
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        jp2.setBorder(new EmptyBorder(0, 0, 5, 0));
        jp2.add(btn);
        btn.setBackground(Color.GREEN);
        btn.setForeground(Color.BLACK);
        btn.setFocusPainted(false);
        jp2.setMaximumSize(btn.getPreferredSize());
        textArea.setEditable(false);
        addKeyListener(this);
        setFocusable(true);
        setAutoRequestFocus(true);
        DefaultCaret caret = (DefaultCaret) textArea.getCaret();
        caret.setUpdatePolicy(ALWAYS_UPDATE);



        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (btn.getText().equalsIgnoreCase("start (F5)")) {
                    btn.setText("Stop (F5)");
                    btn.setBackground(Color.RED);
                    btn.setForeground(Color.WHITE);
                    runIt = true;

                } else {
                    btn.setText("Start (F5)");
                    btn.setBackground(Color.GREEN);
                    btn.setForeground(Color.BLACK);
                    runIt = false;
                }
            }
        });
        jp1.add(jp3);
        jp1.add(jp4);
        jp1.add(jp5);
        jp1.add(new JScrollPane(textArea));
        jp1.add(jp2);
        add(jp1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);


    }

    private void preventRunning(){
        btn.setText("Start (F5)");
        btn.setBackground(Color.GREEN);
        btn.setForeground(Color.BLACK);
        runIt = false;
    }

    public static boolean isRunIt() {
        return runIt;
    }

    public static void textAppend(String string) {
        textArea.append(string + "\n");
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_F5) {
            if (btn.getText().equalsIgnoreCase("Start (F5)")) {
                btn.setText("Stop (F5)");
                btn.setBackground(Color.RED);
                btn.setForeground(Color.WHITE);
                runIt = true;
            } else {
                btn.setText("Start (F5)");
                btn.setBackground(Color.GREEN);
                btn.setForeground(Color.BLACK);
                runIt = false;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public static void errorDialog(){
        btn.setText("Start (F5)");
        btn.setBackground(Color.GREEN);
        btn.setForeground(Color.BLACK);
        runIt = false;
        JOptionPane.showMessageDialog(errorPanel, "The PokerGenius table is not fully visible!", "Error", JOptionPane.ERROR_MESSAGE);
    }

}
