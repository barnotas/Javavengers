package controller;

import model.Project;
import model.ProjectList;
import model.ProjectRepository;
import model.User;
import view.HomePanel;
import view.ProjectsPanel;

/**
 * The ProjectController class is responsible for managing projects.
 * It handles the creation, updating, retrieval, and deletion of projects,
 * as well as loading projects for a given user.
 * This class interacts with ProjectRepository, HomePanel, ProjectsPanel, and UserController.
 * 
 *
 * @author Bernard Bega, Barno Tashpulatova, Ahmed Hassan, Mahri Yalkapova
 */

public class ProjectController {
    /** A field for project repository. */
    private ProjectRepository projectRepository;
    /** A field for home panel. */
    private HomePanel homePanel;
    /** A field for project panel. */
    private ProjectsPanel projectsPanel;
    /** A field for user controller. */
    private UserController userController;

    /**
     * Constructs a ProjectController with the specified ProjectRepository, HomePanel, and UserController.
     * 
     * @param projectRepository the repository for managing project data
     * @param homePanel the panel for displaying project information on the home screen
     * @param userController the controller for managing user information
     */
    public ProjectController(ProjectRepository projectRepository, HomePanel homePanel, UserController userController) {
        this.projectRepository = projectRepository;
        this.homePanel = homePanel;
        this.userController = userController;
    }
    /**
     * Sets the ProjectsPanel for the controller.
     * 
     * @param projectsPanel the panel for displaying project information on the projects screen
     */
    public void setProjectsPanel(ProjectsPanel projectsPanel) {
        this.projectsPanel = projectsPanel;
    }
    
    /**
     * Creates a new project with the specified name, description, and budget.
     * The project will be added to the current user's project list if it doesn't already exist.
     * 
     * @param name the name of the project
     * @param description the description of the project
     * @param budget the budget for the project
     */
    public void createProject(String name, String description, double budget) {
        User currentUser = userController.getCurrentUser();
        if (currentUser != null) {
            // Check if a project with the same name already exists
            ProjectList projectList = currentUser.getProjectList();
            boolean projectExists = projectList.getProjects().stream()
                    .anyMatch(project -> project.getName().equals(name));
    
            if (!projectExists) {
                Project project = new Project(name, description, budget);
                currentUser.getProjectList().addProject(project);
                projectRepository.saveProjects(currentUser);
    
                // Add the new project to the panels
                if (homePanel != null) {
                    homePanel.addProject(project.getName(), project.getDescription(), project.getBudget(), project.getExpenses(), project.isPrivate());
                }
                if (projectsPanel != null) {
                    projectsPanel.addProject(project);
                }
            }
        }
    } 


    /**
     * Creates a new project with the specified name, description, budget, privacy status, and pin.
     * The project will be added to the current user's project list if it doesn't already exist.
     * 
     * @param name the name of the project
     * @param description the description of the project
     * @param budget the budget for the project
     * @param isPrivate the privacy status of the project
     * @param pin the pin for accessing the project
     */
    public void createProject(String name, String description, double budget, boolean isPrivate, String pin) {
        User currentUser = userController.getCurrentUser();
        if (currentUser != null) {
            // Check if a project with the same name already exists
            ProjectList projectList = currentUser.getProjectList();
            boolean projectExists = projectList.getProjects().stream()
                    .anyMatch(project -> project.getName().equals(name));
    
            if (!projectExists) {
                Project project = new Project(name, description, budget);
                project.setPrivate(isPrivate);
                project.setPin(pin);
                currentUser.getProjectList().addProject(project);
                projectRepository.saveProjects(currentUser);
    
                // Add the new project to the panels
                if (homePanel != null) {
                    homePanel.addProject(project.getName(), project.getDescription(), project.getBudget(), project.getExpenses(), project.isPrivate());
                }
                if (projectsPanel != null) {
                    projectsPanel.addProject(project);
                }
            }
        }
    }
    /**
     * Retrieves a project with the specified name from the current user's project list.
     * 
     * @param projectName the name of the project to retrieve
     * @return the project with the specified name, or null if not found
     */
    public Project getProject(String projectName) {
        User currentUser = userController.getCurrentUser();
        if (currentUser != null) {
            ProjectList projectList = currentUser.getProjectList();
            for (Project project : projectList.getProjects()) {
                if (project.getName().equals(projectName)) {
                    return project;
                }
            }
        }
        return null;
    }

    
    /**
     * Loads all projects for the specified user and displays them on the panels.
     * 
     * @param user the user whose projects will be loaded
     */
    public void loadProjects(User user) {
        if (homePanel != null) {
            homePanel.clearProjects();
        }
        if (projectsPanel != null) {
            projectsPanel.clearProjects();
        }
        
        projectRepository.loadProjects(user);
        
        ProjectList projectList = user.getProjectList();
        for (Project project : projectList.getProjects()) {
            boolean isPrivate = project.isPrivate();
            if (homePanel != null) {
                homePanel.addProject(project.getName(), project.getDescription(), project.getBudget(), project.getExpenses(), isPrivate);
            }
            if (projectsPanel != null) {
                projectsPanel.addProject(project);
            }
        }
    }

    public void notifyHomePanel(Project project) {
        if (homePanel != null) {
            homePanel.removeProject(project);
        }
    }

    /**
     * Updates the specified project in the current user's project list and panels.
     * 
     * @param project the project to update
     */
    public void updateProject(Project project) {
        User currentUser = userController.getCurrentUser();
        if (currentUser != null) {
            // Update the Project instance in the ProjectList
            ProjectList projectList = currentUser.getProjectList();
            int index = projectList.getProjects().indexOf(project);
            if (index != -1) {
                projectList.getProjects().set(index, project);
            }
            
            projectRepository.saveProjects(currentUser);
            
            // Update the HomePanel
            if (homePanel != null) {
                int homeIndex = -1;
                for (int i = 0; i < homePanel.getProjectListModel().getSize(); i++) {
                    String listEntry = homePanel.getProjectListModel().getElementAt(i);
                    if (listEntry.startsWith("Project Name: " + project.getName() + " - ")) {
                        homeIndex = i;
                        break;
                    }
                }
                
                // Remove the old project entry from the HomePanel
                if (homeIndex != -1) {
                    homePanel.removeProject(project);
                }
                
                // Add or update the project in the HomePanel based on its privacy status
                if (!project.isPrivate()) {
                    homePanel.addProject(project.getName(), project.getDescription(), project.getBudget(), project.getExpenses(), project.isPrivate());
                }
            }
        }
    }
    /**
     * Deletes the specified project from the current user's project list and panels.
     * 
     * @param project the project to delete
     */
    public void deleteProject(Project project) {
    User currentUser = userController.getCurrentUser();
    if (currentUser != null) {
        // Remove the project from the ProjectList
        ProjectList projectList = currentUser.getProjectList();
        projectList.getProjects().remove(project);
        
        
        projectRepository.removeProject(currentUser, project);
        //loadProjects(currentUser);
    }
}
    /**
     * Returns the ProjectRepository associated with this controller.
     * 
     * @return the project repository
     */
    public ProjectRepository getProjectRepository() {
        return projectRepository;
    }
    /**
     * Returns user controller.
     * @return
     */
    public User getCurrentUser() {
        return userController.getCurrentUser();
    }
}