package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

/**
 * Represents information about an application.
 *
 * @author Bernard Bega, Barno Tashpulatova, Ahmed Hassan, Mahri Yalkapova
 * @version 0.1
 */
public class About implements Serializable  {
    /**
     * The version of the application.
     */
    private static final double VERSION = 1.1;

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

    public void exportAbout(String filePath) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, true))) {
            writer.println("About Info below");
            writer.println("Dev Team: " + DEV_TEAM);
            writer.println("Developers: " + String.join(", ", DEVS));
            writer.println("Version: " + VERSION);
        } catch (IOException e) {
            System.out.println("Error exporting about information: " + e.getMessage());
        }
    }
}
