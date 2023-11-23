/*----------------------------------------------------------------
*
* Fire.java
* Autor: Danfupo03
*
*--------------------------------------------------------------*/

import java.awt.image.BufferedImage;
import java.awt.Image;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;
import java.awt.Rectangle;

public class Fire extends Sprite {
    private int originalWidth, originalHeight;

    /**
     * Constructor of the Fire class
     * @param x
     * @param y
     */
    public Fire(int x, int y) {
        super(x, y);
    }

    /**
     * Loads the image/sprite of the fire
     */
    public void loadImage() {
        ImageIcon ii = new ImageIcon("assets/images/fire.png");
        Image originalImage = ii.getImage();

        originalWidth = originalImage.getWidth(null);
        originalHeight = originalImage.getHeight(null);

        BufferedImage resizedImage = new BufferedImage(25, 25, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resizedImage.createGraphics();
        g2d.drawImage(originalImage, 0, 0, 25, 25, null);
        g2d.dispose();

        image = resizedImage;

        width = image.getWidth(null);
        height = image.getHeight(null);
    }

    /**
     * Moves the fire powerup to the left
     */
    public void move() {
        x--;
    }

    /**
     * Returns the resized bounds of the fire powerup
     * @return
     */
    public Rectangle getResizedBounds() {
        return new Rectangle(x, y, width, height);
    }

    /**
     * Returns the original width of the fire powerup
     * @return
     */
    public int getOriginalWidth() {
        return originalWidth;
    }

    /**
     * Returns the original height of the fire powerup
     * @return
     */
    public int getOriginalHeight() {
        return originalHeight;
    }
}