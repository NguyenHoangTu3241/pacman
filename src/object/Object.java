package object;

import animation.ImageLoader;
import main.Panel;
import misc.*;
import java.awt.*;

import static animation.ImageLoader.loadImage;

public abstract class Object implements Edible {
    protected Image sprite;
    protected Point position;
    protected final int scoreValue;
    protected Hitbox hitbox;
    public Object(String name, int _scoreValue, int col, int row) {
        sprite = loadImage(STR."/sprites/objects/\{name}.png");
        scoreValue = _scoreValue;
        position = new Point(Panel.MAP_X + col * Panel.SPRITE_SIZE, Panel.MAP_Y + row * Panel.SPRITE_SIZE);
    }
    public Point getPosition() {
        return position;
    }
    public Image getSprite() {
        return sprite;
    }
    public Hitbox getHitbox() {
        return hitbox;
    }
}