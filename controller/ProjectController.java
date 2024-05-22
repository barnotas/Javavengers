package controller;

import model.Project;
import view.ProjectsPanel;

public class ProjectController {
    private Project model;
    private ProjectsPanel view;

    public ProjectController(Project model, ProjectsPanel view) {
        this.model = model;
        this.view = view;
    }

    public void createProject(String name, String description) {
        model.setName(name);
        model.setDescription(description);
        updateView();
    }

    public void updateView() {
        view.setProjectName(model.getName());
        view.setProjectDescription(model.getDescription());
    }
}