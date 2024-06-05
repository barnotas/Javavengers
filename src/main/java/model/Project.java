package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a project with a name and description.
 */
public class Project {

    private List<ProjectDocument> documents;
    private String name;
    private String description;
    private double expenses;
    private double budget;
    private List<String> files;

    /**
     * Constructs a new Project with the specified name and description.
     *
     * @param name        the name of the project
     * @param description the description of the project
     */
    public Project(String name, String description, double budget) {
        this.name = name;
        this.description = description;
        this.budget = budget;
        this.documents = new ArrayList<>();
        this.expenses = 0;
        this.files = new ArrayList<>();
    }

    /**
     * Returns the name of the project.
     *
     * @return the name of the project
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the project.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the description of the project.
     *
     * @return the description of the project
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the project.
     *
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    public void setBudget(double budget){
        this.budget = budget;
    }
    public double getBudget(){
        return budget;
    }

    public void addDocument(ProjectDocument document) {
        documents.add(document);
    }

    public void removeDocument(ProjectDocument document) {
        documents.remove(document);
    }

    public List<ProjectDocument> getDocuments() {
        return documents;
    }

    public void addExpense(double amount) {
        expenses += amount;
    }

    public double getExpenses() {
        return expenses;
    }

    public void setExpenses(double expenses) {
        this.expenses = expenses;
    }

    public List<String> getFiles() { 
        return files; 
    }

    public void setFiles(List<String> files) { 
        this.files = files; 
    }

    public void addFile(String file) { 
        this.files.add(file); 
    }

    public void removeFile(String file) { 
        this.files.remove(file); 
    }
}