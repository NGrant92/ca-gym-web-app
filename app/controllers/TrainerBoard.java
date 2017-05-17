package controllers;
import models.*;
import play.Logger;
import play.mvc.Controller;
import java.util.*;

public class TrainerBoard extends Controller
{
    public static void index() {
        Logger.info("Rendering Admin");

        Trainer trainer = Accounts.getLoggedInTrainer();

        List<Member> membersList = trainer.membersList;

        render("trainerboard.html", trainer, membersList);
    }
}
