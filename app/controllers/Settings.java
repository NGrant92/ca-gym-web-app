package controllers;
import models.*;
import utils.*;
import play.Logger;
import play.mvc.Controller;
import java.util.*;


public class Settings extends Controller
{

    public static void index() {
        Logger.info("Rendering Profile");
        Member member = Accounts.getLoggedInMember();
        List<Assessment> assessmentList = member.assessmentList;
        render("settings.html", member, assessmentList);
    }

    public static void setName(String firstName, String lastName){
        //Member member = Member.findById(ID);
        Member member = Accounts.getLoggedInMember();
        member.setFirstName(firstName);
        member.setLastName(lastName);
        member.save();
        Logger.info("Updating First Name: " + firstName);
        redirect("/settings");
    }

    public static void setAddress(String address) {
        Member member = Accounts.getLoggedInMember();
        member.setAddress(address);
        member.save();
        Logger.info("Adding address: " + address);
        redirect("/settings");
    }

    public static void setGender(String gender) {
        Member member = Accounts.getLoggedInMember();
        member.setGender(gender);
        member.save();
        Logger.info("Adding gender: " + gender);
        redirect("/settings");
    }

    public static void setHeight(double height) {
        Member member = Accounts.getLoggedInMember();
        member.setHeight(height);
        member.save();
        Logger.info("Adding height: " + height);
        redirect("/settings");
    }

    public static void setWeight(double weight) {
        Member member = Accounts.getLoggedInMember();
        member.setWeight(weight);
        member.save();
        Logger.info("Adding weight: " + weight);
        redirect("/settings");
    }
}
