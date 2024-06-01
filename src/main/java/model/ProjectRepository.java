package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectRepository {
    private static final String PROJECT_FILE = "/Users/ahmed/360project/Javavengers-1/Javavengers/Projects.txt";
    private List<Project> projects;

    public ProjectRepository() {
        this.projects = loadProjects();
    }

    private List<Project> loadProjects() {
        List<Project> projectList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(PROJECT_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] projectData = line.split(",");
                if (projectData.length == 4) {
                    String name = projectData[0];
                    String description = projectData[1];
                    double budget = Double.parseDouble(projectData[2]);
                    double expenses = Double.parseDouble(projectData[3]);
                    Project project = new Project(name, description, budget);
                    project.setBudget(budget);
                    project.addExpense(expenses);

                    // Load project documents
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

                    projectList.add(project);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading projects: " + e.getMessage());
        }
        return projectList;
    }

    public void saveProjects() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(PROJECT_FILE))) {
            for (Project project : projects) {
                writer.println(project.getName() + "," + project.getDescription() + "," +
                        project.getBudget() + "," + project.getExpenses());

                // Save project documents
                List<ProjectDocument> documents = project.getDocuments();
                writer.println(documents.size());
                for (ProjectDocument document : documents) {
                    writer.println(document.name() + "," + document.getFilepath());
                }
            }
        } catch (IOException e) {
            System.out.println("Error saving projects: " + e.getMessage());
        }
    }

    public void addProject(Project project) {
        projects.add(project);
    }

    public List<Project> getProjects() {
        return projects;
    }

    public boolean removeProject(Project project) {
        return projects.remove(project);
    }
}