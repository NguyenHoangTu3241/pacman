package map;

import main.Panel;
import object.Object;
import object.Pellet;
import object.PowerPellet;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MapDecoder {
    private final List<Point> entityPositions;
    private final List<Object> gameObjects;
    private final char[][] mapTile = new char[Panel.MAP_COL][Panel.MAP_ROW];
    private final List<Wall> walls;
    private final boolean[][] hasWall;
    private final ArrayList<String> wallNames = new ArrayList<>(Arrays.asList("╔", "╗", "╚", "╝", "═", "║", "╩", "╦", "╠", "╣", "╘", "╕", "╓", "╜"));
    public MapDecoder() {
        entityPositions = new ArrayList<>();
        gameObjects = new ArrayList<>();
        walls = new ArrayList<>();
        hasWall = new boolean[main.Panel.MAP_COL][main.Panel.MAP_ROW];
        loadMap();
        readMap();
        System.out.println("Completed initializing map");
        int space = 0;
        for (int row = 0; row < main.Panel.MAP_ROW; row++) {
            for (int col = 0; col < main.Panel.MAP_COL; col++) {
                if (!hasWall[col][row]) space++;
            }
        }
        System.out.println("Space: " + space);
    }
    private void loadMap() {
        try {
            InputStream is = getClass().getResourceAsStream("/encodedmap.txt");
            assert is != null;
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            for (int row = 0; row < Panel.MAP_ROW; row++) {
                String line = br.readLine();
                for (int col = 0; col < Panel.MAP_COL; col++) {
                    mapTile[col][row] = line.charAt(col);
                }
            }
            br.close();
        }
        catch (IOException e) {
            System.out.println("Error loading encodedmap.txt for Decoder");
        }
    }

    private void readMap() {
        for (int row = 0; row < main.Panel.MAP_ROW; row++) {
            for (int col = 0; col < main.Panel.MAP_COL; col++) {
                char name = mapTile[col][row];
                if (wallNames.contains(STR."\{name}")) {
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
                    entityPositions.add(new Point(Panel.MAP_X + col * Panel.SPRITE_SIZE, Panel.MAP_Y + row * Panel.SPRITE_SIZE));
                }
                else if (name == 'b') {
                    entityPositions.add(new Point(main.Panel.MAP_X + col * main.Panel.SPRITE_SIZE, main.Panel.MAP_Y + row * main.Panel.SPRITE_SIZE));
                }
                else if (name == 'c') {
                    entityPositions.add(new Point(main.Panel.MAP_X + col * main.Panel.SPRITE_SIZE, main.Panel.MAP_Y + row * main.Panel.SPRITE_SIZE));
                }
                else if (name == 'd') {
                    entityPositions.add(new Point(main.Panel.MAP_X + col * main.Panel.SPRITE_SIZE, main.Panel.MAP_Y + row * main.Panel.SPRITE_SIZE));
                }
                else if (name == 'p') {
                    entityPositions.addFirst(new Point(main.Panel.MAP_X + col * main.Panel.SPRITE_SIZE, main.Panel.MAP_Y + row * main.Panel.SPRITE_SIZE));
                }
            }
        }
    }

    public char[][] getMapTile() {
        return mapTile;
    }
    public List<Point> getEntityPositions() {
        return entityPositions;
    }
    public List<Object> getGameObjects() {
        return new ArrayList<>(gameObjects);
    }
    public List<Wall> getWalls() {
        return walls;
    }
    public boolean[][] getHasWall() {
        return hasWall;
    }
}