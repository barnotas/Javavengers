package view;

import javax.swing.*;

import model.Project;

public class HomePanel extends JPanel {
    private JList<String> projectList;
    private DefaultListModel<String> projectListModel;

    public HomePanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Welcome to Project Peak!"));
        add(new JLabel("This is the home panel."));

        projectListModel = new DefaultListModel<>();
        projectList = new JList<>(projectListModel);
        add(new JScrollPane(projectList));
    }

    public void addProject(String projectName, String projectDescription, double budget, double expenses) {
        String listEntry = "Project Name: " + projectName + " - Description: " + projectDescription + " - Budget: $" + budget + " - Expenses: $" + expenses;
        projectListModel.addElement(listEntry);
        
    }

    public void updateProject(int index, String name, String description, double budget, double expenses) {
        String listEntry = name + " - Description: " + description + " - Budget: $" + budget + " - Expenses: $" + expenses;
        projectListModel.setElementAt(listEntry, index);
    }
    
    public void removeProject(String projectName) {
        // Find the index of the project in the list model
        int index = -1;
        for (int i = 0; i < projectListModel.getSize(); i++) {
            String listEntry = projectListModel.getElementAt(i);
            if (listEntry.startsWith(projectName + " - ")) {
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