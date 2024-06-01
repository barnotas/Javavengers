package view;

import model.Project;
import controller.ProjectController;

import javax.swing.*;
import java.awt.*;

public class ProjectsPanel extends JPanel {
    private JLabel projectNameLabel;
    private JLabel projectDescriptionLabel;

    private JList<String> projectList;
    private DefaultListModel<String> projectListModel;
    private JLabel budgetLabel;
    private JLabel expensesLabel;
    private ProjectController projectController;

    public ProjectsPanel(ProjectController projectController) {
        this.projectController = projectController;
        setLayout(new BorderLayout());

        // Create a panel for the project list
        JPanel projectListPanel = new JPanel(new BorderLayout());
        projectListModel = new DefaultListModel<>();
        projectList = new JList<>(projectListModel);
        projectListPanel.add(new JScrollPane(projectList), BorderLayout.CENTER);
        add(projectListPanel, BorderLayout.WEST);

       // Create a button to open the "Create a new project" dialog
        JButton newProjectButton = new JButton("Add New Project");
        newProjectButton.addActionListener(e -> showCreateNewProjectDialog());

        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(newProjectButton);
        add(buttonPanel, BorderLayout.NORTH);
    }



    private void showCreateNewProjectDialog() {
        JDialog newProjectDialog = new JDialog(SwingUtilities.getWindowAncestor(this), "Create New Project");
        newProjectDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        newProjectDialog.setLayout(new BorderLayout());
    
        // Create a panel for creating a new project
        JPanel newProjectPanel = new JPanel(new GridBagLayout());
        GridBagConstraints newProjectConstraints = new GridBagConstraints();
        newProjectConstraints.gridx = 0;
        newProjectConstraints.gridy = 0;
        newProjectConstraints.anchor = GridBagConstraints.WEST;
        newProjectConstraints.insets = new Insets(5, 5, 5, 5);
    
        JLabel projectNameLabel = new JLabel("Project Name:");
        newProjectPanel.add(projectNameLabel, newProjectConstraints);
    
        newProjectConstraints.gridx = 1;
        JTextField projectNameField = new JTextField(20);
        newProjectPanel.add(projectNameField, newProjectConstraints);
    
        newProjectConstraints.gridx = 0;
        newProjectConstraints.gridy = 1;
        JLabel projectDescriptionLabel = new JLabel("Project Description:");
        newProjectPanel.add(projectDescriptionLabel, newProjectConstraints);
    
        newProjectConstraints.gridx = 1;
        JTextArea projectDescriptionArea = new JTextArea(4, 20);
        JScrollPane projectDescriptionScrollPane = new JScrollPane(projectDescriptionArea);
        newProjectPanel.add(projectDescriptionScrollPane, newProjectConstraints);
    
        newProjectConstraints.gridx = 0;
        newProjectConstraints.gridy = 2;
        JLabel projectBudgetLabel = new JLabel("Project Budget:");
        newProjectPanel.add(projectBudgetLabel, newProjectConstraints);
    
        newProjectConstraints.gridx = 1;
        JTextField projectBudgetField = new JTextField(10);
        newProjectPanel.add(projectBudgetField, newProjectConstraints);
    
        newProjectDialog.add(newProjectPanel, BorderLayout.CENTER);
    
        // Create a panel for the dialog buttons
        JPanel dialogButtonPanel = new JPanel();
        JButton createButton = new JButton("Create");
        createButton.addActionListener(e -> {
            String projectName = projectNameField.getText();
            String projectDescription = projectDescriptionArea.getText();
            double projectBudget = Double.parseDouble(projectBudgetField.getText());
            Project project = new Project(projectName, projectDescription, projectBudget);
            projectController.createProject(project);
            newProjectDialog.dispose();
        });
        dialogButtonPanel.add(createButton);
    
        newProjectDialog.add(dialogButtonPanel, BorderLayout.SOUTH);
    
        newProjectDialog.pack();
        newProjectDialog.setLocationRelativeTo(SwingUtilities.getWindowAncestor(this));
        newProjectDialog.setVisible(true);
    }

    public void addProject(Project project) {
        String listEntry = "Project Name: " + project.getName() + " - Description: " + project.getDescription() + " - Budget: $" + project.getBudget() + " - Expenses: $" + project.getExpenses();
        projectListModel.addElement(listEntry);
        revalidate();
        repaint();
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

    public void updateProject(int index, Project project) {
        String listEntry = "Project Name: " + project.getName() + " - Description: " + project.getDescription() +
                " - Budget: $" + project.getBudget() + " - Expenses: $" + project.getExpenses();
        projectListModel.setElementAt(listEntry, index);
    }
    
    public void removeProject(Project project) {
        // Find the index of the project in the list model
        int index = -1;
        for (int i = 0; i < projectListModel.getSize(); i++) {
            String listEntry = projectListModel.getElementAt(i);
            if (listEntry.startsWith("Project Name: " + project.getName() + " - ")) {
                index = i;
                break;
            }
        }
    
        // Remove the project from the list model if found
        if (index != -1) {
            projectListModel.remove(index);
        }
    }
}