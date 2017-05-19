package controllers;

import models.*;
import play.Logger;
import play.mvc.Controller;

/**
 * This class is to verify the email and password when a member or trainer logs in
 * It is used to register members
 * It allows the user to be logged out
 *
 * @author Niall Grant
 * @version 2017.05.19
 */
public class Accounts extends Controller {
    public static void signup() {
        render("signup.html");
    }

    /**
     * A simple method that clears the session info
     * and redirects the user to the homepage
     */
    public static void logout() {
        session.clear();
        redirect ("/");
    }

    /**
     * When called it renders the login page
     * This is used if the user failed to log in
     */
    public static void login() {
        render("login.html");
    }

    /**
     * Creates a new member and adds a blank assessment
     *
     * @param firstname New member's first name
     * @param lastname New member's last name
     * @param email New member's email
     * @param password New member's password
     * @param address New member's address
     * @param gender New member's gender
     * @param height New member's height
     * @param weight New member's weight
     */
    public static void register(String firstname, String lastname, String email, String password,  String address, String gender, double height, double weight) {
        //prints to terminal a string
        Logger.info("Registering new user " + email);
        //creates a new assessment object
        Assessment assessment = new Assessment(weight, 0, 0, 0, 0, 0);
        //Creats a new member object
        Member member = new Member(firstname, lastname, email, password, address, gender, height, weight);
        //Adds assessment to new member's assessment list
        member.assessmentList.add(assessment);
        //saves member object
        member.save();
        //redirects to home page
        redirect("/");
    }

    /**
     * Used to authenticate a user when they attempt to log in
     *
     * @param email What the user entered into the email section of the login page
     * @param password What the user entered into the password section of the login page
     */
    public static void authenticate(String email, String password) {
        //prints to terminal a string
        Logger.info("Attempting to authenticate with " + email + ":" + password);

        //searches for member with matching email address
        Member member = Member.findByEmail(email);
        //searches for trainer with matching email address
        Trainer trainer = Trainer.findByEmail(email);
        //checks if member is null and if a member is found checks if their passwords match
        if ((member != null) && (member.checkPassword(password) == true)) {
            //prints to terminal a string
            Logger.info("Authentication successful");
            //adds member to session
            session.put("logged_in_Memberid", member.id);
            //goes to members dashboard
            redirect("/dashboard");
        }
        //checks if trainer is null and if a trainer is found checks if their passwords match
        else if((trainer != null) && (trainer.checkPassword(password) == true)){
            //prints to terminal a string
            Logger.info("Authentication successful");
            //adds member to session
            session.put("logged_in_Trainerid", trainer.id);
            //goes to members dashboard
            redirect("/trainerboard");
        }
        else {
            //prints to terminal a string
            Logger.info("Authentication failed");
            //returns to log in screen
            redirect("/login");
        }
    }

    /**
     * A method that returns a member object from the session
     * that matches the member id of who called the method
     * @return Member object
     */
    public static Member getLoggedInMember()
    {
        //a null object to be filled
        Member member = null;
        //checks if member id matches a member from the session
        if (session.contains("logged_in_Memberid")) {
            //gets the member id from the session
            String memberId = session.get("logged_in_Memberid");
            //searches for member using the above id
            member = Member.findById(Long.parseLong(memberId));
        }
        //returns to login screen
        else {
            login();
        }
        return member;
    }

    /**
     * A method that returns a trainer object from the session
     * that matches the trainer id of who called the method
     * @return trainer object
     */
    public static Trainer getLoggedInTrainer(){
        //a null object to be filled
        Trainer trainer = null;
        //checks if trainer id matches a member from the session
        if (session.contains("logged_in_Trainerid")) {
            //gets the trainer id from the session
            String trainerId = session.get("logged_in_Trainerid");
            //searches for trainer using the above id
            trainer = Trainer.findById(Long.parseLong(trainerId));
        }
        //returns to login screen
        else {
            login();
        }
        return trainer;
    }
}