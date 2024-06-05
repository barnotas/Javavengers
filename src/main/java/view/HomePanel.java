package view;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HomePanel extends JPanel {
    private JList<String> projectList;
    private DefaultListModel<String> projectListModel;



    public HomePanel() {
        setLayout(new BorderLayout());
    
        // Create a panel for the welcome message
        JPanel welcomePanel = new JPanel(new BorderLayout());
        welcomePanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0)); // Add some vertical padding
    
        // Create a label for the welcome message
        JLabel welcomeLabel = new JLabel("Welcome to Project Peak!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Set a larger font size
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center the text horizontally
        welcomePanel.add(welcomeLabel, BorderLayout.CENTER);
    
        // Add the welcome panel to the top of the HomePanel
        add(welcomePanel, BorderLayout.NORTH);
    
        // Create a panel for the project list
        JPanel projectListPanel = new JPanel(new BorderLayout());
        projectListModel = new DefaultListModel<>();
        projectList = new JList<>(projectListModel);
        projectList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int selectedIndex = projectList.getSelectedIndex();
                    if (selectedIndex != -1) {
                        showViewProjectDialog(selectedIndex);
                    }
                }
            }
        });
        projectListPanel.add(new JScrollPane(projectList), BorderLayout.CENTER);
    
        // Add the project list panel to the center of the HomePanel
        add(projectListPanel, BorderLayout.CENTER);
    }

    public void addProject(String projectName, String projectDescription, double budget, double expenses, boolean isPrivate) {
        if (!isPrivate) {
            String listEntry = "Project Name: " + projectName + " - Description: " + projectDescription +
                    " - Budget: $" + budget + " - Expenses: $" + expenses +
                    " - Total Cost: $" + (budget - expenses);
            projectListModel.addElement(listEntry);
        }
    }
    
    public void updateProject(int index, String name, String description, double budget, double expenses, boolean isPrivate) {
        if (!isPrivate) {
            String listEntry = "Project Name: " + name + " - Description: " + description +
                    " - Budget: $" + budget + " - Expenses: $" + expenses +
                    " - Total Cost: $" + (budget - expenses);
            projectListModel.setElementAt(listEntry, index);
        } else {
            projectListModel.remove(index);
        }
    }

    private void showViewProjectDialog(int selectedIndex) {
        String listEntry = projectListModel.getElementAt(selectedIndex);
        String[] parts = listEntry.split(" - ");
        String projectName = parts[0].substring("Project Name: ".length());
        String projectDescription = parts[1].substring("Description: ".length());
        String projectBudget = parts[2].substring("Budget: $".length());
        String projectExpenses = parts[3].substring("Expenses: $".length());

        JDialog viewProjectDialog = new JDialog(SwingUtilities.getWindowAncestor(this), "View Project");
        viewProjectDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        viewProjectDialog.setLayout(new BorderLayout());

        // Create a panel for editing the project details
        JPanel viewProjectPanel = new JPanel(new GridBagLayout());
        GridBagConstraints viewProjectConstraints = new GridBagConstraints();
        viewProjectConstraints.gridx = 0;
        viewProjectConstraints.gridy = 0;
        viewProjectConstraints.anchor = GridBagConstraints.WEST;
        viewProjectConstraints.insets = new Insets(5, 5, 5, 5);

        JLabel projectNameLabel = new JLabel("Project Name:");
        viewProjectPanel.add(projectNameLabel, viewProjectConstraints);

        viewProjectConstraints.gridx = 1;
        JTextField projectNameField = new JTextField(projectName, 20);
        projectNameField.setEditable(false);
        viewProjectPanel.add(projectNameField, viewProjectConstraints);

        viewProjectConstraints.gridx = 0;
        viewProjectConstraints.gridy = 1;
        JLabel projectDescriptionLabel = new JLabel("Project Description:");
        viewProjectPanel.add(projectDescriptionLabel, viewProjectConstraints);

        viewProjectConstraints.gridx = 1;
        JTextArea projectDescriptionArea = new JTextArea(projectDescription, 4, 20);
        projectDescriptionArea.setEditable(false);
        JScrollPane projectDescriptionScrollPane = new JScrollPane(projectDescriptionArea);
        viewProjectPanel.add(projectDescriptionScrollPane, viewProjectConstraints);

        viewProjectConstraints.gridx = 0;
        viewProjectConstraints.gridy = 2;
        JLabel projectBudgetLabel = new JLabel("Project Budget:");
        viewProjectPanel.add(projectBudgetLabel, viewProjectConstraints);

        viewProjectConstraints.gridx = 1;
        JTextField projectBudgetField = new JTextField(projectBudget, 10);
        projectBudgetField.setEditable(false);
        viewProjectPanel.add(projectBudgetField, viewProjectConstraints);

        viewProjectConstraints.gridx = 0;
        viewProjectConstraints.gridy = 3;
        JLabel projectExpensesLabel = new JLabel("Project Expenses:");
        viewProjectPanel.add(projectExpensesLabel, viewProjectConstraints);

        viewProjectConstraints.gridx = 1;
        JTextField projectExpensesField = new JTextField(projectExpenses, 10);
        projectExpensesField.setEditable(false);
        viewProjectPanel.add(projectExpensesField, viewProjectConstraints);

        viewProjectDialog.add(viewProjectPanel, BorderLayout.CENTER);

        viewProjectDialog.pack();
        viewProjectDialog.setLocationRelativeTo(SwingUtilities.getWindowAncestor(this));
        viewProjectDialog.setVisible(true);
    }

    public void removeProject(String projectName) {
        // Find the index of the project in the list model
        int index = -1;
        for (int i = 0; i < projectListModel.getSize(); i++) {
            String listEntry = projectListModel.getElementAt(i);
            if (listEntry.startsWith("Project Name: " + projectName + " - ")) {
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