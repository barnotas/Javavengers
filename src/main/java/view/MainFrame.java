package view;



import javax.swing.*;
import controller.*;
import model.*;

public class MainFrame extends JFrame {
    private NewProjectModel newProjectModel;
    
    public MainFrame(UserController userController) {
        setTitle("Project Peak");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Create the tab pane
        JTabbedPane tabbedPane = new JTabbedPane();

        // Create panels for each tab
        HomePanel homePanel = new HomePanel();
        ProjectsPanel projectsPanel = new ProjectsPanel(null);
        About about = new About();
        AboutPanel aboutPanel = new AboutPanel();
        AboutController aboutController = new AboutController(about, aboutPanel);
        User user = userController.getCurrentUser();
        SettingsPanel settingsPanel = new SettingsPanel();
        SettingsController settingsController = new SettingsController(user, about, settingsPanel);

        
        aboutController.updateView();


        // Add panels to the tab pane
        tabbedPane.addTab("Home", homePanel);
        tabbedPane.addTab("Projects", projectsPanel);
        tabbedPane.addTab("About", aboutPanel);
        tabbedPane.addTab("Settings", settingsPanel);

        // Add tab pane to the frame
        add(tabbedPane);
    }

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
            aboutPanel = new AboutPanel();
            AboutController aboutController = new AboutController(about, aboutPanel);
            ProjectList projectList = new ProjectList();
            NewProjectModel newProjectmodel = new NewProjectModel(newProjectModel, projectList, homePanel,projectsPanel);
            NewProjectController newProjectController = new NewProjectController(newProjectmodel, projectList, homePanel, projectsPanel);
            projectsPanel = new ProjectsPanel(newProjectController);
    

            settingsController = new SettingsController(user, about, settingsPanel);

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