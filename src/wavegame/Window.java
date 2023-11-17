package wavegame;

import javax.swing.*;
import java.awt.*;

public class Window extends Canvas{

    public Window(int width, int height, String title, WaveGame game){
        JFrame frame = new JFrame(title);

        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the program when we click the X button
        frame.setResizable(false); // We don't want the user to resize the window
        frame.setLocationRelativeTo(null); // The window will appear in the center of the screen
        frame.add(game); // Add the game to the window
        frame.setVisible(true); // Make the window visible
        game.start(); // Start the game
    }
}
