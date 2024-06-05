package controller;

import model.Project;
import model.ProjectList;
import model.ProjectRepository;
import model.User;
import view.HomePanel;
import view.ProjectsPanel;

public class ProjectController {
    private ProjectRepository projectRepository;
    private HomePanel homePanel;
    private ProjectsPanel projectsPanel;
    private UserController userController;

    public ProjectController(ProjectRepository projectRepository, HomePanel homePanel, UserController userController) {
        this.projectRepository = projectRepository;
        this.homePanel = homePanel;
        this.userController = userController;
    }

    public void setProjectsPanel(ProjectsPanel projectsPanel) {
        this.projectsPanel = projectsPanel;
    }

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
                homePanel.addProject(project.getName(), project.getDescription(), project.getBudget(), project.getExpenses());
            }
            if (projectsPanel != null) {
                projectsPanel.addProject(project);
            }
        }
   }
}

    public Project getProject(String projectName) {
        User currentUser = userController.getCurrentUser();
        if (currentUser != null){
            ProjectList projectList = currentUser.getProjectList();
            for(Project project : projectList.getProjects()){
                if(project.getName().equals(projectName)){
                    return project;
                }
            }
        }
        return null;

    }

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
            if (homePanel != null) {
                homePanel.addProject(project.getName(), project.getDescription(), project.getBudget(), project.getExpenses());
            }
            if (projectsPanel != null) {
                projectsPanel.addProject(project);
            }
        }
    }


    // public void loadProjects(User user) {
    //     ProjectList projectList = user.getProjectList();
    //     for (Project project : projectList.getProjects()) {
    //         if (homePanel != null) {
    //             homePanel.addProject(project.getName(), project.getDescription(), project.getBudget(), project.getExpenses());
    //         }
    //         if (projectsPanel != null) {
    //             projectsPanel.addProject(project);
    //         }
    //     }
    // }

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
            //loadProjects(currentUser);
        }
    }

    public void deleteProject(Project project) {
    User currentUser = userController.getCurrentUser();
    if (currentUser != null) {
        // Remove the project from the ProjectList
        ProjectList projectList = currentUser.getProjectList();
        projectList.getProjects().remove(project);
        
        projectRepository.removeProject(currentUser, project);
        homePanel.removeProject(project);
        //loadProjects(currentUser);
    }

}
    public ProjectRepository getProjectRepository() {
        return projectRepository;
    }

    public User getCurrentUser() {
        return userController.getCurrentUser();
    }

}

