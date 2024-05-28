package controller;

import model.NewProjectModel;
import model.Project;
import model.ProjectList;
import view.HomePanel;
import view.NewProjectPanel;
import view.ProjectsPanel;

import javax.swing.*;

import java.awt.Frame;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewProjectController {
    private NewProjectModel newProjectModel;
    private JFrame parentFrame;
    private ProjectList projectList;
    private HomePanel homePanel;
    private ProjectsPanel projectsPanel;

    public NewProjectController(NewProjectModel newProjectModel, ProjectList projectList, HomePanel homePanel, ProjectsPanel projectsPanel) {
        this.newProjectModel = newProjectModel;
        this.projectList = projectList;
        this.homePanel = homePanel;
        this.projectsPanel = projectsPanel;
        
    }

    public NewProjectModel newProjectModel() {
        return newProjectModel;
    }


    public void showNewProjectPopup(Window window) {
        NewProjectPanel newProjectPanel = new NewProjectPanel(parentFrame, this);
        newProjectPanel.addCreateProjectListener(new NewProjectListener(newProjectPanel));
        newProjectPanel.setVisible(true);
    }

    public class NewProjectListener implements ActionListener {
        private NewProjectPanel newProjectPanel;

        public NewProjectListener(NewProjectPanel newProjectPanel) {
            this.newProjectPanel = newProjectPanel;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            NewProjectCheck(newProjectPanel);
            newProjectPanel.dispose();
        }
    }

    public void NewProjectCheck(NewProjectPanel newProjectPanel) {
        try {
            int projectId = newProjectPanel.getProjectId();
            String projectName = newProjectPanel.getProjectName();
            String projectDescription = newProjectPanel.getProjectDescription();
            double projectBudget = newProjectPanel.getProjectBudget();

            // Validate project name and description
            if (projectName.trim().isEmpty() || projectDescription.trim().isEmpty()) {
                JOptionPane.showMessageDialog(newProjectPanel, "Project name and description cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Create the project and add it to the project list
            Project project = new Project(projectName, projectDescription);
            project.setBudget(projectBudget);
            //projectList.addProject(project);

            // Update the home panel and projects panel
            homePanel.addProject(projectName, projectDescription);
            //projectsPanel.addProject(projectName);

            newProjectPanel.dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(newProjectPanel, "Invalid input. Please enter valid values.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void showNewProjectPopup(Frame parent) {
        NewProjectPanel newProjectPanel = new NewProjectPanel(parent, this);
        newProjectPanel.setVisible(true);
    }


    public void addProject(Project project) {
        //projectList.addProject(project);
        //homePanel.addProject(project.getName(), project.getDescription());
        //projectsPanel.addProject(project.getName());

        homePanel.revalidate();
        homePanel.repaint();
        projectsPanel.revalidate();
        projectsPanel.repaint();
    }
}
