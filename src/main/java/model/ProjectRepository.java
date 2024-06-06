package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
/**
 * The ProjectRepository class is responsible for managing the loading and saving of projects
 * associated with users. It interacts with the file system to persist project data.
 * 
 * @autor Bernard Bega, Barno Tashpulatova, Ahmed Hassan, Mahri Yalkapova
 */
public class ProjectRepository {
    /** A field for project directory. */
    private static final String PROJECTS_DIRECTORY = "projects";

     /**
     * Constructs a ProjectRepository and ensures the projects directory exists.
     */
    public ProjectRepository() {
        File projectsDir = new File(PROJECTS_DIRECTORY);
        if (!projectsDir.exists()) {
            projectsDir.mkdirs();
        }
    }

    /**
     * Loads the projects for the specified user from the file system.
     * 
     * @param user the user whose projects are to be loaded
     */
    public void loadProjects(User user) {
        ProjectList projectList = user.getProjectList();
        String userProjectFile = getUserProjectFile(user);
        File file = new File(userProjectFile);

        if (!file.exists()) {
            // File doesn't exist, create a new empty file
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating user project file: " + e.getMessage());
            }
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(userProjectFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] projectData = line.split(", ");
                if (projectData.length == 6) {
                    String name = projectData[0];
                    String description = projectData[1];
                    double budget = Double.parseDouble(projectData[2]);
                    double expenses = 0.0;
                    boolean isPrivate = Boolean.parseBoolean(projectData[4]);
                    String pin = isPrivate ? projectData[5] : "";
                    Project project = new Project(name, description, budget);
                    project.setExpenses(expenses);
                    project.setPrivate(isPrivate);
                    project.setPin(pin);

                    int numDocuments = Integer.parseInt(reader.readLine());
                    for (int i = 0; i < numDocuments; i++) {
                        String documentLine = reader.readLine();
                        String[] documentData = documentLine.split(",");
                        if (documentData.length == 2) {
                            String documentName = documentData[0];
                            String documentFilepath = documentData[1];
                            ProjectDocument document = new ProjectDocument(documentName, documentFilepath);
                            project.addDocument(document);
                        }
                    }

                    projectList.addProject(project);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading projects for user " + user.getUsername() + ": " + e.getMessage());
        }
    }

    /**
     * Saves the projects for the specified user to the file system.
     * 
     * @param user the user whose projects are to be saved
     */
    public void saveProjects(User user) {
        String userProjectFile = getUserProjectFile(user);
        File file = new File(userProjectFile);
    
        try {
            // Create the file if it doesn't exist
            if (!file.exists()) {
                file.createNewFile();
            }
    
            try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
                ProjectList projectList = user.getProjectList();
                List<Project> projects = projectList.getProjects();
                for (Project project : projects) {
                    writer.println(project.getName() + ", " + project.getDescription() + ", " 
                    + project.getBudget() + ", " + project.getExpenses() 
                    + ", " + project.isPrivate() + ", " + (project.isPrivate() ? project.getPin() : ""));
    
                    List<ProjectDocument> documents = project.getDocuments();
                    int numDocuments = documents.size();
                    writer.println(numDocuments);
    
                    if (numDocuments > 0) {
                        for (ProjectDocument document : documents) {
                            writer.println(document.name() + "," + document.getFilepath());
                        }
                    }
                }
                writer.flush(); // Flush the PrintWriter to ensure data is written to the file
            }
        } catch (IOException e) {
            System.out.println("Error saving projects for user " + user.getUsername() + ": " + e.getMessage());
        }
    }

    /**
     * Returns the file path for the specified user's project file.
     * 
     * @param user the user whose project file path is to be generated
     * @return the file path for the user's project file
     */
    private String getUserProjectFile(User user) {
        String uniqueIdentifier = user.getEmail().replace("@", "_").replace(".", "_"); // Replace '@' and '.' with '_' in the email address
        return PROJECTS_DIRECTORY + "/" + user.getUsername() + "_" + uniqueIdentifier + "_projects.txt";
    }

    /**
     * Adds a project to the specified user's project list and saves the projects.
     * 
     * @param user the user to whom the project is to be added
     * @param project the project to be added
     */
    public void addProject(User user, Project project) {
        user.addProject(project);
        saveProjects(user);
    }

    /**
     * Removes a project from the specified user's project list and saves the projects.
     * 
     * @param user the user from whom the project is to be removed
     * @param project the project to be removed
     */
    public void removeProject(User user, Project project) {
        user.removeProject(project);
        saveProjects(user);
    }
}