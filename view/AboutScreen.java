package view;

import model.About;

/**
 * A screen displaying information about the application.
 *
 * @version 0.1
 */
public class AboutScreen {

    /** Information about the application. */
    private final About myAbout;

    /**
     * Creates the about page.
     *
     * @param theVersion the application version number.
     * @param theDevTeam the team name of the developers.
     * @param theDevs the developers' names.
     */
    public AboutScreen(int theVersion, String theDevTeam, String[] theDevs) {
        myAbout = null;
    }

    /**
     * Opens the about page.
     */
    public void open() {

    }
}
