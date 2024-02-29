package com.ugispackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Start implements ActionListener{
    JFrame frame = new JFrame("TETRIS");

    public void starGame () {
        SwingUtilities.invokeLater(() -> {
            frame.setContentPane(new Main());
            frame.setSize(608,800);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            frame.setVisible(true);
            frame.getContentPane().setBackground(Color.white);
            frame.setLayout(new BorderLayout());

            JButton button = new JButton("Restart");
            button.setBounds(50,35,80,30);
            button.addActionListener(this);

            JPanel panel1 = new JPanel();
            panel1.setLayout(null);
            panel1.setPreferredSize(new Dimension(50,100));
            panel1.setBackground(Color.LIGHT_GRAY);
            panel1.add(button);
            frame.add(panel1, BorderLayout.NORTH);

            JPanel panel2 = new JPanel();
            panel2.setPreferredSize(new Dimension(50,50));
            panel2.setBackground(Color.LIGHT_GRAY);
            frame.add(panel2, BorderLayout.SOUTH);

            JPanel panel3 = new JPanel();
            panel3.setPreferredSize(new Dimension(156,50));
            panel3.setBackground(Color.LIGHT_GRAY);
            frame.add(panel3, BorderLayout.EAST);

            JPanel panel4 = new JPanel();
            panel4.setPreferredSize(new Dimension(156,50));
            panel4.setBackground(Color.LIGHT_GRAY);
            frame.add(panel4, BorderLayout.WEST);
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Restart")) {
            frame.dispose();
            starGame();
        }
    }
}
