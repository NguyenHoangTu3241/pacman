package map;

import main.Panel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
public class MapDecoder {
    public char[][] mapTile = new char[Panel.MAP_COL][Panel.MAP_ROW];
    public Map<String, Wall> wallSprites;
    public ArrayList<String> wallNames = new ArrayList<>(Arrays.asList("╔", "╗", "╚", "╝", "═", "║", "╩", "╦", "╠", "╣", "╘", "╕", "╓", "╜"));
    public MapDecoder() {
        wallSprites = new HashMap<>();
        loadMap();
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

}