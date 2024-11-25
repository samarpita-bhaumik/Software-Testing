package org.example.Game.SnakesandLadder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameGUI {
    private final JFrame frame;
    private final JPanel boardPanel;
    private final JPanel controlsPanel;
    private final JButton rollDiceButton;
    private final JLabel messageLabel;
    private final Game game;

    public JFrame getFrame() {
        return frame;
    }

    public GameGUI() {
        game = new Game(100);
        game.initializeGame();

        frame = new JFrame("Snakes and Ladders");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setLayout(new BorderLayout());

        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(10, 10));
        createBoardUI();

        controlsPanel = new JPanel();
        controlsPanel.setLayout(new BorderLayout());

        rollDiceButton = new JButton("Roll Dice");
        rollDiceButton.addActionListener(new RollDiceListener());

        messageLabel = new JLabel("Welcome to Snakes and Ladders! Player 1's turn.", JLabel.CENTER);

        controlsPanel.add(rollDiceButton, BorderLayout.NORTH);
        controlsPanel.add(messageLabel, BorderLayout.CENTER);

        frame.add(boardPanel, BorderLayout.CENTER);
        frame.add(controlsPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    // Creates the visual representation of the board
    private void createBoardUI() {
        boardPanel.removeAll();
        for (int i = 100; i > 0; i--) {
            JLabel cell = new JLabel(String.valueOf(i), JLabel.CENTER);
            cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            cell.setOpaque(true);
            cell.setBackground(Color.LIGHT_GRAY);
            boardPanel.add(cell);
        }
        boardPanel.revalidate();
        boardPanel.repaint();
    }

    // Updates the board with player positions
    private void updateBoardUI() {
        Component[] cells = boardPanel.getComponents();
        for (Component cell : cells) {
            JLabel label = (JLabel) cell;
            label.setBackground(Color.LIGHT_GRAY); // Reset background to default
        }

        // Loop through players and set different colors for each
        for (int i = 0; i < game.getPlayers().size(); i++) {
            Player player = game.getPlayers().get(i);
            int position = player.getPosition();
            int index = 100 - position; // Reverse index for grid

            if (index >= 0 && index < cells.length) {
                JLabel label = (JLabel) cells[index];

                // Alternate color for each player
                if (i % 2 == 0) {
                    label.setBackground(Color.GREEN); // Player 1 color
                } else {
                    label.setBackground(Color.BLUE); // Player 2 color
                }
                label.setText(player.getName().substring(0, 1)); // Use player's initials
            }
        }
    }


    // Handles dice rolls and gameplay
    private class RollDiceListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (game.isGameWon()) {
                messageLabel.setText("Game over! Restart to play again.");
                rollDiceButton.setEnabled(false);
                return;
            }

            String result = game.playNextTurn();
            messageLabel.setText(result);
            updateBoardUI();

            if (game.isGameWon()) {
                JOptionPane.showMessageDialog(frame, result, "Game Over", JOptionPane.INFORMATION_MESSAGE);
                rollDiceButton.setEnabled(false);
            }
        }
    }
}

