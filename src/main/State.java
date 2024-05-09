package main;

import control.KeyHandler;
import entity.Entity;

import entity.Ghost;
import entity.PacMan;
import map.MapDecoder;
import map.Wall;
import object.Object;
import object.Pellet;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class State {
    private List<Entity> entities;
    private List<Object> gameObjects;
    private List<Object> gameObjectsToRemove;
    private KeyHandler keyHandler;
    private MapDecoder mapInitializer;
    private List<Wall> walls;
    private boolean[][] hasWall;
    private boolean[][] hasPellet;
    PacMan pacman;
    Ghost ghost_red, ghost_cyan, ghost_orange, ghost_pink;
    public State(KeyHandler _keyHandler) {
        keyHandler = _keyHandler;
        gameObjects = new ArrayList<>();
        gameObjectsToRemove = new ArrayList<>();
        entities = new ArrayList<>();
        mapInitializer = new MapDecoder();
        walls = new ArrayList<>();
        hasWall = new boolean[Panel.MAP_COL][Panel.MAP_ROW];
        hasPellet = new boolean[Panel.MAP_COL][Panel.MAP_ROW];
        reset();
    }

    public void respawn() {
        entities.clear();
    }
    public void reset() {
        entities.clear();
        gameObjects.clear();
        walls.clear();
        for (int row = 0; row < Panel.MAP_ROW; row++) {
            for (int col = 0; col < Panel.MAP_COL; col++) {
                char name = mapInitializer.mapTile[col][row];
                if (mapInitializer.wallNames.contains(STR."\{name}")) {
                    walls.add(new Wall(STR."\{name}", col, row));
                    hasWall[col][row] = true;
                }
                else hasWall[col][row] = false;
                if (name == '.') {
                    gameObjects.add(new Pellet(col, row));
                }
                else if (name == 'o') {}
                else if (name == 'a') {
                    ghost_cyan = new Ghost(Panel.MAP_X + col * Panel.SPRITE_SIZE, Panel.MAP_Y + row * Panel.SPRITE_SIZE, "ghost_cyan");
                    entities.add(ghost_cyan);
                    System.out.println("created a cyan ghost");
                }
                else if (name == 'b') {
                    ghost_red = new Ghost(Panel.MAP_X + col * Panel.SPRITE_SIZE, Panel.MAP_Y + row * Panel.SPRITE_SIZE, "ghost_red");
                    entities.add(ghost_red);
                    System.out.println("created a red ghost");
                }
                else if (name == 'c') {
                    ghost_orange = new Ghost(Panel.MAP_X + col * Panel.SPRITE_SIZE, Panel.MAP_Y + row * Panel.SPRITE_SIZE, "ghost_orange");
                    entities.add(ghost_orange);
                    System.out.println("created a orange ghost");
                }
                else if (name == 'd') {
                    ghost_pink = new Ghost(Panel.MAP_X + col * Panel.SPRITE_SIZE, Panel.MAP_Y + row * Panel.SPRITE_SIZE, "ghost_pink");
                    entities.add(ghost_pink);
                    System.out.println("created a pink ghost");
                }
                else if (name == 'p') {
                    pacman = new PacMan(Panel.MAP_X + col * Panel.SPRITE_SIZE, Panel.MAP_Y + row * Panel.SPRITE_SIZE, keyHandler);
                    entities.add(pacman);
                    System.out.println("created a pacman");
                }
            }
        }
    }

    public void update() {
        for (Entity entity : entities) {
            entity.update(this);
        }
        for (Object object : gameObjects) {
            if (object.getHitbox().collidesWith(pacman.getHitbox())) gameObjectsToRemove.add(object);
        }
        gameObjects.removeAll(gameObjectsToRemove);
        gameObjectsToRemove.clear();
    }
    public List<Object> getGameObjects() {
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
}