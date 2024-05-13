package main;

import control.KeyHandler;
import state.GameState;
import state.State;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    Renderer renderer;
    public Window(KeyHandler keyHandler, Panel _game) {
        this.setTitle("Pacman Game");
        setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.add(_game);
        renderer = new Renderer();


        setResizable(false);
        setFocusable(true);
        addKeyListener(keyHandler);
        pack();
        setVisible(true);
    }

    public void draw(State state, Graphics graphics) {
        renderer.render(state, graphics);
    }
}
