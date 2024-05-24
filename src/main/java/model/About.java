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
    private static final double VERSION = 0.1;

    /**
     * The development team of the application.
     */
    private static final String DEV_TEAM = "Javavengers";

    /**
     * The names of the developers.
     */
    private static final String[] DEVS = {
            "Ahmed Hassan",
            "Barno Tashpulatova",
            "Bernard Bega",
            "Mahri Yalkapova"
    };

    /**
     * Returns the version of the application.
     *
     * @return the version of the application
     */
    public double getVersion() {
        return VERSION;
    }

    /**
     * Returns the development team of the application.
     *
     * @return the development team of the application
     */
    public String getDevTeam() {
        return DEV_TEAM;
    }

    /**
     * Returns the names of the developers.
     *
     * @return an array containing the names of the developers
     */
    public String[] getDevs() {
        return DEVS;
    }
}