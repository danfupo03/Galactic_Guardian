/*----------------------------------------------------------------
*
* Alien.java
* Fecha: 27-Oct-2023
* Autor: Daniel Emilio Fuentes - A01708302
*
*--------------------------------------------------------------*/

import javax.swing.ImageIcon;

public class Alien extends Sprite {
    private static final int INITIAL_X = 450;

    public Alien(int x, int y) {
        super(x, y);
    }

    public void loadImage() {
        ImageIcon ii = new ImageIcon("assets/images/alien.png");
        image = ii.getImage();
    }

    public void move() {
        if (x < 0) {
            x = INITIAL_X;
            y = 5 + (int) (Math.random() * 281);
        }
        x--;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this .y = y;
    }
}
