package state;

import audio.Sound;
import control.KeyHandler;
import entity.Entity;
import entity.Ghost;
import entity.PacMan;
import map.MapDecoder;
import map.Wall;
import misc.Gif;
import misc.Text;
import object.Object;
import object.PowerPellet;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameState extends State {
    private List<Entity> entities;
    private List<Object> gameObjects;
    private final List<Object> gameObjectsToRemove;
    private final List<Wall> walls;
    private final boolean[][] hasWall;
    PacMan pacman;
    private int score = 0;
    private Text scoreText;
    private Gif life, death;
    private boolean win = false, lose = false;
    private Sound newGameSound, nomnomSound, deathSound;
    String action;

    public GameState(KeyHandler _keyHandler, MapDecoder mapDecoder){
        super(_keyHandler);
        gameObjectsToRemove = new ArrayList<>();
        walls = mapDecoder.getWalls();
        hasWall = mapDecoder.getHasWall();
        entities = new ArrayList<>();

        init();
        reset(mapDecoder);
    }

    public void reset(MapDecoder mapDecoder) {
        gameObjects = mapDecoder.getGameObjects();
        entities.clear();
        List<Point> entityPositions = mapDecoder.getEntityPositions();
        pacman = new PacMan(entityPositions.getFirst().x, entityPositions.getFirst().y, keyHandler);
        entities.addFirst(pacman);

        List<String> ghostColors = new ArrayList<>(Arrays.asList("", "ghost_cyan", "ghost_red", "ghost_orange", "ghost_pink"));
        for (int i = 1; i < entityPositions.size(); i++) {
            entities.add(new Ghost(entityPositions.get(i).x, entityPositions.get(i).y, ghostColors.get(i), pacman));
        }
        action = "start";
        newGameSound.play();
    }
    public void init() {
        scoreText = new Text(STR."Current score: \{score}", 20, new Point(31, 30));
        texts.add(scoreText);

        life = new Gif(3, "/sprites/ui/life.png", new Point(710, 50));
        death = new Gif(11, "/sprites/entities/dead.png", new Point(0, 0), 9);
        gifs.add(life);


        newGameSound = new Sound("newGame");
        deathSound = new Sound("death");
        nomnomSound = new Sound("nomnom");
    }

    public void update() {
        if (gameObjects.isEmpty()) {
            win = true;
            nomnomSound.stop();
            return;
        }
        switch (action) {
            case "start":
                if (!newGameSound.isRunning()) {
                    action = "update";
                }
                break;

            case "die":
                if (deathSound.isRunning()) {
                    death.update();
                    break;
                }
                if (pacman.getLife() > 0) {
                    action = "respawn";
                } else {
                    lose = true;
                }
                break;

            case "respawn":
                life.update();
                gifs.removeFirst();
                entities.addFirst(pacman);
                for (Entity entity1 : entities) {
                    entity1.respawn();
                }
                action = "update";
                break;

            case "update":
                if (pacman.update) {
                    nomnomSound.loop();
                } else {
                    nomnomSound.stop();
                }

                for (Entity entity : entities) {
                    entity.update(this);
                    if (entity != pacman && entity.getHitbox().collidesWith(pacman.getHitbox()))
                        if (((Ghost) entity).isFleeing()) {
                            gainScore(((Ghost) entity).getScoreValue());
                            entity.respawn();
                        }
                        else {
                            pacman.die();
                            nomnomSound.stop();
                            deathSound.play();
                            action = "die";
                            entities.removeFirst();
                            gifs.addFirst(death);
                            death.setFrame(0);
                            death.setPosition(pacman.getPosition());
                            break;
                        }
                }

                for (Object object : gameObjects) {
                    if (object.getHitbox().collidesWith(pacman.getHitbox())) {
                        gameObjectsToRemove.add(object);
                        gainScore(object.getScoreValue());
                        if (object instanceof PowerPellet) {
                            startFleeingGhosts();
                        }
                        break;
                    }
                }
                break;
            default:
                action = "break";
                break;
        }

    }
    private void gainScore(int _score) {
        score += _score;
        scoreText.setContent(STR."Current score: \{score}");
    }
    private void startFleeingGhosts() {
        for (Entity entity : entities) {
            if (entity instanceof Ghost) {
                ((Ghost) entity).startFleeing(5);
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