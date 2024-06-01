package controller;

import model.Project;
import model.ProjectRepository;
import view.HomePanel;
import view.ProjectsPanel;

public class ProjectController {
    private ProjectRepository projectRepository;
    private HomePanel homePanel;
    private ProjectsPanel projectsPanel;

    public ProjectController(ProjectRepository projectRepository, HomePanel homePanel) {
        this.projectRepository = projectRepository;
        this.homePanel = homePanel;
    }

    public void setProjectsPanel(ProjectsPanel projectsPanel) {
        this.projectsPanel = projectsPanel;
    }

    public void createProject(Project project) {
        projectRepository.addProject(project);
        projectRepository.saveProjects();
        homePanel.addProject(project.getName(), project.getDescription(), project.getBudget(), project.getExpenses());
        if (projectsPanel != null) {
            projectsPanel.addProject(project);
        }
    }

    public void updateProject(Project project) {
        int index = projectRepository.getProjects().indexOf(project);
        if (index != -1) {
            projectRepository.getProjects().set(index, project);
            projectRepository.saveProjects();
            homePanel.updateProject(index, project.getName(), project.getDescription(), project.getBudget(), project.getExpenses());
            if (projectsPanel != null) {
                projectsPanel.updateProject(index, project);
            }
        }
    }

    public void deleteProject(Project project) {
        boolean removed = projectRepository.removeProject(project);
        if (removed) {
            projectRepository.saveProjects();
            homePanel.removeProject(project.getName());
            if (projectsPanel != null) {
                projectsPanel.removeProject(project);
            }
        }
    }

    public ProjectRepository getProjectRepository() {
        return projectRepository;
    }
}