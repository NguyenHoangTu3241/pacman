package misc;

public enum Direction {
    UP(0), DOWN(2), LEFT(3), RIGHT(1);

    private int spriteRow;

    Direction(int _spriteRow) {
        spriteRow = _spriteRow;
    }

    public int getSpriteRow() {
        return spriteRow;
    }
}
