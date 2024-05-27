package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SettingsPanel extends JPanel {
    private JTextField usernameField;
    private JTextField emailField;
    private JButton saveButton;
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
        usernameField = new JTextField(20);
        usernameField.setEditable(false);
        add(usernameField, constraints);

        // Email
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(new JLabel("Email:"), constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        emailField = new JTextField(20);
        emailField.setEditable(false);
        add(emailField, constraints);

        // Buttons
        JPanel buttonPanel = new JPanel();
        saveButton = new JButton("Save");
        exportButton = new JButton("Export");
        importButton = new JButton("Import");
        buttonPanel.add(saveButton);
        buttonPanel.add(exportButton);
        buttonPanel.add(importButton);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        add(buttonPanel, constraints);
    }

    public String getUsername() {
        return usernameField.getText();
    }

    public void setUsername(String username) {
        usernameField.setText(username);
    }

    public String getEmail() {
        return emailField.getText();
    }

    public void setEmail(String email) {
        emailField.setText(email);
    }

    public void addSaveButtonListener(ActionListener listener) {
        saveButton.addActionListener(listener);
    }

    public void addExportButtonListener(ActionListener listener) {
        exportButton.addActionListener(listener);
    }

    public void addImportButtonListener(ActionListener listener) {
        importButton.addActionListener(listener);
    }
}