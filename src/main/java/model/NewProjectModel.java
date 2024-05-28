package model;

import view.HomePanel;
import view.NewProjectPanel;
import view.ProjectsPanel;

/**
 * Represents a model for creating a new project.
 */
public class NewProjectModel {
    private int id;
    private String name;
    private String description;
    private double budget;
    private double expenses;

    private ProjectList projectList;
    private HomePanel homePanel;
    private ProjectsPanel projectsPanel;

    /**
     * Constructs a new NewProjectModel with default values.
     */
    public NewProjectModel(NewProjectModel newProjectModel, ProjectList projectList, HomePanel homePanel, ProjectsPanel projectsPanel) {
        this.id = 0;
        this.name = "";
        this.description = "";
        this.budget = 0.0;
        this.expenses = 0.0;
        this.projectList = projectList;
        this.homePanel = homePanel;
        this.projectsPanel = projectsPanel;
    }

    public void NewProjectCheck(NewProjectPanel newProjectPanel){
        Project project = new Project(name, description);
        project.setBudget(budget);
        projectList.addProject(project);
        homePanel.addProject(name, description);
       // projectsPanel.addProject(name);
    }

    /**
     * Returns the project ID.
     *
     * @return the project ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the project ID.
     *
     * @param id the project ID to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the project name.
     *
     * @return the project name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the project name.
     *
     * @param name the project name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the project description.
     *
     * @return the project description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the project description.
     *
     * @param description the project description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the project budget.
     *
     * @return the project budget
     */
    public double getBudget() {
        return budget;
    }

    /**
     * Sets the project budget.
     *
     * @param budget the project budget to set
     */
    public void setBudget(double budget) {
        this.budget = budget;
    }

    /**
     * Returns the project expenses.
     *
     * @return the project expenses
     */
    public double getExpenses() {
        return expenses;
    }

    /**
     * Adds an expense to the project.
     *
     * @param amount the expense amount to add
     */
    public void addExpense(double amount) {
        this.expenses += amount;
    }
}