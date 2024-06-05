package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private static final String USER_FOLDER = "user_data";
    private static final String USER_FILE = "Users.txt";
    private List<User> users;

    public UserRepository() {
        createUserFolder();
        this.users = loadUsers();
    }

    private void createUserFolder() {
        Path folderPath = Paths.get(USER_FOLDER);
        if (!Files.exists(folderPath)) {
            try {
                Files.createDirectory(folderPath);
                System.out.println("User folder created: " + folderPath);
            } catch (IOException e) {
                System.out.println("Error creating user folder: " + e.getMessage());
            }
        }
    }

    private List<User> loadUsers() {
        List<User> userList = new ArrayList<>();
        Path userFilePath = Paths.get(USER_FOLDER, USER_FILE);

        if (Files.exists(userFilePath)) {
            try (BufferedReader reader = new BufferedReader(new FileReader(userFilePath.toFile()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] userData = line.split(",");
                    if (userData.length == 4) {
                        String username = userData[0];
                        String password = userData[1];
                        String email = userData[2];
                        String firstName = userData[3];
                        User user = new User(username, password, email, firstName);
                        userList.add(user);
                    }
                }
            } catch (IOException e) {
                System.out.println("Error loading users: " + e.getMessage());
            }
        }
        return userList;
    }

    public void saveUsers() {
        Path userFilePath = Paths.get(USER_FOLDER, USER_FILE);
        try (PrintWriter writer = new PrintWriter(new FileWriter(userFilePath.toFile()))) {
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
        saveUsers();
    }
}