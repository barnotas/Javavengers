package view;

import model.Project;
import model.ProjectDocument;
import controller.ProjectController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.poi.sl.usermodel.Slide;

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
 * The JList for displaying the list of projects.
 */
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

/**
 * Constructs a new ProjectsPanel with the specified NewProjectController.
 *
 * @param projectController the NewProjectController instance for handling the creation of new projects
 */

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

        // Create a button to delete the selected project
        JButton deleteProjectButton = new JButton("Delete Project");
        deleteProjectButton.addActionListener(e -> deleteSelectedProject());
        
        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(newProjectButton);
        buttonPanel.add(deleteProjectButton);
        add(buttonPanel, BorderLayout.NORTH);
    }

    private void showCreateNewProjectDialog() {
        JDialog newProjectDialog = new JDialog(SwingUtilities.getWindowAncestor(this), "Create New Project");
        newProjectDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        newProjectDialog.setLayout(new BorderLayout());
    
        // Create a panel for creating a new project
        JPanel newProjectPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);
    
        JLabel projectNameLabel = new JLabel("Project Name:");
        newProjectPanel.add(projectNameLabel, gbc);
    
        gbc.gridx = 1;
        JTextField projectNameField = new JTextField(20);
        newProjectPanel.add(projectNameField, gbc);
    
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel projectDescriptionLabel = new JLabel("Project Description:");
        newProjectPanel.add(projectDescriptionLabel, gbc);
    
        gbc.gridx = 1;
        JTextArea projectDescriptionArea = new JTextArea(4, 20);
        JScrollPane projectDescriptionScrollPane = new JScrollPane(projectDescriptionArea);
        newProjectPanel.add(projectDescriptionScrollPane, gbc);
    
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel projectBudgetLabel = new JLabel("Project Budget:");
        newProjectPanel.add(projectBudgetLabel, gbc);
    
        gbc.gridx = 1;
        JTextField projectBudgetField = new JTextField(10);
        newProjectPanel.add(projectBudgetField, gbc);
    
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel privateProjectLabel = new JLabel("Private Project:");
        newProjectPanel.add(privateProjectLabel, gbc);
    
        gbc.gridx = 1;
        JCheckBox privateCheckbox = new JCheckBox();
        newProjectPanel.add(privateCheckbox, gbc);
    
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel pinLabel = new JLabel("PIN:");
        newProjectPanel.add(pinLabel, gbc);
    
        gbc.gridx = 1;
        JTextField pinField = new JTextField(10);
        newProjectPanel.add(pinField, gbc);
    
        newProjectDialog.add(newProjectPanel, BorderLayout.CENTER);
    
        // Create a panel for the dialog buttons
        JPanel dialogButtonPanel = new JPanel();
        JButton createButton = new JButton("Create");
        createButton.addActionListener(e -> {
            String projectName = projectNameField.getText();
            String projectDescription = projectDescriptionArea.getText();
            double projectBudget = Double.parseDouble(projectBudgetField.getText());
            boolean isPrivate = privateCheckbox.isSelected();
            String pin = pinField.getText();
    
            Project project = new Project(projectName, projectDescription, projectBudget);
            project.setPrivate(isPrivate);
            project.setPin(pin);
            projectController.createProject(projectName, projectDescription, projectBudget);
            newProjectDialog.dispose();
        });
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
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.WEST;
            gbc.insets = new Insets(5, 5, 5, 5);

            JLabel projectNameLabel = new JLabel("Project Name:");
            editProjectPanel.add(projectNameLabel, gbc);

            gbc.gridx = 1;
            JTextField projectNameField = new JTextField(project.getName(), 20);
            editProjectPanel.add(projectNameField, gbc);

            gbc.gridx = 0;
            gbc.gridy++;
            JLabel projectDescriptionLabel = new JLabel("Project Description:");
            editProjectPanel.add(projectDescriptionLabel, gbc);

            gbc.gridx = 1;
            JTextArea projectDescriptionArea = new JTextArea(project.getDescription(), 4, 20);
            JScrollPane projectDescriptionScrollPane = new JScrollPane(projectDescriptionArea);
            editProjectPanel.add(projectDescriptionScrollPane, gbc);

            gbc.gridx = 0;
            gbc.gridy++;
            JLabel projectBudgetLabel = new JLabel("Project Budget:");
            editProjectPanel.add(projectBudgetLabel, gbc);

            gbc.gridx = 1;
            JTextField projectBudgetField = new JTextField(String.valueOf(project.getBudget()), 10);
            editProjectPanel.add(projectBudgetField, gbc);

            gbc.gridx = 0;
            gbc.gridy++;
            JLabel projectExpensesLabel = new JLabel("Project Expenses:");
            editProjectPanel.add(projectExpensesLabel, gbc);

            gbc.gridx = 1;
            JTextField projectExpensesField = new JTextField(String.valueOf(project.getExpenses()), 10);
            editProjectPanel.add(projectExpensesField, gbc);

            gbc.gridx = 0;
            gbc.gridy++;
            JLabel privateProjectLabel = new JLabel("Private Project:");
            editProjectPanel.add(privateProjectLabel, gbc);

            gbc.gridx = 1;
            JCheckBox privateCheckbox = new JCheckBox();
            privateCheckbox.setSelected(project.isPrivate());
            editProjectPanel.add(privateCheckbox, gbc);

            gbc.gridx = 0;
            gbc.gridy++;
            JLabel pinLabel = new JLabel("PIN:");
            editProjectPanel.add(pinLabel, gbc);

            gbc.gridx = 1;
            JTextField pinField = new JTextField(project.getPin(), 10);
            editProjectPanel.add(pinField, gbc);

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
                
                String pin = pinField.getText();
                project.setPin(pin);

                boolean isPrivate = privateCheckbox.isSelected();
                project.setPrivate(isPrivate);
                
                // Update the project in the list model and call the controller to update the project
                updateProject(selectedIndex, project);
    
                editProjectDialog.dispose();
            });
            dialogButtonPanel.add(saveButton);
    
            editProjectDialog.add(dialogButtonPanel, BorderLayout.SOUTH);
    
            editProjectDialog.pack();
            editProjectDialog.setLocationRelativeTo(SwingUtilities.getWindowAncestor(this));
            editProjectDialog.setVisible(true);
        }
    }

   /** This method opens a documents using the file path 
    *  that is created when adding a project 
    */ 

private void openDocument(ProjectDocument selectedDocument) {
    try {
        String filePath = selectedDocument.getFilepath();
        Path path = Paths.get(filePath);

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

/** This method below is if we want documents to be selected and viewed within the application 
 *  This method has a lot of bugs
 */

// private void openDocument(ProjectDocument selectedDocument) {
//     JFileChooser fileChooser = new JFileChooser();
//     int result = fileChooser.showOpenDialog(SwingUtilities.getWindowAncestor(this));
//     if (result == JFileChooser.APPROVE_OPTION) {
//         File selectedFile = fileChooser.getSelectedFile();
//         try {
//             String filePath = selectedFile.getAbsolutePath();
//             Path path = Paths.get(filePath);
//             String fileExtension = path.getFileName().toString().toLowerCase();
//             int lastDotIndex = fileExtension.lastIndexOf(".");
//             if (lastDotIndex != -1) {
//                 fileExtension = fileExtension.substring(lastDotIndex + 1);
//             }

//             JFrame documentFrame = new JFrame(selectedDocument.name());
//             documentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

//             JPanel documentPanel = new JPanel(new BorderLayout());

//             List<String> pdfExtensions = Arrays.asList("pdf");
//             List<String> docExtensions = Arrays.asList("doc", "docx");
//             List<String> excelExtensions = Arrays.asList("xls", "xlsx");
//             List<String> pptExtensions = Arrays.asList("ppt", "pptx");

//             if (pdfExtensions.contains(fileExtension)) {
//                 PDDocument document = PDDocument.load(selectedFile);
//                 PDFRenderer pdfRenderer = new PDFRenderer(document);
//                 BufferedImage image = pdfRenderer.renderImageWithDPI(0, 300);
//                 JLabel imageLabel = new JLabel(new ImageIcon(image));
//                 documentPanel.add(new JScrollPane(imageLabel), BorderLayout.CENTER);
//                 document.close();
//             } else if (docExtensions.contains(fileExtension)) {
//                 FileInputStream fileInputStream = new FileInputStream(selectedFile);
//                 HWPFDocument wordDocument = new HWPFDocument(fileInputStream);
//                 WordExtractor extractor = new WordExtractor(wordDocument);
//                 String documentText = extractor.getText();
//                 JEditorPane documentViewer = new JEditorPane();
//                 documentViewer.setEditable(false);
//                 documentViewer.setContentType("text/plain");
//                 documentViewer.setText(documentText);
//                 documentPanel.add(new JScrollPane(documentViewer), BorderLayout.CENTER);
//                 fileInputStream.close();
//             } else if (excelExtensions.contains(fileExtension)) {
//                 FileInputStream fileInputStream = new FileInputStream(selectedFile);
//                 Workbook workbook = WorkbookFactory.create(fileInputStream);
//                 Sheet sheet = workbook.getSheetAt(0);
//                 StringBuilder stringBuilder = new StringBuilder();
//                 for (Row row : sheet) {
//                     for (Cell cell : row) {
//                         stringBuilder.append(getCellValue(cell)).append("\t");
//                     }
//                     stringBuilder.append("\n");
//                 }
//                 JEditorPane documentViewer = new JEditorPane();
//                 documentViewer.setEditable(false);
//                 documentViewer.setContentType("text/plain");
//                 documentViewer.setText(stringBuilder.toString());
//                 documentPanel.add(new JScrollPane(documentViewer), BorderLayout.CENTER);
//                 fileInputStream.close();
//             } else if (pptExtensions.contains(fileExtension)) {
//                 FileInputStream fileInputStream = new FileInputStream(selectedFile);
//                 XMLSlideShow pptDocument = new XMLSlideShow(fileInputStream);
//                 boolean isBlank = pptDocument.getSlides().isEmpty();
//                 if (!isBlank) {
//                     Slide slide = pptDocument.getSlides().get(0);
//                     BufferedImage image = new BufferedImage(slide.getSlideShow().getPageSize().width, slide.getSlideShow().getPageSize().height, BufferedImage.TYPE_INT_ARGB);
//                     Graphics2D graphics = image.createGraphics();
//                     graphics.setPaint(Color.WHITE);
//                     graphics.fillRect(0, 0, image.getWidth(), image.getHeight());
//                     slide.draw(graphics);
//                     JLabel imageLabel = new JLabel(new ImageIcon(image));
//                     documentPanel.add(new JScrollPane(imageLabel), BorderLayout.CENTER);
//                 }
//                 fileInputStream.close();
//             } else {
//                 JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(ProjectsPanel.this),
//                         "This file type is not supported for viewing within the application.",
//                         "Unsupported File Type", JOptionPane.WARNING_MESSAGE);
//                 return;
//             }

//             documentFrame.getContentPane().add(documentPanel);
//             documentFrame.setSize(800, 600);
//             documentFrame.setLocationRelativeTo(SwingUtilities.getWindowAncestor(this));
//             documentFrame.setVisible(true);
//         } catch (IOException ex) {
//             ex.printStackTrace();
//             SwingUtilities.invokeLater(() -> {
//                 JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(ProjectsPanel.this),
//                         "Failed to open the document.",
//                         "Error", JOptionPane.ERROR_MESSAGE);
//             });
//         }
//     }
// }

// private String getCellValue(Cell cell) {
//     switch (cell.getCellType()) {
//         case NUMERIC:
//             return String.valueOf(cell.getNumericCellValue());
//         case STRING:
//             return cell.getStringCellValue();
//         case BOOLEAN:
//             return String.valueOf(cell.getBooleanCellValue());
//         case FORMULA:
//             return cell.getCellFormula();
//         default:
//             return "";
//     }
// }

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

    private void deleteSelectedProject() {
        int selectedIndex = projectList.getSelectedIndex();
        if (selectedIndex != -1) {
            String listEntry = projectListModel.getElementAt(selectedIndex);
            String[] parts = listEntry.split(" - ");
            String projectName = parts[0].substring("Project Name: ".length());
            Project project = projectController.getProject(projectName);
            if (project != null) {
                removeProject(project);
            }
        }
    }

    public void clearProjects() {
        projectListModel.clear();
    }
}

    
    



