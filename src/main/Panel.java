package main;
import control.KeyHandler;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {
    public static int SPRITE_SIZE = 16;
    public static int MAP_COL = 40, MAP_ROW = 30;
    public static int MAP_X = 80, MAP_Y = 60;
    private Window gameWindow;
    private KeyHandler keyHandler;
    private static State state;
    public Panel() {
        keyHandler = new KeyHandler();
        state = new State(keyHandler);
        gameWindow = new Window(keyHandler, this);

    }
    public static State getCurrentState() {
        return state;
    }
    public void update() {
        state.update();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 800, 600);
        gameWindow.draw(state, g);
        g.dispose();
    }
}