package com.ugispackage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOver extends JFrame implements ActionListener{
    JPanel panel = new JPanel();
    Start start = new Start();

    public GameOver() {
        setSize(400,200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        JButton restart = new JButton("Restart");
        restart.setFocusable(false);
        JButton exitGame = new JButton("Exit Game");
        exitGame.setFocusable(false);
        restart.addActionListener(this);
        exitGame.addActionListener(this);
        JLabel text = new JLabel("GAME OVER !", SwingConstants.CENTER);

        panel.add(restart);
        panel.add(exitGame);
        panel.add(text);
        add(panel);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Restart")) {
            dispose();
            start.starGame();
        };
        if (e.getActionCommand().equals("Exit Game"))System.exit(0);
    }
}
