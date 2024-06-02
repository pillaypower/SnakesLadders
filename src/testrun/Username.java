package testrun;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Username extends JFrame {

    public JButton updateBtn;
    public JTextField textfield;
    public JLabel textLabel;


    public Username() {
      // Initialize the text label
        textLabel = new JLabel("Username will appear here");

        // Placeholder text field
        textfield = new JTextField("Enter Username To Start Game", 20);
         updateBtn = new JButton("Set Username");
         updateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textLabel.setText(textfield.getText().trim());
            }
            
        });


        // Center Panel
        MainBackground centerPanel = new MainBackground();
        this.add(centerPanel, BorderLayout.CENTER);

        // South Panel
        JPanel southPanel = new JPanel();
        southPanel.add(this.textfield);
        southPanel.add(updateBtn);
        this.add(southPanel, BorderLayout.SOUTH);

        this.setSize(400, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        Username cf = new Username();
        cf.setVisible(true);
    }
}
