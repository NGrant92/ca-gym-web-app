package controllers;
import models.*;
import utils.*;
import play.Logger;
import play.mvc.Controller;
import java.util.*;


public class Profile extends Controller
{

  public static void index()
  {
    Logger.info("Rendering Dashboard");
    Member member = Accounts.getLoggedInMember();
    List<Assessment> assessmentList = member.assessmentList;
    render("profile.html", member, assessmentList);
  }

  public static void setFirstName(String firstName){
      Member member = Accounts.getLoggedInMember();
      member.setFirstName(firstName);
      member.save();
      Logger.info("Updating First Name: " + firstName);
      redirect("/profile");
  }

  public static void setAddress(String address)
  {
    Member member = Accounts.getLoggedInMember();
    member.setAddress(address);
    member.save();
    Logger.info("Adding address: " + address);
    redirect("/profile");
  }

  public static void setGender(String gender)
  {
    Member member = Accounts.getLoggedInMember();
    member.setGender(gender);
    member.save();
    Logger.info("Adding gender: " + gender);
    redirect("/profile");
  }

  public static void setHeight(double height)
  {
    Member member = Accounts.getLoggedInMember();
    member.setHeight(height);
    member.save();
    Logger.info("Adding height: " + height);
    redirect("/profile");
  }

  public static void setWeight(double weight)
  {
    Member member = Accounts.getLoggedInMember();
    member.setWeight(weight);
    member.save();
    Logger.info("Adding weight: " + weight);
    redirect("/profile");
  }
}
