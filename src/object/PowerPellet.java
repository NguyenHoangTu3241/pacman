package object;

import misc.Edible;
import misc.Hitbox;

public class PowerPellet extends Object implements Edible {

    public PowerPellet(int col, int row) {
        super("powerpellet", 20, col, row);
        hitbox = new Hitbox(position.x + 2, position.y + 2, 12, 12);
    }

}