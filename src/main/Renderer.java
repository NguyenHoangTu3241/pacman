package main;

import entity.Entity;
import map.Wall;
import object.Object;

import java.awt.*;

public class Renderer {
    public synchronized void render(State state, Graphics graphics) {
        renderMap(state, graphics);
        renderObjects(state, graphics);
        renderEntities(state, graphics);
    }

    private void renderMap(State state, Graphics graphics) {
        for (Wall wall : state.getWalls()) {
            graphics.drawImage(wall.getWallSprite(), wall.getPosition().x, wall.getPosition().y, null);
        }
    }
    private void renderObjects(State state, Graphics graphics) {
        for (Object gameObject : state.getGameObjects()) {
            graphics.drawImage(gameObject.getSprite(), gameObject.getPosition().x, gameObject.getPosition().y, null);
        }
    }
    private void renderEntities(State state, Graphics graphics) {
        for (Entity entity : state.getEntities()) {
            graphics.drawImage(entity.getSprite(), entity.getPosition().x, entity.getPosition().y, null);
        }
    }
}