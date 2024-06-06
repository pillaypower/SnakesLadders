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
    private JButton[] tiles = new JButton[100]; // Array to keep track of the tiles
    private int currentPosition = 0; // Start position of the player (0-based index)
    private JLabel positionLabel;

    private Set<Integer> snakes = new HashSet<>();
    private Set<Integer> ladders = new HashSet<>();
    private HashMap<Integer, Integer> snakeDeduction = new HashMap<>();
    private HashMap<Integer, Integer> ladderAddition = new HashMap<>();

    private int numberOfMoves = 0;
    private int snakesBitten = 0;
    private int laddersClimbed = 0;

    public GameBoard(String username) {
        this.username = username; // Store the username
        setTitle("Snakes and Ladders");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 400);

        // Set background image of the frame
        setContentPane(new JLabel(new ImageIcon("./resources/background.png"))); // Replace "background.png" with your image path
        setLayout(new BorderLayout()); // Use BorderLayout for more flexible positioning

        // Initialize snakes and ladders
        initializeSnakesAndLadders();

        // Create and add tiles to the board
        JPanel boardPanel = new JPanel();
        boardPanel.setOpaque(false); // Make the panel transparent
        boardPanel.setLayout(new GridLayout(10, 10)); // 10x10 grid layout for the board
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

        // Add the board panel to the center
        add(boardPanel, BorderLayout.CENTER);

        // Create panel for the bottom section
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());

        // Add username label to the bottom
        JLabel usernameLabel = new JLabel("Welcome, " + username);
        usernameLabel.setOpaque(true); // Make the label opaque to show the background
        usernameLabel.setBackground(Color.WHITE); // Set the background color of the label
        usernameLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center align the text
        bottomPanel.add(usernameLabel, BorderLayout.NORTH);

        // Create roll button
        JButton rollButton = new JButton("Roll");
        rollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rollDice();
            }
        });
        bottomPanel.add(rollButton, BorderLayout.WEST);

        // Create exit button
        JButton exitBtn = new JButton("Exit");
        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Dispose of the current GameBoard window
                Main mainMenu = new Main(); // Create a new instance of the Main menu
                mainMenu.setVisible(true); // Display the Main menu window
            }
        });

        bottomPanel.add(exitBtn, BorderLayout.EAST);

        // Create position label
        positionLabel = new JLabel("Position: 1");
        positionLabel.setOpaque(true); // Make the label opaque
        positionLabel.setBackground(Color.WHITE); // Set the background color of the label
        positionLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center align the text
        bottomPanel.add(positionLabel, BorderLayout.CENTER);

        // Add bottom panel
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);

        // Highlight the starting position
        highlightCurrentPosition();
    }

    private void addTileToBoard(JPanel boardPanel, int row, int col) {
        int tileNumber = row * 10 + col + 1;
        JButton tile = new JButton(Integer.toString(tileNumber));
        tile.setPreferredSize(new Dimension(35, 35)); // smaller tiles
        tile.setEnabled(false); // Disable buttons
        tiles[tileNumber - 1] = tile; // Store reference in array
        colorTile(tileNumber, tile); // Color the tile if it's a snake or ladder
        boardPanel.add(tile); // Add buttons to the board panel
    }

    private void initializeSnakesAndLadders() {
        // Snakes
        snakes.add(24);
        snakes.add(51);
        snakes.add(75);

        snakeDeduction.put(24, -10);
        snakeDeduction.put(51, -23);
        snakeDeduction.put(75, -20);

        // Ladders
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
        int roll = rand.nextInt(6) + 1; // Roll a 6-sided die
        int newPosition = currentPosition + roll;

        if (newPosition > 99) {
            JOptionPane.showMessageDialog(this, "You rolled too high! You need to roll " + (99 - currentPosition) + " or less to win. Roll again.");
        } else {
            numberOfMoves++; // Increment the number of moves
            updatePosition(newPosition);
            if (currentPosition == 99) {
                JOptionPane.showMessageDialog(this, "Congratulations, " + username + "! You have completed Snakes and Ladders! \n Exit to Leaderboard to view your score!");
                saveGameResult(); // Save the game result to the leaderboard database
            }
        }
    }

    private void saveGameResult() {
    Connection conn = null;
    PreparedStatement pstmt = null;

    try {
        // Connect to the database
        String dbURL = "jdbc:derby://localhost:1527/Leader_boardDB;user=myUsername;password=myPassword";
        conn = DriverManager.getConnection(dbURL);

        // Verify connection
        if (conn != null) {
            System.out.println("Connected to the database successfully.");
        } else {
            System.err.println("Failed to make connection to the database.");
            return;
        }

        // Prepare the SQL statement
        String sql = "INSERT INTO GameStatistics (username, numberOfMoves, snakesBitten, laddersClimbed) VALUES (?, ?, ?, ?)";
        pstmt = conn.prepareStatement(sql);

        // Set the parameters
        pstmt.setString(1, username);
        pstmt.setInt(2, numberOfMoves);
        pstmt.setInt(3, snakesBitten);
        pstmt.setInt(4, laddersClimbed);

        // Execute the statement
        int rowsInserted = pstmt.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new game result was inserted successfully.");
        } else {
            System.err.println("No rows were inserted into the database.");
        }
    } catch (SQLException e) {
        // Print detailed error information
        System.err.println("SQLException: " + e.getMessage());
        System.err.println("SQLState: " + e.getSQLState());
        System.err.println("VendorError: " + e.getErrorCode());
        e.printStackTrace();
    } finally {
        // Close resources
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
        tiles[currentPosition].setBackground(null); // Revert the current position's tile color
        colorTile(currentPosition + 1, tiles[currentPosition]); // Revert the color of the tile
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
