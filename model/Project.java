package model;
/**
 * Project class contains project details. 
 * This class encapsulates project details.
 * @author Bega Bernard
 * @author Mahri Yalkapova
 * @author Ahmed Hassan 
 * @author Barno Tashpulatova
 * @version 1.1
 */
public class Project {
    /**
     * A field for name.
     */
    private String name;
    /**
     * A field for description.
     */
    private String description;
    /**
     * Constructs Project class with parameters.
     * @param name
     * @param description
     */
    public Project(String name, String description) {
        this.name = name;
        this.description = description;
    }
    /**
     * Returns name of project.
     * @return
     */
    public String getName() {
        return name;
    }
    /**
     * Sets name of project.
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Returns the description of a project.
     * @return
     */

    public String getDescription() {
        return description;
    }

    /**
     * Set the description of a project.
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
