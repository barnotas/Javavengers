import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class EmptyUI {
    /** The title of the window. */
    private static final String TITLE = "Empty UI";


    /** The main window that holds the app. */
    private final JFrame myWindow;

    public EmptyUI() {
        myWindow = new JFrame(TITLE);
        myWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        myWindow.addWindowListener(new WindowExitListener());
        myWindow.setSize(500, 500);
        myWindow.setLocationRelativeTo(null);
        myWindow.setVisible(true);
    }

    private void exitConfirmation() {
        if (JOptionPane.showConfirmDialog(myWindow, "You sure that you want to exit?",
                "Exit Confirmation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            myWindow.dispose();
        }
    }

    /**
     * Listens for the close window button,
     * and confirms with the user to exit the window.
     */
    private class WindowExitListener extends WindowAdapter implements WindowListener {
        @Override
        public void windowClosing(final WindowEvent theEvent) {
            exitConfirmation();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(EmptyUI::new);
    }
}