package tankgame.gameScreen;

import tankgame.GameDriver;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Start_End_Menu_View extends JPanel { //JPanel class handling the start and end menu screen UI
    public static final Dimension SCREEN_DIMENSIONS = new Dimension(510, 550);
    private static final String START_MENU_IMAGE_FILE = "title.png";

    private static final Dimension BUTTON_SIZE = new Dimension(200, 100);
    private static final Font BUTTON_FONT = new Font("Consolas", Font.BOLD, 22);
    private static final Rectangle START_BUTTON_BOUNDS = new Rectangle(150, 300, 200, 50);
    private static final Rectangle EXIT_BUTTON_BOUNDS = new Rectangle(150, 400, 200, 50);

    public static final String START_BUTTON_ACTION_COMMAND = "start_ac";
    public static final String EXIT_BUTTON_ACTION_COMMAND = "exit_ac";

    private final BufferedImage menuBackground;

    public Start_End_Menu_View(String startButtonText, GameDriver.GameActionListener gameActionListener) {
        URL imageUrl = getClass().getClassLoader().getResource(START_MENU_IMAGE_FILE);
        if (imageUrl == null) {
            throw new RuntimeException("Unable to create an image URL from: " + START_MENU_IMAGE_FILE);
        }
        try {
            menuBackground = ImageIO.read(imageUrl);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        setBackground(Color.BLACK);
        setLayout(null);

        addButton(startButtonText, START_BUTTON_BOUNDS, START_BUTTON_ACTION_COMMAND, gameActionListener);
        addButton("Exit", EXIT_BUTTON_BOUNDS, EXIT_BUTTON_ACTION_COMMAND, gameActionListener);
    }

    private void addButton(
            String buttonText, Rectangle buttonBounds, String buttonActionCommand, ActionListener actionListener) {
        JButton button = new JButton(buttonText);
        button.setSize(BUTTON_SIZE);
        button.setFont(BUTTON_FONT);
        button.setBounds(buttonBounds);
        button.setActionCommand(buttonActionCommand);
        button.addActionListener(actionListener);
        add(button);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(menuBackground, 0, 0, null);
    }

}
