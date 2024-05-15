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
    public static int MAP_X = 80, MAP_Y = 60;
    private final Window gameWindow;
    public final KeyHandler keyHandler;
    private static State state;
    private final MapDecoder mapDecoder;
    public Panel() {
        keyHandler = new KeyHandler();
        mapDecoder = new MapDecoder();
    //    state = new MenuState(keyHandler);
        state = new WinState(keyHandler, 0);
        gameWindow = new Window(keyHandler, this);
    }
    public void update() {
        state.update();
        if (MenuState.class == state.getClass() && ((MenuState) state).gameStart()) {
            state = new GameState(keyHandler, mapDecoder);
        }
        else if (GameState.class == state.getClass()) {
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
        else if (WinState.class == state.getClass() && ((WinState) state).gameMenu()) {
            state = new MenuState(keyHandler);
        }
        else if (LoseState.class == state.getClass() && ((LoseState) state).gameMenu()) {
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