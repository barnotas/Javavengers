package view;

import controller.NewProjectController;
import model.NewProjectModel;
import model.Project;
import model.ProjectList;

import javax.swing.*;
import java.awt.*;


/**
 * The ProjectsPanel class represents a panel for displaying and managing projects.
 * It provides a list of projects, project details, and buttons for adding new projects.
 */

public class ProjectsPanel extends JPanel {
    
    /**
     * The JLabel for displaying the project name.
     */
    private JLabel projectNameLabel;

    /**
     * The JLabel for displaying the project description.
     */
    private JLabel projectDescriptionLabel;

    /**
     * The JButton for adding a new project.
     */
    private JButton newProjectButton;

    /**
     * The NewProjectController instance for handling the creation of new projects.
     */
    private NewProjectController newProjectController;

    /**
     * The JList for displaying the list of projects.
     */
    private JList<String> projectList;

    /**
     * The DefaultListModel for storing the project list items.
     */
    private DefaultListModel<String> projectListModel;

    /**
     * The JLabel for displaying the project budget.
     */
    private JLabel budgetLabel;

    /**
     * The JLabel for displaying the project expenses.
     */
    private JLabel expensesLabel;

    /**
     * Constructs a new ProjectsPanel with the specified NewProjectController.
     *
     * @param projectController the NewProjectController instance for handling the creation of new projects
     */

    public ProjectsPanel(NewProjectController projectController) {
        setLayout(new BorderLayout());

        // Create a panel for the project list
        JPanel projectListPanel = new JPanel(new BorderLayout());
        projectListModel = new DefaultListModel<>();
        projectList = new JList<>(projectListModel);
        projectListPanel.add(new JScrollPane(projectList), BorderLayout.CENTER);
        add(projectListPanel, BorderLayout.WEST);

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


     /**
     * Sets the project name to be displayed.
     *
     * @param name the name of the project
     */
    public void setProjectName(String name) {
        projectNameLabel.setText("Project Name: " + name);
    }

     /**
     * Sets the project name to be displayed.
     *
     * @param name the name of the project
     */
    public void setProjectDescription(String description) {
        projectDescriptionLabel.setText("Project Description: " + description);
    }

    /**
     * Sets the project budget to be displayed.
     *
     * @param budget the budget of the project
     */
    public void setBudget(double budget) {
        budgetLabel.setText("Budget: $" + budget);
    }

    /**
     * Sets the project expenses to be displayed.
     *
     * @param expenses the expenses of the project
     */
    public void setExpenses(double expenses) {
        expensesLabel.setText("Expenses: $" + expenses);
    }

    /**
     * Adds a new project to the project list.
     *
     * @param projectName the name of the project to be added
     */
    public void addProject(String projectName) {
        projectListModel.addElement(projectName);
        revalidate();
        repaint();
    }

     /**
     * Adds a new project to the project list.
     *
     * @param project the Project object to be added
     */
       public void addProject(Project project) {
        String listEntry = project.getName() + " - Description: " + project.getDescription();
        projectListModel.addElement(listEntry);
        revalidate();
        repaint();
    }

    /**
     * Adds a new project to the project list.
     *
     * @param projectName the name of the project to be added
     * @param projectDescription the description of the project to be added
     */
    public void addProject(String projectName, String projectDescription) {
        String listEntry = projectName + " - Description: " + projectDescription;
        projectListModel.addElement(listEntry);
        revalidate();
        repaint();
    }

    /**
     * Sets the NewProjectController instance for handling the creation of new projects.
     *
     * @param newProjectController the NewProjectController instance to be set
     */
    public void setNewProjectController(NewProjectController newProjectController) {
        this.newProjectController = newProjectController;
    }
}