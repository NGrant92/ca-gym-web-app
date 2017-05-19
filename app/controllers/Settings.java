package controllers;
import models.*;
import utils.*;
import play.Logger;
import play.mvc.Controller;
import java.util.*;

/**
 * This class is to render the settings page to allow the member to update their info
 *
 * @author Niall Grant
 * @version 2017.05.19
 */
public class Settings extends Controller
{

    /**
     * Renders the dashboard page and aquires the needed variables
     */
    public static void index() {
        Logger.info("Rendering Profile");
        Member member = Accounts.getLoggedInMember();
        List<Assessment> assessmentList = member.assessmentList;
        render("settings.html", member, assessmentList);
    }

    /**
     * Sets the member's first name and last name
     * @param firstName New first name to be set
     * @param lastName New last name to be set
     */
    public static void setName(String firstName, String lastName){
        Member member = Accounts.getLoggedInMember();
        member.setFirstName(firstName);
        member.setLastName(lastName);
        member.save();
        Logger.info("Updating First Name: " + firstName);
        redirect("/settings");
    }

    /**
     * Sets the member's email
     * @param email New email to be set
     */
    public static void setEmail(String email){

        Member member = Accounts.getLoggedInMember();
        member.setEmail(email);
        member.save();
        Logger.info("Updating First Name: " + email);
        redirect("/settings");
    }

    /**
     * Sets the member's address
     * @param address New address to be set
     */
    public static void setAddress(String address) {
        Member member = Accounts.getLoggedInMember();
        member.setAddress(address);
        member.save();
        Logger.info("Adding address: " + address);
        redirect("/settings");
    }

    /**
     * Sets the member's gender
     * @param address New gender to be set
     */
    public static void setGender(String gender) {
        Member member = Accounts.getLoggedInMember();
        member.setGender(gender);
        member.save();
        Logger.info("Adding gender: " + gender);
        redirect("/settings");
    }

    /**
     * Sets the member's height
     * @param address New height to be set
     */
    public static void setHeight(double height) {
        Member member = Accounts.getLoggedInMember();
        member.setHeight(height);
        member.save();
        Logger.info("Adding height: " + height);
        redirect("/settings");
    }

    /**
     * Sets the member's weight
     * @param address New weight to be set
     */
    public static void setWeight(double weight) {
        Member member = Accounts.getLoggedInMember();
        member.setWeight(weight);
        member.save();
        Logger.info("Adding weight: " + weight);
        redirect("/settings");
    }
}
