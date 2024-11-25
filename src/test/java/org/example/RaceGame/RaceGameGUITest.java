package org.example.RaceGame;

import org.example.Game.RaceGame.RaceGameGUI;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class RaceGameGUITest {

    @Test
    public void testGUIInitialization() {
        SwingUtilities.invokeLater(() -> {
            RaceGameGUI gui = new RaceGameGUI();
            assertNotNull(gui); // Check if GUI object is created
        });
    }

    @Test
    public void testRollButtonExists() {
        SwingUtilities.invokeLater(() -> {
            Container gui = new Container();
            JButton rollButton = findComponent(gui, JButton.class);
            assertNotNull(rollButton, "Roll button should be present in the GUI");
        });
    }

    // Helper method to find a component of a specific type in the GUI
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
