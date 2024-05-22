package view;

import controller.NewProjectController;
import model.NewProjectModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.*;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ProjectsPanel extends JPanel{
    private JLabel projectNameLabel ;
    private JLabel projectDescriptionLabel;
    private JButton newProjectButton;
    private NewProjectController newProjectController;
    public ProjectsPanel() {

        projectNameLabel = new JLabel();
        projectDescriptionLabel = new JLabel();
        newProjectButton = new JButton("Add new project");
        add(projectNameLabel);
        add(projectDescriptionLabel);
        add(new JLabel("Projects"));
        add(new JLabel("This is where you can manage your projects."));
       // GridBagConstraints constraints = new GridBagConstraints();
        //constraints.fill = GridBagConstraints.HORIZONTAL;
        newProjectButton.addNewProjectListener(new ActionListener());
    }


    public void setProjectName(String name) {
        projectNameLabel.setText("Project Name: " + name);
    }

    public void setProjectDescription(String description) {
        projectDescriptionLabel.setText("Project Description: " + description);
    }

    public void addNewProjectListener(ActionListener listener) {
        newProjectButton.addActionListener(listener);
    }
}