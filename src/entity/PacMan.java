package entity;

import animation.Animator;
import control.KeyHandler;
import misc.Direction;

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

    public Direction getNewDirection() {
        return keyHandler.newDirection;
    }

    @Override
    public void handleCollision(Object other) {

    }
    @Override
    public Image getSprite() {
        return animator.getSprite();
    }
}
