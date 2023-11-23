/*----------------------------------------------------------------
*
* Heart.java
* Autor: Danfupo03
*
*--------------------------------------------------------------*/

import javax.swing.ImageIcon;
import java.awt.Image;

public class Heart extends Sprite {

    /**
     * Constructor of the Heart class
     * 
     * @param x
     * @param y
     */
    public Heart(int x, int y) {
        super(x, y);
    }

    /**
     * Loads the image/sprite of the heart
     */
    public void loadImage() {
        ImageIcon ii = new ImageIcon("assets/images/heart.png");
        image = ii.getImage();

        Image resizedImage = image.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        image = resizedImage;
    }
}
