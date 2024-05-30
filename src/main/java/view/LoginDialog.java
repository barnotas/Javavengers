package view;

import controller.UserController;
import model.User;

import javax.swing.*;
import java.awt.*;


/**
 * The LoginDialog class represents a dialog window for user login.
 * It allows users to enter their username and password to authenticate and log in to the application.
 * The dialog also provides an option to create a new user account.
 */

public class LoginDialog extends JDialog {

    /** The UserController instance for handling user authentication. */
    private UserController userController;

    /** The JTextField for entering the username. */
    private JTextField usernameField;

    /** The JPasswordField for entering the password. */
    private JPasswordField passwordField;

    
    /**
     * Constructs a new LoginDialog with the specified UserController.
     *
     * @param userController the UserController instance for handling user authentication
     */

    public LoginDialog(UserController userController) {
        this.userController = userController;
        setTitle("Login");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(600, 800));
        setResizable(true);
        initComponents();
        pack();
        setLocationRelativeTo(null);
    }

    /**
     * Initializes the components of the LoginDialog.
     * Creates and configures the necessary UI components such as labels, text fields, and buttons.
     */
    private void initComponents() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
    
        JLabel usernameLabel = new JLabel("Username:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(usernameLabel, constraints);
    
        usernameField = new JTextField(20);
        usernameField.setPreferredSize(new Dimension(200, 25));
        constraints.gridx = 1;
        constraints.gridy = 0;
        panel.add(usernameField, constraints);
    
        JLabel passwordLabel = new JLabel("Password:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(passwordLabel, constraints);
    
        passwordField = new JPasswordField(20);
        passwordField.setPreferredSize(new Dimension(200, 25));
        constraints.gridx = 1;
        constraints.gridy = 1;
        panel.add(passwordField, constraints);
    
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> loginButtonClicked());
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(loginButton, constraints);
    
        JButton createUserButton = new JButton("Create User");
        createUserButton.addActionListener(e -> createUserButtonClicked());
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(createUserButton, constraints);
    
        add(panel);
    }
    
    /**
     * Handles the login button click event.
     * Retrieves the entered username and password and attempts to log in the user using the UserController.
     * If the login is successful, the dialog is disposed, and the main application frame is shown.
     * If the login fails, an error message is displayed.
     */
    private void loginButtonClicked() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        User user = userController.loginUser(username, password);
        if (user != null) {
            dispose();
            App.showMainFrame(user);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Handles the create user button click event.
     * Opens a new CreateUserDialog to allow the user to create a new account.
     */
    private void createUserButtonClicked() {
        CreateUserDialog createUserDialog = new CreateUserDialog(userController);
        createUserDialog.setVisible(true);
    }

}