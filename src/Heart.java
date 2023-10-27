/*----------------------------------------------------------------
*
* Heart.java
* Fecha: 27-Oct-2023
* Autor: Daniel Emilio Fuentes - A01708302
*
*--------------------------------------------------------------*/

import javax.swing.ImageIcon;

public class Heart extends Sprite {
    public Heart(int x, int y) {
        super(x, y);
    }

    public void loadImage() {
        ImageIcon ii = new ImageIcon("assets/images/craft.png");
        image = ii.getImage();
    }
}
