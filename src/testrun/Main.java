package testrun;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {

    public JButton startBtn;
    public JButton exitBtn;
    public JButton leaderboardBtn;

    public Main() {
        // start button
        startBtn = new JButton("Start");
        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Username usernameFrame = new Username();
                usernameFrame.setVisible(true);
            }
        });

        // exit button
        exitBtn = new JButton("Exit");
        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // leaderboard button
        leaderboardBtn = new JButton("Leaderboard");
        leaderboardBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Display the Leaderboard GUI
                Leaderboard leaderboardFrame = new Leaderboard();
                leaderboardFrame.setVisible(true);
            }
        });

        // stuff for background
        MainBackground centerPanel = new MainBackground();
        this.add(centerPanel, BorderLayout.CENTER);
        JPanel southPanel = new JPanel();
        southPanel.add(startBtn);
        southPanel.add(leaderboardBtn);
        southPanel.add(exitBtn);
        this.add(southPanel, BorderLayout.SOUTH);

        this.setSize(700, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        Main cf = new Main();
        cf.setVisible(true);
    }
}
