package model;

/**
 * Information about an application.
 *
 * @author Bernard Bega
 * @version 0.1
 */
public class About {

    /** The version number. */
    private final int myVersion;
    /** The developer team. */
    private final String myDevTeam;
    /** The developers' names. */
    private final String[] myDevs;

    /**
     * Creates an object with information about an application.
     *
     * @param theVersion the application version number.
     * @param theDevTeam the team name of the developers.
     * @param theDevs the developers' names.
     */
    public About(int theVersion, String theDevTeam, String[] theDevs) {
        myVersion = theVersion;
        myDevTeam = theDevTeam;
        myDevs = theDevs.clone();
    }

    /**
     * Gets the version number.
     *
     * @return the version number.
     */
    public int getVersion() {
        return myVersion;
    }

    /**
     * Gets the developers' team name.
     *
     * @return the team name.
     */
    public String getDevTeam() {
        return myDevTeam;
    }

    /**
     * Gets the developers' names.
     *
     * @return the developers' names.
     */
    public String[] getDevs() {
        return myDevs.clone();
    }
}
