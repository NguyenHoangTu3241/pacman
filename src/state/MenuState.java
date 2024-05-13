package state;

import control.KeyHandler;
import misc.CurrentState;
import misc.Text;
import java.util.ArrayList;
import java.util.List;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class MenuState extends State {
    private Text titleshadow, title, designer, description;
    private int count = 0;
    public MenuState(KeyHandler _keyHandler) {
        super(_keyHandler);
        init();
    }
    @Override
    public void init() {
        texts.clear();
        titleshadow = new Text("PACMAN GAME", 75, new Point(96, 96));
        titleshadow.setColor(Color.DARK_GRAY);
        title = new Text("PACMAN GAME", 75, new Point(100, 100));
        title.setColor(Color.YELLOW);
        designer = new Text("made by Nguyễn Hoàng Tú", 20, new Point(500, 150));
        description = new Text("< press any key to start >", 32, new Point(200, 500));
        texts.add(titleshadow);
        texts.add(title);
        texts.add(designer);
        texts.add(description);
    }

    @Override
    public void update() {
        count++;
        if (count == 40) description.setColor(Color.DARK_GRAY);
        else if (count == 80){
            description.setColor(Color.GRAY);
            count = 0;
        }
    }
    public boolean gameStart() {
        return keyHandler.isGameStarted();
    }
}