package state;

import control.KeyHandler;
import misc.Gif;
import misc.Text;

import java.awt.*;
import java.io.*;

public class GameOver extends State {
    private int score, stage, bonusscore, bestScore;

    public GameOver(KeyHandler _keyHandler, int _score, int _stage) {
        super(_keyHandler);
        score = _score;
        stage = _stage;
        bonusscore = (stage - 1) * 1000;
        bestScore = readBestScore();
        if (score + bonusscore > bestScore) {
            bestScore = score + bonusscore;
            writeBestScore();
        }
        init();
    }

    @Override
    public void init() {
        texts.add(new Text("< press any key to return to menu >", 25, new Point(120, 520)));
        texts.add(new Text(STR."Gained score: \{score}", 20, new Point(100, 350)));
        texts.add(new Text(STR."Stage bonus: \{bonusscore}", 20, new Point(450, 350)));
        texts.add(new Text(STR."Total score: \{score + bonusscore}", 20, new Point(100, 450)));
        texts.add(new Text(STR."Best score: \{bestScore}", 20, new Point(450, 450)));

        gifs.add(new Gif(1, "/sprites/ui/game_over.png", new Point(-40, 50)));
    }

    @Override
    public void update() {
    }

    public boolean gameMenu() {
        return keyHandler.isMenu();
    }

    private int readBestScore() {
        try (BufferedReader reader = new BufferedReader(new FileReader("pacman/res/bestscore.txt"))) {
            String line = reader.readLine();
            if (line != null) {
                return Integer.parseInt(line);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private void writeBestScore() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("pacman/res/bestscore.txt"))) {
            writer.write(String.valueOf(bestScore));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}