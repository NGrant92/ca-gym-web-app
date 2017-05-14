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
     * This method determines the BMI category that the member belongs to.
     * The category is determined by the magnitude of the members BMI according to the following:
     *
     * BMI less than    15   (exclusive)                      is "VERY SEVERLY UNDERWEIGHT"
     * BMI between      15   (inclusive) and 16   (exclusive) is "SEVERELY UNDERWEIGHT"
     * BMI between      16   (inclusive) and 18.5 (exclusive) is "UNDERWEIGHT"
     * BMI between      18.5 (inclusive) and 25   (exclusive) is "NORMAL"
     * BMI between      25   (inclusive) and 30   (exclusive) is "OVERWEIGHT"
     * BMI between      30   (inclusive) and 35   (exclusive) is "MODERATELY OBESE"
     * BMI between      35   (inclusive) and 40   (exclusive) is "SEVERELY OBESE"
     * BMI greater than 40   (inclusive)                      is "VERY SEVERELY OBESE"
     *
     * @return the format of a String is similar to (note the double quotes around the category): "NORMAL".
     */
    public static String determineBMICategory(double bmiValue)
    {
        String bmiResult;

        if (bmiValue < 15){
            bmiResult = "VERY SEVERELY UNDERWEIGHT";
        }
        else if(bmiValue >= 15 && bmiValue < 16){
            bmiResult = "SEVERELY UNDERWEIGHT";
        }
        else if(bmiValue >= 16 && bmiValue < 18.5){
            bmiResult = "UNDERWEIGHT";
        }
        else if(bmiValue >= 18.5 && bmiValue < 25){
            bmiResult = "NORMAL";
        }
        else if(bmiValue >= 25 && bmiValue < 30){
            bmiResult = "OVERWEIGHT";
        }
        else if(bmiValue >= 30 && bmiValue < 35){
            bmiResult = "MODERATELY OBESE";
        }
        else if(bmiValue >= 35 && bmiValue < 40){
            bmiResult = "SEVERELY OBESE";
        }
        else if(bmiValue >= 40){
            bmiResult = "VERY SEVERELY OBESE";
        }
        else{
            bmiResult = "Invalid result";
        }
        return bmiResult;
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