package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private static final String USER_FILE = "/Users/ahmed/360project/Javavengers-1/Javavengers/Users.txt";
    private List<User> users;

    public UserRepository() {
        this.users = loadUsers();
    }

    private List<User> loadUsers() {
        List<User> userList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                if (userData.length == 4) {
                    String username = userData[0];
                    String password = userData[1];
                    String email = userData[2];
                    String firstName = userData[3];
                    User user = new User(username, password, email, firstName);
                    user.setEmail(email);
                    user.setFirstName(firstName);
                    userList.add(user);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading users: " + e.getMessage());
        }
        return userList;
    }

    public void saveUsers() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(USER_FILE))) {
            for (User user : users) {
                writer.println(user.getUsername() + "," + user.getPassword() + "," + user.getEmail() + "," + user.getFirstName());
            }
        } catch (IOException e) {
            System.out.println("Error saving users: " + e.getMessage());
        }
    }

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