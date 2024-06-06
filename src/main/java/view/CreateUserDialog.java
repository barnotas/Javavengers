package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.UserController;
import model.User;
/**
 * CreateUserDialog is a modal dialog for creating a new user.
 * It provides input fields for username, password, email, and first name.
 * It also includes validation for the email format.
 * 
 * @autor Bernard Bega, Barno Tashpulatova, Ahmed Hassan, Mahri Yalkapova
 */
public class CreateUserDialog extends JDialog {
    /** A field for user controller. */
    private UserController userController;
    /** A field for username.*/
    private JTextField usernameField;
    /** A field for JPassword. */
    private JPasswordField passwordField;
    /** A field for email field. */
    private JTextField emailField;
    /** A field for first name field. */
    private JTextField firstNameField;

    /** A constant for email pattern. */
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]+$");

    /**
     * Constructs a CreateUserDialog with the specified UserController.
     *
     * @param userController the user controller to manage user operations
     */
    public CreateUserDialog(UserController userController) {
        this.userController = userController;
        setTitle("Create User");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(600, 800));
        setResizable(true);
        initComponents();
        pack();
        setLocationRelativeTo(null);
    }

    /**
     * Initializes components of JPanel.
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
    
    
        JLabel emailLabel = new JLabel("Email:");
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(emailLabel, constraints);
    
        emailField = new JTextField(20);
        emailField.setPreferredSize(new Dimension(200, 25));
        constraints.gridx = 1;
        constraints.gridy = 2;
        panel.add(emailField, constraints);

        JLabel firstNameLabel = new JLabel("FirstName:");
        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(firstNameLabel, constraints);
    
        firstNameField = new JTextField(20);
        firstNameField.setPreferredSize(new Dimension(200, 25));
        constraints.gridx = 1;
        constraints.gridy = 3;
        panel.add(firstNameField, constraints);
    
        JButton createButton = new JButton("Create");
        createButton.addActionListener(e -> createUserButtonClicked());
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(createButton, constraints);
    
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dispose());
        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(cancelButton, constraints);
    
        add(panel);
    }


    /**
     * Creates user button.
     */
    private void createUserButtonClicked() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String email = emailField.getText();
        String firstName = firstNameField.getText();

        // Validate email format
        if (!isValidEmail(email)) {
            JOptionPane.showMessageDialog(this, "Invalid email format", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        User user = userController.createUser(username, password, email, firstName);
        if (user != null) {
            user.setUsername(username);
            user.setEmail(email);
            user.setFirstName(firstName);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to create user", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Checks if email pattern matches entered details.
     * @param email
     * @return
     */
    private boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }
}

