package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SettingsPanel extends JPanel {
    private JLabel usernameLabel;
    private JLabel emailLabel;
    private JButton exportButton;
    private JButton importButton;

    public SettingsPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        // Username
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(new JLabel("Username:"), constraints);
        constraints.gridx = 1;
        constraints.gridy = 0;
        usernameLabel = new JLabel();
        add(usernameLabel, constraints);

        // Email
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(new JLabel("Email:"), constraints);
        constraints.gridx = 1;
        constraints.gridy = 1;
        emailLabel = new JLabel();
        add(emailLabel, constraints);

        // Buttons
        JPanel buttonPanel = new JPanel();
        exportButton = new JButton("Export");
        importButton = new JButton("Import");
        buttonPanel.add(exportButton);
        buttonPanel.add(importButton);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        add(buttonPanel, constraints);
    }

    public void setUsername(String username) {
        usernameLabel.setText(username);
    }

    public void setEmail(String email) {
        emailLabel.setText(email);
    }

    public void addExportButtonListener(ActionListener listener) {
        exportButton.addActionListener(listener);
    }

    public void addImportButtonListener(ActionListener listener) {
        importButton.addActionListener(listener);
    }
}