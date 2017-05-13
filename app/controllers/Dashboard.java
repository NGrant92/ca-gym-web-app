package controllers;
import models.*;
import play.Logger;
import play.mvc.Controller;
import java.util.*;

public class Dashboard extends Controller
{

  public static void addAssessment(double weight, double chest, double thigh, double upperArm, double waist, double hips)
  {
    Member member = Accounts.getLoggedInMember();
    Assessment newAssessment = new Assessment(weight, chest, thigh, upperArm, waist, hips);
    member.assessmentList.add(newAssessment);
    member.save();
    Logger.info("Adding Assessment " + weight + ", " + chest + ", " + thigh + ", " + upperArm + ", " + waist + " and " + hips);
    redirect("/dashboard");
  }

  public static void deleteAssessment(Long ID, Long assessID)
  {
    Member member = Member.findById(ID);
    Assessment remAssess = Assessment.findById(assessID);
    member.assessmentList.remove(assessID);
    member.save();
    remAssess.delete();
    Logger.info("Deleting Assessment");
    redirect("/dashboard");
  }

  public static void index()
  {
    Logger.info("Rendering Dashboard");
    Member member = Accounts.getLoggedInMember();
    List<Assessment> assessmentList = member.assessmentList;
    render("dashboard.html", member, assessmentList);
  }

  public static void setAddress(String address)
  {
    Member member = Accounts.getLoggedInMember();
    member.setAddress(address);
    member.save();
    Logger.info("Adding address: " + address);
    redirect("/dashboard");
  }
  public static void setGender(String gender)
  {
    Member member = Accounts.getLoggedInMember();
    member.setGender(gender);
    member.save();
    Logger.info("Adding gender: " + gender);
    redirect("/dashboard");
  }
  public static void setHeight(double height)
  {
    Member member = Accounts.getLoggedInMember();
    member.setHeight(height);
    member.save();
    Logger.info("Adding height: " + height);
    redirect("/dashboard");
  }

  public static void setWeight(double weight)
  {
    Member member = Accounts.getLoggedInMember();
    member.setWeight(weight);
    member.save();
    Logger.info("Adding weight: " + weight);
    redirect("/dashboard");
  }


}
