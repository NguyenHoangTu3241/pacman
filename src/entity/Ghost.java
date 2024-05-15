package entity;

import animation.Animator;
import misc.Direction;
import misc.Hitbox;
import state.GameState;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Ghost extends Entity {
    protected BufferedImage ghost;
    private final PacMan target;
    public Ghost(int startX, int startY, String name, PacMan pacman) {
        super(startX, startY);
        speed = 2;
        direction = Direction.DOWN;
        ghost = loadSprites(name);
        animator = new Animator(ghost);
        target = pacman;
        System.out.println(STR."Created a \{name}. Screen position: \{position.x}, \{position.y}");
    }

    public void update(GameState state) {
        Direction newDirection = getNewDirection();
        Point newPosition = move(newDirection);

        // Check if the new position is valid
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

}
