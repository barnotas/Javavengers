package model;
/**
 * User class contains user data.
 * This class encapsulates user data.
 * @author Bega Bernard
 * @author Mahri Yalkapova
 * @author Ahmed Hassan 
 * @author Barno Tashpulatova
 * @version 1.1
 */
public class User {
    /**
     * A field for first name.
     */
    private String firstName;
    /**
     * A field for email.
     */
    private String email;
    /**
     * A field for loggedIn.
     */
    private boolean loggedIn;

    /**
     * Constructs user class.
     */
    public User() {
        // Initialize the User object with default values
        this.firstName = "Guest";
        this.email = "";
    }

    /**
     * Constructs class with parameters.
     * @param firstName
     * @param email
     */
    public User(String firstName, String email) {
        this.firstName = firstName;
        this.email = email;
    }
    /**
     * Returns first name of user.
     * @return
     */
    public String getFirstName() {
        return firstName;
    }
    /**
     * Sets first name of user.
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns user email.
     * @return
     */
    public String getEmail() {
        return email;
    }
    /**
     * Sets user email.
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

}