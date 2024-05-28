package controller;

import model.Project;
import model.ProjectList;
import view.HomePanel;
import view.ProjectsPanel;

import java.awt.Window;

import javax.swing.*;

public class ProjectController {
    private ProjectList projectList;
    private HomePanel homePanel;
    private ProjectsPanel projectsPanel;

    public ProjectController(ProjectList projectList, HomePanel homePanel, ProjectsPanel projectsPanel) {
        this.projectList = projectList;
        this.homePanel = homePanel;
        this.projectsPanel = projectsPanel;
    }

    public ProjectController() {

    }

    public void addProject(Project project) {
        projectList.addProject(project);
        homePanel.addProject(project.getName(), project.getDescription());
        projectsPanel.addProject(project);
        homePanel.revalidate();
        homePanel.repaint();
        projectsPanel.revalidate();
        projectsPanel.repaint();
    }

    public void showNewProjectPopup(Window window) {
        JDialog newProjectDialog = new JDialog(window, "Create New Project");
        JPanel newProjectPanel = new JPanel();

        JButton createButton = new JButton("Create Project");
        createButton.addActionListener(e -> {
            // Get project details from the input fields
            String projectName = ""; // Get from input field
            String projectDescription = ""; // Get from input field
            double projectBudget = 0.0; // Get from input field

            // Validate project name and description
            if (projectName.trim().isEmpty() || projectDescription.trim().isEmpty()) {
                JOptionPane.showMessageDialog(newProjectDialog, "Project name and description cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Create the project and add it to the project list
            Project project = new Project(projectName, projectDescription);
            project.setBudget(projectBudget);
            addProject(project);

            newProjectDialog.dispose();
        });

        newProjectPanel.add(createButton);
        newProjectDialog.getContentPane().add(newProjectPanel);
        newProjectDialog.pack();
        newProjectDialog.setLocationRelativeTo(window);
        newProjectDialog.setVisible(true);
    }

    

    
}