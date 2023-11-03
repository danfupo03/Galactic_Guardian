/*----------------------------------------------------------------
*
* Alien.java
* Fecha: 27-Oct-2023
* Autor: Daniel Emilio Fuentes - A01708302
*
*--------------------------------------------------------------*/

import javax.swing.ImageIcon;
// import java.awt.image.BufferedImage;
// import java.awt.Image;
// import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
//import java.awt.Rectangle;

public class Alien extends Sprite {
    private static final int INITIAL_X = 450;

    //private int originalWidth, originalHeight;

    private List<Particle> particles;
    private int particleLife = 5;
    private int particleIndex = 0;
    private int particleCounter = 0;

    public Alien(int x, int y) {
        super(x, y);
        particles = new ArrayList<Particle>();
    }

    public void loadImage() {
        ImageIcon ii = new ImageIcon("assets/images/alien.png");
        image = ii.getImage();
        
        // Image originalImage = ii.getImage();

        // originalWidth = originalImage.getWidth(null);
        // originalHeight = originalImage.getHeight(null);

        // BufferedImage resizedImage = new BufferedImage(13, 13, BufferedImage.TYPE_INT_ARGB);
        // Graphics2D g2d = resizedImage.createGraphics();
        // g2d.drawImage(originalImage, 0, 0, 13, 13, null);
        // g2d.dispose();

        // image = resizedImage;

        // width = image.getWidth(null);
        // height = image.getHeight(null);
    }

    public List<Particle> getParticles() {
        return particles;
    }

    public void addParticle() {
        particles.add(new Particle(x + width / 2, y + height / 2 - 2, particleLife, particleIndex));
        particleCounter++;

        if (particleCounter >= 5) {
            particleCounter = 0;
            particleIndex++;
            if (particleIndex > 2) {
                particleIndex = 0;
            }
        }
    }

    public void move() {
        if (x < 0) {
            x = INITIAL_X;
            y = 5 + (int) (Math.random() * 281);
        }
        x--;
        addParticle();
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    // public Rectangle getResizedBounds() {
    //     return new Rectangle(x, y, width, height);
    // }

    // public int getOriginalWidth() {
    //     return originalWidth;
    // }

    // public int getOriginalHeight() {
    //     return originalHeight;
    // }
}
