package main;

import control.KeyHandler;
import map.MapDecoder;
import misc.CurrentState;
import state.*;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {
    public static int SPRITE_SIZE = 16;
    public static int MAP_COL = 40, MAP_ROW = 30;
    public static int MAP_X = 30, MAP_Y = 50;
    private final Window gameWindow;
    public final KeyHandler keyHandler;
    private static State state;
    private final MapDecoder mapDecoder;
    public Panel() {
        keyHandler = new KeyHandler();
        mapDecoder = new MapDecoder();
        state = new MenuState(keyHandler);
        gameWindow = new Window(keyHandler, this);
    }
    public void update() {
        state.update();
        if (state instanceof MenuState && ((MenuState) state).gameStart()) {
            state = new GameState(keyHandler, mapDecoder);
        }
        else if (state instanceof GameState) {
            if (((GameState) state).gameWon()) {
                int score = ((GameState) state).getScore();
                keyHandler.setCurrentState(CurrentState.WIN_STATE);
                state = new WinState(keyHandler, score);
            }
            else if (((GameState) state).gameLost()) {
                int score = ((GameState) state).getScore();
                keyHandler.setCurrentState(CurrentState.LOSE_STATE);
                state = new LoseState(keyHandler, score);
            }
        }
        else if (state instanceof WinState && ((WinState) state).gameMenu()) {
            state = new MenuState(keyHandler);
        }
        else if (state instanceof LoseState && ((LoseState) state).gameMenu()) {
            state = new MenuState(keyHandler);
        }
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