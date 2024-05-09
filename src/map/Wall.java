package map;

import animation.ImageLoader;
import main.Panel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Wall {
    private final BufferedImage sprite;
    private final Point position;
    public Wall(String name, int _col, int _row) {
        sprite = (BufferedImage) ImageLoader.loadImage(STR."/sprites/walls/\{name}.png");
        position = new Point(Panel.MAP_X + _col * Panel.SPRITE_SIZE, Panel.MAP_Y + _row * Panel.SPRITE_SIZE);
    }
    public BufferedImage getWallSprite() {
        return sprite;
    }
    public Point getPosition() {
        return position;
    }
}