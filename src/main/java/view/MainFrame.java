package view;

import javax.swing.*;
import controller.*;
import model.*;

public class MainFrame extends JFrame {
    public MainFrame(User user, HomePanel homePanel, ProjectsPanel projectsPanel, SettingsPanel settingsPanel,
                     AboutPanel aboutPanel, ProjectController projectController, SettingsController settingsController) {
        setTitle("Project Peak");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

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