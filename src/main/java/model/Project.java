package model;

/**
 * Represents a project with a name and description.
 */
public class Project {
    private String name;
    private String description;

    /**
     * Constructs a new Project with the specified name and description.
     *
     * @param name        the name of the project
     * @param description the description of the project
     */
    public Project(String name, String description) {
        this.name = name;
        this.description = description;
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
    public double setBudget(double budget){
        return budget;
    }
}