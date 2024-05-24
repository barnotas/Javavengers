package view;

import controller.UserController;
import model.User;

import javax.swing.*;
import java.awt.*;

public class LoginDialog extends JDialog {
    private UserController userController;
    private JTextField usernameField;
    private JPasswordField passwordField;

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

    private void createUserButtonClicked() {
        CreateUserDialog createUserDialog = new CreateUserDialog(userController);
        createUserDialog.setVisible(true);
    }

}