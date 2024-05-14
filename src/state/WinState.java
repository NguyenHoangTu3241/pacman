package state;

import control.KeyHandler;
import misc.Text;

import java.awt.*;

public class WinState extends State {
    private int score;
    private Text description, scoreText, option;
    public WinState(KeyHandler _keyHandler, int _score) {
        super(_keyHandler);
        score = _score;
        init();
    }
    @Override
    public void init() {
        texts.clear();
        description = new Text("YOU WIN!", 20, new Point(10, 10));
        scoreText = new Text(STR."Score: \{score}", 20, new Point(200, 200));
        option = new Text("< press any key to return to menu >", 30, new Point(150, 500));
        texts.add(description);
        texts.add(scoreText);
        texts.add(option);
    }

    @Override
    public void update() {}
    public boolean gameMenu() {
        return keyHandler.isMenu();
    }
}