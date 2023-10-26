import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import java.util.ArrayList;
import java.util.List;

public class SpaceShip extends Sprite {
    private int dx, dy;
    private List<Missile> missiles;
    private List<Particle> particles;

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

        if (dx != 0 || dy != 0) {
            particles.add(new Particle(x, y + height / 2));
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
    }
}