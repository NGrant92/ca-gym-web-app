package controllers;
import models.*;
import utils.*;
import play.Logger;
import play.mvc.Controller;
import java.util.*;


public class TrainerAssess extends Controller
{

    public static void setComment(Long memberid, Long assessid, String comment) {

        Trainer trainer = Accounts.getLoggedInTrainer();
        Member member = Member.findById(memberid);

        List<Assessment> assessmentList = member.assessmentList;

        Assessment commAssessment = Assessment.findById(assessid);
        commAssessment.setComment(comment);
        commAssessment.save();
        member.save();

        Logger.info("Adding Comment to Assessment: " + comment);
        render("trainerassess.html", trainer, member, assessmentList);
    }

    public static void index(Long memberid) {

        Logger.info("Rendering Dashboard");
        Trainer trainer = Accounts.getLoggedInTrainer();
        Member member = Member.findById(memberid);

        List<Assessment> assessmentList = member.assessmentList;

        render("trainerassess.html", trainer, member, assessmentList);
    }
}
