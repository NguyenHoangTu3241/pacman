package animation;

import misc.Direction;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Animator {
    BufferedImage sprite;
    private final int updatesPerFrame;
    private int currentFrame;
    private int frameIndex;
    private int directionIndex;
    public Animator(BufferedImage _sprite) {
        sprite = _sprite;
        updatesPerFrame = 10;
        currentFrame = 0;
        frameIndex = 0;
        directionIndex = 0;
    }

    public void updateSprite(Direction direction) {
        currentFrame++;
        directionIndex = direction.getSpriteRow();
        if(currentFrame >= updatesPerFrame) {
            currentFrame = 0;
            frameIndex++;
            if(frameIndex > sprite.getWidth()/ main.Panel.SPRITE_SIZE - 1) {
                frameIndex = 0;
            }
        }
    }

    public Image getSprite() {
        return (sprite.getSubimage(frameIndex * main.Panel.SPRITE_SIZE, directionIndex * main.Panel.SPRITE_SIZE, main.Panel.SPRITE_SIZE, main.Panel.SPRITE_SIZE));
    }
}