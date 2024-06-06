package view;

import javax.swing.SwingUtilities;

import controller.ProjectController;
import controller.SettingsController;
import controller.UserController;
import model.About;
import model.ProjectList;
import model.ProjectRepository;
import model.User;
import model.UserRepository;
/**
 * The App class is the entry point for the application.
 * It initializes the user repository and user controller, 
 * and launches the login dialog.
 * 
 * @autor Bernard Bega, Barno Tashpulatova, Ahmed Hassan, Mahri Yalkapova
 */
public class App {

    /**
     * The main method is the entry point of the application.
     * It sets up the user repository, user controller, and displays the login dialog.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UserRepository userRepository = new UserRepository();
            UserController userController = new UserController(userRepository);
            LoginDialog loginDialog = new LoginDialog(userController);
            loginDialog.setVisible(true);
        });
    }

    /**
     * Displays the main frame of the application after a user has logged in.
     * It sets up the necessary controllers and panels for the main application window.
     *
     * @param user the currently logged-in user
     * @param userController the user controller managing user operations
     */
    public static void showMainFrame(User user, UserController userController) {
        SwingUtilities.invokeLater(() -> {
            ProjectList projectList = new ProjectList();
            HomePanel homePanel = new HomePanel();
            SettingsPanel settingsPanel = new SettingsPanel();
            AboutPanel aboutPanel = new AboutPanel();

            // Create the ProjectRepository
            ProjectRepository projectRepository = new ProjectRepository();

            // Create the ProjectController
            ProjectController projectController = new ProjectController(projectRepository, homePanel, userController);
            

            // Create the ProjectsPanel with the ProjectController
            ProjectsPanel projectsPanel = new ProjectsPanel(projectController);
            
            // Set the ProjectsPanel in the ProjectController
            projectController.setProjectsPanel(projectsPanel);

            // Create the SettingsController
            About about = new About();
            SettingsController settingsController = new SettingsController(user, about, settingsPanel);

            // Create and show the MainFrame
            MainFrame mainFrame = new MainFrame(user, homePanel, projectsPanel, settingsPanel, aboutPanel, projectController, settingsController, userController);
            mainFrame.setVisible(true);
        });
    }
}



