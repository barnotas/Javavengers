package view;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 * Represents JPanel window for displaying GUI components.
 * This class provides a window container for setting feature.
 * 
 * @author Bega Bernard
 * @author Mahri Yalkapova
 * @author Ahmed Hassan 
 * @author Barno Tashpulatova
 * @version 1.1
 */

public class SettingsPanel extends JPanel {
    /**
     * A field for welcome label.
     */
    private JLabel welcomeLabel;
    /**
     * A field for name label.
     */
    private JLabel nameLabel;
    /**
     * A field for email label.
     */
    private JLabel emailLabel;
    /**
     * A field for first name field.
     */
    private JTextField firstNameField;
    /**
     * A field for email field.
     */
    private JTextField emailField;
    /**
     * A field for export button.
     */
    private JButton exportButton;
    /**
     * A field for import button.
     */
    private JButton importButton;
    /**
     * A field for export info button.
     */
    private JButton exportInfoButton;
    /**
     * A field for import info button.
     */
    private JButton importInfoButton;
    /**
     * A field for login status label.
     */
    private JLabel loginStatusLabel;

    /**
     * Constructs the panel with components.
     */
    public SettingsPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        // Create and add the welcome label
        welcomeLabel = new JLabel("Welcome to Project Peak!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        add(welcomeLabel, constraints);

        // Create and add the name label
        nameLabel = new JLabel();
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.LINE_START;
        add(nameLabel, constraints);

        // Create and add the email label
        emailLabel = new JLabel();
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        emailLabel.setVisible(false); // Hide the email label initially
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.LINE_START;
        add(emailLabel, constraints);

        // Create and add the first name field
        firstNameField = new JTextField(20);
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.LINE_START;
        add(firstNameField, constraints);

        // Create and add the email field
        emailField = new JTextField(20);
        emailField.setVisible(false); // Hide the email field initially
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.LINE_START;
        add(emailField, constraints);
        // Create and add the export button
        exportButton = new JButton("Export Settings");
        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.LINE_START;
        add(exportButton, constraints);

        // Create and add the import button
        importButton = new JButton("Import Settings");
        constraints.gridx = 1;
        constraints.gridy = 5;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.LINE_START;
        add(importButton, constraints);

        // Create and add the login status label
        loginStatusLabel = new JLabel();
        loginStatusLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        constraints.gridx = 0;
        constraints.gridy = 6;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        add(loginStatusLabel, constraints);

    }
    
    /**
     * Returns first name.
     * @return
     */
    public String getFirstName() {
        return firstNameField.getText();
    }
    /**
     * Sets first name.
     * @param firstName
     */
    public void setFirstName(String firstName) {
        nameLabel.setText("Name: " + firstName);
        firstNameField.setText(firstName);
    }
    /**
     * Returns email.
     * @return
     */

    public String getEmail() {
        return emailField.getText();
    }
    /**
     * Sets email.
     * @param email
     */
    public void setEmail(String email) {
        emailLabel.setText("Email: " + email);
        emailField.setText(email);
    }
    
    /**
     * Sets loggedIn.
     * @param loggedIn
     */
    public void setLoggedIn(boolean loggedIn) {
        // Show/hide the email label and field based on the login status
        emailLabel.setVisible(loggedIn);
        emailField.setVisible(loggedIn);

        // Update the login status label
        if (loggedIn) {
            loginStatusLabel.setText("Logged In");
        } else {
            loginStatusLabel.setText("Logged Out");
        }
    }

    
    /**
     * Updates panel when authentication is successful.
     * @param isLoggedIn
     */
    public void updatePanel(boolean isLoggedIn) {
        if (isLoggedIn) {
            // Update the panel to show the logged-in state
            // For example, you can show a welcome message or enable certain components
            JLabel welcomeLabel = new JLabel("Welcome, " + getFirstName() + "!");
            add(welcomeLabel);
            // Enable or show other components specific to the logged-in state
        } else {
            // Update the panel to show the logged-out state
            // For example, you can clear the panel or disable certain components
            removeAll();
            // Disable or hide components specific to the logged-out state
        }
        revalidate();
        repaint();
    }
    /**
     * Action listener for enabling JButtons.
     * @param listener
     */
    public void addExportSettingsListener(ActionListener listener) {
        exportButton.addActionListener(listener);
    }

     /**
     * Action listener for enabling JButtons.
     * @param listener
     */
    public void addImportSettingsListener(ActionListener listener) {
        importButton.addActionListener(listener);
    }
    /**
     * Action listener for enabling JButtons.
     * @param listener
     */
    public void addExportInfoListener(ActionListener listener) {
        exportInfoButton.addActionListener(listener);
    }
    /**
     * Action listener for enabling JButtons.
     * @param listener
     */

    public void addImportInfoListener(ActionListener listener) {
        importInfoButton.addActionListener(listener);
    }
}