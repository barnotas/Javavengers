package model;

/**
 * Represents information about an application.
 *
 * @author Bernard Bega, Barno Tashpulatova, Ahmed Hassan, Mahri Yalkapova
 * @version 0.1
 */
public class About {
    /**
     * The version of the application.
     */
    private double version;

    /**
     * The development team of the application.
     */
    private String devTeam;

    /**
     * The names of the developers.
     */
    private String[] devs;

    /**
     * Constructs an About object with default values.
     */
    public About() {
        // Initialize the About object with default values
        this.version = 0.1;
        this.devTeam = "Javavengers";
        this.devs = new String[]{"Ahmed Hassan", "Barno Tashpulatova", "Bernard Bega", "Mahri Yalkapova"};
    }

    /**
     * Returns the version of the application.
     *
     * @return the version of the application
     */
    public double getVersion() {
        return version;
    }

    /**
     * Returns the development team of the application.
     *
     * @return the development team of the application
     */
    public String getDevTeam() {
        return devTeam;
    }

    /**
     * Returns the names of the developers.
     *
     * @return an array containing the names of the developers
     */
    public String[] getDevs() {
        return devs;
    }
}