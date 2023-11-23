/*----------------------------------------------------------------
*
* Sprite.java
* Autor: Danfupo03
*
*--------------------------------------------------------------*/

import java.awt.*;

abstract class Sprite {
    protected int x, y, width, height;
    protected boolean visible;
    protected Image image;

    /**
     * Constructor of the Sprite class
     * @param x
     * @param y
     */
    public Sprite(int x, int y) {
        this.x = x;
        this.y = y;
        this.visible = true;
        loadImage();
        width = image.getWidth(null);
        height = image.getHeight(null);
    }

    /**
     * Abstract method to load the image/sprite of the sprites
     */
    abstract void loadImage(); 

    /**
     * Returns the x coordinate of the sprite
     * @return
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the y coordinate of the sprite
     * @return
     */
    public int getY() {
        return y;
    }

    /**
     * Returns the width of the sprite
     * @return
     */
    public int getWidth() {
        return width; 
    }

    /**
     * Returns the height of the sprite
     * @return
     */
    public int getHeight() {
        return height;
    }

    /**
     * Returns the image of the sprite
     * @return
     */
    public Image getImage() {
        return image;
    }

    /**
     * Returns the visibility of the sprite
     * @return
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * Sets the visibility of the sprite
     * @param visible
     */
    public void setVisible(boolean visible) {
        this.visible = visible; 
    }

    /**
     * Returns the bounds of the sprite
     * @return
     */
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}