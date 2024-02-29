package com.ugispackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class Main extends JPanel implements ActionListener, KeyListener {
    Klucis klucis = new Klucis();
    Start start = new Start();
    GameOver gameOver = new GameOver();
    Random random = new Random();
    int delay = 5;
    int step = 1;
    int maxXright = 425;
    int maxXleft = 165;
    Timer tm = new Timer(delay, this);
    int turnIndex = 0;
    int stepY = 20;
    int stepX;
    int stop;
    int skaits;
    int countLine;
    int finishLine;
    int pauseON = 0;
    int newRnd = random.nextInt(6);
    ArrayList<Integer> DownX = new ArrayList<>();
    ArrayList<Integer> DownY = new ArrayList<>();

    public static void main(String[] args) {
        Start start = new Start();
        start.starGame();
    }

    public Main () {
        addKeyListener(this);
        setFocusable(true);
        tm.start();
        DownX.add(1000);
        DownY.add(1000);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP && stepY < 580)    chekTurn(1);
        if (e.getKeyCode() == KeyEvent.VK_DOWN && stepY < 580)  chekTurn(-1);
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && stepY < 580) chekSide(1, maxXright);
        if (e.getKeyCode() == KeyEvent.VK_LEFT && stepY < 580)  chekSide(-1, maxXleft);
        if (e.getKeyCode() == KeyEvent.VK_SPACE && stepY < 580) tm.setDelay(0);
    }

    private void chekTurn(int index) {
        int p=0;
        turnIndex=turnIndex+index;
        if (turnIndex < 0) turnIndex = 3;
        if (turnIndex > 3) turnIndex = 0;
        while (p < DownX.size()) {
            for (int j = turnIndex * 8 + 32 * newRnd; j < 4 + turnIndex * 8 + 32 * newRnd; j++) {
                if (klucis.figura1[j] + stepX > maxXright || klucis.figura1[j] + stepX < maxXleft ||
                    (klucis.figura1[j] + stepX == DownX.get(p) && klucis.figura1[j + 4] + stepY >= DownY.get(p) -10 &&
                    klucis.figura1[j + 4] + stepY <= DownY.get(p) +10)) {
                    turnIndex=turnIndex-index;
                    if (turnIndex < 0) turnIndex = 3;
                    if (turnIndex > 3) turnIndex = 0;
                    break;
                }
            }
            p++;
        }
    }

    private void chekSide(int sideIndex, int maxSide) {
        int p=0;
        while (p < DownX.size()) {
            for (int j = turnIndex * 8+ 32 * newRnd; j < 4 + turnIndex * 8 + 32 * newRnd; j++) {
                if (klucis.figura1[j] + stepX == maxSide ||
                        (((klucis.figura1[j] + stepX +20 * sideIndex) == DownX.get(p)) && ((klucis.figura1[j + 4] + stepY) >= DownY.get(p) -10 &&
                                (klucis.figura1[j + 4] + stepY) <= DownY.get(p) +10))) {
                    stepX = stepX - 20 * sideIndex;
                    break;
                }
            }
            p++;
        }
        stepX = stepX + 20 * sideIndex;
    }

    public void paint(Graphics g) {
        if(stop == 0) {
            int n = 0;
            super.paint(g);
            klucis.draw(g, turnIndex, newRnd, stepX, stepY);
            klucis.drawScore(g,countLine);
            while (n < DownX.size()) {
                klucis.drawElement(g, DownX.get(n), DownY.get(n));
                n++;
            }

            if (skaits==14 && pauseON == 0) {
                klucis.drawLine(g, finishLine, Color.red);
                pauseON = 2;
                skaits = 0;
            }
            if (skaits==0 && pauseON == 1)klucis.drawLine(g, finishLine, Color.white);

        }
        else {
            tm.stop();
            gameOver.setVisible(true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int p = 0;

        while (p < DownX.size()) {
            for (int j = turnIndex * 8 + 32 * newRnd; j < 4 + turnIndex * 8 + 32 * newRnd; j++) {
                if ((klucis.figura1[j] + stepX == DownX.get(p) && klucis.figura1[j + 4] + stepY + 20 == DownY.get(p)) || klucis.figura1[j + 4] + stepY + 10 == 710) {
                    for (int i = turnIndex * 8 + 32 * newRnd; i < 4 + turnIndex * 8 + 32 * newRnd; i++) {
                        DownX.add(klucis.figura1[i] + stepX);
                        DownY.add(klucis.figura1[i + 4] + stepY);
                        if (klucis.figura1[i + 4] + stepY == 120) stop = 1;
                        if (chekLine(klucis.figura1[i + 4] + stepY) == 14) break;
                    }
                    newRnd = random.nextInt(6);
                    stepX = 0;
                    stepY = 0;
                    turnIndex = 0;
                    break;
                }
            }
            p++;
        }
        stepY=stepY+step;
        repaint();

        if (pauseON > 0) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            pauseON--;
        }

    }

    public int chekLine (int line) {
        skaits = 0;

        for (int m=maxXleft; m<=maxXright; m=m+20){
            int o = 0;
            while (o < DownX.size()) {
                if (DownX.get(o) == m && DownY.get(o) == line) {
                    skaits++;
                    break;
                }
                o++;
            }
        }
        if (skaits == 14) {
            finishLine = line;

            for (int b=maxXleft; b<=maxXright; b=b+20){
                int u = 1;
                while (u < DownX.size()) {
                    if (DownX.get(u) == b && DownY.get(u) == line) {
                        DownX.set(u,1000);
                        DownY.set(u,1000);
                    }
                    u++;
                }
            }
                int o = 0;
                while (o < DownY.size()) {
                    if (DownY.get(o) < line)  DownY.set(o,DownY.get(o) +20);
                    o++;
                }
            countLine++;
        }
        return skaits;
    }

    @Override
    public void keyReleased(KeyEvent e) {
            tm.setDelay(delay);
        }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
