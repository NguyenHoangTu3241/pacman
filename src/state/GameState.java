package state;

import control.KeyHandler;
import entity.Entity;

import entity.Ghost;
import entity.PacMan;
import map.MapDecoder;
import map.Wall;
import misc.CurrentState;
import object.Object;
import object.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameState extends State {
    private List<Entity> entities;
    private List<Object> gameObjects;
    private List<Object> gameObjectsToRemove;
    private MapDecoder mapInitializer;
    private List<Wall> walls;
    private boolean[][] hasWall;
    PacMan pacman;
    Ghost ghost_red, ghost_cyan, ghost_orange, ghost_pink;
    Point init_ghost_red, init_ghost_cyan, init_ghost_orange, init_ghost_pink, init_pacman;
    private int score = 0;
    private boolean win = false, lose = false;
    public GameState(KeyHandler _keyHandler){
        super(_keyHandler);
        gameObjects = new ArrayList<>();
        gameObjectsToRemove = new ArrayList<>();
        entities = new ArrayList<>();
        mapInitializer = new MapDecoder();
        walls = new ArrayList<>();
        hasWall = new boolean[main.Panel.MAP_COL][main.Panel.MAP_ROW];
        init();
    }

    public void respawn() {
        entities.clear();
    }
    public void init() {
        entities.clear();
        gameObjects.clear();
        walls.clear();
        for (int row = 0; row < main.Panel.MAP_ROW; row++) {
            for (int col = 0; col < main.Panel.MAP_COL; col++) {
                char name = mapInitializer.mapTile[col][row];
                if (mapInitializer.wallNames.contains(STR."\{name}")) {
                    walls.add(new Wall(STR."\{name}", col, row));
                    hasWall[col][row] = true;
                }
                else hasWall[col][row] = false;
                if (name == '.') {
                    gameObjects.add(new Pellet(col, row));
                }
                else if (name == 'o') {
                    gameObjects.add(new PowerPellet(col, row));
                }
                else if (name == 'a') {
                    init_ghost_cyan = new Point(main.Panel.MAP_X + col * main.Panel.SPRITE_SIZE, main.Panel.MAP_Y + row * main.Panel.SPRITE_SIZE);
                }
                else if (name == 'b') {
                    init_ghost_red = new Point(main.Panel.MAP_X + col * main.Panel.SPRITE_SIZE, main.Panel.MAP_Y + row * main.Panel.SPRITE_SIZE);
                }
                else if (name == 'c') {
                    init_ghost_orange = new Point(main.Panel.MAP_X + col * main.Panel.SPRITE_SIZE, main.Panel.MAP_Y + row * main.Panel.SPRITE_SIZE);
                }
                else if (name == 'd') {
                    init_ghost_pink = new Point(main.Panel.MAP_X + col * main.Panel.SPRITE_SIZE, main.Panel.MAP_Y + row * main.Panel.SPRITE_SIZE);
                }
                else if (name == 'p') {
                    init_pacman = new Point(main.Panel.MAP_X + col * main.Panel.SPRITE_SIZE, main.Panel.MAP_Y + row * main.Panel.SPRITE_SIZE);
                }
            }
        }
        ghost_cyan = new Ghost(init_ghost_cyan.x, init_ghost_cyan.y, "ghost_cyan");
        ghost_red = new Ghost(init_ghost_red.x, init_ghost_red.y, "ghost_red");
        ghost_orange = new Ghost(init_ghost_orange.x, init_ghost_orange.y, "ghost_orange");
        ghost_pink = new Ghost(init_ghost_pink.x, init_ghost_pink.y, "ghost_pink");
        pacman = new PacMan(init_pacman.x, init_pacman.y, keyHandler);
        entities.add(ghost_cyan);
        entities.add(ghost_red);
        entities.add(ghost_orange);
        entities.add(ghost_pink);
        entities.add(pacman);

    }

    public void update() {
        for (Entity entity : entities) {
            entity.update(this);
            if (entity != pacman && entity.getHitbox().collidesWith(pacman.getHitbox())) {
                lose = true;
            }
        }
        for (Object object : gameObjects) {
            if (object.getHitbox().collidesWith(pacman.getHitbox())) {
                gameObjectsToRemove.add(object);
                score += 10;
                System.out.println("score update: " + score);
            }
        }
        gameObjects.removeAll(gameObjectsToRemove);
        gameObjectsToRemove.clear();

        if (gameObjects.isEmpty()) win = true;
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