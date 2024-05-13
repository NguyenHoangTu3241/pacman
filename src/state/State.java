package state;

import control.KeyHandler;
import misc.CurrentState;
import misc.Text;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public abstract class State {
    protected KeyHandler keyHandler;
    protected List<Text> texts;
    public State(KeyHandler _keyHandler) {
        keyHandler = _keyHandler;
        texts = new ArrayList<>();
    }
    public abstract void init();
    public abstract void update();
    public List<Text> getTexts() {
        return texts;
    }
}