package model;

import java.util.ArrayList;
import java.util.List;
/**
 * This class created list of projects
 * @author Bernard Bega, Barno Tashpulatova, Ahmed Hassan, Mahri Yalkapova
 */

public class ProjectList {
    /** A field for project list. */
    private List<Project> projects;

    /**
     * Constructs project list class.
     */
    public ProjectList() {
        projects = new ArrayList<>();
    }

    /**
     * Adds projects to the list.
     * @param project
     */
    public void addProject(Project project) {
        projects.add(project);
    }

    /** 
     * Returns project list.
     */
    public List<Project> getProjects () {
        return projects;
    }


}
