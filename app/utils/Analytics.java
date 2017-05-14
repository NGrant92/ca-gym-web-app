package utils;
import models.*;


/**
 * Created by Niall on 24/04/2017.
 */

public class Analytics{


    /**
     * This method calculate the BMI value for the member. BMI = KG x (Height x Height)
     *
     * @return the BMI value for the member. The number returned is truncated to two decimal places.
     */
    public static double calculateBMI(Member member, Assessment assessment) {
        return toTwoDecimalPlaces((assessment.getWeight()) / (member.getHeight() * member.getHeight()));
    }

    /**
     * A method to truncate any double to two decimal places
     *
     * @return end result is a double with only 2 decimal places
     */
    private static double toTwoDecimalPlaces(double num) {
        return(int)(num*100)/100.0;
    }
}