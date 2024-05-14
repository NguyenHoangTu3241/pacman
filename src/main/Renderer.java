package main;

import entity.Entity;
import map.Wall;
import misc.Text;
import object.Object;
import state.*;

import java.awt.*;
public class Renderer {

    public synchronized void render(State state, Graphics graphics) {
        if (state.getClass() == GameState.class) {
            renderMap((GameState) state, graphics);
            renderObjects((GameState) state, graphics);
            renderEntities((GameState) state, graphics);
        }
        renderTexts(state, graphics);
        renderImages(state, graphics);
    }

    private void renderMap(GameState state, Graphics graphics) {
        for (Wall wall : state.getWalls()) {
            graphics.drawImage(wall.getWallSprite(), wall.getPosition().x, wall.getPosition().y, null);
        }
    }
    private void renderObjects(GameState state, Graphics graphics) {
        for (Object gameObject : state.gameObjects) {
            graphics.drawImage(gameObject.getSprite(), gameObject.getPosition().x, gameObject.getPosition().y, null);
        }
    }
    private void renderEntities(GameState state, Graphics graphics) {
        for (Entity entity : state.getEntities()) {
            graphics.drawImage(entity.getSprite(), entity.getPosition().x, entity.getPosition().y, null);
        }
    }
    private void renderTexts(State state, Graphics graphics) {
        for (Text text : state.getTexts()) {
            graphics.setFont(text.getFont());
            graphics.setColor(text.getColor());
            graphics.drawString(text.getContent(), text.getPosition().x, text.getPosition().y);
        }
    }
    private void renderImages(State state, Graphics graphics) {

    }

}