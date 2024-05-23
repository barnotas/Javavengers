package view;

import controller.NewProjectController;
import model.NewProjectModel;
import model.ProjectList;

import javax.swing.*;
import java.awt.*;

public class ProjectsPanel extends JPanel {
    private JLabel projectNameLabel;
    private JLabel projectDescriptionLabel;
    private JButton newProjectButton;
    private NewProjectController newProjectController;
    private JList<String> projectList;
    private DefaultListModel<String> projectListModel;
    private JLabel budgetLabel;
    private JLabel expensesLabel;

    public ProjectsPanel(NewProjectController projectController) {
        setLayout(new BorderLayout());

        // Create a panel for the project list
        JPanel projectListPanel = new JPanel(new BorderLayout());
        projectListModel = new DefaultListModel<>();
        projectList = new JList<>(projectListModel);
        projectListPanel.add(new JScrollPane(projectList), BorderLayout.CENTER);
        add(projectListPanel, BorderLayout.EAST);

        // Create a panel for the project details
        JPanel projectDetailsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 5, 5, 5);

        projectNameLabel = new JLabel();
        projectDetailsPanel.add(projectNameLabel, constraints);

        constraints.gridy = 1;
        projectDescriptionLabel = new JLabel();
        projectDetailsPanel.add(projectDescriptionLabel, constraints);

        constraints.gridy = 2;
        budgetLabel = new JLabel();
        projectDetailsPanel.add(budgetLabel, constraints);

        constraints.gridy = 3;
        expensesLabel = new JLabel();
        projectDetailsPanel.add(expensesLabel, constraints);

        add(projectDetailsPanel, BorderLayout.CENTER);

        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel();
        newProjectButton = new JButton("Add new project");
        buttonPanel.add(newProjectButton);
        add(buttonPanel, BorderLayout.SOUTH);

        newProjectController = projectController;
        newProjectButton.addActionListener(e -> {
            newProjectController.showNewProjectPopup(SwingUtilities.getWindowAncestor(ProjectsPanel.this));
        });
    }

    public void setProjectName(String name) {
        projectNameLabel.setText("Project Name: " + name);
    }

    public void setProjectDescription(String description) {
        projectDescriptionLabel.setText("Project Description: " + description);
    }

    public void setBudget(double budget) {
        budgetLabel.setText("Budget: $" + budget);
    }

    public void setExpenses(double expenses) {
        expensesLabel.setText("Expenses: $" + expenses);
    }

    public void addProject(String projectName) {
        projectListModel.addElement(projectName);
        revalidate();
        repaint();
    }
}