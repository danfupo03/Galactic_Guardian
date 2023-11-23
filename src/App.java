/*----------------------------------------------------------------
*
* App.java
* Autor: Danfupo03
*
*--------------------------------------------------------------*/

import java.awt.EventQueue;
import javax.swing.JFrame;

public class App extends JFrame {
    private static final long serialVersionUID = 2389876656674844231L;

    /**
     * Constructor of the App class
     */
    public App() {
        initUI();
    }

    /**
     * Initializes the UI of the App
     */
    private void initUI() {
        add(new Board());
        setTitle("Spaceship");
        setSize(450, 375);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    /**
     * Main method of the App
     * 
     * @param args
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            App app = new App();
            app.setVisible(true);
        });
    }
}
