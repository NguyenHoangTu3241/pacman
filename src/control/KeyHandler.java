package control;

import misc.CurrentState;
import misc.Direction;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public Direction newDirection = Direction.RIGHT;
    private CurrentState currentState = CurrentState.MENU;
    private boolean wf = false;

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (currentState) {
            case MENU:
                if (keyCode == KeyEvent.VK_SHIFT) wf = true;
                currentState = CurrentState.GAME;
                newDirection = Direction.UP;
                break;
            case GAME:
                GameHandler(keyCode);
                break;
            case GAME_OVER:
                wf = false;
                currentState = CurrentState.MENU;
                break;
        }

    }

    public void setCurrentState(CurrentState _currentState) {
        currentState = _currentState;
    }

    public void GameHandler(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_UP:
                newDirection = Direction.UP;
                break;
            case KeyEvent.VK_DOWN:
                newDirection = Direction.DOWN;
                break;
            case KeyEvent.VK_LEFT:
                newDirection = Direction.LEFT;
                break;
            case KeyEvent.VK_RIGHT:
                newDirection = Direction.RIGHT;
                break;
        }
    }

    public boolean isGame() {
        return currentState == CurrentState.GAME;
    }

    public boolean isMenu() {
        return currentState == CurrentState.MENU;
    }

    public boolean waifu() {
        return wf;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}