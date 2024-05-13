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
        Direction newDirection = getNewDirection();
        Point newPosition = move(newDirection);
        boolean update;
        if (isOnGrid(newPosition) && !state.hasWall(hitbox.nextGrid(newDirection))) {
            direction = newDirection;
            update = true;
        } else {
            newPosition = move(direction);
            update = !state.hasWall(hitbox.nextGrid(direction));
        }
        if (update) {
            position = newPosition;
            hitbox = new Hitbox(newPosition);
            animator.updateSprite(direction);
        }
    }

    private Point move(Direction newDirection) {
        int newX = position.x;
        int newY = position.y;
        switch (newDirection) {
            case UP:
                newY -= speed;
                break;
            case DOWN:
                newY += speed;
                break;
            case LEFT:
                newX -= speed;
                break;
            case RIGHT:
                newX += speed;
                break;
        }
        return new Point(newX, newY);
    }

    public boolean isOnGrid(Point pos) {
        return (pos.x - Panel.MAP_X) % Panel.SPRITE_SIZE == 0 || (pos.y - Panel.MAP_Y) % Panel.SPRITE_SIZE == 0;
    }

    public abstract void handleCollision(Object other);

    public abstract Image getSprite();

    public Hitbox getHitbox() {
        return hitbox;
    }
}

