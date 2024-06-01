package view;

import javax.swing.SwingUtilities;

import controller.*;
import model.*;
import view.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UserRepository userRepository = new UserRepository();
            UserController userController = new UserController(userRepository);
            LoginDialog loginDialog = new LoginDialog(userController);
            loginDialog.setVisible(true);
        });
    }

    public static void showMainFrame(User user) {
        SwingUtilities.invokeLater(() -> {
            ProjectList projectList = new ProjectList();
            HomePanel homePanel = new HomePanel();
            SettingsPanel settingsPanel = new SettingsPanel();
            AboutPanel aboutPanel = new AboutPanel();

            // Create the ProjectRepository
            ProjectRepository projectRepository = new ProjectRepository();

            // Create the ProjectController
            ProjectController projectController = new ProjectController(projectRepository, homePanel);

            // Create the ProjectsPanel with the ProjectController
            ProjectsPanel projectsPanel = new ProjectsPanel(projectController);
            
            // Set the ProjectsPanel in the ProjectController
            projectController.setProjectsPanel(projectsPanel);

            // Create the SettingsController
            About about = new About();
            SettingsController settingsController = new SettingsController(user, about, settingsPanel);

            // Create and show the MainFrame
            MainFrame mainFrame = new MainFrame(user, homePanel, projectsPanel, settingsPanel, aboutPanel, projectController, settingsController);
            mainFrame.setVisible(true);
        });
    }
}



