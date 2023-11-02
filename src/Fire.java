import javax.swing.ImageIcon;
import java.awt.Image;

public class Fire extends Sprite {

    private static final int INITIAL_X = 450;

    public Fire(int x, int y) {
        super(x, y);
    }

    public void loadImage() {
        ImageIcon ii = new ImageIcon("assets/images/fire.png");
        image = ii.getImage();

        Image resizedImage = image.getScaledInstance(15, 15, Image.SCALE_DEFAULT);
        image = resizedImage;
    }

    public void move() {
        if (x < 0) {
            x = INITIAL_X;
        }
        x--;
    }

}