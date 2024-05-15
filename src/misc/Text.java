package misc;

import java.awt.*;

public class Text {
    private String content;
    private final Font font;
    private final Color color;
    private final Point position;
    public Text(String _content, int _size, Point _position) {
        content = _content;
        font = new Font("Consolas", Font.PLAIN, _size);
        color = Color.WHITE;
        position = _position;
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