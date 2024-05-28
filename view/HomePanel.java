package view;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * This class creates a panel for welcome page.
 * @author Bega Bernard
 * @author Mahri Yalkapova
 * @author Ahmed Hassan 
 * @author Barno Tashpulatova
 * @version 1.1
 */

/**
 * Constructs HomePanel class.
 */
public class HomePanel extends JPanel {
    public HomePanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Welcome to Project Peak!"));
        add(new JLabel("This is the home panel."));
    }
}
