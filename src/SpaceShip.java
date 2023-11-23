/*----------------------------------------------------------------
*
* Spaceship.java
* Autor: Danfupo03
*
*--------------------------------------------------------------*/

import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import java.util.ArrayList;
import java.util.List;

public class SpaceShip extends Sprite {
    private static final int B_WIDTH = 450;
    private static final int B_HEIGHT = 285;

    private int dx, dy;
    private List<Missile> missiles;
    Sound sound = new Sound();

    private List<Particle> particles;
    private int particleLife = 10;
    private int particleIndex = 0;
    private int particleCounter = 0;

    /**
     * Constructor of the SpaceShip class
     */
    public SpaceShip() {
        super(40, 50);
        missiles = new ArrayList<Missile>();
        particles = new ArrayList<Particle>();
    }

    /**
     * Loads the image/sprite of the spaceship
     */
    public void loadImage() {
        ImageIcon ii = new ImageIcon("assets/images/craft.png");
        image = ii.getImage();
    }

    /**
     * Moves the spaceship
     * if it goes out of bounds, it is moved to the other side
     * and a particle is added.
     */
    public void move() {
        x += dx;
        y += dy;

        if (x > B_WIDTH) {
            x = 0;
        }

        if (x < 0) {
            x = B_WIDTH - width;
        }

        if (y > B_HEIGHT) {
            y = 0;
        }

        if (y < 0) {
            y = B_HEIGHT - height;
        }

        if (dx != 0 || dy != 0) {
            addParticle();
        }
    }

    /**
     * Adds a particle to the spaceship when it moves
     */
    public void addParticle() {
        particles.add(new Particle(x, y + height / 2 - 3, particleLife, particleIndex));
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
     * Returns the missiles of the spaceship when it fires
     * 
     * @return
     */
    public List<Missile> getMissiles() {
        return missiles;
    }

    /**
     * Returns the moving particles of the spaceship
     * 
     * @return
     */
    public List<Particle> getParticles() {
        return particles;
    }

    /**
     * Moving method of the spaceship, it is called when a key is pressed
     * the spaceship moves in the direction of the key pressed
     * if the space key is pressed, it fires a missile
     * if the w key is pressed, it moves up
     * if the s key is pressed, it moves down
     * if the a key is pressed, it moves left
     * if the d key is pressed, it moves right
     * 
     * @param e
     */
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            dx = -2;
        }
        if (key == KeyEvent.VK_RIGHT) {
            dx = 2;
        }
        if (key == KeyEvent.VK_UP) {
            dy = -2;
        }
        if (key == KeyEvent.VK_DOWN) {
            dy = 2;
        }
        if (key == KeyEvent.VK_SPACE) {
            fire();
        }
        if (key == KeyEvent.VK_W) {
            dy = -2;
        }
        if (key == KeyEvent.VK_S) {
            dy = 2;
        }
        if (key == KeyEvent.VK_A) {
            dx = -2;
        }
        if (key == KeyEvent.VK_D) {
            dx = 2;
        }
    }

    /**
     * Moving method of the spaceship, it is called when a key is released
     * the spaceship stops moving in the direction of the key released
     * 
     * @param e
     */
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }
        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }
        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }
        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
        if (key == KeyEvent.VK_W) {
            dy = 0;
        }
        if (key == KeyEvent.VK_S) {
            dy = 0;
        }
        if (key == KeyEvent.VK_A) {
            dx = 0;
        }
        if (key == KeyEvent.VK_D) {
            dx = 0;
        }
    }

    /**
     * Fires a missile from the spaceship
     */
    public void fire() {
        missiles.add(new Missile(x + width, y + height / 2));
        sound.setFile(7);
        sound.play();
    }
}