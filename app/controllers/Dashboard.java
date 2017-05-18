package controllers;
import models.*;
import utils.*;
import play.Logger;
import play.mvc.Controller;
import java.util.*;


public class Dashboard extends Controller
{

    public static void addAssessment(double weight, double chest, double thigh, double upperArm, double waist, double hips) {

        Member member = Accounts.getLoggedInMember();
        Assessment newAssessment = new Assessment(weight, chest, thigh, upperArm, waist, hips, "");
        member.assessmentList.add(newAssessment);
        member.save();
        Logger.info("Adding Assessment " + weight + ", " + chest + ", " + thigh + ", " + upperArm + ", " + waist +  " and "  + hips);
        redirect("/dashboard");
    }

    public static void deleteAssessment(Long ID, Long assessID) {

        Member member = Member.findById(ID);
        Assessment remAssess = Assessment.findById(assessID);
        member.assessmentList.remove(remAssess);
        member.save();
        remAssess.delete();
        Logger.info("Deleting Assessment");
        redirect("/dashboard");
    }

    public static void addTodo(String comment) {

        Member member = Accounts.getLoggedInMember();
        Todo newTodo = new Todo(comment);
        member.todoList.add(newTodo);
        member.save();
        Logger.info("Adding Todo: " + comment);
        redirect("/dashboard");
    }

    public static void deleteTodo(Long id, Long todoid) {

        Member member = Member.findById(id);
        Todo remTodo = Todo.findById(todoid);
        member.todoList.remove(remTodo);
        member.save();
        remTodo.delete();
        Logger.info("Deleting Comment");
        redirect("/dashboard");
    }

    public static void index() {

        Logger.info("Rendering Dashboard");
        Member member = Accounts.getLoggedInMember();

        List<Assessment> assessmentList = member.assessmentList;

        List<Todo> todoList = member.todoList;

        String weightIndicator = Analytics.isIdealBodyWeight(member);

        render("dashboard.html", member, assessmentList, todoList, weightIndicator);
    }
}
