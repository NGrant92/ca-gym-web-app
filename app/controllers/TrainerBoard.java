package controllers;
import models.*;
import play.Logger;
import play.mvc.Controller;
import java.util.*;

public class TrainerBoard extends Controller
{

    public static void deleteMember(long memberid){

        Member member = Member.findById(memberid);
        member.delete();
        Logger.info("Deleting Member");
        redirect("/trainerboard");
    }

    public static void index() {

        Logger.info("Rendering Trainer Dashboard");

        Trainer trainer = Accounts.getLoggedInTrainer();

        List<Member> membersList = Member.findAll();

        render("trainerboard.html", trainer, membersList);
    }
}
