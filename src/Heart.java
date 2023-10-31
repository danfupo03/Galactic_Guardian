/*----------------------------------------------------------------
*
* Heart.java
* Fecha: 27-Oct-2023
* Autor: Daniel Emilio Fuentes - A01708302
*
*--------------------------------------------------------------*/

import javax.swing.ImageIcon;
import java.awt.Image;

public class Heart extends Sprite {
    private static final int INITIAL_X = 450;

    public Heart(int x, int y) {
        super(x, y);
    }

    public void loadImage() {
        ImageIcon ii = new ImageIcon("assets/images/heart.png");
        image = ii.getImage();

        Image resizedImage = image.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        image = resizedImage;
    }

    public void move() {
        if (x < 0) {
            x = INITIAL_X;
        }
        x--;
    }
}
