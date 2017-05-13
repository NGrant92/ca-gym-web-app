package controllers;
import models.*;
import play.Logger;
import play.mvc.Controller;
import java.util.*;

public class Admin extends Controller
{
  public static void index()
  {
    Logger.info("Rendering Dashboard");
    //List<Todo> todolist = Todo.findAll();
    render("admin.html");
  }
}
