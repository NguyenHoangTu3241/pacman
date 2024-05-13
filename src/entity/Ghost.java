package entity;

import animation.Animator;
import misc.Direction;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Ghost extends Entity {
    private Point position; // Ghost's position on the game grid
    private Direction direction; // Ghost's current direction of movement
    protected BufferedImage ghost;
    private int speed;
    public Ghost(int startX, int startY, String name) {
        super(startX, startY);
        speed = 4;
        direction = Direction.DOWN;
        ghost = loadSprites(name);
        animator = new Animator(ghost);
    }

    @Override
    public Direction getNewDirection() {
        double random = (Math.random() * 3);
        if (0 < random && random < 0.25)
                return Direction.UP;
        if (0.25 < random && random < 0.5)
            return Direction.RIGHT;
        if (0.5 < random && random < 0.75)
            return Direction.DOWN;
        return Direction.LEFT;
    }

    @Override
    public void handleCollision(Object other) {

    }

    @Override
    public Image getSprite() {
        return animator.getSprite();
    }

}
