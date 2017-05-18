package controllers;
import models.*;
import utils.*;
import play.Logger;
import play.mvc.Controller;
import java.util.*;


public class TrainerAssess extends Controller
{

    public static void deleteAssessment(Long ID, Long assessID) {

        Member member = Member.findById(ID);
        Assessment remAssess = Assessment.findById(assessID);
        member.assessmentList.remove(remAssess);
        member.save();
        remAssess.delete();
        Logger.info("Deleting Assessment");
        redirect("/trainerassess");
    }

    /**
    public static void setComment(Long ID, Long assessID, String comment) {

        Member member = Member.findById(ID);
        Assessment assessment = Assessment.findById(assessID);
        member.assessmentList.findById(assessID).setComment(comment);
        member.save();
        Logger.info("Adding Comment to Assessment: " + comment);
        redirect("/trainerassess");
    }
     */

    public static void index(Long memberid) {

        Logger.info("Rendering Dashboard");
        Trainer trainer = Accounts.getLoggedInTrainer();
        Member member = Member.findById(memberid);

        List<Assessment> assessmentList = member.assessmentList;

        String weightIndicator = Analytics.isIdealBodyWeight(member);

        render("trainerassess.html", member, assessmentList, weightIndicator);
    }
}
