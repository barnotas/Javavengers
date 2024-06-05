package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;

/**
 * Represents a user with a first name and email address.
 */
public class User implements Serializable {
    private String email;
    private String password;
    private String username;
    private String firstName;
    private ProjectList projectList;

    /**
     * Constructs a new User with the specified first name and email address.
     *
     * @param username the username of the user
     * @param password     the password of the user
     */
    public User(String username, String password, String email, String firstName) {
        this.username = username;
        this.password = password;
    }
    

    /**
     * Returns username.
     * @return
     */
    public String getUsername() {
        return username;
    }
    /**
     * Sets username.
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
     * Returns password.
     * @return
     */
    public String getPassword() {
        return password;
    }
    /**
     * Sets password.
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the email address of the user.
     *
     * @return the email address of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the user.
     *
     * @param email the email address to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
    * Exports the user settings to a specified file.
    *
    * @param filePath the path of the file where settings will be exported
    */
    public void exportSettings(String filePath) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            writer.println(username);
            writer.println(email);
            writer.println(password);
            System.out.println("User settings exported successfully.");
        } catch (IOException e) {
            System.out.println("Error exporting user settings: " + e.getMessage());
        }
    }
    /**
    * Imports the user settings from a specified file.
    *
    * @param filePath the path of the file from which settings will be imported
    */
    public void importSettings(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String username = reader.readLine();
            String email = reader.readLine();
            String password = reader.readLine();
    
            if (username != null && email != null && password != null) {
                this.username = username;
                this.email = email;
                this.password = password;
                System.out.println("User settings imported successfully.");
            } else {
                System.out.println("Invalid user settings file format.");
            }
        } catch (IOException e) {
            System.out.println("Error importing user settings: " + e.getMessage());
        }
    }


    /**
     * Exports the user object to a file.
     */
    public void export() {
        File outputFile = new File("Javavengers/profile.ser");
        try (FileOutputStream fout = new FileOutputStream(outputFile, true);
             ObjectOutputStream oos = new ObjectOutputStream(fout)) {
            oos.writeObject(this);
            System.out.println("Export successful!");
        } catch (IOException e) {
            System.err.println("Error during serialization: " + e.getMessage());
        }
    }
    /**
    * Authenticates the user based on the provided username and password.
    *
    * @param username the username to authenticate
    * @param password the password to authenticate
    * @return true if the username and password match the user's credentials, false otherwise
    */
    public boolean authenticate(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }



    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getFirstName(){
        return firstName;
    }
}