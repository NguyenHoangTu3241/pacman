package main;

import control.KeyHandler;
import map.MapDecoder;
import misc.CurrentState;
import state.*;
import state.Menu;

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
        state = new Menu(keyHandler);
        state = new GameOver(keyHandler, 99, 2);
        gameWindow = new Window(keyHandler, this);
    }

    public void update() {
        state.update();
        if (state instanceof Menu && ((Menu) state).gameStart()) {
            state = new Game(keyHandler, mapDecoder);
        } else if (state instanceof Game) {
            if (((Game) state).gameWon()) {
                keyHandler.setCurrentState(CurrentState.GAME);
                state = new Game(keyHandler, mapDecoder, ((Game) state).getScore(), ((Game) state).getStage(), ((Game) state).getLive());
            } else if (((Game) state).gameLost()) {
                keyHandler.setCurrentState(CurrentState.GAME_OVER);
                state = new GameOver(keyHandler, ((Game) state).getScore(), ((Game) state).getStage());
            }
        } else if (state instanceof GameOver && ((GameOver) state).gameMenu()) {
            state = new Menu(keyHandler);
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