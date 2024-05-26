package controller;

import model.User;
import model.UserRepository;

public class UserController {
    
    private UserRepository userRepository;
    private User currentUser;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User loginUser(String username, String password) {
        User user = userRepository.findUser(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public User createUser(String username, String password, String email) {
        User existingUser = userRepository.findUser(username);
        if (existingUser == null) {
            User newUser = new User(username, password, email);
            userRepository.addUser(newUser);
            userRepository.saveUsers();
            return newUser;
        }
        return null;
    }

    public User getCurrentUser() {
        return currentUser;
    }
}