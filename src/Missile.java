/*----------------------------------------------------------------
*
* Missile.java
* Autor: Danfupo03
*
*--------------------------------------------------------------*/

import javax.swing.ImageIcon;

public class Missile extends Sprite {
    private static final int B_WIDTH = 450;
    private static final int MISSILE_SPEED = 2;

    /**
     * Constructor of the Missile class
     * 
     * @param x
     * @param y
     */
    public Missile(int x, int y) {
        super(x, y);
    }

    /**
     * Loads the image/sprite of the missile
     */
    @Override
    public void loadImage() {
        ImageIcon ii = new ImageIcon("assets/images/missile.png");
        image = ii.getImage();
    }

    /**
     * Moves the missile to the right
     * if it goes out of bounds, it is no longer visible
     * and it is removed from the missiles list.
     */
    public void move() {
        x += MISSILE_SPEED;
        if (x > B_WIDTH) {
            visible = false;
        }
    }

}
