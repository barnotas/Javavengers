package model;

import java.io.*;
import java.util.List;

public class ProjectRepository {
    private static final String PROJECTS_DIRECTORY = "projects";

    public ProjectRepository() {
        File projectsDir = new File(PROJECTS_DIRECTORY);
        if (!projectsDir.exists()) {
            projectsDir.mkdirs();
        }
    }

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


    private String getUserProjectFile(User user) {
        String uniqueIdentifier = user.getEmail().replace("@", "_").replace(".", "_"); // Replace '@' and '.' with '_' in the email address
        return PROJECTS_DIRECTORY + "/" + user.getUsername() + "_" + uniqueIdentifier + "_projects.txt";
    }

    public void addProject(User user, Project project) {
        user.addProject(project);
        saveProjects(user);
    }

    public void removeProject(User user, Project project) {
        user.removeProject(project);
        saveProjects(user);
    }
}