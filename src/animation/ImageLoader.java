package animation;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class ImageLoader {
    public static Image loadImage(String path) {
        try {
            return ImageIO.read(Objects.requireNonNull(ImageLoader.class.getResourceAsStream(path)));
        }
        catch (IOException e) {
            System.out.println(STR."Error loading sprite: \{path}");
        }
        return null;
    }
}