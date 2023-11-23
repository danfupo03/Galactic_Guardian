/*----------------------------------------------------------------
*
* Particle.java
* Autor: Danfupo03
*
*--------------------------------------------------------------*/

import javax.swing.ImageIcon;
import java.awt.Image;

public class Particle extends Sprite {
    private static final int B_WIDTH = 390;
    private static final int PARTICLE_SPEED = -2;
    private static final int PARTICLE_ALIEN_SPEED = 2;

    private int life;

    private int imageIndex;

    /**
     * Constructor of the Particle class
     * 
     * @param x
     * @param y
     * @param life
     * @param imageIndex
     */
    public Particle(int x, int y, int life, int imageIndex) {
        super(x, y);
        this.life = life;
        this.imageIndex = imageIndex;
        loadImage();
    }

    /**
     * Loads the images/sprites of the particles
     */
    @Override
    public void loadImage() {
        ImageIcon[] particleImages = {
                new ImageIcon("assets/images/particles_yellow.png"),
                new ImageIcon("assets/images/particles_orange.png"),
                new ImageIcon("assets/images/particles_red.png")
        };

        image = particleImages[imageIndex].getImage();
        image = image.getScaledInstance(5, 5, Image.SCALE_SMOOTH);
    }

    /**
     * Moves the particles to the right
     * if it goes out of bounds, it is no longer visible
     * and it is removed from the particles list.
     * This are the particles of the player.
     */
    public void move() {
        x += PARTICLE_SPEED;
        life--;

        if (x > B_WIDTH || life <= 0) {
            visible = false;
        }
    }

    /**
     * Moves the particles to the left
     * if it goes out of bounds, it is no longer visible
     * and it is removed from the particles list.
     * This are the particles of the alien.
     */
    public void moveAlien() {
        x += PARTICLE_ALIEN_SPEED;
        life--;

        if (life <= 0) {
            visible = false;
        }
    }
}
