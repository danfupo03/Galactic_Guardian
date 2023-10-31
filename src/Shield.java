import javax.swing.ImageIcon;
import java.awt.Image;

public class Shield extends Sprite {

    public Shield(int x, int y) {
        super(x, y);
    }

    public void loadImage() {
        ImageIcon ii = new ImageIcon("assets/images/shield.png");
        image = ii.getImage();

        Image resizedImage = image.getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        image = resizedImage;
    }

    public void move() {
        x--;
    }
}
