package view;

import controller.UserController;
import model.User;

import javax.swing.*;
import java.awt.*;

public class CreateUserDialog extends JDialog {
    private UserController userController;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField emailField;

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
    
        JButton createButton = new JButton("Create");
        createButton.addActionListener(e -> createUserButtonClicked());
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(createButton, constraints);
    
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dispose());
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(cancelButton, constraints);
    
        add(panel);
    }


    private void createUserButtonClicked() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String email = emailField.getText();
    
        User user = userController.createUser(username, password, email);
        if (user != null) {
            user.setUsername(username);
            user.setEmail(email);
            dispose();
            //App.showMainFrame(user);
        } else {
            JOptionPane.showMessageDialog(this, "Failed to create user", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
