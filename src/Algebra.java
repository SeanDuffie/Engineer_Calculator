import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.lang.reflect.Array;
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
        String power = "";
        int degree = 1;
        int curr_num;
        for (int i = 0; i < leftside.length(); i++) {
            if (leftside.charAt(i) == '+' || leftside.charAt(i) == '-' || leftside.charAt(i) == '*' || leftside.charAt(i) == '/') {
                left_elements.add(current);
                left_elements.add(Character.toString(leftside.charAt(i)));
                current = "";
            } else {
                if (leftside.charAt(i) == ' ') {
                } else if (isNumeric(Character.toString(leftside.charAt(i))) || leftside.charAt(i) == 'x' || leftside.charAt(i) == '^') {
                    current += leftside.charAt(i);
                }
            }
        }
        left_elements.add(current);
        current = "";
        for (int i = 0; i < left_elements.size(); i++) {
            if (left_elements.get(i).contains("^")) {
                if (degree < Integer.parseInt(left_elements.get(i).substring(left_elements.get(i).indexOf("^") + 1))) {
                    degree = Integer.parseInt(left_elements.get(i).substring(left_elements.get(i).indexOf("^") + 1));
                }
            }
        }

        //Right side grouping

        for (int i = 0; i < rightside.length(); i++) {
            if (rightside.charAt(i) == '+' || rightside.charAt(i) == '-' || rightside.charAt(i) == '*' || rightside.charAt(i) == '/') {
                if (i != 0) {
                    right_elements.add(current);
                    right_elements.add(Character.toString(rightside.charAt(i)));
                    current = "";
                }

            } else {
                if (rightside.charAt(i) == ' ') {
                } else if (isNumeric(Character.toString(rightside.charAt(i))) || rightside.charAt(i) == 'x' || rightside.charAt(i) == '^') {
                    current += rightside.charAt(i);
                }
            }
        }
        right_elements.add(current);
        current = "";
        for (int i = 0; i < right_elements.size(); i++) {
            if (right_elements.get(i).contains("^")) {
                if (degree < Integer.parseInt(right_elements.get(i).substring(right_elements.get(i).indexOf("^") + 1))) {
                    degree = Integer.parseInt(right_elements.get(i).substring(right_elements.get(i).indexOf("^") + 1));
                }
            }
        }
        System.out.println("Left side: " + left_elements);
        System.out.println("Right side: " + right_elements);
        //IF either of the elements contain powers of degree >1, we will use a findRoots() method instead.
        for (int i = 0; i < left_elements.size(); i++) {
            if (left_elements.get(i).contains("^")) {
                return findRoots(left_elements, right_elements, degree);
            }
        }
        for (int i = 0; i < right_elements.size(); i++) {
            if (right_elements.get(i).contains("^")) {
                return findRoots(left_elements, right_elements, degree);
            }
        }
        //Add those together, subtract right side coefficients
        ArrayList<Integer> coefficients = new ArrayList<Integer>();
        ArrayList<Integer> constants = new ArrayList<Integer>();
        //start with left
        String curr_coeff = "";
        boolean isNeg = false;
        for (int i = 0; i < left_elements.size(); i++) {
            if(left_elements.get(i).equals("-")){
                isNeg = true;
            }
            else if(left_elements.get(i).contains("x")){
                if(isNeg){
                    coefficients.add(Integer.parseInt("-"+
                            left_elements.get(i).substring(0,left_elements.get(i).indexOf('x'))));
                    isNeg = false;
                }
                else{
                    coefficients.add(Integer.parseInt(left_elements.get(i).substring(0,left_elements.get(i).indexOf('x'))));
                }
            }
            else if(isNumeric(left_elements.get(i))){
                if(isNeg){
                    constants.add(Integer.parseInt(left_elements.get(i)));
                    isNeg = false;
                }
                else{
                    constants.add(Integer.parseInt("-"+left_elements.get(i)));

                }
            }
        }
        //Also do the right
        for (int i = 0; i < right_elements.size(); i++) {
            if(right_elements.get(i).equals("-")){
                isNeg = true;
            }
            else if(right_elements.get(i).contains("x")){
                if(isNeg){
                    coefficients.add(Integer.parseInt(
                            right_elements.get(i).substring(0,right_elements.get(i).indexOf('x'))));
                    isNeg = false;
                }
                else{
                    coefficients.add(Integer.parseInt("-"+
                            right_elements.get(i).substring(0,right_elements.get(i).indexOf('x'))));
                }
            }
            else if(isNumeric(right_elements.get(i))){
                if(isNeg){
                    constants.add(Integer.parseInt("-"+right_elements.get(i)));
                    isNeg = false;
                }
                else{
                    constants.add(Integer.parseInt(right_elements.get(i)));
                }
            }
        }
        curr_coeff = "";
        //Add Coefficients and Constants
        int coeff_sum = 0;
        int const_sum = 0;
        for (int i = 0; i < coefficients.size(); i++) {
            coeff_sum += coefficients.get(i);
        }
        System.out.println(coefficients);
        for (int i = 0; i < constants.size(); i++) {
            const_sum += constants.get(i);
        }
        System.out.println(constants);
        if (const_sum == 0 && const_sum != coeff_sum) {
            return "x = 0";
        } else if (coeff_sum == 0 && const_sum == coeff_sum) {
            return "x = All Real Numbers";
        } else if (coeff_sum == 0 && const_sum != coeff_sum) {
            return "No Solution";
        } else {
            return "x = " + simplifiedFrac(const_sum, coeff_sum);
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
    public String simplifiedFrac(int numerator,int denominator) {
        for (int i = 2; i < 10; i++) {//Probably a placeholder until something works better tbh.
            while (numerator % i == 0 && denominator % i == 0) {
                numerator /= i;
                denominator /= i;
            }
        }
        if((numerator < 0 && denominator<0)||(denominator<0 && numerator>0)){
            numerator*=-1;
            denominator*=-1;
        }
        if (denominator == 1) {
            return Integer.toString(numerator);
        } else {
            return Integer.toString(numerator) + "/" + Integer.toString(denominator);
        }
    }

    public String findRoots(ArrayList<String> leftside, ArrayList<String> rightside,int degree){
        //Isolate all of the 'x' variables on one side
        //Create an array of ArrayLists length = degree
        int[] coeff_sums = new int[degree];
        int const_sum = 0;
        //Because of the lack of '^', coefficients for x degree 1 will be done separately, along with constants
        for(int i = 0;i<leftside.size();i++){ // X DEGREE 1
            if(!leftside.get(i).contains("^")){
                if(leftside.get(i).contains("x") && leftside.get(i).charAt(0) == 'x'){
                    coeff_sums[0]+=1;
                }
                else if(leftside.get(i).contains("x") && leftside.get(i).charAt(0) == '-'){
                    coeff_sums[0]+= -1;
                }
                else if(leftside.get(i).contains("x")){
                    coeff_sums[0]+=Integer.parseInt(leftside.get(i).substring(0,leftside.get(i).indexOf('x')));
                }
            }

        }
        for(int i = 0;i<leftside.size();i++){ // CONSTANTS
            if(!leftside.get(i).contains("x") && isNumeric(leftside.get(i))){
                if(i != 0){
                    if(leftside.get(i-1).equals("-")){
                        const_sum+=Integer.parseInt(leftside.get(i))*-1;
                    }
                    else{
                        const_sum+=Integer.parseInt(leftside.get(i));
                    }

                }
                else{
                    const_sum+=Integer.parseInt(leftside.get(i));
                }
            }
        }
        int current_deg;
        for(int i = 0;i<leftside.size();i++) {//nth power
            if (leftside.get(i).contains("^")) {
                current_deg = Integer.parseInt(leftside.get(i).substring(leftside.get(i).indexOf("^") + 1));
                if (leftside.get(i).charAt(0) == 'x') {
                    if (i != 0) {
                        if (leftside.get(i - 1).equals("-")) {
                            coeff_sums[current_deg-1] += -1;
                        } else {
                            coeff_sums[current_deg-1] += 1;
                        }
                    } else {
                        coeff_sums[current_deg-1] += 1;
                    }
                } else if (isNumeric(leftside.get(i).substring(0, leftside.get(i).indexOf("x")))) {
                    if (i != 0) {
                        if (leftside.get(i - 1).equals("-")) {
                            coeff_sums[current_deg-1] += Integer.parseInt(leftside.get(i).substring(0, leftside.get(i).indexOf("x"))) * -1;
                        } else {
                            coeff_sums[current_deg-1] += Integer.parseInt(leftside.get(i).substring(0, leftside.get(i).indexOf("x")));
                        }
                    } else {
                        coeff_sums[current_deg-1] += Integer.parseInt(leftside.get(i).substring(0, leftside.get(i).indexOf("x")));
                    }
                }
            }
        }


        //Right side coefficients an constants:


        for(int i = 0;i<rightside.size();i++){ // X DEGREE 1
            if(!rightside.get(i).contains("^")){
                if(rightside.get(i).contains("x") && rightside.get(i).charAt(0) == 'x'){
                    coeff_sums[0]-=1;
                }
                else if(rightside.get(i).contains("x") && rightside.get(i).charAt(0) == '-'){
                    coeff_sums[0]-= -1;
                }
                else if(rightside.get(i).contains("x")){
                    coeff_sums[0]-=Integer.parseInt(rightside.get(i).substring(0,rightside.get(i).indexOf('x')));
                }
            }

        }
        for(int i = 0;i<rightside.size();i++){ // CONSTANTS
            if(!rightside.get(i).contains("x") && isNumeric(rightside.get(i))){
                if(i != 0){
                    if(rightside.get(i-1).equals("-")){
                        const_sum-=Integer.parseInt(rightside.get(i))*-1;
                    }
                    else{
                        const_sum-=Integer.parseInt(rightside.get(i));
                    }

                }
                else{
                    const_sum-=Integer.parseInt(rightside.get(i));
                }
            }
        }

        current_deg = 0;
        for(int i = 0;i<rightside.size();i++) {//nth power
            if (rightside.get(i).contains("^")) {
                current_deg = Integer.parseInt(rightside.get(i).substring(rightside.get(i).indexOf("^") + 1));
                if (rightside.get(i).charAt(0) == 'x') {
                    if (i != 0) {
                        if (rightside.get(i - 1).equals("-")) {
                            coeff_sums[current_deg - 1] -= -1;
                        } else {
                            coeff_sums[current_deg - 1] -= 1;
                        }
                    } else {
                        coeff_sums[current_deg - 1] -= 1;
                    }
                } else if (isNumeric(rightside.get(i).substring(0, rightside.get(i).indexOf("x")))) {
                    if (i != 0) {
                        if (rightside.get(i - 1).equals("-")) {
                            coeff_sums[current_deg - 1] -= Integer.parseInt(rightside.get(i).substring(0, rightside.get(i).indexOf("x"))) * -1;
                        } else {
                            coeff_sums[current_deg - 1] -= Integer.parseInt(rightside.get(i).substring(0, rightside.get(i).indexOf("x")));
                        }
                    } else {
                        coeff_sums[current_deg - 1] -= Integer.parseInt(rightside.get(i).substring(0, rightside.get(i).indexOf("x")));
                    }
                }
            }
        }
        for(int i = 0;i<degree;i++){
            System.out.println(coeff_sums[i]);
        }
        System.out.println(const_sum);
        String new_eq = "";
        //Build a string with everything on the left set equal to 0
        for(int i = degree-1;i>=0;i--) {
            new_eq += coeff_sums[i] + "x";
            if (i > 0) {
                new_eq += "^" + Integer.toString(i + 1);
                new_eq += " + ";
            }
        }
        System.out.println(new_eq);
        //TO DO: find a new method of finding roots in java
        //????
        //Profit
        return "";
    }


    public String expandEq(String eq) {
        return "";
        //This will essentially get rid of parenthesis by FOILing and distributing.
    }


    }
    //To Do: Implement SolveMulti and SolveSys




