package testrun;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class GameBoard extends JFrame {

    private String username;
    private JButton[] tiles = new JButton[100]; 
    private int currentPosition = 0; 
    private JLabel positionLabel;

    private Set<Integer> snakes = new HashSet<>();
    private Set<Integer> ladders = new HashSet<>();
    private HashMap<Integer, Integer> snakeDeduction = new HashMap<>();
    private HashMap<Integer, Integer> ladderAddition = new HashMap<>();

    private int numberOfMoves = 0;
    private int snakesBitten = 0;
    private int laddersClimbed = 0;

    public GameBoard(String username) {
        this.username = username; 
        setTitle("Snakes and Ladders");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 400);

       
        setContentPane(new JLabel(new ImageIcon("./resources/background.png"))); 
        setLayout(new BorderLayout());

        initializeSnakesAndLadders();

       
        JPanel boardPanel = new JPanel();
        boardPanel.setOpaque(false); 
        boardPanel.setLayout(new GridLayout(10, 10)); 
        boolean isEvenRow = true;
        for (int row = 9; row >= 0; row--) {
            if (isEvenRow) {
                for (int col = 0; col < 10; col++) {
                    addTileToBoard(boardPanel, row, col);
                }
            } else {
                for (int col = 9; col >= 0; col--) {
                    addTileToBoard(boardPanel, row, col);
                }
            }
            isEvenRow = !isEvenRow;
        }

    
        add(boardPanel, BorderLayout.CENTER);
   
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());

        JLabel usernameLabel = new JLabel("Welcome, " + username);
        usernameLabel.setOpaque(true); 
        usernameLabel.setBackground(Color.WHITE); 
        usernameLabel.setHorizontalAlignment(SwingConstants.CENTER); 
        bottomPanel.add(usernameLabel, BorderLayout.NORTH);

   
        JButton rollButton = new JButton("Roll");
        rollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rollDice();
            }
        });
        bottomPanel.add(rollButton, BorderLayout.WEST);

      
        JButton exitBtn = new JButton("Exit");
        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Main mainMenu = new Main(); 
                mainMenu.setVisible(true); 
            }
        });

        bottomPanel.add(exitBtn, BorderLayout.EAST);

        
        positionLabel = new JLabel("Position: 1");
        positionLabel.setOpaque(true); 
        positionLabel.setBackground(Color.WHITE); 
        positionLabel.setHorizontalAlignment(SwingConstants.CENTER); 
        bottomPanel.add(positionLabel, BorderLayout.CENTER);

        
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);

        highlightCurrentPosition();
    }

    private void addTileToBoard(JPanel boardPanel, int row, int col) {
        int tileNumber = row * 10 + col + 1;
        JButton tile = new JButton(Integer.toString(tileNumber));
        tile.setPreferredSize(new Dimension(35, 35)); 
        tile.setEnabled(false); 
        tiles[tileNumber - 1] = tile; 
        colorTile(tileNumber, tile); 
        boardPanel.add(tile); 
    }

    private void initializeSnakesAndLadders() {
        // snakes positions and value
        snakes.add(24);
        snakes.add(51);
        snakes.add(75);

        snakeDeduction.put(24, -10);
        snakeDeduction.put(51, -23);
        snakeDeduction.put(75, -20);

        // ladders position and value
        ladders.add(9);
        ladders.add(39);
        ladders.add(63);
        ladders.add(88);

        ladderAddition.put(9, 9);
        ladderAddition.put(39, 21);
        ladderAddition.put(63, 8);
        ladderAddition.put(88, 4);
    }

    private void rollDice() {
        Random rand = new Random();
        int roll = rand.nextInt(6) + 1; 
        int newPosition = currentPosition + roll;

        if (newPosition > 99) {
            JOptionPane.showMessageDialog(this, "You rolled too high! You need to roll " + (99 - currentPosition) + " or less to win. Roll again.");
        } else {
            numberOfMoves++; 
            updatePosition(newPosition);
            if (currentPosition == 99) {
                JOptionPane.showMessageDialog(this, "Congratulations, " + username + "! You have completed Snakes and Ladders! \n Exit to Leaderboard to view your score!");
                saveGameResult(); 
            }
        }
    }

    private void saveGameResult() {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/Leader_boardDB", "pdc", "pdc");
            pstmt = conn.prepareStatement("INSERT INTO GameStatistics (USERNAME, NUMBEROFMOVES, SNAKESBITTEN, LADDERSCLIMBED) VALUES (?, ?, ?, ?)");
            pstmt.setString(1, username);
            pstmt.setInt(2, numberOfMoves);
            pstmt.setInt(3, snakesBitten);
            pstmt.setInt(4, laddersClimbed);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void updatePosition(int newPosition) {
        tiles[currentPosition].setBackground(null); 
        colorTile(currentPosition + 1, tiles[currentPosition]); 
        currentPosition = newPosition;
        highlightCurrentPosition();
        if (snakes.contains(newPosition + 1)) {
            newPosition += snakeDeduction.get(newPosition + 1);
            snakesBitten++;
            JOptionPane.showMessageDialog(this, "Oops, " + username + "! You landed on a snake and moved back to position " + (newPosition + 1) + ".");
        } else if (ladders.contains(newPosition + 1)) {
            newPosition += ladderAddition.get(newPosition + 1);
            laddersClimbed++;
            JOptionPane.showMessageDialog(this, "Great, " + username + "! You climbed a ladder and moved forward to position " + (newPosition + 1) + ".");
        }
        highlightCurrentPosition();
    }

    private void highlightCurrentPosition() {
        tiles[currentPosition].setBackground(Color.YELLOW);
        positionLabel.setText("Position: " + (currentPosition + 1));
    }

    private void colorTile(int tileNumber, JButton tile) {
        if (snakes.contains(tileNumber)) {
            tile.setBackground(Color.RED);
        } else if (ladders.contains(tileNumber)) {
            tile.setBackground(Color.GREEN);
        } else {
            tile.setBackground(null);
        }
    }

    public static void main(String[] args) {
        new GameBoard("testUser");
    }
}