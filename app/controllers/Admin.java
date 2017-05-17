package controllers;
import models.*;
import play.Logger;
import play.mvc.Controller;
import java.util.*;

public class Admin extends Controller
{
    public static void index() {
        Logger.info("Rendering Dashboard");

        Trainer trainer = Accounts.getLoggedInTrainer();

        List<Member> membersList = trainer.membersList;

        render("admin.html", trainer, membersList);
    }
}
