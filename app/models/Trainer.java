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

@Entity
public class Trainer extends Model {

    public String firstname;
    public String lastname;
    public String email;
    public String password;
    public String address;
    public String gender;
    public double height;
    public double weight;

    @OneToMany(cascade = CascadeType.ALL)
    public List<Member> memberList = new ArrayList<Member>();


    public Member(String firstname, String lastname, String email, String password, String address, String gender, double height, double weight)
    {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.address = address;
        this.gender = gender;
    }

    public static Trainer findByEmail(String email) {
        return find("email", email).first();
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public void setFirstName(String firstName) {
        this.firstname = firstName;
    }

    public void setLastName(String lastname) {
        this.lastname = lastname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public void setGender(String gender){
        this.gender = gender;
    }
}