package misc;

import java.awt.*;
import java.awt.image.BufferedImage;

import static animation.ImageLoader.loadImage;

public class Gif {
    private final int frames;
    int frame = 0;
    private final int width, height;
    private final BufferedImage source;
    private Point position;
    public Gif(int _frames, String path, Point _position) {
        frames = _frames;
        source = (BufferedImage) loadImage(path);
        assert source != null;
        width = source.getWidth() / frames;
        height = source.getHeight();

        position = _position;
    }
    public Image getImage() {
        return source.getSubimage(frame * width, 0, width, height);
    }
    public void update() {
        frame++;
        frame %= frames;
    }
    public Point getPosition() {
        return position;
    }
    public void setPosition(Point _position) {
        position = _position;
    }
    public int getFrames() {
        return frames;
    }
    public void setFrame(int _frame) {
        frame = _frame;
    }
}