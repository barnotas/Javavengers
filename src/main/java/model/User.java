package model;

import java.awt.List;
import java.io.*;
import java.util.ArrayList;

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
        this.email = email;
        this.firstName = firstName;
        this.projectList = new ProjectList();

    }

    public ProjectList getProjectList() {
        return projectList;
    }

    public void addProject(Project project) {
        projectList.addProject(project);
    }
    
    public void removeProject(Project project) {
        projectList.getProjects().remove(project);
    }



    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
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