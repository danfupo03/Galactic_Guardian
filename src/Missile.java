import javax.swing.ImageIcon;

public class Missile extends Sprite {
    private static final int B_WIDTH = 390;
    private static final int MISSILE_SPEED = 2;

    public Missile(int x, int y) {
        super(x, y);
    }

    @Override
    public void loadImage() {
        ImageIcon ii = new ImageIcon("images/missile.png");
        image = ii.getImage();
    }

    public void move() {
        x += MISSILE_SPEED;
        if (x > B_WIDTH) {
            visible = false;
        }
    }

}
