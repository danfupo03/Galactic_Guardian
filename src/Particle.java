import javax.swing.ImageIcon;
import java.awt.Image;

public class Particle extends Sprite {
    private static final int B_WIDTH = 390;
    private static final int PARTICLE_SPEED = -2;

    private int life;

    private int imageIndex;

    public Particle(int x, int y, int life, int imageIndex) {
        super(x, y);
        this.life = life;
        this.imageIndex = imageIndex;
        loadImage();
    }

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

    public void move() {
        x += PARTICLE_SPEED;
        life--;

        if (x > B_WIDTH || life <= 0) {
            visible = false;
        }
    }
}
