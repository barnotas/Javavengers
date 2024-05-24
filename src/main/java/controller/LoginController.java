package controller;

import model.LoginModel;
import model.Settings;
import view.LoginPanel;
import view.SettingsPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class LoginController {
    private LoginModel loginModel;
    private Settings settingsModel;
    private LoginPanel loginView;
    private SettingsPanel settingsView;
    private SettingsController settingsController;

    public LoginController(LoginModel loginModel, Settings settingsModel, LoginPanel loginView, SettingsPanel settingsView, SettingsController settingsController) {
        this.loginModel = loginModel;
        this.settingsModel = settingsModel;
        this.loginView = loginView;
        this.settingsView = settingsView;
        this.settingsController = settingsController;

        // Add action listener to the login button
        loginView.addLoginListener(new LoginListener());
    }

    private class LoginListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            loginCheck();
        }
    }

    public void loginCheck() {
    // Get the entered username and PIN from the view
    String username = loginView.getUsername();
    String pin = loginView.getPin();

    // Update the model with the entered username and PIN
    loginModel.setUsername(username);
    loginModel.setPin(pin);

    // Perform authentication
    if (loginModel.authenticate()) {
        // Authentication successful
        loginModel.setLoggedIn(true);
        settingsModel.getUser().setFirstName(username);
        settingsView.setLoggedIn(true);
        settingsView.setFirstName(username);
        settingsController.updateView(true);

        // Show a pop-up message for successful login
        JOptionPane.showMessageDialog(loginView, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);

        loginView.showWelcomeMessage(username);

        // Perform necessary actions upon successful login
    } else {
        // Authentication failed
        loginModel.setLoggedIn(false);
        settingsView.setLoggedIn(false);
        settingsController.updateView(false);

        // Show a pop-up message for failed login
        JOptionPane.showMessageDialog(loginView, "Login failed. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);

        // Perform necessary actions upon failed login
    }
}

    
}