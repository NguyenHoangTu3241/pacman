package object;

import misc.Edible;
import misc.Hitbox;

public class Pellet extends Object implements Edible {

    public Pellet(int col, int row) {
        super("pellet", 10, col, row);
        hitbox = new Hitbox(position.x + 6, position.y + 6, 4, 4);
    }

}