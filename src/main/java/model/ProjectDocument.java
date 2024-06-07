package model;

/**
 * The ProjectDocument class represents a document associated with a project.
 * It contains the name and filepath of the document.
 * 
 * @autor Bernard Bega, Barno Tashpulatova, Ahmed Hassan, Mahri Yalkapova
 */
public class ProjectDocument {
    /** A field for name. */
    private String name;
    /** A field for file path. */
    private String filepath;
    
    /**
     * Constructs a ProjectDocument with the specified name and filepath.
     * 
     * @param name the name of the document
     * @param filepath the filepath of the document
     */
    public ProjectDocument(String name, String filepath) {
        this.name = name;
        this.filepath = filepath;
    }

    /**
     * Returns file path.
     * @return
     */
    public String getFilepath() {
        return filepath;
    }

    /**
     * Returns name of file.
     * @return
     */
    public String name() {
        return name;
    }


}
