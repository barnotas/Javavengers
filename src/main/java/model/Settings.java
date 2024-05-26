package model;


/**
 * Represents the settings of the application.
 */
public class Settings {
    private User user;

    /**
     * Constructs a new Settings object with a default User.
     */
    public Settings() {
        // Initialize the Settings object with a default User
        this.user = new User(null, null, null);
    }

    /**
     * Returns the User associated with the settings.
     *
     * @return the User object
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the User associated with the settings.
     *
     * @param user the User object to set
     */
    public void setUser(User user) {
        this.user = user;
    }
}