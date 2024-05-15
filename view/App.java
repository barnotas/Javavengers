package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.User;

/**
 * Presents the Home page for ProjectPeak.
 *
 * @version 0.1
 * @author Bernard Bega, Barno Tashpulatova, Ahmed Hassan, Mahri Yalkapova
 */
public class App implements ActionListener {
    /** The main window of the application.*/
    private final JFrame myFrame;

    /** The user of the application. */
    private User myUser;
    private JButton loginButton;
    private JTextField nameField;
    private JTextField emailField;
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
        setup();
        createMenu();
        login();
    }

    /**
     * Builds the main window that the user will be interacting with.
     */
    private void setup() {
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setSize(300, 300);
        myFrame.setVisible(true);
    }

    /**
     * Creates the menu bar and adds menu items.
     */
    private void createMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutMenuItem = new JMenuItem("About");
        aboutMenuItem.addActionListener(e -> {
            String[] devs = {"Bernard Bega", "Barno Tashpulatova, Ahmed Hassan, Mahri Yalkapova"};
            AboutScreen aboutScreen = new AboutScreen("Javavengers", devs);
            aboutScreen.open();
        });
        helpMenu.add(aboutMenuItem);
        menuBar.add(helpMenu);
        myFrame.setJMenuBar(menuBar);
    }

    /**
     * Pops up the user log-in page.
     */
    private void login() {
        // create appropriate layout
        // create labels for name and email and text input and button
        JTextField nameField = new JTextField(50);
        JTextField emailField = new JTextField(50);
        JButton loginButton = new JButton("Login");

        JPanel north = new JPanel(new GridLayout(2, 2));
        north.add(new JLabel("Name "));
        north.add(nameField);
        north.add(new JLabel("Email "));
        north.add(emailField);
        myFrame.add(north, BorderLayout.NORTH);
        loginButton.addActionListener(this);
        myFrame.add(loginButton, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String name = nameField.getText();
            String email = emailField.getText();
            myUser = new User(name, email);
            JOptionPane.showMessageDialog(myFrame, "Login successful for: " + name);
        }
    }
}
