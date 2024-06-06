package view;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import controller.*;
import model.*;

public class MainFrame extends JFrame {
    
    private UserController userController;

    public MainFrame(User user, HomePanel homePanel, ProjectsPanel projectsPanel, SettingsPanel settingsPanel,
                     AboutPanel aboutPanel, ProjectController projectController, SettingsController settingsController, UserController userController) {
        setTitle("Project Peak");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        this.userController = userController;



        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Dispose the main frame
                dispose();
                
                // Show the login dialog
                LoginDialog loginDialog = new LoginDialog(userController);
                loginDialog.setVisible(true);
            }
        });

        // Create the tab pane
        JTabbedPane tabbedPane = new JTabbedPane();

        // Create panels for each tab
        About about = new About();
        AboutController aboutController = new AboutController(about, aboutPanel);

        // Load user-specific projects
        projectController.loadProjects(user);

        aboutController.updateView();

        // Add panels to the tab pane
        tabbedPane.addTab("Home", homePanel);
        tabbedPane.addTab("Projects", projectsPanel);
        tabbedPane.addTab("About", aboutPanel);
        tabbedPane.addTab("Settings", settingsPanel);

        // Add tab pane to the frame
        add(tabbedPane);
    }
}