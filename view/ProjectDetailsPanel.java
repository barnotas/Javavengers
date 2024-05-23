package view;

import javax.swing.*;

public class ProjectDetailsPanel extends JPanel {
    private JLabel projectNameLabel;
    private JLabel projectDescriptionLabel;
    private JList<String> documentList;
    private DefaultListModel<String> documentListModel;
    private JLabel budgetLabel;
    private JLabel expensesLabel;

    public ProjectDetailsPanel() {
        // Initialize and add components to the panel
        // ...
    }

    public void setProjectName(String name) {
        projectNameLabel.setText("Project Name: " + name);
    }

    public void setProjectDescription(String description) {
        projectDescriptionLabel.setText("Project Description: " + description);
    }

    public void addDocument(String documentName) {
        documentListModel.addElement(documentName);
    }

    public void setBudget(double budget) {
        budgetLabel.setText("Budget: $" + budget);
    }

    public void setExpenses(double expenses) {
        expensesLabel.setText("Expenses: $" + expenses);
    }
}