package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.LoginModel;
import model.Settings;
import view.LoginPanel;
import view.SettingsPanel;
/**
 * LoginController class provides event handling 
 * feature for login page and implements ActionListener 
 * as an inner class
 * @author Bega Bernard
 * @author Mahri Yalkapova
 * @author Ahmed Hassan 
 * @author Barno Tashpulatova
 * @version 1.1
 */

public class LoginController {
    /**
     * A field for login model.
     */
    private LoginModel loginModel;
    /**
     * A field for setting model.
     */
    private Settings settingsModel;
    /**
     * A field for login view.
     */
    private LoginPanel loginView;
    /**
     * A field for setting view.
     */
    private SettingsPanel settingsView;
    /**
     * A field for setting controller. 
     */
    private SettingsController settingsController;

    /**
     * Constructs a login controller class.
     * @param loginModel
     * @param settingsModel
     * @param loginView
     * @param settingsView
     * @param settingsController
     */
    public LoginController(LoginModel loginModel, Settings settingsModel, LoginPanel loginView, SettingsPanel settingsView, SettingsController settingsController) {
        this.loginModel = loginModel;
        this.settingsModel = settingsModel;
        this.loginView = loginView;
        this.settingsView = settingsView;
        this.settingsController = settingsController;

        // Add action listener to the login button
        loginView.addLoginListener(new LoginListener());
    }

    /**
     * Inner class implements Action
     *  Listener interface to handle action 
     */
    private class LoginListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            loginCheck();
        }
    }
    /**
     * This method authenticates a user's login credentials.
     * Checks if the provided username and password match 
     * the credentials stored in the system. If credentials 
     * are valid, the user is considered authenticated.
     */
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