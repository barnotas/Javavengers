package view;

import model.Project;
import model.ProjectDocument;
import controller.ProjectController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


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

    private JList<String> projectList;

    /**
     * The DefaultListModel for storing the project list items.
     */
    public DefaultListModel<String> projectListModel;

    /**
     * The JLabel for displaying the project budget.
     */
    private JLabel budgetLabel;

    /**
     * The JLabel for displaying the project expenses.
     */
    private JLabel expensesLabel;
    private ProjectController projectController;

    public ProjectsPanel(ProjectController projectController) {
        this.projectController = projectController;
        setLayout(new BorderLayout());

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
                        showEditProjectDialog(selectedIndex);
                    }
                }
            }
        });
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
            projectController.createProject(projectName, projectDescription, projectBudget);
            newProjectDialog.dispose();
            
            // Update the UI by loading the projects
            //projectController.loadProjects(projectController.getCurrentUser());
        });
        dialogButtonPanel.add(createButton);
        dialogButtonPanel.add(createButton);
    
        newProjectDialog.add(dialogButtonPanel, BorderLayout.SOUTH);
    
        newProjectDialog.pack();
        newProjectDialog.setLocationRelativeTo(SwingUtilities.getWindowAncestor(this));
        newProjectDialog.setVisible(true);
    }



    private void showEditProjectDialog(int selectedIndex) {
        String listEntry = projectListModel.getElementAt(selectedIndex);
        String[] parts = listEntry.split(" - ");
        String projectName = parts[0].substring("Project Name: ".length());
    
        // Retrieve the actual Project object using the ProjectController
        Project project = projectController.getProject(projectName);
    
        if (project != null) {
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
            JTextField projectNameField = new JTextField(project.getName(), 20);
            editProjectPanel.add(projectNameField, editProjectConstraints);
    
            editProjectConstraints.gridx = 0;
            editProjectConstraints.gridy = 1;
            JLabel projectDescriptionLabel = new JLabel("Project Description:");
            editProjectPanel.add(projectDescriptionLabel, editProjectConstraints);
    
            editProjectConstraints.gridx = 1;
            JTextArea projectDescriptionArea = new JTextArea(project.getDescription(), 4, 20);
            JScrollPane projectDescriptionScrollPane = new JScrollPane(projectDescriptionArea);
            editProjectPanel.add(projectDescriptionScrollPane, editProjectConstraints);
    
            editProjectConstraints.gridx = 0;
            editProjectConstraints.gridy = 2;
            JLabel projectBudgetLabel = new JLabel("Project Budget:");
            editProjectPanel.add(projectBudgetLabel, editProjectConstraints);
    
            editProjectConstraints.gridx = 1;
            JTextField projectBudgetField = new JTextField(String.valueOf(project.getBudget()), 10);
            editProjectPanel.add(projectBudgetField, editProjectConstraints);
    
            editProjectConstraints.gridx = 0;
            editProjectConstraints.gridy = 3;
            JLabel projectExpensesLabel = new JLabel("Project Expenses:");
            editProjectPanel.add(projectExpensesLabel, editProjectConstraints);
    
            editProjectConstraints.gridx = 1;
            JTextField projectExpensesField = new JTextField(String.valueOf(project.getExpenses()), 10);
            editProjectPanel.add(projectExpensesField, editProjectConstraints);
    
            editProjectConstraints.gridx = 0;
            editProjectConstraints.gridy = 4;
            JLabel totalCostLabel = new JLabel("Total Cost:");
            editProjectPanel.add(totalCostLabel, editProjectConstraints);
    
            editProjectConstraints.gridx = 1;
            JLabel totalCostValueLabel = new JLabel(String.valueOf(project.getBudget() - project.getExpenses()));
            editProjectPanel.add(totalCostValueLabel, editProjectConstraints);
    
            editProjectDialog.add(editProjectPanel, BorderLayout.CENTER);
    
            // Create a panel for the dialog buttons
            JPanel dialogButtonPanel = new JPanel();
            JButton saveButton = new JButton("Save");
            saveButton.addActionListener(e -> {
                String updatedProjectName = projectNameField.getText();
                String updatedProjectDescription = projectDescriptionArea.getText();
                double updatedProjectBudget = Double.parseDouble(projectBudgetField.getText());
                double updatedProjectExpenses = Double.parseDouble(projectExpensesField.getText());
    
                // Update the project details
                project.setName(updatedProjectName);
                project.setDescription(updatedProjectDescription);
                project.setBudget(updatedProjectBudget);
                project.setExpenses(updatedProjectExpenses);
    
                // Update the project in the list model and call the controller to update the project
                updateProject(selectedIndex, project);
    
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
                    projectController.deleteProject(project);
                }
                editProjectDialog.dispose();
            }
            });
            dialogButtonPanel.add(deleteButton);

            // Create a panel for displaying project documents
            JPanel documentsPanel = new JPanel(new BorderLayout());
            JLabel documentsLabel = new JLabel("Documents:");
            documentsPanel.add(documentsLabel, BorderLayout.NORTH);

            DefaultListModel<ProjectDocument> documentsListModel = new DefaultListModel<>();
            documentsListModel.clear();
            //add exsiting documents
            for (ProjectDocument document : project.getDocuments()) {
                documentsListModel.addElement(document);
            }
            JList<ProjectDocument> documentsList = new JList<>(documentsListModel);
            documentsList.setCellRenderer(new DefaultListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                    super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    if (value instanceof ProjectDocument) {
                        ProjectDocument document = (ProjectDocument) value;
                        setText(document.name());
                    }
                    return this;
                }
            });
            documentsPanel.add(new JScrollPane(documentsList), BorderLayout.CENTER);
           
            JButton addDocumentButton = new JButton("Add Document");
            addDocumentButton.addActionListener(e -> {
                    JFileChooser fileChooser = new JFileChooser();
                    int result = fileChooser.showOpenDialog(SwingUtilities.getWindowAncestor(this));
                    if (result == JFileChooser.APPROVE_OPTION) {
                        File selectedFile = fileChooser.getSelectedFile();
                        // Create a new ProjectDocument with the selected file
                        String documentName = selectedFile.getName();
                        String filePath = selectedFile.getAbsolutePath();
                        ProjectDocument document = new ProjectDocument(documentName, filePath);
                        // Add the document to the project
                        project.addDocument(document);
                        // Save the updated project
                        projectController.updateProject(project);
                        documentsListModel.clear();

                        for (ProjectDocument documents : project.getDocuments()) {
                            documentsListModel.addElement(documents);
                        }

                        JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this), "Document added successfully!");
                    }
                
            });
            documentsPanel.add(addDocumentButton, BorderLayout.NORTH);

            JButton viewDocumentButton = new JButton("View Document");
            viewDocumentButton.addActionListener(e -> {
                int selectedDocumentIndex = documentsList.getSelectedIndex();
                if (selectedDocumentIndex != -1) {
                    ProjectDocument selectedDocument = documentsListModel.getElementAt(selectedDocumentIndex);
                    // Open the selected document in a separate thread
                    new Thread(() -> openDocument(selectedDocument)).start();
                }
            });
            documentsPanel.add(viewDocumentButton, BorderLayout.LINE_END);

            JButton deleteDocumentButton = new JButton("Delete Document");
            deleteDocumentButton.addActionListener(e -> {
                int selectedDocumentIndex = documentsList.getSelectedIndex();
                if (selectedDocumentIndex != -1) {
                    ProjectDocument selectedDocument = documentsListModel.getElementAt(selectedDocumentIndex);
                    int confirmResult = JOptionPane.showConfirmDialog(this,
                            "Are you sure you want to delete this document?",
                            "Confirm Delete", JOptionPane.YES_NO_OPTION);
                    if (confirmResult == JOptionPane.YES_OPTION) {
                        project.removeDocument(selectedDocument);
                        projectController.updateProject(project);
                        // Refresh the documents list
                        documentsListModel.clear();
                        for (ProjectDocument document : project.getDocuments()) {
                            documentsListModel.addElement(document);
                        }
                    }
                }
            });
            documentsPanel.add(deleteDocumentButton, BorderLayout.PAGE_END);

            editProjectDialog.add(documentsPanel, BorderLayout.EAST);
            
            editProjectDialog.add(dialogButtonPanel, BorderLayout.SOUTH);
    
            editProjectDialog.pack();
            editProjectDialog.setLocationRelativeTo(SwingUtilities.getWindowAncestor(this));
            editProjectDialog.setVisible(true);
        }
    }

    private void openDocument(ProjectDocument selectedDocument) {
        try {
            String filePath = selectedDocument.getFilepath();
            Path path = Paths.get(filePath);

            // Get the MIME type of the file
            String mimeType = Files.probeContentType(path);

            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.OPEN)) {
                Desktop desktop = Desktop.getDesktop();
                desktop.open(path.toFile());
            } else {
                // Handle the case when the Desktop API is not supported
                JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(ProjectsPanel.this),
                        "Failed to open the document. Desktop API is not supported.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            SwingUtilities.invokeLater(() -> {
                JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(ProjectsPanel.this),
                        "Failed to open the document.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            });
        }
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

    public void addProject(Project project) {
        String listEntry = "Project Name: " + project.getName() + " - Description: " + project.getDescription() + " - Budget: $" + project.getBudget() + " - Expenses: $" + project.getExpenses()+
        " - Total Cost: $" + (project.getBudget() - project.getExpenses());
        projectListModel.addElement(listEntry);
        revalidate();
        repaint();
    }

    public void updateProject(int index, Project project) {
        String listEntry = "Project Name: " + project.getName() + " - Description: " + project.getDescription() +
                " - Budget: $" + project.getBudget() + " - Expenses: $" + project.getExpenses() +
                " - Total Cost: $" + (project.getBudget() - project.getExpenses());
        projectListModel.setElementAt(listEntry, index);
    
        // Call the ProjectController to update the project in the repository
        projectController.updateProject(project);
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
        
        // Call the ProjectController to delete the project
        projectController.deleteProject(project); 
    }



    public void clearProjects() {
        projectListModel.clear();
    }


}


