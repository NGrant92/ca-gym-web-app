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

/**
 * This class is to create and store member details and array lists
 * It has various methods to return various bmi information
 *
 * @author Niall Grant
 * @version 2017.05.19
 */
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


    /**
     * Constructor method for member object
     *
     * @param firstname Member's name
     * @param lastname Member's last name
     * @param email Member's email
     * @param password Member's password
     * @param address Member's address
     * @param gender Member's gender
     * @param height Member's height
     * @param weight Member's weight
     */
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

    /**
     * Method to search for an entered email and see if it matches a member's email
     * @param email Entered email to be searched for
     * @return returns found email
     */
    public static Member findByEmail(String email) {
        return find("email", email).first();
    }

    /**
     * Ensure the entered password matches the member's password
     * @param password Entered password
     * @return true or false
     */
    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    /**
     * Returns member's bmi number
     * @return
     */
    public double getBMI(){

        //calls method from analytics class to calculate bmi value
        return Analytics.calculateBMI(height, latestWeight());
    }

    /**
     * Returns the BMI Category the member is in
     * @return String with the category name
     */
    public String getBMICategory(){
        //calls method from analytics class to calculate bmi category
        return Analytics.determineBMICategory(getBMI());
    }

    /**
     * Returns a string with a colour to be used in the dashboard for ideal weight indicator
     * @return String with a colour in it
     */
    public String idealBodyWeight(){

        //calls method from analytics class to calculate member's ideal weight
        double idealWeight = Analytics.isIdealBodyWeight(height, latestWeight(), gender);

        //if member weight is within a certain range it will return a certain colour
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

    /**
     * This is to stop errors when there are zero or 1 assessments in the member's assessment list
     * @return A number with the person's weight
     */
    public double latestWeight(){
        double lastWeight;
        //if array is populated then it will get the weight from the latest assessment
        if(assessmentList.size() > 0){
            return assessmentList.get(assessmentList.size() - 1).weight;
        }
        //otherwise it will return their starting weight
        else{
            return weight;
        }
    }

    /**
     * A method that gets the difference between their current weight the calculated ideal weight
     * It gets this difference from their current assessment and the assessment immediately preceeding it
     * It then compares those two numbers and will return a String containing a colour depending on wheter
     * they have made improvements or not
     *
     * @param assessmentid Is the ID of the assessment being tested
     * @return String with a colour in it
     */
    public String getTrend(long assessmentid){

        //gathers the assessments that belong to the member
        List<Assessment> memberAssessments = new ArrayList<Assessment>(assessmentList);
        //inverts the order of the list so the latest assessment is first
        Collections.reverse(memberAssessments);

        //matches the assessment in the memberAssessments to the entered ID number
        Assessment currAssessment = memberAssessments.get(memberAssessments.indexOf(Assessment.findById(assessmentid)));

        //calculates ideal weight
        double idealWeight = Analytics.isIdealBodyWeight(height, latestWeight(), gender);
        //gets weight from current assessment
        double currWeight = currAssessment.getWeight();
        //variable to hold the weight from the assessment preceeding the current assessment
        double prevWeight;

        //varliable to hold the difference between ideal weight and current weight
        double currWeightDiff;
        //varliable to hold the difference between ideal weight and previous weight
        double prevWeightDiff;

        //checks wheter to assing prev weight to starting weight or to weight from an assessment
        if(memberAssessments.indexOf(currAssessment) == 0){
            prevWeight = weight;
        }
        else{
            prevWeight = memberAssessments.get(memberAssessments.indexOf(currAssessment) + 1).getWeight();
        }

        //variables are populated with weight diff
        currWeightDiff = Math.abs(currWeight - idealWeight);
        prevWeightDiff = Math.abs(prevWeight - idealWeight);

        //if currentWeightDiff is less than the prevWeightDiff then it means they're closer to their goal
        //which deserves a smiley face
        if(currWeightDiff < prevWeightDiff || currWeight == idealWeight){
            return "green smile";
        }
        else if(prevWeightDiff < currWeightDiff){
            return "red frown";
        }
        else{
            return "blue meh";
        }
    }

    /**
     * Avoids errors when only one assessment in the array
     * @return
     */
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

    /**
     * Sets member's first name
     * @param firstName Member's new name
     */
    public void setFirstName(String firstName) {
        this.firstname = firstName;
    }

    /**
     * Sets member's last name
     * @param lastname Member's new name
     */
    public void setLastName(String lastname) {
        this.lastname = lastname;
    }

    /**
     * Sets member's email
     * @param email Member's new email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets member's password
     * @param password Member's new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sets member's address
     * @param address Member's new address
     */
    public void setAddress(String address){
        this.address = address;
    }

    /**
     * Sets member's gender
     * @param gender Member's new gender
     */
    public void setGender(String gender){
        this.gender = gender;
    }

    /**
     * Sets member's height
     * @param height Member's new height
     */
    public void setHeight(double height){
        this.height = height;
    }

    /**
     * Sets member's starting weight
     * @param weight Member's new starting weight
     */
    public void setWeight(double weight){
        this.weight = weight;
    }


}