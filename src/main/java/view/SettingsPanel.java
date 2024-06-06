package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * The SettingsPanel class represents a panel for displaying user settings.
 * It provides fields for displaying username and email, as well as buttons for exporting and importing data.
 */
public class SettingsPanel extends JPanel {
    /** A field for username label. */
    private JLabel usernameLabel;
    /** A field for email label. */
    private JLabel emailLabel;
    /** A field for export button. */
    private JButton exportButton;
    /** A field for import button. */
    private JButton importButton;
    /**
     * Constructs a SettingsPanel.
     */
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
    /**
     * Sets the username to be displayed.
     * @param username the username
     */
    public void setUsername(String username) {
        usernameLabel.setText(username);
    }
    /**
     * Sets the email to be displayed.
     * @param email the email
     */
    public void setEmail(String email) {
        emailLabel.setText(email);
    }
    /**
     * Adds an ActionListener to the export button.
     * @param listener the ActionListener
     */
    public void addExportButtonListener(ActionListener listener) {
        exportButton.addActionListener(listener);
    }
    /**
     * Adds an ActionListener to the import button.
     * @param listener the ActionListener
     */
    public void addImportButtonListener(ActionListener listener) {
        importButton.addActionListener(listener);
    }
}