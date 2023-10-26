import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.List;
import java.util.ListIterator;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Board extends JPanel implements ActionListener {
    private static final int DELAY = 10;
    private static final int B_WIDTH = 450;
    private static final int B_HEIGHT = 300;

    private SpaceShip spaceShip;
    private List<Alien> aliens;

    private boolean ingame;
    private boolean isGameStarted = false;
    private boolean pause = false;
    private int score = 0;

    private Timer timer;

    private int farBackgroundX = 0;
    private int midBackgroundX = 0;
    private int foregroundX = 0;
    private Image farBackground;
    private Image midBackground;
    private Image foreground;

    /**
     * Constructor
     */
    public Board() {
        initUI();
    }

    /**
     * Initialize the UI
     */
    private void initUI() {
        addKeyListener(new TAdapter());

        loadBackground();

        setBackground(Color.BLACK);
        setFocusable(true);

        spaceShip = new SpaceShip();

        aliens = new ArrayList<Alien>();

        ingame = true;

        timer = new Timer(DELAY, this);
        timer.start();
    }

    /**
     * Load background images
     */
    private void loadBackground() {
        ImageIcon farBackgroundIcon = new ImageIcon("assets/images/spr_starfield_0.png");
        farBackground = farBackgroundIcon.getImage();
        farBackground = farBackground.getScaledInstance(B_WIDTH, B_HEIGHT, Image.SCALE_DEFAULT);

        ImageIcon midBackgroundIcon = new ImageIcon("assets/images/spr_starfield_1.png");
        midBackground = midBackgroundIcon.getImage();
        midBackground = midBackground.getScaledInstance(B_WIDTH, B_HEIGHT, Image.SCALE_DEFAULT);

        ImageIcon foregroundIcon = new ImageIcon("assets/images/spr_starfield_2.png");
        foreground = foregroundIcon.getImage();
        foreground = foreground.getScaledInstance(B_WIDTH, B_HEIGHT, Image.SCALE_DEFAULT);
    }

    /**
     * Paint the component
     * 
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // * Draw the background
        g.drawImage(farBackground, farBackgroundX, 0, null);
        g.drawImage(farBackground, farBackgroundX + B_WIDTH, 0, null);

        g.drawImage(midBackground, midBackgroundX, 0, null);
        g.drawImage(midBackground, midBackgroundX + B_WIDTH, 0, null);

        g.drawImage(foreground, foregroundX, 0, null);
        g.drawImage(foreground, foregroundX + B_WIDTH, 0, null);

        // * Draw the UI line
        int x1 = 0;
        int y1 = 300;
        int x2 = 450;
        int y2 = 300;

        g.setColor(Color.WHITE);
        g.drawLine(x1, y1, x2, y2);

        // * Draw the game
        if (!isGameStarted) {
            g.setColor(Color.black);
            g.fillRect(0, 0, B_WIDTH, B_HEIGHT);
            drawStartScreen(g);
        } else {
            if (ingame) {
                doDrawing(g);
            } else {
                drawGameOver(g);
            }
        }

        Toolkit.getDefaultToolkit().sync();
    }

    /**
     * Draw the game
     * 
     * @param g
     */
    private void doDrawing(Graphics g) {
        if (spaceShip.isVisible()) {
            g.drawImage(spaceShip.getImage(), spaceShip.getX(), spaceShip.getY(), null);
        }

        List<Missile> missiles = spaceShip.getMissiles();
        for (Missile missile : missiles) {
            g.drawImage(missile.getImage(), missile.getX(), missile.getY(), null);
        }

        List<Particle> particles = spaceShip.getParticles();
        for (Particle particle : particles) {
            g.drawImage(particle.getImage(), particle.getX(), particle.getY(), null);
        }

        for (Alien alien : aliens) {
            g.drawImage(alien.getImage(), alien.getX(), alien.getY(), null);
        }

        g.setColor(Color.WHITE);
        g.drawString("Your score: " + score, 5, 320);
    }

    /**
     * Draw the game over screen
     * 
     * @param g
     */
    private void drawGameOver(Graphics g) {
        String msg = "Game Over";
        String scoreMsg = "Your score: " + score;
        String restartMsg = "Press R to restart";
        Font small = new Font("Poppins", Font.BOLD, 14);
        FontMetrics fm = getFontMetrics(g.getFont());

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - fm.stringWidth(msg)) / 2, B_HEIGHT / 2);
        g.drawString(scoreMsg, (B_WIDTH - fm.stringWidth(scoreMsg)) / 2, B_HEIGHT / 2 + 20);
        g.drawString(restartMsg, (B_WIDTH - fm.stringWidth(restartMsg)) / 2, B_HEIGHT / 2 + 40);
    }

    private void drawStartScreen(Graphics g) {
        String instructions = "Use the ARROW KEYS to move and SPACE to shoot";
        String pause = "Press P to pause";
        String msg = "Press ENTER to start";
        Font small = new Font("Poppins", Font.BOLD, 14);
        FontMetrics fmI = getFontMetrics(g.getFont());
        FontMetrics fmP = getFontMetrics(g.getFont());
        FontMetrics fmM = getFontMetrics(g.getFont());

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(instructions, (B_WIDTH - fmI.stringWidth(instructions)) / 3, B_HEIGHT / 2 - 20);
        g.drawString(pause, (B_WIDTH - fmP.stringWidth(pause)) / 2, B_HEIGHT / 2);
        g.drawString(msg, (B_WIDTH - fmM.stringWidth(msg)) / 2, B_HEIGHT / 2 + 20);
    }

    /**
     * Handle the action event
     * 
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (isGameStarted) {
            ingame();

            updateSpaceShip();

            updateMissiles();

            updateAliens();

            updateParticles();

            checkCollisions();

            farBackgroundX -= 1;
            midBackgroundX -= 2;
            foregroundX -= 3;
        }

        if (farBackgroundX == -B_WIDTH) {
            farBackgroundX = 0;
        }

        if (midBackgroundX == -B_WIDTH) {
            midBackgroundX = 0;
        }

        if (foregroundX == -B_WIDTH) {
            foregroundX = 0;
        }

        repaint();

        if (!ingame) {
            timer.stop();
        }
    }

    // * Update the game state

    /**
     * Update the space ship
     */
    private void updateSpaceShip() {
        if (spaceShip.isVisible()) {
            spaceShip.move();
        }
    }

    /**
     * Update the missiles
     */
    private void updateMissiles() {
        List<Missile> missiles = spaceShip.getMissiles();
        ListIterator<Missile> itr = missiles.listIterator();
        while (itr.hasNext()) {
            Missile missile = itr.next();
            if (missile.isVisible()) {
                missile.move();
            } else {
                itr.remove();
            }
        }
    }

    /**
     * Update the particles
     */
    private void updateParticles() {
        List<Particle> particles = spaceShip.getParticles();
        ListIterator<Particle> itr = particles.listIterator();
        while (itr.hasNext()) {
            Particle particle = itr.next();
            if (particle.isVisible()) {
                particle.move();
            } else {
                itr.remove();
            }
        }
    }

    /**
     * Update the aliens
     */
    private void updateAliens() {
        ListIterator<Alien> itr = aliens.listIterator();
        while (itr.hasNext()) {
            Alien alien = itr.next();
            if (alien.isVisible()) {
                alien.move();
            } else {
                itr.remove();
            }
        }

        while (aliens.size() < 5) {
            int randomX = B_WIDTH + (int) (Math.random() * B_WIDTH);
            int randomY = (int) (Math.random() * B_HEIGHT);
            aliens.add(new Alien(randomX, randomY));
        }
    }

    // * Collisions

    /**
     * Check for collisions
     */
    private void checkCollisions() {
        Rectangle rSpaceShip = spaceShip.getBounds();

        // * Check for collisions between the space ship and the aliens
        for (Alien alien : aliens) {
            Rectangle rAlien = alien.getBounds();
            if (rSpaceShip.intersects(rAlien)) {
                spaceShip.setVisible(false);
                alien.setVisible(false);
                ingame = false;
            }
        }

        // * Check for collisions between the missiles and the aliens
        List<Missile> missiles = spaceShip.getMissiles();
        for (Missile missile : missiles) {
            Rectangle rMissile = missile.getBounds();
            for (Alien alien : aliens) {
                Rectangle rAlien = alien.getBounds();
                if (rMissile.intersects(rAlien)) {
                    missile.setVisible(false);
                    alien.setVisible(false);
                    score += 1;
                }
            }
        }
    }

    /**
     * Check if the game is still running
     */
    private void ingame() {
        if (!ingame) {
            timer.stop();
        }
    }

    /**
     * Restart the game
     */
    private void restart() {
        spaceShip = new SpaceShip();
        aliens = new ArrayList<Alien>();
        ingame = true;
        score = 0;
        timer.start();
    }

    /**
     * Pause the game
     */
    private void pause() {
        if (pause) {
            timer.start();
        } else {
            timer.stop();
        }
        pause = !pause;
    }

    // * Key events
    private class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_ENTER && !isGameStarted) {
                isGameStarted = true;
            } else if (key == KeyEvent.VK_R && !ingame) {
                restart();
            } else if (key == KeyEvent.VK_P) {
                pause();
            }

            spaceShip.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            spaceShip.keyReleased(e);
        }
    }

    /**
     * TODO - Adjust alien spawning and spawning rate
     * TODO - Fix the players movement
     * TODO - Fix the UI
     * TODO - Add a lives system
     * TODO - Fix the particles
     */
}
