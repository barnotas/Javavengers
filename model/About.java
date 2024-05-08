package model;

/**
 * Information about an application.
 *
 * @author Bernard Bega, Barno Tashpulatova, Ahmed Hassan, Mahri Yalkapova
 * @version 0.1
 */
public class About {
    /** The version number. */
    private static final double VERSION = 0.1;
    
    /** The developer team. */
    private final String myDevTeam;
    
    /** The developers' names. */
    private final String[] myDevs;
    
    /**
     * Creates an object with information about an application.
     *
     * @param theDevTeam the team name of the developers.
     * @param theDevs the developers' names.
     */
    public About(String theDevTeam, String[] theDevs) {
        myDevTeam = theDevTeam;
        myDevs = theDevs.clone();
    }
    
    /**
     * Gets the version number.
     *
     * @return the version number.
     */
    public static double getVersion() {
        return VERSION;
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