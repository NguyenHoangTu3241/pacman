package control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandlerMenu implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode !=0) {

        }
    }
    @Override
    public void keyReleased(KeyEvent e) {}
}