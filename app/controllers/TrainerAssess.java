package controllers;
import models.*;
import utils.*;
import play.Logger;
import play.mvc.Controller;
import java.util.*;

/**
 * This class is to render the page for the trainer to view
 * It is used to view member assessments
 * It allows the user to add comments to the member's assessment
 *
 * @author Niall Grant
 * @version 2017.05.19
 */
public class TrainerAssess extends Controller
{

    /**
     * Adds comment to member's assessment
     * @param memberid The ID of the member
     * @param assessid The ID of the Assessment
     * @param comment The comment to be added
     */
    public static void setComment(Long memberid, Long assessid, String comment) {

        //gets trainer object so it can refresh the page correctly
        Trainer trainer = Accounts.getLoggedInTrainer();
        //gets member to have assessment
        Member member = Member.findById(memberid);
        //gets list of assessments for member to be used to re render page
        List<Assessment> assessmentList = member.assessmentList;
        //inverts the assessment list so the latest assessment is first
        Collections.reverse(assessmentList);

        //assessment to be commented
        Assessment commAssessment = Assessment.findById(assessid);
        //sets comment
        commAssessment.setComment(comment);
        //saves the assessment
        commAssessment.save();
        //saves member object
        member.save();

        //prints string to terminal
        Logger.info("Adding Comment to Assessment: " + comment);
        //renders the page with the updated assessments
        render("trainerassess.html", trainer, member, assessmentList);
    }

    /**
     * Renders the page and aquires the needed variables
     * @param memberid ID of the member who to print assessments
     */
    public static void index(Long memberid) {

        //prints string to terminal
        Logger.info("Rendering Dashboard");
        //gets logged in trainer
        Trainer trainer = Accounts.getLoggedInTrainer();
        //gets member object that is being assessed
        Member member = Member.findById(memberid);

        //member's assessment list
        List<Assessment> assessmentList = member.assessmentList;
        //inverts the assessment list so the latest assessment is first
        Collections.reverse(assessmentList);

        //renders page
        render("trainerassess.html", trainer, member, assessmentList);
    }
}
