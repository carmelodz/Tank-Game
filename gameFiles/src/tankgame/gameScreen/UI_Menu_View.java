package tankgame.gameScreen;

import tankgame.GameDriver;
import tankgame.TankControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class UI_Menu_View { //Class handles the UI element of the game menus/screen

    public enum Screen {
        START_MENU_SCREEN("start"),
        RUN_GAME_SCREEN("game"),
        END_MENU_SCREEN("end");

        private final String screenName;

        Screen(String screenName) {
            this.screenName = screenName;
        }

        public String getScreenName() {
            return screenName;
        }
    }

    private final JFrame mainJFrame;
    private final JPanel mainPanel;
    private final CardLayout mainPanelLayout;
    private final RunGameView runGameView;

    public UI_Menu_View(TankControl tankControl, GameDriver.GameActionListener gameActionListener) {
        mainJFrame = new JFrame();
        mainJFrame.setVisible(false);
        mainJFrame.setResizable(false);
        mainJFrame.setTitle("Tank Wars");
        mainJFrame.setLocationRelativeTo(null);
        mainJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainJFrame.addKeyListener(tankControl);

        mainPanel = new JPanel();
        mainPanelLayout = new CardLayout();
        mainPanel.setLayout(mainPanelLayout);

        Start_End_Menu_View startEndMenuView = new Start_End_Menu_View("Start Game", gameActionListener);
        mainPanel.add(startEndMenuView, Screen.START_MENU_SCREEN.getScreenName());

        Start_End_Menu_View endMenuView = new Start_End_Menu_View("Restart Game", gameActionListener);
        mainPanel.add(endMenuView, Screen.END_MENU_SCREEN.getScreenName());

        runGameView = new RunGameView();
        mainPanel.add(runGameView, Screen.RUN_GAME_SCREEN.getScreenName());

        mainJFrame.add(mainPanel);
    }

    public RunGameView getRunGameView() {
        return runGameView;
    }

    public void setScreen(Screen screen) {
        mainJFrame.setVisible(false);

        Dimension screenSize = switch (screen) {
            case START_MENU_SCREEN, END_MENU_SCREEN -> Start_End_Menu_View.SCREEN_DIMENSIONS;
            case RUN_GAME_SCREEN -> RunGameView.SCREEN_DIMENSIONS;
        };
        mainJFrame.setSize(screenSize);
        mainPanelLayout.show(mainPanel, screen.getScreenName());

        mainJFrame.setVisible(true);
    }

    public void closeGame() {
        mainJFrame.dispatchEvent(new WindowEvent(mainJFrame, WindowEvent.WINDOW_CLOSING));
    }
}
