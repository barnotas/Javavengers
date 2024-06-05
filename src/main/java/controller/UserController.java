package controller;

import model.User;
import model.UserRepository;
/**
 * This class implements the controller for user-related operations, 
 * such as logging in and creating new users. It interacts with the 
 * UserRepository to manage user data.
 *  @author Bernard Bega, Barno Tashpulatova, Ahmed Hassan, Mahri Yalkapova
 */

public class UserController {
    /** A field for user repository. */
    private UserRepository userRepository;
    /** A field for user. */
    private User currentUser;

    /**
     * Constructs a new UserController.
     *
     * @param userRepository the repository for managing user data
     */
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    /**
     * Logs in a user by verifying the provided username and password.
     *
     * @param username the username of the user
     * @param password the password of the user
     * @return the logged-in user if the credentials are correct; otherwise, null
     */
    public User loginUser(String username, String password) {
        User user = userRepository.findUser(username);
        if (user != null && user.getPassword().equals(password)) {
            currentUser = user;
            return user;
        }
        return null;
    }

    /**
     * Creates a new user with the specified username and password.
     *
     * @param username the username for the new user
     * @param password the password for the new user
     * @return the created user if the username is not already taken; otherwise, null
     */
    public User createUser(String username, String password, String email, String firstName) {
        User existingUser = userRepository.findUser(username);
        if (existingUser == null) {
            User newUser = new User(username, password, email, firstName);
            userRepository.addUser(newUser);
            userRepository.saveUsers();
            return newUser;
        }
        return null;
    }
     /**
     * Returns the currently logged-in user.
     *
     * @return the current user
     */
    public User getCurrentUser() {
        return currentUser;
    }
}