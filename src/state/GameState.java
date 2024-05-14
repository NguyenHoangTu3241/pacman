package state;

import control.KeyHandler;
import entity.Entity;
import entity.Ghost;
import entity.PacMan;
import map.MapDecoder;
import map.Wall;
import misc.Text;
import object.Object;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameState extends State {
    private List<Entity> entities;
    public List<Object> gameObjects;
    private List<Object> gameObjectsToRemove;
    private final List<Wall> walls;
    private final boolean[][] hasWall;
    PacMan pacman;
    private int score = 0;
    private Text scoreText;
    private boolean win = false, lose = false;

    public GameState(KeyHandler _keyHandler, MapDecoder mapDecoder){
        super(_keyHandler);

        gameObjects = mapDecoder.getGameObjects();
        gameObjectsToRemove = new ArrayList<>();
        walls = mapDecoder.getWalls();
        hasWall = mapDecoder.getHasWall();

        init();
        respawn(mapDecoder);
    }

    public void respawn(MapDecoder mapDecoder) {
        entities = new ArrayList<>();

        List<Point> entityPositions = mapDecoder.getEntityPositions();
        pacman = new PacMan(entityPositions.getFirst().x, entityPositions.getFirst().y, keyHandler);
        entities.add(pacman);
        System.out.println("created pacman");

        List<String> ghostColors = new ArrayList<>(Arrays.asList("", "ghost_cyan", "ghost_red", "ghost_orange", "ghost_pink"));
        for (int i = 1; i < entityPositions.size(); i++) {
            entities.add(new Ghost(entityPositions.get(i).x, entityPositions.get(i).y, ghostColors.get(i), pacman));
            System.out.println(STR."created \{ghostColors.get(i)}");
        }
    }
    public void init() {
        scoreText = new Text(STR."Current score: \{score}", 20, new Point(50, 50));
        texts.add(scoreText);
    }

    public void update() {

        for (Object object : gameObjects) {
            if (object.getHitbox().collidesWith(pacman.getHitbox())) {
                gameObjectsToRemove.add(object);
                score += object.getScoreValue();
                System.out.println(STR."score update: \{score}");
                scoreText.setContent(STR."Current score: \{score}");
                break;
            }
        }
        gameObjects.removeAll(gameObjectsToRemove);
        gameObjectsToRemove.clear();

        if (gameObjects.isEmpty()) win = true;

        for (Entity entity : entities) {
            entity.update(this);
            if (entity != pacman && entity.getHitbox().collidesWith(pacman.getHitbox())) {
                lose = true;
            }
        }
    }
    public List<Object> getGameObjects() {
        gameObjects.removeAll(gameObjectsToRemove);
        return gameObjects;
    }
    public List<Entity> getEntities() {
        return entities;
    }
    public List<Wall> getWalls() {
        return walls;
    }
    public boolean hasWall(Point grid) {
        return hasWall[grid.x][grid.y];
    }
    public int getScore() {
        return score;
    }
    public boolean gameWon() {
        return win;
    }
    public boolean gameLost() {
        return lose;
    }
}