import java.text.DecimalFormat;
import java.util.Scanner;
import java.lang.Math;
import java.lang.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
public class Algebra {
    public String eqn;
    float ans;
    float coefficients;
    float constants;
    Calculator calc = new Calculator();

    //Constructors
    public Algebra() {
    }

    public Algebra(String eq) {
        this.eqn = eq;
        coefficients = 0;
        constants = 0;
    }

    public String solveLin() {
        //To Do: Converting equation to equivalent one sans parenthesis
        //Implement Distributive Property, Multiplication and division of parenthesis'd elements


        //First, split the eq into left and right side
        String leftside = eqn.substring(0, eqn.indexOf('='));
        String rightside = eqn.substring(eqn.indexOf('=') + 1);
        //Arraylists of the "elements" in each
        //Elements are either a coefficient-x, an operator, or a constant
        ArrayList<String> left_elements = new ArrayList<String>();
        ArrayList<String> right_elements = new ArrayList<String>();
        String current = "";
        for (int i = 0; i < leftside.length(); i++) {
            if (Character.isDigit(leftside.charAt(i))) {
                current += leftside.charAt(i);
                if (i != leftside.length() - 1) {
                    if (!Character.isDigit(leftside.charAt(i + 1))) {
                        left_elements.add(current);
                        current = "";
                    }
                } else {
                    left_elements.add(current);
                    current = "";
                }
            } else if (leftside.charAt(i) == '+' || leftside.charAt(i) == '-' || leftside.charAt(i) == '*' || leftside.charAt(i) == '/') {
                if (i == 0 && leftside.charAt(i) == '-') {
                    current += leftside.charAt(i);
                } else {
                    current += leftside.charAt(i);
                    left_elements.add(current);
                    current = "";
                }

            } else if (leftside.charAt(i) == 'x') {
                current += leftside.charAt(i);
                left_elements.add(current);
                current = "";
            } else if (leftside.charAt(i) == ' ') {
            }
        }
        //Right side grouping
        for (int i = 0; i < rightside.length(); i++) {
            if (Character.isDigit(rightside.charAt(i))) {
                current += rightside.charAt(i);
                if (i != rightside.length() - 1) {
                    if (!Character.isDigit(rightside.charAt(i + 1))) {
                        right_elements.add(current);
                        current = "";
                    }
                } else {
                    right_elements.add(current);
                    current = "";
                }
            } else if (rightside.charAt(i) == '+' || rightside.charAt(i) == '-' || rightside.charAt(i) == '*' || rightside.charAt(i) == '/') {
                current += rightside.charAt(i);
                right_elements.add(current);
                current = "";
            } else if (rightside.charAt(i) == 'x') {
                current += rightside.charAt(i);
                right_elements.add(current);
                current = "";
            } else if (rightside.charAt(i) == ' ') {//skip
            }
        }
        //Add those together, subtract right side coefficients
        ArrayList<String> coefficients = new ArrayList<String>();
        ArrayList<String> constants = new ArrayList<String>();
        //start with left
        for (int i = 0; i < left_elements.size(); i++) {
            if (i != left_elements.size() - 1) {
                if (left_elements.get(i).equals("-") && isNumeric(left_elements.get(i + 1))) {
                    left_elements.set(i + 1, Integer.toString(Integer.parseInt(left_elements.get(i + 1)) * -1));
                }
                if (isNumeric(left_elements.get(i)) && left_elements.get(i + 1).equals("x")) {
                    coefficients.add(left_elements.get(i));
                } else if (isNumeric(left_elements.get(i))) {
                    left_elements.set(i, Integer.toString(Integer.parseInt(left_elements.get(i)) * -1)); //Left constants will be added to right side
                    constants.add(left_elements.get(i));
                }
            } else {
                if (isNumeric(left_elements.get(i))) {
                    left_elements.set(i, Integer.toString(Integer.parseInt(left_elements.get(i)) * -1));
                    constants.add(left_elements.get(i));
                }
            }
        }
        //Also do the right
        for (int i = 0; i < right_elements.size(); i++) {
            if (i != right_elements.size() - 1) {
                if (right_elements.get(i).equals("-") && isNumeric(right_elements.get(i + 1))) {
                    right_elements.set(i + 1, Integer.toString(Integer.parseInt(right_elements.get(i + 1)) * -1));
                }
                if (isNumeric(right_elements.get(i)) && right_elements.get(i + 1).equals("x")) {
                    right_elements.set(i, Integer.toString(Integer.parseInt(right_elements.get(i)) * -1)); //Right coefficients will be added to left side
                    coefficients.add(right_elements.get(i));
                } else if (isNumeric(right_elements.get(i))) {
                    constants.add(right_elements.get(i));
                }
            } else {
                if (isNumeric(right_elements.get(i))) {
                    constants.add(right_elements.get(i));
                }
            }
        }
        //Add Coefficients and Constants
        int coeff_sum = 0;
        int const_sum = 0;
        for (int i = 0; i < coefficients.size(); i++) {
            coeff_sum += Integer.parseInt(coefficients.get(i));
        }
        for (int i = 0; i < constants.size(); i++) {
            const_sum += Integer.parseInt(constants.get(i));
        }
        if(const_sum == 0 && const_sum != coeff_sum){
            return "x = 0";
        }
        else if(coeff_sum == 0 && const_sum == coeff_sum){
            return "x = All Real Numbers";
        }
        else if(coeff_sum == 0 && const_sum != coeff_sum){
            return "No Solution";
        }
        else{
            return "x = "+const_sum+"/"+coeff_sum;
        }
    }

    public static boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
    //To Do: Implement SolveMulti and SolveSys



