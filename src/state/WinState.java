package state;

import control.KeyHandler;
import misc.Text;

import java.awt.*;

public class WinState extends State {
    private int score;
    public WinState(KeyHandler _keyHandler, int _score) {
        super(_keyHandler);
        score = _score;
        init();
    }
    @Override
    public void init() {
        texts.add(new Text("< press any key to return to menu >", 25, new Point(170, 520)));
        texts.add(new Text(STR."Score: \{score}", 20, new Point(200, 200)));
    }

    @Override
    public void update() {}
    public boolean gameMenu() {
        return keyHandler.isMenu();
    }
}