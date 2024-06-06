package view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import controller.AboutController;
import controller.ProjectController;
import controller.SettingsController;
import controller.UserController;
import model.About;
import model.User;
/**
 * MainFrame is the main window for the Project Peak application. It contains tabs for Home, Projects,
 * About, and Settings. It also handles the closing event to display the login dialog.
 * 
 * @autor Bernard Bega, Barno Tashpulatova, Ahmed Hassan, Mahri Yalkapova
 */
public class MainFrame extends JFrame {
    /** A field for user controller. */
    private UserController userController;
    
    /**
     * Constructs a MainFrame with the specified user and controllers.
     * 
     * @param user the current user
     * @param homePanel the HomePanel instance
     * @param projectsPanel the ProjectsPanel instance
     * @param settingsPanel the SettingsPanel instance
     * @param aboutPanel the AboutPanel instance
     * @param projectController the ProjectController instance
     * @param settingsController the SettingsController instance
     * @param userController the UserController instance
     */
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