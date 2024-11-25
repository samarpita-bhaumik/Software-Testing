package org.example.Game.RaceGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RaceGameGUI {
    private JFrame frame;
    private JPanel mainPanel;
    private JLabel statusLabel;
    private JButton rollButton;
    private Board board;
    private JTextArea gameLog;
    private LudoBoardPanel boardPanel;

    public RaceGameGUI() {
        frame = new JFrame("Ludo Game");
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Create players
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");
        Player player3 = new Player("Player 3");
        Player player4 = new Player("Player 4");
        Player[] players = {player1, player2, player3, player4};
        board = new Board(players);

        // Create board panel
        boardPanel = new LudoBoardPanel(players);

        // UI Components
        statusLabel = new JLabel("Current Turn: " + board.getCurrentPlayerName());
        statusLabel.setFont(new Font("Arial", Font.BOLD, 18));

        rollButton = new JButton("Roll Dice");
        rollButton.setFont(new Font("Arial", Font.PLAIN, 16));
        rollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                board.playTurn();
                updateUI();
                checkForWin();
            }
        });

        gameLog = new JTextArea(8, 30);
        gameLog.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(gameLog);

        // Add components to the panel
        mainPanel.add(statusLabel, BorderLayout.NORTH);
        mainPanel.add(boardPanel, BorderLayout.CENTER);
        mainPanel.add(rollButton, BorderLayout.SOUTH);
        mainPanel.add(scrollPane, BorderLayout.EAST);

        frame.add(mainPanel);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void updateUI() {
        String currentPlayerName = board.getCurrentPlayerName();
        int currentPlayerPosition = board.getCurrentPlayerPosition();
        statusLabel.setText("Current Turn: " + currentPlayerName + " (Position: " + currentPlayerPosition + ")");

        gameLog.append(currentPlayerName + " rolled the dice and moved to position " + currentPlayerPosition + "\n");

        // Update the board
        boardPanel.repaint();
    }

    private void checkForWin() {
        if (board.checkWin()) {
            JOptionPane.showMessageDialog(frame, board.getCurrentPlayerName() + " wins the game!");
            board.resetBoard();
            gameLog.append("Game reset. Players' positions are back to 0.\n");
            boardPanel.repaint();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new RaceGameGUI();
            }
        });
    }

    // Custom JPanel for the Ludo Board
    private class LudoBoardPanel extends JPanel {
        private Player[] players;

        public LudoBoardPanel(Player[] players) {
            this.players = players;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            // Draw the Ludo board (simple grid)
            g2d.setColor(Color.BLACK);
            int cellSize = 50;
            int boardSize = 10;
            for (int i = 0; i <= boardSize; i++) {
                g2d.drawLine(i * cellSize, 0, i * cellSize, boardSize * cellSize); // Vertical lines
                g2d.drawLine(0, i * cellSize, boardSize * cellSize, i * cellSize); // Horizontal lines
            }

            // Draw player positions
            Color[] playerColors = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW};
            for (int i = 0; i < players.length; i++) {
                Player player = players[i];
                int pos = player.getPosition();

                // Calculate grid position
                int row = (pos - 1) / boardSize;
                int col = (pos - 1) % boardSize;

                if (pos > 0) {
                    g2d.setColor(playerColors[i]);
                    g2d.fillOval(col * cellSize + 10, row * cellSize + 10, cellSize - 20, cellSize - 20);
                }
            }
        }
    }
}

