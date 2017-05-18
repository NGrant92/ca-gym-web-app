package models;

import play.db.jpa.Model;

import java.util.Date;
import java.text.SimpleDateFormat;

import javax.persistence.Entity;

@Entity
public class Assessment extends Model {
    public double weight;
    public double chest;
    public double thigh;
    public double upperArm;
    public double waist;
    public double hips;
    public String comment;
    public Date date;

    public Assessment(double weight, double chest, double thigh, double upperArm, double waist, double hips) {
        this.weight = weight;
        this.chest = chest;
        this.thigh = thigh;
        this.upperArm = upperArm;
        this.waist = waist;
        this.hips = hips;
        this.comment = "";
        this.date = new Date();
    }

    //-------
    //GETTERS
    //-------

    /**
     * Returns the member's weight in kg
     * @return The member's weight in kg
     */
    public String getDate() {

        String newDate;

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        if(date != null){
            newDate = dateFormat.format(date);
        }
        else{
            newDate = "null";
        }

        return newDate;
    }

    /**
     * Returns the member's weight in kg
     * @return The member's weight in kg
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Returns the member's chest in cm
     * @return The member's chest in cm
     */
    public double getChest() {
        return chest;
    }

    /**
     * Returns the member's thigh legnth in cm
     * @return The member's thigh legnth in cm
     */
    public double getThigh() {
        return thigh;
    }

    /**
     * Returns the member's upper arm legnth in cm
     * @return The member's upper arm legnth in cm
     */
    public double getUpperArm() {

        return upperArm;
    }

    /**
     * Returns the member's waist measurements in cm
     * @return The member's waist measurements in cm
     */
    public double getWaist() {
        return waist;
    }

    /**
     * Returns the member's hip measurements in cm
     * @return The member's hip measurements in cm
     */
    public double getHips() {
        return hips;
    }

    /**
     * Returns the member's hip measurements in cm
     * @return The member's hip measurements in cm
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets a trainer's comment to a member's assessment
     *
     */
    public void setComment(String comment) {
        this.comment = comment;
    }
}