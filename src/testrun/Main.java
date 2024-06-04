package testrun;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Main extends JFrame {

    public JButton startBtn;
    public JButton exitBtn;
    public JButton leaderboardBtn;

    public Main() {
        // Start button
        startBtn = new JButton("Start");
        startBtn.addActionListener(new ActionListener() {
            @Override
          public void actionPerformed(ActionEvent e) {
                Username usernameFrame = new Username();
                usernameFrame.setVisible(true);
            }
        });

        // Exit button
        exitBtn = new JButton("Exit");
        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Leaderboard button
        leaderboardBtn = new JButton("Leaderboard");
        leaderboardBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Replace this with the action to show the leaderboard
                JOptionPane.showMessageDialog(Main.this, "Showing Leaderboard");
            }
        });

        // Center Panel
        MainBackground centerPanel = new MainBackground();
        this.add(centerPanel, BorderLayout.CENTER);

        // South Panel
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
