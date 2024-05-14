package state;

import control.KeyHandler;
import misc.Text;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class State {
    protected KeyHandler keyHandler;
    protected List<Text> texts;
    protected List<Image> images;
    protected List<Point> imagePositions;
    public State(KeyHandler _keyHandler) {
        keyHandler = _keyHandler;
        texts = new ArrayList<>();
        images = new ArrayList<>();
        imagePositions = new ArrayList<>();
    }
    public abstract void init();
    public abstract void update();
    public List<Text> getTexts() {
        return texts;
    }
    public List<Image> getImages() {
        return images;
    }
    public List<Point> getImagePositions() {
        return imagePositions;
    }
}