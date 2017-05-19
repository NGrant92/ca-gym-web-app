package controllers;
import models.*;
import play.Logger;
import play.mvc.Controller;
import java.util.*;

/**
 * This class is to render the dashboard for the trainer object once logged in
 * It is used to view members
 * It allows the user to delete members
 *
 * @author Niall Grant
 * @version 2017.05.19
 */
public class TrainerBoard extends Controller
{

    /**
     * Method to deleted a member from the database
     * @param memberid The ID of the member to be deleted
     */
    public static void deleteMember(long memberid){

        //finds the member object using their id
        Member member = Member.findById(memberid);
        //deletes member
        member.delete();
        //prints string to terminal
        Logger.info("Deleting Member");
        //refreshes dashboard
        redirect("/trainerboard");
    }

    /**
     * Renders the dashboard page and aquires the needed variables
     */
    public static void index() {

        //prints string to terminal
        Logger.info("Rendering Trainer Dashboard");
        //gets trainer object using an Accounts method
        Trainer trainer = Accounts.getLoggedInTrainer();
        //gets all the members currently in the database and puts them into an array
        List<Member> membersList = Member.findAll();
        //renders trainer dashboard
        render("trainerboard.html", trainer, membersList);
    }
}
