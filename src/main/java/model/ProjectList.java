package model;

import java.util.*;

public class ProjectList {
    private List<Project> projects;

    public ProjectList() {
        projects = new ArrayList<>();
    }

    public void addProject(Project project) {
        projects.add(project);
    }

    public List<Project> getProjects () {
        return projects;
    }


}
