package view;

import javax.swing.SwingUtilities;

import controller.*;
import model.NewProjectModel;
import model.ProjectList;
import model.*;


public class App {
    private static NewProjectController newProjectController;
    private static SettingsController settingsController;
    private static About about;

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
    
            // Create the ProjectsPanel first
            ProjectsPanel projectsPanel = new ProjectsPanel(newProjectController);
    
            // Create the NewProjectModel and NewProjectController
            NewProjectModel newProjectModel = new NewProjectModel(null, projectList, homePanel, projectsPanel);
            NewProjectController newProjectController = new NewProjectController(newProjectModel, projectList, homePanel, projectsPanel);
    
            // Set the NewProjectController in the ProjectsPanel
            projectsPanel.setNewProjectController(newProjectController);
    
            // Create the ProjectController with the updated ProjectsPanel
            ProjectController projectController = new ProjectController(projectList, homePanel, projectsPanel);
    
            // Create the SettingsController
            SettingsController settingsController = new SettingsController(user, about, settingsPanel);

    
            // Create and show the MainFrame
            MainFrame mainFrame = new MainFrame(user, homePanel, projectsPanel, settingsPanel, aboutPanel, projectController, settingsController);
            mainFrame.setVisible(true);
        });
    }
    
    
    
    
    
    
    
    
    
    // public static void showMainFrame(User user) {
    //     SwingUtilities.invokeLater(() -> {
    //         ProjectList projectList = new ProjectList();
    //         HomePanel homePanel = new HomePanel();
    //         ProjectsPanel projectsPanel = new ProjectsPanel(null);
    //         SettingsPanel settingsPanel = new SettingsPanel();
    //         AboutPanel aboutPanel = new AboutPanel();
    //         ProjectController projectController = new ProjectController(projectList, homePanel, projectsPanel);
    //         SettingsController settingsController = new SettingsController(user, settingsPanel);

    //         MainFrame mainFrame = new MainFrame(user, homePanel, projectsPanel, settingsPanel, aboutPanel, projectController, settingsController);
    //         mainFrame.setVisible(true);
    //     });
    // }



//     public static void showMainFrame(User user) {
//     SwingUtilities.invokeLater(() -> {
//         ProjectList projectList = new ProjectList();
//         HomePanel homePanel = new HomePanel();
//         SettingsPanel settingsPanel = new SettingsPanel();
//         AboutPanel aboutPanel = new AboutPanel();

//         ProjectController projectController = new ProjectController(projectList, homePanel, null);
//         NewProjectModel newProjectModel = new NewProjectModel();
//         NewProjectController newProjectController = new NewProjectController(newProjectModel, projectList, homePanel, null);
//         ProjectsPanel projectsPanel = new ProjectsPanel(newProjectController);

//         SettingsController settingsController = new SettingsController(user, settingsPanel);

//         MainFrame mainFrame = new MainFrame(user, homePanel, projectsPanel, settingsPanel, aboutPanel, projectController, settingsController);
//         mainFrame.setVisible(true);
//     });
// }


}