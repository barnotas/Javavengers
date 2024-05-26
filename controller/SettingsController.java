package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFileChooser;

import model.Settings;
import model.User;
import view.SettingsPanel;
/**
 * Setting controller class provides event handling 
 * feature for project page.
 * as an inner class
 * @author Bega Bernard
 * @author Mahri Yalkapova
 * @author Ahmed Hassan 
 * @author Barno Tashpulatova
 * @version 1.1
 */

public class SettingsController {
    private Settings model;
    private SettingsPanel view;

    /**
     * Constructs setting controller class.
     * @param model
     * @param view
     */
    public SettingsController(Settings model, SettingsPanel view) {
        this.model = model;
        this.view = view;

        // Add ActionListeners to the buttons
        view.addExportSettingsListener(new ExportSettingsListener());
        view.addImportSettingsListener(new ImportSettingsListener());
    }
    /**
     * Updates view when user authentication is approved.
     * @param loggedIn
     */

    public void updateView(boolean loggedIn) {
        User user = model.getUser();
        view.setFirstName(user.getFirstName());
        view.setEmail(user.getEmail());
        view.setLoggedIn(loggedIn);
        //view.updatePanel(loggedIn);
    }
    /**
     * Demonstrates user credentials. 
     */

    public void updateModel() {
        String firstName = view.getFirstName();
        String email = view.getEmail();
        User user = new User(firstName, email);
        model.setUser(user);
    }
    

    /**
     * Inner class implements Action 
     * Listener interface to handle action event.
     * 
     */
    private class ExportSettingsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            exportSettings();
        }
    }

    /**
     * Inner class implements Action Listener 
     * interface to handle action events and file chooser.
     */
    private class ImportSettingsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            importSettings();
        }
    }

private void exportSettings() {
    try {
        // Create a file chooser dialog
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(view);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            // Create a PrintWriter to write the settings to the file
            PrintWriter writer = new PrintWriter(file);
            
            // Write the settings data to the file
            writer.println(model.getUser().getFirstName());
            writer.println(model.getUser().getEmail());
            
            writer.close();
            System.out.println("Settings exported successfully.");
        }
    } catch (FileNotFoundException e) {
        System.out.println("Error exporting settings: " + e.getMessage());
    }
}

private void importSettings() {
    try {
        // Create a file chooser dialog
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(view);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            // Create a BufferedReader to read the settings from the file
            BufferedReader reader = new BufferedReader(new FileReader(file));
            
            // Read the settings data from the file
            String firstName = reader.readLine();
            String email = reader.readLine();
            
            reader.close();
            
            
            // Update the model with the imported settings
            User user = new User(firstName, email);
            model.setUser(user);
            
            // Update the view with the imported settings
            updateView(true);
            
            System.out.println("Settings imported successfully.");
        }
    } catch (IOException e) {
        System.out.println("Error importing settings: " + e.getMessage());
    }
}
}
