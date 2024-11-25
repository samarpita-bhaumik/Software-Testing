package org.example.SnakesandLadder;

import org.example.Game.SnakesandLadder.GameGUI;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class GameGUITest {

    @Test
    public void testGUIInitialization() {
        SwingUtilities.invokeLater(() -> {
            GameGUI gui = new GameGUI();
            assertNotNull(gui, "Game GUI should initialize successfully");
        });
    }

    @Test
    public void testRollDiceButtonExists() {
        SwingUtilities.invokeLater(() -> {
            GameGUI gui = new GameGUI();
            JButton rollDiceButton = findComponent(gui.getFrame().getContentPane(), JButton.class);
            assertNotNull(rollDiceButton, "Roll Dice button should be present in the GUI");
        });
    }

    @Test
    public void testMessageLabelExists() {
        SwingUtilities.invokeLater(() -> {
            GameGUI gui = new GameGUI();
            JLabel messageLabel = findComponent(gui.getFrame().getContentPane(), JLabel.class);
            assertNotNull(messageLabel, "Message label should be present in the GUI");
            assertTrue(messageLabel.getText().contains("Player 1's turn"), "Message label should display the initial turn");
        });
    }

    private <T> T findComponent(Container container, Class<T> clazz) {
        for (Component component : container.getComponents()) {
            if (clazz.isInstance(component)) {
                return clazz.cast(component);
            }
            if (component instanceof Container) {
                T result = findComponent((Container) component, clazz);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }
}

