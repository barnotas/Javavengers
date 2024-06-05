package model;
/**
 * This class implements data encapsulation for Project document.
 * @author Bernard Bega, Barno Tashpulatova, Ahmed Hassan, Mahri Yalkapova
 */
public class ProjectDocument {
    /** A field for name. */
    private String name;
    /** A field for file path. */
    private String filepath;
    /**
     * Constructs project document class.
     * @param name
     * @param filepath
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
     * Returns name.
     * @return
     */
    public String name() {
        return name;
    }


}
