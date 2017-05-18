package models;
import utils.*;

import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.SortedSet;
import java.util.*;
import java.lang.Math;

@Entity
public class Member extends Model {

    public String firstname;
    public String lastname;
    public String email;
    public String password;
    public String address;
    public String gender;
    public double height;
    public double weight;

    @OneToMany(cascade = CascadeType.ALL)
    public List<Assessment> assessmentList = new ArrayList<Assessment>();
    @OneToMany(cascade = CascadeType.ALL)
    public List<Todo> todoList = new ArrayList<Todo>();


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

    public double getBMI(){

        return Analytics.calculateBMI(height, latestWeight());
    }

    public String getBMICategory(){
        return Analytics.determineBMICategory(getBMI());
    }

    public String idealBodyWeight(){

        double idealWeight = Analytics.isIdealBodyWeight(height, latestWeight(), gender);

        if(idealWeight >= (latestWeight() - 2) && idealWeight <= (latestWeight() + 2)){
            return "green";
        }
        else if(idealWeight >= (latestWeight() - 5) && idealWeight <= (latestWeight() + 5)){
            return "orange";
        }
        else{
            return "red";
        }
    }

    public double latestWeight(){
        double lastWeight;
        if(assessmentList.size() > 0){
            return assessmentList.get(assessmentList.size() - 1).weight;
        }
        else{
            return weight;
        }
    }

    public String getTrend(long assessmentid){

        List<Assessment> memberAssessments = new ArrayList<Assessment>(assessmentList);

        Assessment currAssessment = memberAssessments.get(memberAssessments.indexOf(Assessment.findById(assessmentid)));

        double idealWeight = Analytics.isIdealBodyWeight(height, latestWeight(), gender);
        double currWeight = currAssessment.getWeight();
        double prevWeight;

        double currWeightDiff;
        double prevWeightDiff;

        if(assessmentid == 1){
            prevWeight = weight;
        }
        else{
            prevWeight = memberAssessments.get(memberAssessments.indexOf(currAssessment) - 1).getWeight();
        }

        currWeightDiff = Math.abs(currWeight - idealWeight);
        prevWeightDiff = Math.abs(prevWeight - idealWeight);

        if(currWeightDiff < prevWeightDiff){
            return "smile";
        }
        else if(prevWeightDiff < currWeightDiff){
            return "frown";
        }
        else{
            return "meh";
        }
    }

    public Assessment latestAssessment(){
        if(assessmentList.size() > 1){
            return assessmentList.get(assessmentList.size() - 1);
        }
        else
            return assessmentList.get(0);
    }

    /**
     * Returns the member's height in meters
     * @return The member's height in meters
     */
    public double getHeight() {
        return height;
    }

    /**
     * Returns the member's weight in kg
     * @return The member's weight in kg
     */
    public double getWeight() {
        return weight;
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

    public void setHeight(double height){
        this.height = height;
    }

    public void setWeight(double weight){
        this.weight = weight;
    }


}