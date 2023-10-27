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

    public SpaceShip() {
        super(40, 50);
        missiles = new ArrayList<Missile>();
        particles = new ArrayList<Particle>();
    }

    public void loadImage() {
        ImageIcon ii = new ImageIcon("assets/images/craft.png");
        image = ii.getImage();
    }

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

    public List<Missile> getMissiles() {
        return missiles;
    }

    public List<Particle> getParticles() {
        return particles;
    }

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
    }

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
    }

    public void fire() {
        missiles.add(new Missile(x + width, y + height / 2));
        sound.setFile(7);
        sound.play();
    }
}