package state;

import audio.Sound;
import control.KeyHandler;
import misc.Gif;
import misc.Text;

import java.util.ArrayList;
import java.util.List;

public abstract class State {
    protected KeyHandler keyHandler;
    protected List<Text> texts;
    protected List<Gif> gifs;
    public State(KeyHandler _keyHandler) {
        keyHandler = _keyHandler;
        texts = new ArrayList<>();
        gifs = new ArrayList<>();
    }
    public abstract void init();
    public abstract void update();
    public List<Text> getTexts() {
        return texts;
    }
    public List<Gif> getGifs() {
        return gifs;
    }
}