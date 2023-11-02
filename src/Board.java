/*----------------------------------------------------------------
*
* Board.java
* Fecha: 27-Oct-2023
* Autor: Daniel Emilio Fuentes - A01708302
*
*--------------------------------------------------------------*/

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
import java.util.Random;

public class Board extends JPanel implements ActionListener {
    private static final int DELAY = 10;
    private static final int B_WIDTH = 450;
    private static final int B_HEIGHT = 300;

    private SpaceShip spaceShip;
    private List<Alien> aliens;

    private List<Shield> shields;
    private List<Fire> fires;
    private List<Bomb> bombs;
    private List<Heart> hearts;

    Sound sound = new Sound();

    private boolean ingame;
    private boolean isGameStarted = false;
    private boolean pause = false;

    private int score = 0;
    private int lives = 3;
    private int alienCount = 5;
    private int level = 1;

    private int heartX = 135;
    private int heartY = 306;

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
     * Initialize the UI and the game itself
     */
    private void initUI() {
        addKeyListener(new TAdapter());

        loadBackground();

        setBackground(Color.black);
        setFocusable(true);

        spaceShip = new SpaceShip();
        aliens = new ArrayList<Alien>();
        hearts = new ArrayList<Heart>();

        shields = new ArrayList<Shield>();
        fires = new ArrayList<Fire>();
        bombs = new ArrayList<Bomb>();

        for (int i = 0; i < lives; i++) {
            hearts.add(new Heart(heartX, heartY));
            heartX += 20;
        }

        ingame = true;

        timer = new Timer(DELAY, this);
        timer.start();
    }

    /**
     * Load the background images
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

    /* #region Drawing Methods */
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
        int y1 = 305;
        int x2 = 450;
        int y2 = 305;

        g.setColor(Color.white);
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

        // * Dibujar la caja de colisión de la nave en rojo
        if (spaceShip.isVisible()) {
            g.setColor(Color.RED);
            Rectangle rSpaceShip = spaceShip.getBounds();
            g.drawRect(rSpaceShip.x, rSpaceShip.y, rSpaceShip.width, rSpaceShip.height);
            g.drawImage(spaceShip.getImage(), spaceShip.getX(), spaceShip.getY(), null);
        }

        // * Dibujar la caja de colisión de los aliens en rojo
        for (Alien alien : aliens) {
            if (alien.isVisible()) {
                g.setColor(Color.RED);
                Rectangle rAlien = alien.getBounds();
                g.drawRect(rAlien.x, rAlien.y, rAlien.width, rAlien.height);
                g.drawImage(alien.getImage(), alien.getX(), alien.getY(), null);
            }
        }

        // * Dibujar la caja de colisión de los misiles en rojo
        List<Missile> missiles = spaceShip.getMissiles();
        for (Missile missile : missiles) {
            if (missile.isVisible()) {
                g.setColor(Color.RED);
                Rectangle rMissile = missile.getBounds();
                g.drawRect(rMissile.x, rMissile.y, rMissile.width, rMissile.height);
                g.drawImage(missile.getImage(), missile.getX(), missile.getY(), null);
            }
        }

        // * Dibujar la caja de colisión de los escudos en rojo
        for (Shield shield : shields) {
            if (shield.isVisible()) {
                g.setColor(Color.RED);
                Rectangle rShield = shield.getBounds();
                g.drawRect(rShield.x, rShield.y, rShield.width, rShield.height);
                g.drawImage(shield.getImage(), shield.getX(), shield.getY(), null);
            }
        }

        // * Dibujar la caja de colisión de los fuegos en rojo
        for (Fire fire : fires) {
            if (fire.isVisible()) {
                g.setColor(Color.RED);
                Rectangle rFire = fire.getBounds();
                g.drawRect(rFire.x, rFire.y, rFire.width, rFire.height);
                g.drawImage(fire.getImage(), fire.getX(), fire.getY(), null);
            }
        }

        // * Dibujar la caja de colisión de las bombas en rojo
        for (Bomb bomb : bombs) {
            if (bomb.isVisible()) {
                g.setColor(Color.RED);
                Rectangle rBomb = bomb.getBounds();
                g.drawRect(rBomb.x, rBomb.y, rBomb.width, rBomb.height);
                g.drawImage(bomb.getImage(), bomb.getX(), bomb.getY(), null);
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

        for (Heart heart : hearts) {
            g.drawImage(heart.getImage(), heart.getX(), heart.getY(), null);
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

        for (Shield shield : shields) {
            g.drawImage(shield.getImage(), shield.getX(), shield.getY(), null);
        }

        for (Fire fire : fires) {
            g.drawImage(fire.getImage(), fire.getX(), fire.getY(), null);
        }

        for (Bomb bomb : bombs) {
            g.drawImage(bomb.getImage(), bomb.getX(), bomb.getY(), null);
        }

        g.setColor(Color.WHITE);
        g.drawString("Your score: " + score, 5, 325);

        g.setColor(Color.WHITE);
        g.drawString("Lives:", 100, 325);

        g.setColor(Color.WHITE);
        g.drawString("Level: " + level, 385, 325);
    }

    /**
     * Draw the game over screen
     * 
     * @param g
     */
    private void drawGameOver(Graphics g) {
        String msg = "Game Over";
        String scoreMsg = "Your score: " + score;
        String levelMsg = "Your reached level: " + level;
        String restartMsg = "Press R to restart";

        Font small = new Font("Poppins", Font.BOLD, 14);
        Font large = new Font("Joystix", Font.BOLD, 20);
        FontMetrics fm = getFontMetrics(g.getFont());

        g.setColor(Color.red);
        g.setFont(large);
        g.drawString(msg, (B_WIDTH - fm.stringWidth(msg)) / 2 - 35, B_HEIGHT / 2 - 30);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(scoreMsg, (B_WIDTH - fm.stringWidth(scoreMsg)) / 2, B_HEIGHT / 2);
        g.drawString(levelMsg, (B_WIDTH - fm.stringWidth(levelMsg)) / 2, B_HEIGHT / 2 + 20);
        g.drawString(restartMsg, (B_WIDTH - fm.stringWidth(restartMsg)) / 2, B_HEIGHT / 2 + 40);

        playSE(2);
    }

    /**
     * Draw the start screen
     * 
     * @param g
     */
    private void drawStartScreen(Graphics g) {
        String instructions = "Use the ARROW KEYS/WASD to move and SPACE to shoot";
        String pause = "Press P to pause";
        String msg = "Press ENTER to start";
        String title = "Galactic Guardian";

        Font small = new Font("Poppins", Font.BOLD, 12);
        Font large = new Font("Joystix", Font.BOLD, 20);

        FontMetrics fmSmall = getFontMetrics(small);
        FontMetrics fmLarge = getFontMetrics(large);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(instructions, (B_WIDTH - fmSmall.stringWidth(instructions)) / 2, B_HEIGHT / 2);
        g.drawString(pause, (B_WIDTH - fmSmall.stringWidth(pause)) / 2, B_HEIGHT / 2 - 20);
        g.drawString(msg, (B_WIDTH - fmSmall.stringWidth(msg)) / 2, B_HEIGHT / 2 + 40);

        g.setColor(Color.blue);
        g.setFont(large);
        g.drawString(title, (B_WIDTH - fmLarge.stringWidth(title)) / 2, B_HEIGHT / 2 - 60);
    }
    /* #endregion */

    /* #region Sound Methods */
    /**
     * Play the music
     * 
     * @param i
     */
    public void playMusic(int i) {
        sound.setFile(i);
        sound.play();
        sound.loop();
    }

    /**
     * Stop the music
     */
    public void stopMusic() {
        sound.stop();
    }

    /**
     * Play the sound effect
     * 
     * @param i
     */
    public void playSE(int i) {
        sound.setFile(i);
        sound.play();
    }
    /* #endregion */

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

            updateLives();

            updateShields();
            updateFires();
            updateBombs();

            checkCollisions();

            level = levelUp(score);

            farBackgroundX -= 1;
            midBackgroundX -= 2;
            foregroundX -= 3;

            // if (ingame) {
            // playMusic(1); // Assuming 0 is the appropriate music index, adjust if
            // necessary
            // } else {
            // stopMusic();
            // }
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

    /* #region Update methods */

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

        while (aliens.size() < alienCount) {
            int randomX = B_WIDTH + (int) (Math.random() * B_WIDTH);
            int randomY = 5 + (int) (Math.random() * 281);
            aliens.add(new Alien(randomX, randomY));
        }
    }

    /**
     * Update the shields
     */
    private void updateShields() {
        ListIterator<Shield> itr = shields.listIterator();
        while (itr.hasNext()) {
            Shield shield = itr.next();
            if (shield.isVisible()) {
                shield.move();
                if (shield.getX() < 0) {
                    itr.remove();
                }
            } else {
                itr.remove();
            }
        }
    }

    /**
     * Update the fires
     */
    private void updateFires() {
        ListIterator<Fire> itr = fires.listIterator();
        while (itr.hasNext()) {
            Fire fire = itr.next();
            if (fire.isVisible()) {
                fire.move();
                if (fire.getX() < 0) {
                    itr.remove();
                }
            } else {
                itr.remove();
            }
        }
    }

    /**
     * Update the bombs
     */
    private void updateBombs() {
        ListIterator<Bomb> itr = bombs.listIterator();
        while (itr.hasNext()) {
            Bomb bomb = itr.next();
            if (bomb.isVisible()) {
                bomb.move();
                if (bomb.getX() < 0) {
                    itr.remove();
                }
            } else {
                itr.remove();
            }
        }
    }

    /**
     * Update the lives
     */
    private void updateLives() {
        if (lives == 0) {
            die();
        } else if (lives < hearts.size()) {
            hearts.remove(hearts.size() - 1);
        }
    }

    /**
     * Reset the player
     */
    private void resetPlayer() {
        spaceShip = new SpaceShip();

        if (lives >= 1) {
            for (Alien alien : aliens) {
                alien.setX(B_WIDTH + (int) (Math.random() * B_WIDTH));
                alien.setY(5 + (int) (Math.random() * 281));
            }
        }
    }

    /**
     * Player dies
     */
    private void die() {
        ingame = false;
        stopMusic();
    }

    /**
     * Leveling up
     */
    private int levelUp(int score) {
        int level = 1;
        int temp = 0;
        int increment = 5;

        while (score >= temp) {
            temp += increment;
            increment += 5;
            level += 1;
        }

        return level - 1;
    }

    /* #endregion */

    // * Collisions

    /**
     * Check for collisions
     */
    private void checkCollisions() {

        Random random = new Random();

        Rectangle rSpaceShip = spaceShip.getBounds();

        // * Check for collisions between the space ship and the aliens
        for (Alien alien : aliens) {
            Rectangle rAlien = alien.getBounds();
            if (rSpaceShip.intersects(rAlien)) {
                spaceShip.setVisible(false);
                alien.setVisible(false);
                lives -= 1;
                playSE(3);

                if (lives > 0) {
                    resetPlayer();
                }
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

                    if (shields.isEmpty() && fires.isEmpty() && bombs.isEmpty()) {
                        if (random.nextInt(25) == 0) {
                            int powerUp = random.nextInt(3);

                            if (powerUp == 0) {
                                shields.add(new Shield(alien.getX(), alien.getY()));
                            } else if (powerUp == 1) {
                                fires.add(new Fire(alien.getX(), alien.getY()));
                            } else if (powerUp == 2) {
                                bombs.add(new Bomb(alien.getX(), alien.getY()));
                            }
                        }
                    }

                    playSE(4);

                    if (score % 10 == 0) {
                        alienCount += 5;
                    }
                }
            }
        }

        // * Check for collisions between the space ship and the power ups
        for (Shield shield : shields) {
            Rectangle rShield = shield.getResizedBounds();
            if (rSpaceShip.intersects(rShield)) {
                shield.setVisible(false);
            }
        }

        for (Fire fire : fires) {
            Rectangle rFire = fire.getResizedBounds();
            if (rSpaceShip.intersects(rFire)) {
                fire.setVisible(false);
            }
        }

        for (Bomb bomb : bombs) {
            Rectangle rBomb = bomb.getResizedBounds();
            if (rSpaceShip.intersects(rBomb)) {
                bomb.setVisible(false);
            }
        }
    }

    /* #region Game logic methods */
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
        hearts = new ArrayList<Heart>();
        ingame = true;
        alienCount = 5;
        score = 0;
        lives = 3;
        timer.start();

        heartX = 140;
        heartY = 311;

        for (int i = 0; i < lives; i++) {
            hearts.add(new Heart(heartX, heartY));
            heartX += 20;
        }
    }

    /**
     * Pause the game
     */
    private void pause(Graphics g) {
        String msg = "Paused";
        String instructions = "Press P to resume";

        Font s = new Font("Poppins", Font.BOLD, 14);
        Font i = new Font("Poppins", Font.BOLD, 10);

        FontMetrics fms = getFontMetrics(g.getFont());
        FontMetrics fmi = getFontMetrics(g.getFont());

        g.setColor(Color.WHITE);

        g.setFont(s);
        g.drawString(msg, (B_WIDTH - fms.stringWidth(msg)) / 2, B_HEIGHT / 2);
        g.setFont(i);
        g.drawString(instructions, (B_WIDTH - fmi.stringWidth(instructions)) / 2 + 12, B_HEIGHT / 2 + 20);

        if (pause) {
            timer.start();
            playSE(6);
        } else {
            playSE(5);
            timer.stop();
        }
        pause = !pause;
    }
    /* #endregion */

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
                pause(getGraphics());
            }

            spaceShip.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            spaceShip.keyReleased(e);
        }
    }

    /**
     * 
     * TODO - Add power ups logic
     * TODO - Add a boss
     */
}