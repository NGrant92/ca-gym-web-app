package models;

import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Entity
public class Member extends Model
{
    public String firstname;
    public String lastname;
    public String email;
    public String password;
    public String address;
    public String gender;
    public double height;
    public double weight;

    @OneToMany(cascade = CascadeType.ALL)
    public HashMap<Date, Assessment> assessmentList = new HashMap<>();

    public Member(String firstname, String lastname, String email, String password, String address, String gender, double height, double weight)
    {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.address = address;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
    }

    public static Member findByEmail(String email) {
        return find("email", email).first();
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public void setAddress(String address){
        this.address = address;
    }

    public void setGender(String address){
        this.gender = gender;
    }

    public void setHeight(double height){
        this.height = height;
    }

    public void setWeight(double weight){
        this.weight = weight;
    }
}