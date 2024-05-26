package controller;

import model.User;
import view.SettingsPanel;

import javax.swing.*;


public class SettingsController {
    private User model;
    private SettingsPanel view;

    public SettingsController(User user, SettingsPanel view) {
        this.model = user;
        this.view = view;
    }

    public void updateView(boolean loggedIn) {
        view.setUsername(model.getUsername());
        view.setEmail(model.getEmail());
        view.setLoggedIn(loggedIn);
    }

    public void updateModel() {
        String username = view.getUsername();
        String email = view.getEmail();
        model.setUsername(username);
        model.setEmail(email);
    }

    public void importSettings() {
        JFileChooser fileChooser = new JFileChooser("/Users/ahmed/360project/Javavengers-1/Javavengers");
        int result = fileChooser.showOpenDialog(view);
        if (result == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            model.importSettings(filePath);
            updateView(true);
        }
    }

    public void exportSettings() {
        JFileChooser fileChooser = new JFileChooser("/Users/ahmed/360project/Javavengers-1/Javavengers");
        int result = fileChooser.showSaveDialog(view);
        if (result == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            model.exportSettings(filePath);
        }
    }
}