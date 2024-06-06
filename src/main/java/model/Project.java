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
    private boolean isPrivate;
    private String pin;

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
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
    
    public boolean isPrivate(){
        return isPrivate;

    } 

    public void setPrivate(boolean isPrivate){
        this.isPrivate = isPrivate;
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
        this.budget = Math.round(budget * 100.0) / 100.0;
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
        this.expenses = Math.round(expenses * 100.0) / 100.0;
    }
}