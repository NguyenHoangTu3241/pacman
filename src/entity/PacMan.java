package entity;

import animation.Animator;
import control.KeyHandler;
import misc.Direction;
import misc.Hitbox;
import state.GameState;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PacMan extends Entity {
    private final KeyHandler keyHandler;
    protected BufferedImage pacman;
    public boolean update;
    private final int maxLife = 3;
    private int life;

    public PacMan(int startX, int startY, KeyHandler _keyHandler) {
        super(startX, startY);
        keyHandler = _keyHandler;
        life = maxLife;
        if (keyHandler.waifu()) pacman = loadSprites("pacmanwaifu");
        else pacman = loadSprites("pacman");
        animator = new Animator(pacman);
        System.out.println(STR."Created pacman. Screen position: \{position.x}, \{position.y}");
    }

    public void update(GameState state) {
        Direction newDirection = getNewDirection();
        Point newPosition = move(newDirection);
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

    public int getLife() {
        return life;
    }
    public void die() {
        if (life < 0) return;
        life--;
    }
}
