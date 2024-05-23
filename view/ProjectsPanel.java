package view;

import controller.ProjectController;
import javax.swing.*;


public class ProjectsPanel extends JPanel {
     private JLabel projectNameLabel;
    private JLabel projectDescriptionLabel;
    private JButton newProjectButton;
    private ProjectController projectController;
    private JList<String> projectList;
    private DefaultListModel<String> projectListModel;

    public ProjectsPanel() {

        projectNameLabel = new JLabel();
        projectDescriptionLabel = new JLabel();
        newProjectButton = new JButton("Add new project");

        add(new JLabel("Projects"));
        add(new JLabel("This is where you can manage your projects."));
        add(newProjectButton);
        add(projectNameLabel);
        add(projectDescriptionLabel);

        projectListModel = new DefaultListModel<>();
        projectList = new JList<>(projectListModel);
        add(new JScrollPane(projectList));
        projectListModel = new DefaultListModel<>();
        projectList = new JList<>(projectListModel);
        add(new JScrollPane(projectList));

        newProjectButton.addActionListener(e -> {
            projectController.showNewProjectPopup(SwingUtilities.getWindowAncestor(ProjectsPanel.this));
        });
    }

    public void setProjectName(String name) {
        projectNameLabel.setText("Project Name: " + name);
    }

    public void setProjectDescription(String description) {
        projectDescriptionLabel.setText("Project Description: " + description);
    }

    public void addProject(String projectName) {
        projectListModel.addElement(projectName);
        revalidate();
        repaint();
    }

}