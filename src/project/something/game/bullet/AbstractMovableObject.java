package project.something.game.bullet;


import java.awt.*;

/**
 * Created by Asus on 26.10.2020.
 */
public abstract class AbstractMovableObject extends AbstractBFObject {

    public AbstractMovableObject(int x, int y) {
        super(x, y);
    }

    public abstract void draw(Graphics g);
}
