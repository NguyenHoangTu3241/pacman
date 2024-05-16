package main;

import state.Game;
import state.State;

import java.awt.*;

public class Renderer {

    public synchronized void render(State state, Graphics graphics) {
        if (state.getClass() == Game.class) {
            renderMap((Game) state, graphics);
            renderObjects((Game) state, graphics);
            renderEntities((Game) state, graphics);
        }
        renderGifs(state, graphics);
        renderTexts(state, graphics);

    }

    private void renderMap(Game state, Graphics graphics) {
        state.getWalls().forEach(wall -> graphics.drawImage(wall.getWallSprite(), wall.getPosition().x, wall.getPosition().y, null));

    }

    private void renderObjects(Game state, Graphics graphics) {
        state.getGameObjects().forEach(gameObject ->
                graphics.drawImage(gameObject.getSprite(), gameObject.getPosition().x, gameObject.getPosition().y, null));
    }

    private void renderEntities(Game state, Graphics graphics) {
        state.getEntities().forEach(entity ->
                graphics.drawImage(entity.getSprite(), entity.getPosition().x, entity.getPosition().y, null));
    }

    private void renderTexts(State state, Graphics graphics) {
        state.getTexts().forEach(text -> {
            graphics.setFont(text.getFont());
            graphics.setColor(text.getColor());
            graphics.drawString(text.getContent(), text.getPosition().x, text.getPosition().y);
        });
    }

    private void renderGifs(State state, Graphics graphics) {
        state.getGifs().forEach(gif ->
                graphics.drawImage(gif.getImage(), gif.getPosition().x, gif.getPosition().y, null));
    }
}