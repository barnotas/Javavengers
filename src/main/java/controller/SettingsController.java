package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import model.About;
import model.User;
import view.SettingsPanel;
/**
 * The SettingsController class manages the settings for the application,
 * including exporting and importing user settings. It interacts with the
 * User and About models, and the SettingsPanel view.
 * 
 * @autor Bernard Bega, Barno Tashpulatova, Ahmed Hassan, Mahri Yalkapova
 */
public class SettingsController {
    /** A field for user model. */
    private User userModel;
    /** A field for about model */
    private About aboutModel;
    /** A field for setting panel. */
    private SettingsPanel view;

     /**
     * Constructs a SettingsController with the specified User, About models, and SettingsPanel view.
     * Initializes the view with the current user's username and email, and sets up action listeners.
     * 
     * @param userModel the user model containing user information
     * @param aboutModel the about model containing application information
     * @param view the settings panel view
     */
    public SettingsController(User userModel, About aboutModel, SettingsPanel view) {
        this.userModel = userModel;
        this.aboutModel = aboutModel;
        this.view = view;

        // Set initial values for username and email labels
        view.setUsername(userModel.getUsername());
        view.setEmail(userModel.getEmail());

        // Add action listeners to the buttons in the SettingsPanel
        view.addExportButtonListener(e -> exportSettings());
        view.addImportButtonListener(e -> importSettings());
    }
    /**
     * Exports the current user settings to a file chosen by the user.
     * Includes the username, email, and additional information from the About model.
     */
    private void exportSettings() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(view);
        if (result == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
                writer.println("Username: " + userModel.getUsername());
                writer.println("Email: " + userModel.getEmail());
                writer.close();
                aboutModel.exportAbout(filePath);
                JOptionPane.showMessageDialog(view, "Settings exported successfully.");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(view, "Error exporting settings: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    /**
     * Imports user settings from a file chosen by the user.
     * Updates the user model and view with the imported username and email.
     */
    private void importSettings() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(view);
        if (result == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                boolean usernameFound = false;
                boolean emailFound = false;
                
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("Username: ")) {
                        String username = line.substring("Username: ".length());
                        userModel.setUsername(username);
                        view.setUsername(username);
                        usernameFound = true;
                    } else if (line.startsWith("Email: ")) {
                        String email = line.substring("Email: ".length());
                        userModel.setEmail(email);
                        view.setEmail(email);
                        emailFound = true;
                    }
                }
                
                if (usernameFound && emailFound) {
                    JOptionPane.showMessageDialog(view, "Settings imported successfully.");
                } else {
                    JOptionPane.showMessageDialog(view, "Invalid file format. Username or email not found.", "Import Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(view, "Error importing settings: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}