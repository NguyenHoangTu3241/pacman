package state;

import control.KeyHandler;

import misc.Gif;
import misc.Text;

import java.awt.*;
import java.awt.image.BufferedImage;

import static animation.ImageLoader.loadImage;

public class MenuState extends State {
    private final int framePerUpdate = 5;
    private int frame = 0;
    public MenuState(KeyHandler _keyHandler) {
        super(_keyHandler);
        init();
    }
    @Override
    public void init() {
        texts.add(new Text("< press any key to start >", 25, new Point(220, 520)));
        gifs.add(new Gif(12, "/sprites/ui/art_menu.png", new Point(140, 170)));
        gifs.add(new Gif(1, "/sprites/ui/logo_menu.png", new Point(70, 50)));
    }

    @Override
    public void update() {
        frame++;
        if (frame == framePerUpdate) {
            frame = 0;
            for (Gif gif : gifs) gif.update();
        }
    }
    public boolean gameStart() {
        return keyHandler.isGame();
    }
}