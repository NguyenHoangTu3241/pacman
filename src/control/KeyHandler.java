package control;

import misc.Direction;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public Direction newDirection = Direction.RIGHT;
    public KeyHandler() {}
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        switch (keyCode) {
            case KeyEvent.VK_UP:
                System.out.println("up");
                newDirection = Direction.UP;
                break;
            case KeyEvent.VK_DOWN:
                System.out.println("down");
                newDirection = Direction.DOWN;
                break;
            case KeyEvent.VK_LEFT:
                System.out.println("left");
                newDirection = Direction.LEFT;
                break;
            case KeyEvent.VK_RIGHT:
                System.out.println("right");
                newDirection = Direction.RIGHT;
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
}