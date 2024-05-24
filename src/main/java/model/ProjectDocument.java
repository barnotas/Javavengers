package model;

public class ProjectDocument {
    private String name;
    private String filepath;

    public ProjectDocument(String name, String filepath) {
        this.name = name;
        this.filepath = filepath;
    }

    public String getFilepath() {
        return filepath;
    }

    public String name() {
        return name;
    }


}
