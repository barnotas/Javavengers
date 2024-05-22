package model;

import java.io.*;
import java.io.Serializable;

public class User implements Serializable{
    private String firstName;
    private String email;
    private boolean loggedIn;

    public User() {
        // Initialize the User object with default values
        this.firstName = "Guest";
        this.email = "";
    }

    public User(String firstName, String email) {
        this.firstName = firstName;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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

}       
