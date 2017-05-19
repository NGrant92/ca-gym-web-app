package controllers;

import play.*;
import play.mvc.*;
import java.util.*;
import models.*;

/**
 * This class is to render the about webpage
 * @author Niall Grant
 * @version 2017.05.19
 */
public class About extends Controller {

    /**
     * Renders webpage
     */
    public static void index() {
        Logger.info("Rendering about");
        render ("about.html");
    }
}
