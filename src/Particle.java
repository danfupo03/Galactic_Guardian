import javax.swing.ImageIcon;
import java.awt.Image;

public class Particle extends Sprite {
    private static final int B_WIDTH = 390;
    private static final int MISSILE_SPEED = -2;

    public Particle(int x, int y) {
        super(x, y);
    }

    @Override
    public void loadImage() {
        ImageIcon ii = new ImageIcon("images/spr_exhaust.png");
        image = ii.getImage();

        int nWidth = image.getWidth(null) / 2;
        int nHeight = image.getHeight(null) / 2;

        Image scaledImage = image.getScaledInstance(nWidth, nHeight, Image.SCALE_SMOOTH);

        image = scaledImage;
    }

    public void move() {
        x += MISSILE_SPEED;
        if (x > B_WIDTH) {
            visible = false;
        }
    }
}
