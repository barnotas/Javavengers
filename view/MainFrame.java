package view;

import javax.swing.*;

import controller.AboutController;
import controller.LoginController;
import controller.SettingsController;
import model.About;
import model.LoginModel;
import model.Settings;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("Project Peak");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Create the tab pane
        JTabbedPane tabbedPane = new JTabbedPane();

        // Create panels for each tab
        HomePanel homePanel = new HomePanel();
        ProjectsPanel projectsPanel = new ProjectsPanel();
        
        About about = new About();
        AboutPanel aboutPanel = new AboutPanel();
        AboutController aboutController = new AboutController(about, aboutPanel);
        
        Settings settings = new Settings();
        SettingsPanel settingsPanel = new SettingsPanel();
        SettingsController settingsController = new SettingsController(settings, settingsPanel);


        LoginModel loginModel = new LoginModel();
        Settings settingsModel = new Settings();
        LoginPanel loginPanel = new LoginPanel();
        LoginController loginController = new LoginController(loginModel, settingsModel, loginPanel, settingsPanel, settingsController);

        // Update the view with initial data from the model
        settingsController.updateView(false);
        settingsController.updateModel();
        aboutController.updateView();


        

        // Add panels to the tab pane
        tabbedPane.addTab("Home", homePanel);
        tabbedPane.addTab("Projects", projectsPanel);
        tabbedPane.addTab("About", aboutPanel);
        tabbedPane.addTab("Settings", settingsPanel);
        tabbedPane.addTab("Login", loginPanel);

        // Add tab pane to the frame
        add(tabbedPane);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}