package misc;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Text {
    private String content;
    private final Font font;
    private final Color color;
    private final Point position;

    public Text(String _content, int _size, Point _position) {
        content = _content;
        font = loadFont(_size);
        color = Color.WHITE;
        position = _position;
    }

    private Font loadFont(int _size) {
        Font customFont = null;
        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("res/font/pixelfont.ttf")).deriveFont(Font.PLAIN, _size);
        } catch (IOException | FontFormatException e) {
            System.out.println("Error loading font");
            System.out.println(e.getMessage());
        }
        return customFont;
    }

    public void setContent(String _content) {
        content = _content;
    }

    public String getContent() {
        return content;
    }

    public Font getFont() {
        return font;
    }

    public Point getPosition() {
        return position;
    }

    public Color getColor() {
        return color;
    }
}