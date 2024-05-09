package entity;

import animation.Animator;
import main.State;
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
        int random = (int) (Math.random() * 3);
        switch (random) {
            case 0:
                return Direction.UP;
            case 1:
                return Direction.RIGHT;
            case 2:
                return Direction.DOWN;
            default:
                return Direction.LEFT;
        }
    }

    @Override
    public void handleCollision(Object other) {

    }

    @Override
    public Image getSprite() {
        return animator.getSprite();
    }

}
