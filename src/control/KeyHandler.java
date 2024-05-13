package control;

import misc.CurrentState;
import misc.Direction;
import state.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public Direction newDirection = Direction.RIGHT;
    private CurrentState currentState = CurrentState.MENU_STATE;
    public KeyHandler() {}
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (currentState == CurrentState.MENU_STATE) {
            currentState = CurrentState.GAME_STATE;
            newDirection = Direction.UP;
        }
        else if (currentState == CurrentState.GAME_STATE) {
            GameHandler(keyCode);
        }
        else if (currentState == CurrentState.WIN_STATE) {
            currentState = CurrentState.MENU_STATE;
        }
        else if (currentState == CurrentState.LOSE_STATE) {
            currentState = CurrentState.MENU_STATE;
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
    public boolean isGameStarted() {
        return currentState == CurrentState.GAME_STATE;
    }
    public boolean isMenu() {
        return currentState == CurrentState.MENU_STATE;
    }
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
}