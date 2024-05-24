package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel {
    private JLabel usernameLabel;
    private JTextField usernameField;
    private JLabel pinLabel;
    private JPasswordField pinField;
    private JButton loginButton;
    private JLabel welcomeLabel;

    public LoginPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 5, 5, 5);

        usernameLabel = new JLabel("Username:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(usernameLabel, constraints);

        constraints.gridx = 1;
        usernameField = new JTextField(20);
        add(usernameField, constraints);

        pinLabel = new JLabel("Password:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(pinLabel, constraints);

        constraints.gridx = 1;
        pinField = new JPasswordField(4);
        add(pinField, constraints);

        loginButton = new JButton("Login");
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        add(loginButton, constraints);

        welcomeLabel = new JLabel();
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 25));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        add(welcomeLabel, constraints);
        welcomeLabel.setVisible(false);
    }

    public String getUsername() {
        return usernameField.getText();
    }

    public String getPin() {
        return new String(pinField.getPassword());
    }

    public void addLoginListener(ActionListener listener) {
        loginButton.addActionListener(listener);
    }

    public void showWelcomeMessage(String username) {
        usernameLabel.setVisible(false);
        usernameField.setVisible(false);
        pinLabel.setVisible(false);
        pinField.setVisible(false);
        loginButton.setVisible(false);

        welcomeLabel.setText("Welcome, " + username + "!");
        welcomeLabel.setVisible(true);

        revalidate();
        repaint();
    }
}