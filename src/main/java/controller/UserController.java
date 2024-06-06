package controller;

import model.User;
import model.UserRepository;
/**
 * The UserController class is responsible for managing user-related operations
 * such as logging in, creating a new user, and retrieving the current logged-in user.
 * It interacts with the UserRepository to perform these operations.
 * 
 * @autor Bernard Bega, Barno Tashpulatova, Ahmed Hassan, Mahri Yalkapova
 */
public class UserController {
    /** A field for user repository. */
    private UserRepository userRepository;
    /** A field for current user. */
    private User currentUser;

    /**
     * Constructs a UserController with the specified UserRepository.
     * 
     * @param userRepository the repository for managing user data
     */
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    /**
     * Logs in a user with the specified username and password.
     * If the username and password are correct, the user is set as the current user.
     * 
     * @param username the username of the user
     * @param password the password of the user
     * @return the logged-in user if the credentials are correct, null otherwise
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
     * Creates a new user with the specified username, password, email, and first name.
     * If a user with the same username does not already exist, the new user is added to the repository.
     * 
     * @param username the username of the new user
     * @param password the password of the new user
     * @param email the email of the new user
     * @param firstName the first name of the new user
     * @return the newly created user if the username is unique, null otherwise
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
     * Returns the current logged-in user.
     * 
     * @return the current user
     */
    public User getCurrentUser() {
        return currentUser;
    }
}