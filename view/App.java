package view;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.User;
/**
 * Presents the Home page for ProjectPeak.
 *
 * @version 0.1
 * @author Bernard Bega, Barno Tashpulatova
 */

public class App implements ActionListener{

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
        setup();
        login();


    }

    /**
     * 
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
        // create appropriate layout 
        // create labels for name and email and text input and button
         JTextField nameField = new JTextField(50);
         JTextField emailField = new JTextField(50);
         JButton login = new JButton("Login");
         //JLabel label = new JLabel("User Login Page");
         

         // layout 
         //myFrame.add(label, BorderLayout.CENTER);
         JPanel north = new JPanel (new GridLayout(2, 2));
         north.add(new JLabel("Name "));
         north.add(nameField);
         north.add(new JLabel("Email "));
         north.add(emailField);

         myFrame.add(north, BorderLayout.NORTH);
         myFrame.add(login, BorderLayout.SOUTH);





    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}
