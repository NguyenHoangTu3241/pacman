package misc;

import main.Panel;

import java.awt.*;

public class Hitbox {
    private int x, y, width, height;
    private Rectangle bounds;
    public Hitbox(int _x, int _y, int _width, int _height) {
        x = _x;
        y = _y;
        width = _width;
        height = _height;
        bounds = new Rectangle(x, y, width, height);
    }
    public Hitbox(int _x, int _y) {
        this(_x, _y, Panel.SPRITE_SIZE, Panel.SPRITE_SIZE);
    }
    public Hitbox(Point position) {
        this(position.x, position.y);
    }
    public void setPosition(Point newPoint) {
        x = newPoint.x;
        y = newPoint.y;
        bounds.setLocation(newPoint);
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public Point nextGrid(Direction direction) {
        int newX = x - main.Panel.MAP_X;
        int newY = y - main.Panel.MAP_Y;
        switch (direction) {
            case UP:
                newY -= 1;
                break;
            case DOWN:
                newY += main.Panel.SPRITE_SIZE + 1;
                break;
            case LEFT:
                newX -= 1;
                break;
            case RIGHT:
                newX += main.Panel.SPRITE_SIZE + 1;
                break;
        }
        return new Point(newX / main.Panel.SPRITE_SIZE, newY / Panel.SPRITE_SIZE);
    }
    public Rectangle getHitbox() {
        return bounds;
    }
    public boolean collidesWith(Hitbox other) {
        return bounds.intersects(other.getHitbox());
    }

}