import java.awt.image.BufferedImage;
import java.awt.Image;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;
import java.awt.Rectangle;

public class Boss extends Sprite {
    private static final int B_HEIGHT = 250;
    private static final int BOSS_HEIGHT = 25;

    private int yDirection = 1;
    private int originalWidth, originalHeight;

    public Boss(int x, int y) {
        super(x, y);
    }

    public void loadImage() {
        ImageIcon ii = new ImageIcon("assets/images/boss.png");
        Image originalImage = ii.getImage();

        originalWidth = originalImage.getWidth(null);
        originalHeight = originalImage.getHeight(null);

        BufferedImage resizedImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resizedImage.createGraphics();
        g2d.drawImage(originalImage, 0, 0, 100, 100, null);
        g2d.dispose();

        image = resizedImage;

        width = image.getWidth(null);
        height = image.getHeight(null);
    }

    public void move() {
        y += yDirection;

        if (y <= 0 - BOSS_HEIGHT || y >= B_HEIGHT - BOSS_HEIGHT) {
            yDirection *= -1;
        }
    }

    public Rectangle getResizedBounds() {
        return new Rectangle(x, y, width, height);
    }

    public int getOriginalWidth() {
        return originalWidth;
    }

    public int getOriginalHeight() {
        return originalHeight;
    }
}