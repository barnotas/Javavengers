package view;

import controller.NewProjectController;
import model.NewProjectModel;

import controller.NewProjectController;
import model.NewProjectModel;

import javax.swing.*;

public class ProjectsPanel extends JPanel {
    public ProjectsPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Projects"));
        add(new JLabel("This is where you can manage your projects."));
    }
}