/*----------------------------------------------------------------
*
* Boss.java
* Autor: Danfupo03
*
*--------------------------------------------------------------*/

import java.awt.image.BufferedImage;
import java.awt.Image;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;
import java.awt.Rectangle;

public class Boss extends Sprite {
    private static final int B_HEIGHT = 250;
    private static final int BOSS_HEIGHT = 25;

    private int yDirection = 1;
    private int originalWidth, originalHeight;

    /**
     * Constructor of the Boss class
     * 
     * @param x
     * @param y
     */
    public Boss(int x, int y) {
        super(x, y);
    }

    /**
     * Loads the image/sprite of the boss
     */
    public void loadImage() {
        ImageIcon ii = new ImageIcon("assets/images/boss.png");
        Image originalImage = ii.getImage();

        originalWidth = originalImage.getWidth(null);
        originalHeight = originalImage.getHeight(null);

        BufferedImage resizedImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resizedImage.createGraphics();
        g2d.drawImage(originalImage, 0, 0, 100, 100, null);
        g2d.dispose();

        image = resizedImage;

        width = image.getWidth(null);
        height = image.getHeight(null);
    }

    /**
     * Moves the boss up and down
     */
    public void move() {
        y += yDirection;

        if (y <= 0 - BOSS_HEIGHT || y >= B_HEIGHT - BOSS_HEIGHT) {
            yDirection *= -1;
        }
    }

    /**
     * Returns the resized bounds of the boss
     * 
     * @return
     */
    public Rectangle getResizedBounds() {
        return new Rectangle(x, y, width, height);
    }

    /**
     * Returns the original width of the boss
     * 
     * @return
     */
    public int getOriginalWidth() {
        return originalWidth;
    }

    /**
     * Returns the original height of the boss
     * 
     * @return
     */
    public int getOriginalHeight() {
        return originalHeight;
    }
}