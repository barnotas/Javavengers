package view;

import javax.swing.JFrame;

import model.User;

/**
 * Presents the Home page for ProjectPeak.
 *
 * @version 0.1
 * @author Bernard Bega, Barno Tashpulatova
 */

public class App {

    /** The main window of the application.*/
    private final JFrame myFrame;
    /** The user of the application. */
    private User myUser;

    /**
     * Constructs a GUI for the application, ProjectPeak.
     */
    public App() {
        myFrame = new JFrame("Javavengers");
        myUser = null;
    }

    /**
     * Performs all tasks necessary to display the GUI via delegation.
     */
    public void start() {

    }

    /**
     * Builds the main window that the user will be interacting with.
     */
    private void setup() {
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setSize(300,300);
        myFrame.setVisible(true);
    }

    /**
     * Pops up the user log-in page.
     */
    private void login() {

    }
}
