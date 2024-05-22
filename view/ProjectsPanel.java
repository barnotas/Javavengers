package view;

import controller.NewProjectController;
import model.NewProjectModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProjectsPanel extends JPanel {
    private JLabel projectNameLabel;
    private JLabel projectDescriptionLabel;
    private JButton newProjectButton;
    private NewProjectController newProjectController;
    private NewProjectModel newProjectModel;

    public ProjectsPanel() {

        projectNameLabel = new JLabel();
        projectDescriptionLabel = new JLabel();
        newProjectButton = new JButton("Add new project");

        add(new JLabel("Projects"));
        add(new JLabel("This is where you can manage your projects."));
        add(newProjectButton);
        add(projectNameLabel);
        add(projectDescriptionLabel);

        newProjectModel = new NewProjectModel();
        newProjectController = new NewProjectController(newProjectModel);
        newProjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newProjectController.showNewProjectPopup();
            }
        });
    }

    public void setProjectName(String name) {
        projectNameLabel.setText("Project Name: " + name);
    }

    public void setProjectDescription(String description) {
        projectDescriptionLabel.setText("Project Description: " + description);
    }
}
