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
    /**
     * Returns pin number.
     * @return
     */
    public String getPin() {
        return pin;
    }

    /**
     * Sets pin number.
     * @param pin
     */
    public void setPin(String pin) {
        this.pin = pin;
    }
    /**
     * Returns if private button is check.
     * @return
     */
    public boolean isPrivate(){
        return isPrivate;

    } 

    /**
     * Sets project private 
     * @param isPrivate
     */
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
    /**
     * Sets the budget for this project. The budget is rounded to two decimal places.
 *
     * @param budget
     */
    public void setBudget(double budget){
        this.budget = Math.round(budget * 100.0) / 100.0;
    }
    /**
     * Returns the budget.
     * @return
     */
    public double getBudget(){
        return budget;
    }

    /**
     * Adds document to the list.
     * @param document
     */
    public void addDocument(ProjectDocument document) {
        documents.add(document);
    }

    /**
     * Removes documents from the list.
     * @param document
     */
    public void removeDocument(ProjectDocument document) {
        documents.remove(document);
    }

    /**
     * Return list of documents.
     * @return
     */
    public List<ProjectDocument> getDocuments() {
        return documents;
    }

    /**
     * Adds expense to total amount.
     * @param amount
     */
    public void addExpense(double amount) {
        expenses += amount;
    }

    /**
     * Returns expenses.
     * @return
     */
    public double getExpenses() {
        return expenses;
    }

    /**
     * Rounds expenses to decimal points.
     * @param expenses
     */
    public void setExpenses(double expenses) {
        this.expenses = Math.round(expenses * 100.0) / 100.0;
    }
}