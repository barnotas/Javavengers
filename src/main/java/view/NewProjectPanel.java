package view;

import javax.swing.*;

import controller.NewProjectController;
import model.Project;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewProjectPanel extends JDialog {
    private JLabel idLabel;
    private JTextField idField;
    private JLabel nameLabel;
    private JTextField nameField;
    private JLabel descriptionLabel;
    private JTextArea descriptionArea;
    private JLabel budgetLabel;
    private JTextField budgetField;
    private JButton createButton;

    public NewProjectPanel(Frame parent,  NewProjectController controller) {
        super(parent, "Create New Project", true);
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 5, 5, 5);

        idLabel = new JLabel("Project ID:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(idLabel, constraints);

        idField = new JTextField(10);
        constraints.gridx = 1;
        add(idField, constraints);

        nameLabel = new JLabel("Project Name:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(nameLabel, constraints);

        nameField = new JTextField(20);
        constraints.gridx = 1;
        add(nameField, constraints);

        descriptionLabel = new JLabel("Description:");
        constraints.gridx = 0;
        constraints.gridy = 2;
        add(descriptionLabel, constraints);

        descriptionArea = new JTextArea(5, 20);
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.BOTH;
        add(new JScrollPane(descriptionArea), constraints);

        budgetLabel = new JLabel("Budget:");
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        add(budgetLabel, constraints);

        budgetField = new JTextField(10);
        constraints.gridx = 1;
        add(budgetField, constraints);

        createButton = new JButton("Create Project");
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 3;
        constraints.anchor = GridBagConstraints.CENTER;
        add(createButton, constraints);
        
        createButton.addActionListener(new CreateProjectListener(this, controller));

        pack();
        setLocationRelativeTo(parent);
    }
    
    public String getProjectName() {
        return nameField.getText();
    }

    public String getProjectDescription() {
        return descriptionArea.getText();
    }

    public int getProjectId() throws NumberFormatException {
        return Integer.parseInt(idField.getText());
    }

    public double getProjectBudget() throws NumberFormatException {
        return Double.parseDouble(budgetField.getText());
    }

    public void addCreateProjectListener(ActionListener listener) {
        createButton.addActionListener(listener);
    }

    class CreateProjectListener implements ActionListener {
        private NewProjectPanel newProjectPanel;
        private NewProjectController controller;
    
        public CreateProjectListener(NewProjectPanel newProjectPanel, NewProjectController controller) {
            this.newProjectPanel = newProjectPanel;
            this.controller = controller;
        }
    
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int projectId = newProjectPanel.getProjectId();
                String projectName = newProjectPanel.getProjectName();
                String projectDescription = newProjectPanel.getProjectDescription();
                double projectBudget = newProjectPanel.getProjectBudget();
    
                // Validate project name and description
                if (projectName.trim().isEmpty() || projectDescription.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(newProjectPanel, "Project name and description cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
    
                // Create the project and add it to the project list
                Project project = new Project(projectName, projectDescription);
                project.setBudget(projectBudget);
                controller.addProject(project);
    
                newProjectPanel.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(newProjectPanel, "Invalid input. Please enter valid values.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        
    }
}
