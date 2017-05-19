package models;

import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.SortedSet;
import java.util.*;

/**
 * This class is to create and store trainer details
 *
 * @author Niall Grant
 * @version 2017.05.19
 */

@Entity
public class Trainer extends Model {

    public String firstname;
    public String lastname;
    public String email;
    public String password;
    public String address;
    public String gender;

    /**
     * Constructor method for trainer object
     *
     * @param firstname Trainer's name
     * @param lastname Trainer's last name
     * @param email Trainer's email
     * @param password Trainer's password
     * @param address Trainer's address
     * @param gender Trainer's gender
     *
     */
    public Trainer(String firstname, String lastname, String email, String password, String address, String gender) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.address = address;
        this.gender = gender;
    }

    /**
     * Method to search for an entered email and see if it matches a trainer's email
     * @param email Entered email to be searched for
     * @return returns found email
     */
    public static Trainer findByEmail(String email) {
        return find("email", email).first();
    }

    /**
     * Ensure the entered password matches the trainer's password
     * @param password Entered password
     * @return true or false
     */
    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }
}