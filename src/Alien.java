import javax.swing.ImageIcon;

public class Alien extends Sprite {
    private static final int INITIAL_X = 400;

    public Alien(int x, int y) {
        super(x, y);
    }

    public void loadImage() {
        ImageIcon ii = new ImageIcon("images/alien.png");
        image = ii.getImage();
    }

    public void move() {
        if (x < 0) {
            x = INITIAL_X;
        }
        x--;
    }
}
