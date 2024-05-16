package entity;

import animation.Animator;
import main.Loop;
import main.Panel;
import misc.Direction;
import misc.Edible;
import misc.Hitbox;
import state.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

public class Ghost extends Entity implements Edible {
    private final BufferedImage ghost;
    private final BufferedImage ghostFleeing;
    private boolean fleeing = false;
    private final PacMan pacman;
    private double fleeingTimeLeft = 0;
    private long lastTime;
    private List<Point> path;

    public Ghost(int startX, int startY, String name, int baseSpeed, PacMan _pacman) {
        super(startX, startY);
        speed = baseSpeed;
        direction = Direction.DOWN;
        ghost = loadSprites(name);
        ghostFleeing = loadSprites("ghost_vul_blue");
        animator = new Animator(ghost);
        pacman = _pacman;
        path = new ArrayList<>();
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

    public void update(Game state) {
        if (fleeingTimeLeft > 0) {
            fleeingTimeLeft -= Loop.now - lastTime;
            lastTime = Loop.now;

            if (fleeingTimeLeft <= 0) {
                fleeingTimeLeft = 0;
                animator = new Animator(ghost);
                fleeing = false;
            }
        }

        boolean update = false;
        if (path.isEmpty()) {
            path = pathFind(state, hitbox.currentGrid(), pacman.hitbox.currentGrid());
        }
        Point target = new Point(Panel.MAP_X + path.getFirst().x * Panel.SPRITE_SIZE, Panel.MAP_Y + path.getFirst().y * Panel.SPRITE_SIZE);
        if (!Objects.equals(position, target)) {
            update = true;
            if (target.x > position.x) direction = Direction.RIGHT;
            else if (target.x < position.x) direction = Direction.LEFT;
            else if (target.y > position.y) direction = Direction.DOWN;
            else if (target.y < position.y) direction = Direction.UP;
        } else {
            path.removeFirst();
        }

        if (update) {
            position = move(direction);
            hitbox = new Hitbox(position);
            animator.updateSprite(direction);
        }
    }

    private List<Point> FindNeighbors(Game state, Point point) {
        if (point.x < 1 || point.x >= Panel.MAP_COL || point.y < 1 || point.y >= Panel.MAP_ROW) return null;
        List<Point> neighbors = new ArrayList<>();
        Point up = new Point(point.x, point.y + 1);
        Point down = new Point(point.x, point.y - 1);
        Point left = new Point(point.x - 1, point.y);
        Point right = new Point(point.x + 1, point.y);
        if (!state.hasWall(up)) neighbors.add(up);
        if (!state.hasWall(down)) neighbors.add(down);
        if (!state.hasWall(left)) neighbors.add(left);
        if (!state.hasWall(right)) neighbors.add(right);
        return neighbors;
    }

    private List<Point> pathFind(Game state, Point start, Point end) {
        Map<Point, Point> previous = new HashMap<>();

        boolean finished = false;
        List<Point> used = new ArrayList<>();
        used.add(start);
        while (!finished) {
            List<Point> newOpen = new ArrayList<>();
            for (int i = 0; i < used.size(); ++i) {
                Point point = used.get(i);
                for (Point neighbor : Objects.requireNonNull(FindNeighbors(state, point))) {
                    if (!used.contains(neighbor) && !newOpen.contains(neighbor)) {
                        newOpen.add(neighbor);
                        previous.put(neighbor, point);
                    }
                }
            }

            for (Point point : newOpen) {
                used.add(point);
                if (end.equals(point)) {
                    finished = true;
                    break;
                }
            }

            if (!finished && newOpen.isEmpty()) {
                return new ArrayList<>();
            }

        }

        List<Point> path = new ArrayList<>();
        Point point = used.getLast();
        while (previous.get(point) != null) {
            path.addFirst(point);
            point = previous.get(point);
        }
        return path;
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
    public Image getSprite() {
        return animator.getSprite();
    }

    @Override
    public void respawn() {
        path.clear();
        fleeing = false;
        fleeingTimeLeft = 0;
        position = initialPosition;
        hitbox = new Hitbox(position);
        animator = new Animator(ghost);
    }

    @Override
    public int getScoreValue() {
        return 100;
    }
}
