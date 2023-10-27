/*----------------------------------------------------------------
*
* App.java
* Fecha: 27-Oct-2023
* Autor: Daniel Emilio Fuentes - A01708302
*
*--------------------------------------------------------------*/

import java.awt.EventQueue;
import javax.swing.JFrame;

public class App extends JFrame {
    private static final long serialVersionUID = 2389876656674844231L;

    public App() {
        initUI();
    }

    private void initUI() {
        add(new Board());
        setTitle("Spaceship");
        setSize(450, 375);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            App app = new App();
            app.setVisible(true);
        });
    }
}
