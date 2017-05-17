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

    public static void index(Long memberid) {

        Logger.info("Rendering Dashboard");
        Trainer trainer = Accounts.getLoggedInTrainer();
        Member member = Member.findById(memberid);

        List<Assessment> assessmentList = member.assessmentList;

        double bmi = Analytics.calculateBMI(member, member.assessmentList.get(assessmentList.size() - 1));

        String bmiCategory = Analytics.determineBMICategory(bmi);

        String weightIndicator = weightIndicatorColour(bmiCategory);

        render("trainerassess.html", member, assessmentList, bmi, bmiCategory, weightIndicator);
    }

    public static String weightIndicatorColour(String bmiCategory){

        String colour;

        if(bmiCategory.contains("SEVERELY")){
            colour = "red";
        }
        else if(!bmiCategory.equals("NORMAL")){
            colour = "orange";
        }
        else{
            colour = "green";
        }

        return colour;
    }
}
