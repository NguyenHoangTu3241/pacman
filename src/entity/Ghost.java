package entity;

import animation.Animator;
import main.Loop;
import misc.Direction;
import misc.Edible;
import misc.Hitbox;
import state.GameState;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Ghost extends Entity implements Edible {
    private final BufferedImage ghost;
    private final BufferedImage ghostFleeing;
    private boolean fleeing = false;
    private final PacMan target;
    private double fleeingTimeLeft = 0;
    private long lastTime;
    public Ghost(int startX, int startY, String name, PacMan pacman) {
        super(startX, startY);
        speed = 2;
        direction = Direction.DOWN;
        ghost = loadSprites(name);
        ghostFleeing = loadSprites("ghost_vul_blue");
        animator = new Animator(ghost);
        target = pacman;
        System.out.println(STR."Created a \{name}. Screen position: \{position.x}, \{position.y}");
    }

    public void startFleeing(int seconds) {
        fleeingTimeLeft = seconds * 1_000_000_000L;
        lastTime = Loop.now;
        animator = new Animator(ghostFleeing);
        fleeing = true;
    }
    public boolean isFleeing() {
        return fleeing;
    }
    public void update(GameState state) {
        if (fleeingTimeLeft > 0) {
            fleeingTimeLeft -= Loop.now - lastTime;
            lastTime = Loop.now;
            System.out.println("timeleft: " + fleeingTimeLeft);

            if (fleeingTimeLeft <= 0) {
                fleeingTimeLeft = 0;
                animator = new Animator(ghost);
                fleeing = false;
            }
        }
        else {

        }

        Direction newDirection = getNewDirection();
        Point newPosition = move(newDirection);

        boolean update = false;
        if (isOnGrid(newPosition) && !state.hasWall(hitbox.nextGrid(newDirection))) {
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
    @Override
    public Direction getNewDirection() {
        int dx = target.getPosition().x - position.x;
        int dy = target.getPosition().y - position.y;

        if (Math.abs(dx) > Math.abs(dy)) {
            return (dx < 0) ? Direction.LEFT : Direction.RIGHT;
        } else {
            return (dy < 0) ? Direction.UP : Direction.DOWN;
        }
    }

    @Override
    public Image getSprite() {
        return animator.getSprite();
    }
    @Override
    public void respawn() {
        fleeing = false;
        position = initialPosition;
        hitbox = new Hitbox(position);
        animator = new Animator(ghost);
    }

    @Override
    public void onConsumed() {

    }

    @Override
    public int getScoreValue() {
        return 100;
    }
}
