import java.awt.image.BufferedImage;
import java.awt.Image;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;
import java.awt.Rectangle;

public class Shield extends Sprite {
    private int originalWidth, originalHeight;

    public Shield(int x, int y) {
        super(x, y);
    }

    public void loadImage() {
        ImageIcon ii = new ImageIcon("assets/images/shield.png");
        Image originalImage = ii.getImage();

        originalWidth = originalImage.getWidth(null);
        originalHeight = originalImage.getHeight(null);

        BufferedImage resizedImage = new BufferedImage(25, 25, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resizedImage.createGraphics();
        g2d.drawImage(originalImage, 0, 0, 25, 25, null);
        g2d.dispose();

        image = resizedImage;

        width = image.getWidth(null);
        height = image.getHeight(null);
    }

    public void move() {
        x--;
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