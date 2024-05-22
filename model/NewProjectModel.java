package model;

public class NewProjectModel {
    private int id;
    private String name;
    private String description;
    private double budget;
    private double expenses;

    // Constructor
    public NewProjectModel() {
        this.id = 0;
        this.name = "";
        this.description = "";
        this.budget = 0.0;
        this.expenses = 0.0;
    }

    // Getter and Setter for id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter and Setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter for description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Getter and Setter for budget
    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    // Getter for expenses
    public double getExpenses() {
        return expenses;
    }

    // Method to add expenses
    public void addExpense(double amount) {
        this.expenses += amount;
    }
}
