package misc;

import java.awt.*;

public class Text {
    private String content;
    private final Font font;
    private Color color;
    private final int size;
    private Point position;
    public Text(String _content, int _size, Point _position) {
        content = _content;
        size = _size;
        font = new Font("Arial", Font.BOLD, _size);
        color = Color.WHITE;
        position = _position;
    }

    public void setContent(String _content) {
        content = _content;
    }
    public void setPosition(Point _position) {
        position = _position;
    }
    public void setColor(Color _color) {
        color = _color;
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