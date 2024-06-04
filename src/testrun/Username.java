package testrun;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Username extends JFrame {

    private JButton updateBtn;
    private JTextField textField;

    public Username() {
        setTitle("Enter Username");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLayout(new BorderLayout());

        JLabel label = new JLabel("Enter Username:");
        add(label, BorderLayout.NORTH);

        textField = new JTextField();
        add(textField, BorderLayout.CENTER);

        updateBtn = new JButton("Set Username");
        updateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = textField.getText().trim();
                if (!username.isEmpty()) {
                    dispose(); // Close the Username window
                    // Pass the username to the GameBoard
                    SwingUtilities.invokeLater(() -> new GameBoard(username));
                } else {
                    JOptionPane.showMessageDialog(Username.this, "Please enter a valid username.");
                }
            }
        });
        add(updateBtn, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Username::new);
    }
}
