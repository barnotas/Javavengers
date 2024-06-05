package view;

import model.Project;
import controller.ProjectController;
import model.ProjectList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The ProjectsPanel class represents a panel for displaying and managing projects.
 * It provides a list of projects, project details, and buttons for adding new projects.
 */
public class ProjectsPanel extends JPanel {

    private ProjectController projectController;
    private JPanel projectsContainer;
    private ProjectList projectList;

    /**
     * Constructs a new ProjectsPanel with the specified ProjectController.
     *
     * @param projectController the ProjectController instance for handling the creation of new projects
     */
    public ProjectsPanel(ProjectController projectController, ProjectList projectList) {
        this.projectController = projectController;
        this.projectList = projectList;
        setLayout(new BorderLayout());

        // Create a container panel for the project cards
        projectsContainer = new JPanel(new GridLayout(0, 2, 10, 10)); // 2 columns, 10px horizontal and vertical gaps
        JScrollPane scrollPane = new JScrollPane(projectsContainer);
        add(scrollPane, BorderLayout.CENTER);

        for (Project project : projectList.getProjects()) {
            addProject(project);
        }

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

            // Update the UI by adding the new project as a card
            addProject(new Project(projectName, projectDescription, projectBudget)); // Assuming initial expenses are 0
        });
        dialogButtonPanel.add(createButton);

        newProjectDialog.add(dialogButtonPanel, BorderLayout.SOUTH);

        newProjectDialog.pack();
        newProjectDialog.setLocationRelativeTo(SwingUtilities.getWindowAncestor(this));
        newProjectDialog.setVisible(true);
    }

    public void addProject(Project project) {
        JPanel projectPanel = new JPanel(new BorderLayout());
        projectPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        projectPanel.setBackground(Color.WHITE);
        projectPanel.setPreferredSize(new Dimension(150, 100));

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);
        JLabel projectNameLabel = new JLabel("Name: " + project.getName());
        JButton deleteButton = new JButton("X");
        deleteButton.setForeground(Color.RED);
        deleteButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        deleteButton.setContentAreaFilled(false);

        deleteButton.addActionListener(e -> {
            removeProject(project);
            projectsContainer.remove(projectPanel);
            projectsContainer.revalidate();
            projectsContainer.repaint();
        });

        headerPanel.add(projectNameLabel, BorderLayout.WEST);
        headerPanel.add(deleteButton, BorderLayout.EAST);

        JLabel projectDescriptionLabel = new JLabel("<html>Description: " + project.getDescription() + "</html>");
        projectDescriptionLabel.setVerticalAlignment(SwingConstants.TOP);

        JPanel footerPanel = new JPanel(new BorderLayout());
        footerPanel.setBackground(Color.WHITE);
        JLabel projectBudgetLabel = new JLabel("Budget: $" + project.getBudget());
        projectBudgetLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        projectBudgetLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        footerPanel.add(projectBudgetLabel, BorderLayout.EAST);

        projectPanel.add(headerPanel, BorderLayout.NORTH);
        projectPanel.add(projectDescriptionLabel, BorderLayout.CENTER);
        projectPanel.add(footerPanel, BorderLayout.SOUTH);

        projectPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showEditProjectPanel(project);
            }
        });

        projectsContainer.add(projectPanel);
        projectsContainer.revalidate();
        projectsContainer.repaint();
    }

    private void showEditProjectPanel(Project project) {
        removeAll(); // Clear the ProjectsPanel

        // Create a main panel for editing the project details and managing files
        JPanel mainPanel = new JPanel(new BorderLayout());

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

        // Add the edit panel to the main panel
        mainPanel.add(editProjectPanel, BorderLayout.CENTER);

        // Create a side panel for managing project files
        JPanel filesPanel = new JPanel();
        filesPanel.setLayout(new BoxLayout(filesPanel, BoxLayout.Y_AXIS));
        JScrollPane filesScrollPane = new JScrollPane(filesPanel);
        filesScrollPane.setPreferredSize(new Dimension(200, 0)); // Set preferred width for the side panel

        JButton addFileButton = new JButton("Add File");
        addFileButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                project.addFile(filePath);
                addFileToPanel(filePath, filesPanel, project);
            }
        });

        filesPanel.add(addFileButton);

        // Add existing files to the panel
        for (String filePath : project.getFiles()) {
            addFileToPanel(filePath, filesPanel, project);
        }

        mainPanel.add(filesScrollPane, BorderLayout.EAST);

        add(mainPanel, BorderLayout.CENTER);

        // Create a panel for the dialog buttons
        JPanel dialogButtonPanel = new JPanel();
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            String updatedProjectName = projectNameField.getText();
            String updatedProjectDescription = projectDescriptionArea.getText();
            double updatedProjectBudget = Double.parseDouble(projectBudgetField.getText());
            double updatedProjectExpenses = Double.parseDouble(projectExpensesField.getText());
        
            project.setName(updatedProjectName);
            project.setDescription(updatedProjectDescription);
            project.setBudget(updatedProjectBudget);
            project.setExpenses(updatedProjectExpenses);
        
            // Update the project in the ProjectList
            projectList.addProject(project);
        
            // Dispose of the editProjectFrame
            //editProjectFrame.dispose();
        });
        dialogButtonPanel.add(saveButton);

        add(dialogButtonPanel, BorderLayout.SOUTH);

        revalidate();
        repaint();
    }

    private void addFileToPanel(String filePath, JPanel filesPanel, Project project) {
        JPanel filePanel = new JPanel(new BorderLayout());
        JLabel fileNameLabel = new JLabel(new File(filePath).getName());
        JButton deleteFileButton = new JButton("X");
        deleteFileButton.setForeground(Color.RED);
        deleteFileButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        deleteFileButton.setContentAreaFilled(false);

        deleteFileButton.addActionListener(e -> {
            project.removeFile(filePath);
            filesPanel.remove(filePanel);
            filesPanel.revalidate();
            filesPanel.repaint();
        });

        filePanel.add(fileNameLabel, BorderLayout.CENTER);
        filePanel.add(deleteFileButton, BorderLayout.EAST);
        fileNameLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openProjectFile(filePath);
            }
        });

        filesPanel.add(filePanel);
        filesPanel.revalidate();
        filesPanel.repaint();
    }

    private void openProjectFile(String filePath) {
        try {
            Desktop.getDesktop().open(new File(filePath));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error opening project file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void exportProjectSettings(Project project) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
                writer.println("Project Name: " + project.getName());
                writer.println("Description: " + project.getDescription());
                writer.println("Budget: " + project.getBudget());
                writer.println("Expenses: " + project.getExpenses());
                writer.println("Total Cost: " + (project.getBudget() - project.getExpenses()));
                for (String file : project.getFiles()) {
                    writer.println("File: " + file);
                }
                JOptionPane.showMessageDialog(this, "Project settings exported successfully.");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error exporting project settings: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void importProjectSettings(Project project) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                List<String> importedFiles = new ArrayList<>();
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("Project Name: ")) {
                        project.setName(line.substring("Project Name: ".length()));
                    } else if (line.startsWith("Description: ")) {
                        project.setDescription(line.substring("Description: ".length()));
                    } else if (line.startsWith("Budget: ")) {
                        project.setBudget(Double.parseDouble(line.substring("Budget: ".length())));
                    } else if (line.startsWith("Expenses: ")) {
                        project.setExpenses(Double.parseDouble(line.substring("Expenses: ".length())));
                    } else if (line.startsWith("File: ")) {
                        importedFiles.add(line.substring("File: ".length()));
                    }
                }
                project.setFiles(importedFiles);
                updateProject(project);
                JOptionPane.showMessageDialog(this, "Project settings imported successfully.");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error importing project settings: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void updateProject(Project project) {
        for (Component comp : projectsContainer.getComponents()) {
            if (comp instanceof JPanel) {
                JPanel projectPanel = (JPanel) comp;
                JLabel nameLabel = (JLabel) ((JPanel) projectPanel.getComponent(0)).getComponent(0);
                if (nameLabel.getText().equals("Name: " + project.getName())) {
                    JLabel descriptionLabel = (JLabel) projectPanel.getComponent(1);
                    JLabel budgetLabel = (JLabel) ((JPanel) projectPanel.getComponent(2)).getComponent(0);
                    descriptionLabel.setText("<html>Description: " + project.getDescription() + "</html>");
                    budgetLabel.setText("Budget: $" + project.getBudget());
                    projectPanel.revalidate();
                    projectPanel.repaint();
                }
            }
        }

        // Call the ProjectController to update the project in the repository
        projectController.updateProject(project);
    }

    public void removeProject(Project project) {
        // Call the ProjectController to delete the project
        projectController.deleteProject(project);
    }

    public void clearProjects() {
        projectsContainer.removeAll();
        projectsContainer.revalidate();
        projectsContainer.repaint();
    }

    private void restoreProjectsView() {
        removeAll();

        // Restore the projectsContainer and the new project button
        projectsContainer = new JPanel(new GridLayout(0, 2, 10, 10));
        JScrollPane scrollPane = new JScrollPane(projectsContainer);
        add(scrollPane, BorderLayout.CENTER);

        JButton newProjectButton = new JButton("Add New Project");
        newProjectButton.addActionListener(e -> showCreateNewProjectDialog());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(newProjectButton);
        add(buttonPanel, BorderLayout.NORTH);

        // Re-add the existing projects
        for (Project project : projectList.getProjects()) {
            addProject(project);
        }

        revalidate();
        repaint();
    }
}
