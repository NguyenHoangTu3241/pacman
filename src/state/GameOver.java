package state;

import control.KeyHandler;
import misc.Gif;
import misc.Text;

import java.awt.*;
import java.io.*;
import java.net.URL;

public class GameOver extends State {
    private final int score;
    private final int bonusScore;
    private int bestScore;

    public GameOver(KeyHandler _keyHandler, int _score, int _stage) {
        super(_keyHandler);
        score = _score;
        bonusScore = (_stage - 1) * 1000;
        bestScore = readBestScore();
        if (score + bonusScore > bestScore) {
            bestScore = score + bonusScore;
            writeBestScore();
        }
        init();
    }

    @Override
    public void init() {
        texts.add(new Text("< press any key to return to menu >", 25, new Point(120, 520)));
        texts.add(new Text(STR."Gained score: \{score}", 20, new Point(100, 350)));
        texts.add(new Text(STR."Stage bonus: \{bonusScore}", 20, new Point(450, 350)));
        texts.add(new Text(STR."Total score: \{score + bonusScore}", 20, new Point(100, 450)));
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
        try {
            InputStream is = getClass().getResourceAsStream("/score/bestscore.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line = reader.readLine();
            if (line != null) {
                return Integer.parseInt(line);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    private void writeBestScore() {
        URL url = getClass().getResource("/score/bestscore.txt");
        if (url != null) {
            File file = new File(url.getPath());
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(String.valueOf(bestScore));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}