/*----------------------------------------------------------------
*
* Alien.java
* Autor: Danfupo03
*
*--------------------------------------------------------------*/

import javax.swing.ImageIcon;
import java.util.ArrayList;
import java.util.List;

public class Alien extends Sprite {
    private static final int INITIAL_X = 450;

    private List<Particle> particles;
    private int particleLife = 5;
    private int particleIndex = 0;
    private int particleCounter = 0;

    /**
     * Constructor of the Alien class
     * 
     * @param x
     * @param y
     */
    public Alien(int x, int y) {
        super(x, y);
        particles = new ArrayList<Particle>();
    }

    /**
     * Loads the image/sprite of the alien
     */
    public void loadImage() {
        ImageIcon ii = new ImageIcon("assets/images/alien.png");
        image = ii.getImage();
    }

    /**
     * Returns the moving particles of the alien
     * 
     * @return
     */
    public List<Particle> getParticles() {
        return particles;
    }

    /**
     * Adds a particle to the alien when it moves
     */
    public void addParticle() {
        particles.add(new Particle(x + width / 2, y + height / 2 - 2, particleLife, particleIndex));
        particleCounter++;

        if (particleCounter >= 5) {
            particleCounter = 0;
            particleIndex++;
            if (particleIndex > 2) {
                particleIndex = 0;
            }
        }
    }

    /**
     * Moving method of the alien to the left
     * if the alien is out of the screen, it will be
     * repositioned to the right of the screen.
     */
    public void move() {
        if (x < 0) {
            x = INITIAL_X;
            y = 5 + (int) (Math.random() * 281);
        }
        x--;
        addParticle();
    }

    /**
     * Returns the x coordinate of the alien
     * 
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Returns the y coordinate of the alien
     * 
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }
}
