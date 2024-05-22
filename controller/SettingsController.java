package controller;

import model.Settings;
import model.User;
import view.SettingsPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFileChooser;

public class SettingsController {
    private Settings model;
    private SettingsPanel view;


    public SettingsController(Settings model, SettingsPanel view) {
        this.model = model;
        this.view = view;

        // Add ActionListeners to the buttons
        view.addExportSettingsListener(new ExportSettingsListener());
        view.addImportSettingsListener(new ImportSettingsListener());
    }

    public void updateView(boolean loggedIn) {
        User user = model.getUser();
        view.setFirstName(user.getFirstName());
        view.setEmail(user.getEmail());
        view.setLoggedIn(loggedIn);
        //view.updatePanel(loggedIn);
    }

    public void updateModel() {
        String firstName = view.getFirstName();
        String email = view.getEmail();
        User user = new User(firstName, email);
        model.setUser(user);
    }


    private class ExportSettingsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            exportSettings();
        }
    }

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