package controller;

import model.User;
import view.SettingsPanel;

import javax.swing.*;

public class SettingsController {
    private User model;
    private SettingsPanel view;

    public SettingsController(User model, SettingsPanel view) {
        this.model = model;
        this.view = view;

        // Add action listeners to the buttons in the SettingsPanel
        view.addSaveButtonListener(e -> saveSettings());
        view.addExportButtonListener(e -> exportSettings());
        view.addImportButtonListener(e -> importSettings());
    }

    private void saveSettings() {
        String username = view.getUsername();
        String email = view.getEmail();
        model.setUsername(username);
        model.setEmail(email);
        JOptionPane.showMessageDialog(view, "Settings saved successfully.");
    }

    private void exportSettings() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(view);
        if (result == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            model.exportSettings(filePath);
            JOptionPane.showMessageDialog(view, "Settings exported successfully.");
        }
    }

    private void importSettings() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(view);
        if (result == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            model.importSettings(filePath);
            view.setUsername(model.getUsername());
            view.setEmail(model.getEmail());
            JOptionPane.showMessageDialog(view, "Settings imported successfully.");
        }
    }
}