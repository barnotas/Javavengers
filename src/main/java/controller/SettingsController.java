package controller;

import model.About;
import model.User;
import view.SettingsPanel;

import javax.swing.*;
import java.io.*;

public class SettingsController {
    private User userModel;
    private About aboutModel;
    private SettingsPanel view;

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