package com.ugispackage;
import java.awt.*;

public class Klucis {

    int[] figura1 = {265, 285, 285, 285, 100, 100, 120, 120,
                     285, 285, 265, 265, 100, 120, 120, 120,
                     285, 265, 265, 265, 120, 120, 100, 100,
                     265, 265, 285, 285, 120, 100, 100, 100,

                     265, 285, 285, 285, 100, 100, 100, 100,
                     285, 285, 285, 285, 100, 120, 120, 120,
                     285, 265, 265, 265, 120, 120, 120, 120,
                     265, 265, 265, 265, 120, 100, 100, 100,

                     265, 265, 265, 265, 100, 100, 100, 100,
                     265, 265, 265, 265, 100, 100, 100, 100,
                     265, 265, 265, 265, 100, 100, 100, 100,
                     265, 265, 265, 265, 100, 100, 100, 100,

                     265, 285, 285, 265, 100, 100, 120, 120,
                     265, 285, 285, 265, 100, 100, 120, 120,
                     265, 285, 285, 265, 100, 100, 120, 120,
                     265, 285, 285, 265, 100, 100, 120, 120,

                     265, 285, 285, 285, 100, 100, 120, 140,
                     285, 285, 265, 245, 120, 140, 140, 140,
                     265, 245, 245, 245, 140, 140, 120, 100,
                     245, 245, 265, 285, 120, 100, 100, 100,

                     265, 285, 285, 305, 100, 100, 120, 100,
                     305, 305, 305, 285, 100, 120, 140, 120,
                     305, 285, 265, 285, 140, 140, 140, 120,
                     265, 265, 265, 285, 140, 120, 100, 120};

    public void draw (Graphics g,int turn, int rnd, int x, int y) {

        g.setColor(Color.GRAY);
        int t = turn * 8 + 32 * rnd;

        for (int i= t; i<4 + t; i++)
            g.fillPolygon(new int[]{figura1[i] - 10 + x, figura1[i] + 10 + x, figura1[i] + 10 + x, figura1[i] - 10 + x},
                          new int[]{figura1[i + 4] - 10 + y, figura1[i + 4] - 10 + y, figura1[i + 4] + 10 + y, figura1[i + 4] + 10 + y}, 4);

    }

    public void drawElement (Graphics g, int a, int b){
        g.setColor(Color.GRAY);
        g.fillPolygon(new int[]{a - 10, a + 10, a + 10, a - 10}, new int[]{b - 10, b - 10, b + 10, b + 10},4);
    }

    public void drawScore (Graphics g, int rinduSkaits) {
        g.setColor(Color.blue);
        g.setFont(new Font("Ariel",Font.BOLD,32));
        g.drawString(String.valueOf(rinduSkaits),450,70);
    }

    public void drawLine (Graphics g, int linijasYass, Color color){
        g.setColor(color);
        g.fillPolygon(new int[]{435, 155, 155, 435},new int[]{linijasYass-10, linijasYass-10, linijasYass+10, linijasYass+10},4);
    }

}
