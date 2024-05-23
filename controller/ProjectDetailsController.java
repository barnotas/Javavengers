package controller;

import model.Project;
import view.ProjectDetailsPanel;

public class ProjectDetailsController {
    private Project project;
    private ProjectDetailsPanel projectDetailsPanel;

    public ProjectDetailsController(Project project, ProjectDetailsPanel projectDetailsPanel) {
        this.project = project;
        this.projectDetailsPanel = projectDetailsPanel;
    }

    public void updateView() {
        projectDetailsPanel.setProjectName(project.getName());
        projectDetailsPanel.setProjectDescription(project.getDescription());
        
    }
}