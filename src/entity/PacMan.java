package entity;

import animation.Animator;
import control.KeyHandler;
import misc.Direction;
import misc.Hitbox;
import state.GameState;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PacMan extends Entity {
    private KeyHandler keyHandler;
    protected BufferedImage pacman;

    private final int maxLife = 2;
    private int life;
    private int lastUpdate = 0;

    public PacMan(int startX, int startY, KeyHandler _keyHandler) {
        super(startX, startY);
        keyHandler = _keyHandler;
        life = maxLife;
        pacman = loadSprites("pacman");
        animator = new Animator(pacman);
    }

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

    public Direction getNewDirection() {
        return keyHandler.newDirection;
    }
    @Override
    public Image getSprite() {
        return animator.getSprite();
    }
}
