package entity;

import animation.Animator;
import animation.ImageLoader;
import main.Panel;
import misc.Direction;
import state.GameState;
import misc.Hitbox;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {
    protected final Point initialPosition;
    protected Point position;
    protected Direction direction;
    protected Animator animator;
    protected Hitbox hitbox;
    protected int speed = 2;

    public Entity(int startX, int startY) {
        position = new Point(startX, startY);
        initialPosition = position;
        direction = Direction.RIGHT;
        hitbox = new Hitbox(startX, startY);
    }

    public BufferedImage loadSprites(String imagePath) {
        return (BufferedImage) ImageLoader.loadImage(STR."/sprites/entities/\{imagePath}.png" );
    }

    public Point getPosition() {
        return position;
    }

    public abstract Direction getNewDirection();

    public void update(GameState state) {

    }

    public boolean isOnGrid(Point pos) {
        return (pos.x - Panel.MAP_X) % Panel.SPRITE_SIZE == 0 || (pos.y - Panel.MAP_Y) % Panel.SPRITE_SIZE == 0;
    }

    public abstract Image getSprite();

    public Hitbox getHitbox() {
        return hitbox;
    }
}

