package model;

import java.util.*;
/**
 * This class represents a list of projects. It provides methods to add projects
 * to the list and retrieve the list of projects.
 * 
 * @author Bernard Bega, Barno Tashpulatova, Ahmed Hassan, Mahri Yalkapova
 */
public class ProjectList {
    /** A field for project list. */
    private List<Project> projects;
    /**
     * Constructs a new ProjectList.
     */
    public ProjectList() {
        projects = new ArrayList<>();
    }
    /**
     * Adds project to the list.
     * @param project
     */
    public void addProject(Project project) {
        projects.add(project);
    }
    /**
     * Returns the list of projects.
     * @return
     */
    public List<Project> getProjects () {
        return projects;
    }


}
