package controllers;
import models.*;
import utils.*;
import play.Logger;
import play.mvc.Controller;
import java.util.*;

/**
 * This class is to render the Dashboard page for the member object
 * Allows member to add and delete assessments
 * Allows member to add and delete Notes
 *
 * @author Niall Grant
 * @version 2017.05.19
 */
public class Dashboard extends Controller
{

    /**
     * Adds a new assessment to member's assessment list
     * @param weight member's new measurement
     * @param chest member's new measurement
     * @param thigh member's new measurement
     * @param upperArm member's new measurement
     * @param waist member's new measurement
     * @param hips member's new measurement
     */
    public static void addAssessment(double weight, double chest, double thigh, double upperArm, double waist, double hips) {

        //Gets the member object by calling a method from Accounts class
        Member member = Accounts.getLoggedInMember();
        //creates assessment object
        Assessment newAssessment = new Assessment(weight, chest, thigh, upperArm, waist, hips);
        //adds assessment object to member's list
        member.assessmentList.add(newAssessment);
        //saves the member object
        member.save();
        //prints a string to terminal
        Logger.info("Adding Assessment " + weight + ", " + chest + ", " + thigh + ", " + upperArm + ", " + waist +  " and "  + hips);
        //refreshes dashboard page
        redirect("/dashboard");
    }

    /**
     * Deletes an assessment from the member's assessment array
     * @param id The ID of the member
     * @param assessmentid The ID of the assessment
     */
    public static void deleteAssessment(Long id, Long assessmentid) {

        //finds the member object using their id
        Member member = Member.findById(id);
        //finds the assessment using the id and passes it into an assessment variable
        Assessment remAssess = Assessment.findById(assessmentid);
        //calls member array list and removes the assessment that matches remAssess
        member.assessmentList.remove(remAssess);
        //saves member object
        member.save();
        //deletes the remAssess object
        remAssess.delete();
        //prints string to terminal
        Logger.info("Deleting Assessment");
        //refreshes dashboard page
        redirect("/dashboard");
    }

    /**
     * Adds a note to the members To Do list
     * @param comment Note to be added to the list
     */
    public static void addTodo(String comment) {

        //gets logged in member by using a method in accounts class
        Member member = Accounts.getLoggedInMember();
        //new todo object to be added
        Todo newTodo = new Todo(comment);
        //adds todo to the member's list
        member.todoList.add(newTodo);
        //saves member object
        member.save();
        //prints string to terminal
        Logger.info("Adding Todo: " + comment);
        //refreshes dashboard page
        redirect("/dashboard");
    }

    /**
     * Deletes note from members todo list
     * @param id The member id
     * @param todoid The todo id
     */
    public static void deleteTodo(Long id, Long todoid) {

        //finds the member object using their id
        Member member = Member.findById(id);
        //finds the todo using the id and passes it into an todo variable
        Todo remTodo = Todo.findById(todoid);
        //calls member array list and removes the todo that matches remTodo
        member.todoList.remove(remTodo);
        //saves member object
        member.save();
        //deletes the remAssess object
        remTodo.delete();
        //prints string to terminal
        Logger.info("Deleting Comment");
        //refreshes dashboard page
        redirect("/dashboard");
    }

    /**
     * Renders the dashboard page and aquires the needed variables
     */
    public static void index() {

        //prints string to terminal
        Logger.info("Rendering Dashboard");
        //gets member object using an Accounts method
        Member member = Accounts.getLoggedInMember();
        //gets assessment list from member object
        List<Assessment> assessmentList = member.assessmentList;
        //reverses the arrangement of the assessment list so the latest assessment is first
        Collections.reverse(assessmentList);
        //todo list from member
        List<Todo> todoList = member.todoList;
        //renders dashboard page
        render("dashboard.html", member, assessmentList, todoList);
    }
}
