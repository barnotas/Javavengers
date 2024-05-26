package model;
/**
 * Setting class contains a user setting.
 * This class encapsulates a user setting.
 * @author Bega Bernard
 * @author Mahri Yalkapova
 * @author Ahmed Hassan 
 * @author Barno Tashpulatova
 * @version 1.1
 */
public class Settings {
    /**
     * A field for user.
     */
    private User user;

    /**
     * Constructs a setting class.
     */
    public Settings() {
        // Initialize the Settings object with a default User
        this.user = new User();
    }
    /**
     * Returns an user.
     * @return
     */

    public User getUser() {
        return user;
    }

    /**
     * Sets a user.
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
    }
}