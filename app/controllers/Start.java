package controllers;

import play.Logger;
import play.mvc.Controller;

/**
 * This class is to render the home page
 * @author Niall Grant
 * @version 2017.05.19
 */
public class Start extends Controller
{
    /**
     * Renders webpage
     */
    public static void index() {
        Logger.info("Rendering Start");
        render ("start.html");

    }
}
