package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
/**
 * This class represents a repository for managing user data. It provides methods
 * to load users from a file, save users to a file, find users by username, and
 * add new users to the repository.
 * 
 * @author Bernard Bega, Barno Tashpulatova, Ahmed Hassan, Mahri Yalkapova
 */

public class UserRepository {
    /** A constant field for user file. */
    private static final String USER_FILE = "/Users/ahmed/360project/Javavengers-1/Javavengers/Users.txt";
    /** A field for user list. */
    private List<User> users;
    /**
     * Constructs a new UserRepository and loads the users from the file.
     */
    public UserRepository() {
        this.users = loadUsers();
    }
    /**
     * Loads the users from the file.
     *
     * @return the list of users
     */
    private List<User> loadUsers() {
        List<User> userList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                if (userData.length == 4) {
                    String username = userData[0];
                    String password = userData[1];
                    String firstName = userData[2];
                    String email = userData[3];
                    User user = new User(username, password);
                    user.setFirstName(firstName);
                    user.setEmail(email);
                    userList.add(user);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading users: " + e.getMessage());
        }
        return userList;
    }
    /**
     * Saves the users to the file.
     */
    public void saveUsers() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(USER_FILE))) {
            for (User user : users) {
                writer.println(user.getUsername() + "," + user.getPassword() + "," + user.getFirstName() + "," + user.getEmail());
            }
        } catch (IOException e) {
            System.out.println("Error saving users: " + e.getMessage());
        }
    }
    /**
     * Finds a user by username.
     *
     * @param username the username of the user to find
     * @return the user if found, otherwise null
     */
    public User findUser(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public void addUser(User user) {
        users.add(user);
    }
}