package model;

import java.io.*;
/**
 * A User with a name and email.
 *
 * @author Bernard Bega
 * @version 0.1
 */
public class User implements Serializable{

    /** This user's name. */
    private String myName;
    /** This user's email. */
    private String myEmail;

    /**
     * Creates a new User.
     *
     * @param theName is the name.
     * @param theEmail is the email.
     */
    public User(String theName, String theEmail) {
        myName = theName;
        myEmail = theEmail;
    }

    /**
     * Gets this user's name.
     *
     * @return the user's name.
     */
    public String getName() {
        return myName;
    }

    /**
     * Gets this user's email.
     *
     * @return the user's email.
     */
    public String getEmail() {
        return myEmail;
    }

    /**
     * Sets this user's name to the given name.
     *
     * @param theName the given name.
     */
    public void setName(String theName) {
        this.myName = theName;
    }

    /**
     * Sets this user's email to the given email.
     *
     * @param theEmail the given email.
     */
    public void setEmail(String theEmail) {
        this.myEmail = theEmail;
    }

    public void export() {
    try (FileOutputStream fout = new FileOutputStream("Javavengers/profile.ser");
         ObjectOutputStream oos = new ObjectOutputStream(fout)) {
        oos.writeObject(this);
    } catch (Exception ex) {
        ex.printStackTrace();
  }
}

}
