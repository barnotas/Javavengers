package view;

import javax.swing.*;

import model.Project;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HomePanel extends JPanel {
    private JList<String> projectList;
    private DefaultListModel<String> projectListModel;
    private ProjectController projectController;



    public HomePanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Welcome to Project Peak!"));
        add(new JLabel("This is the home panel."));

        projectListModel = new DefaultListModel<>();
        projectList = new JList<>(projectListModel);
        projectList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int selectedIndex = projectList.getSelectedIndex();
                    if (selectedIndex != -1) {
                        showEditProjectDialog(selectedIndex);
                    }
                }
            }
        });
        add(new JScrollPane(projectList));
    }

    public void setProjectController(ProjectController projectController) {
        this.projectController = projectController;
    }

    public void addProject(String projectName, String projectDescription, double budget, double expenses) {
        String listEntry = "Project Name: " + projectName + " - Description: " + projectDescription +
                " - Budget: $" + budget + " - Expenses: $" + expenses +
                " - Total Cost: $" + (budget - expenses);
        projectListModel.addElement(listEntry);
    }

    public void updateProject(int index, String name, String description, double budget, double expenses) {
        String listEntry = "Project Name: " + name + " - Description: " + description +
                " - Budget: $" + budget + " - Expenses: $" + expenses +
                " - Total Cost: $" + (budget - expenses);
        projectListModel.setElementAt(listEntry, index);
    }

    private void showEditProjectDialog(int selectedIndex) {
        String listEntry = projectListModel.getElementAt(selectedIndex);
        String[] parts = listEntry.split(" - ");
        String projectName = parts[0].substring("Project Name: ".length());
        String projectDescription = parts[1].substring("Description: ".length());
        String projectBudget = parts[2].substring("Budget: $".length());
        String projectExpenses = parts[3].substring("Expenses: $".length());

        JDialog editProjectDialog = new JDialog(SwingUtilities.getWindowAncestor(this), "Edit Project");
        editProjectDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        editProjectDialog.setLayout(new BorderLayout());

        // Create a panel for editing the project details
        JPanel editProjectPanel = new JPanel(new GridBagLayout());
        GridBagConstraints editProjectConstraints = new GridBagConstraints();
        editProjectConstraints.gridx = 0;
        editProjectConstraints.gridy = 0;
        editProjectConstraints.anchor = GridBagConstraints.WEST;
        editProjectConstraints.insets = new Insets(5, 5, 5, 5);

        JLabel projectNameLabel = new JLabel("Project Name:");
        editProjectPanel.add(projectNameLabel, editProjectConstraints);

        editProjectConstraints.gridx = 1;
        JTextField projectNameField = new JTextField(projectName, 20);
        editProjectPanel.add(projectNameField, editProjectConstraints);

        editProjectConstraints.gridx = 0;
        editProjectConstraints.gridy = 1;
        JLabel projectDescriptionLabel = new JLabel("Project Description:");
        editProjectPanel.add(projectDescriptionLabel, editProjectConstraints);

        editProjectConstraints.gridx = 1;
        JTextArea projectDescriptionArea = new JTextArea(projectDescription, 4, 20);
        JScrollPane projectDescriptionScrollPane = new JScrollPane(projectDescriptionArea);
        editProjectPanel.add(projectDescriptionScrollPane, editProjectConstraints);

        editProjectConstraints.gridx = 0;
        editProjectConstraints.gridy = 2;
        JLabel projectBudgetLabel = new JLabel("Project Budget:");
        editProjectPanel.add(projectBudgetLabel, editProjectConstraints);

        editProjectConstraints.gridx = 1;
        JTextField projectBudgetField = new JTextField(projectBudget, 10);
        editProjectPanel.add(projectBudgetField, editProjectConstraints);

        editProjectConstraints.gridx = 0;
        editProjectConstraints.gridy = 3;
        JLabel projectExpensesLabel = new JLabel("Project Expenses:");
        editProjectPanel.add(projectExpensesLabel, editProjectConstraints);

        editProjectConstraints.gridx = 1;
        JTextField projectExpensesField = new JTextField(projectExpenses, 10);
        editProjectPanel.add(projectExpensesField, editProjectConstraints);

        editProjectDialog.add(editProjectPanel, BorderLayout.CENTER);

        // Create a panel for the dialog buttons
        JPanel dialogButtonPanel = new JPanel();
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            String updatedProjectName = projectNameField.getText();
            String updatedProjectDescription = projectDescriptionArea.getText();
            double updatedProjectBudget = Double.parseDouble(projectBudgetField.getText());
            double updatedProjectExpenses = Double.parseDouble(projectExpensesField.getText());

            // Update the project in the list model
            updateProject(selectedIndex, updatedProjectName, updatedProjectDescription, updatedProjectBudget, updatedProjectExpenses);

            editProjectDialog.dispose();
        });
        dialogButtonPanel.add(saveButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> {
            int confirmResult = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to delete this project?",
            "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (confirmResult == JOptionPane.YES_OPTION) {
            // Remove the project from the list model and delete it using the ProjectController
            String projectname = parts[0].substring("Project Name: ".length());
            Project projects = projectController.getProject(projectname);
            if (projects != null) {
                projectListModel.remove(selectedIndex);
                projectController.deleteProject(projects);
            }
            editProjectDialog.dispose();
        }
        });
        dialogButtonPanel.add(deleteButton);

        editProjectDialog.add(dialogButtonPanel, BorderLayout.SOUTH);

        editProjectDialog.pack();
        editProjectDialog.setLocationRelativeTo(SwingUtilities.getWindowAncestor(this));
        editProjectDialog.setVisible(true);
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

    public void clearProjects() {
        projectListModel.clear();
    }
}