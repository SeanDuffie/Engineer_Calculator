import java.text.DecimalFormat;
import java.util.Scanner;
import java.lang.Math;
import java.lang.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Calculator_B { //calculator Basic
    //Variables
    private List<String> no_num_2 = Arrays.asList("sqrt", "ln", "log", "sin", "cos", "tan", "csc", "sec", "cot"
            , "asin", "acos", "atan", "acsc", "asec", "acot", "sind", "cosd", "tand", "cscd", "secd", "cotd","!");

    //Functions
    private double addition(double a, double b) {
        return a + b;
    }

    private double subtract(double a, double b) {
        return a - b;
    }

    private double multiply(double a, double b) {
        return a * b;
    }

    private double divide(double a, double b) {
        return a / b;
    }

    private double n_root(double a, double b) {
        return Math.pow(b, (1 / a));
    }

    private double npower(double a, double b) {
        return Math.pow(a, b);
    }

    private double logn(double a, double b) {
        return Math.log(b) / Math.log(a);
    }

    //No number_2
    private double square_root(double a) {
        return Math.sqrt(a);
    }

    private double natlog(double a) {
        return Math.log(a);
    }

    private double log(double a) {
        return Math.log10(a);
    }

    //TRIG FUNCTIONS
    private double sine(double a) {
        return Math.sin(a);
    }

    private double cosine(double a) {
        return Math.cos(a);
    }

    private double tangent(double a) {
        return Math.tan(a);
    }

    private double csc(double a) {
        return 1 / Math.sin(a);
    }

    private double sec(double a) {
        return 1 / Math.cos(a);
    }

    private double cot(double a) {
        return 1 / Math.tan(a);
    }

    //INVERSE TRIG FUNCTIONS (RETURN RADIANS)
    private double arcsine(double a) {
        return Math.asin(a);
    }

    private double arccosine(double a) {
        return Math.acos(a);
    }

    private double arctangent(double a) {
        return Math.atan(a);
    }

    private double arccsc(double a) {
        return Math.asin(1 / a);
    }

    private double arcsec(double a) {
        return Math.acos(1 / a);
    }

    private double arccot(double a) {
        return Math.atan(1 / a);
    }

    //DEGREE FUNCTIONALITY
    private double sined(double a) {
        return Math.sin(Math.toRadians(a));
    }

    private double cosined(double a) {
        return Math.cos(Math.toRadians(a));
    }

    private double tangentd(double a) {
        return Math.tan(Math.toRadians(a));
    }

    private double cscd(double a) {
        return 1 / Math.sin(Math.toRadians(a));
    }

    private double secd(double a) {
        return 1 / Math.cos(Math.toRadians(a));
    }

    private double cotd(double a) {
        return 1 / Math.tan(Math.toRadians(a));
    }

    private double factorial(double a) {
        double num = 1;
        int i;
        for (i = 1; i <= a; i++) {
            num *= i;
        }
        return num;
    }

    public double calculate(String oper, double number_1, double number_2) {
        double result=0;
        double ans = 0;

        switch (oper) {
            case "+":
                result = truncate(addition(number_1, number_2), number_1, number_2);
                break;
            case "-":
                result = truncate(subtract(number_1, number_2),number_1, number_2);
                break;
            case "*":
                result = truncate(multiply(number_1, number_2), number_1, number_2);
                break;
            case "/":
                result = divide(number_1, number_2);
                break;
            case "nrt":
                result = truncate(n_root(number_1, number_2), number_1, number_2);
                break;
            case "^":
                result = truncate(npower(number_1, number_2),number_1,number_2);
                break;
            case "logn":
                result = truncate(logn(number_1, number_2),number_1,number_2);
                break;
            case "%":
                result = truncate(number_1 % number_2, number_1, number_2);
                break;
            //1 number cases
            case "sqrt":
                result = square_root(number_1);
                break;
            case "ln":
                result = natlog(number_1);
                break;
            case "log":
                result = log(number_1);
                break;
            case "sin":
                result = sine(number_1);
                break;
            case "cos":
                result = cosine(number_1);
                break;
            case "tan":
                result = tangent(number_1);
                break;
            case "csc":
                result = csc(number_1);
                break;
            case "sec":
                result = sec(number_1);
                break;
            case "cot":
                result = cot(number_1);
                break;
            case "asin":
                result = arcsine(number_1);
                break;
            case "acos":
                result = arccosine(number_1);
                break;
            case "atan":
                result = arctangent(number_1);
                break;
            case "acsc":
                result = arccsc(number_1);
                break;
            case "asec":
                result = arcsec(number_1);
                break;
            case "acot":
                result = arccot(number_1);
                break;
            case "sind":
                result = truncate(sined(number_1),number_1,number_2);
                break;
            case "cosd":
                result = truncate(cosined(number_1),number_1,number_2);
                break;
            case "tand":
                if(number_1%90==0){
                    if((number_1/90)%2==1){
                        result = Double.NaN;
                    }
                }
                else{
                    result = truncate(tangentd(number_1),number_1,number_2);
                }
                break;
            case "cscd":
                result = truncate(cscd(number_1),number_1,number_2);
                break;
            case "secd":
                result = truncate(secd(number_1),number_1,number_2);
                break;
            case "cotd":
                if(number_1%90==0){
                    if((number_1/90)%2==0 || number_1 == 0){
                        result = Double.NaN;
                    }
                }
                else{
                    result = truncate(cotd(number_1),number_1,number_2);
                }
                break;
            case "!":
                if(Double.toString(number_1).substring(Double.toString(number_1).length()-2).equals(".0")){
                    result = factorial(number_1);
                }
                else{
                    result = Double.NaN;
                }
                break;
            default:
                System.out.println("Invalid Operator!");
                break;
        }
        String result_string = Double.toString(result);
        String num1str = Double.toString(number_1);
        String num2str = Double.toString(number_2);
        if (result_string.substring(result_string.length() - 2).equals(".0")) { // This whole mess will get rid of ".0" at the end of an integer result
            DecimalFormat df = new DecimalFormat("0.#");
            int intresult = Integer.parseInt(df.format(result));
            ans = intresult;
        }
        else {
            ans = result;
        }

        return ans;

    }

    public void help() {
        helpMenu();
    }

    //Truncation Method
    private double truncate(double result, double number_1, double number_2) {
        String decform = "0.##";
        String num1str = Double.toString(number_1);
        String num2str = Double.toString(number_2);
        if (num1str.length() > num2str.length()) {
            for (int i = 0; i < num1str.substring(num1str.indexOf(".")).length() - 1; i++) {
                decform += "#";
            }
        } else if (num2str.length() >= num1str.length()) {
            for (int i = 0; i < num2str.substring(num2str.indexOf(".")).length() - 1; i++) {
                decform += "#";
            }
        }
        DecimalFormat df2 = new DecimalFormat(decform);
        if(Double.toString(result).equals("∞") || Double.toString(result).equals("-∞")){
            return Double.NaN;
        }
        else{
            return Double.parseDouble(df2.format(result));
        }
    }

    //Help Menu
    private void helpMenu() {
        System.out.println("--List of valid operators--");
        System.out.println("---Double input Calculations---");
        System.out.println("+ -- Addition");
        System.out.println("- -- Subtraction");
        System.out.println("* -- Multiplication");
        System.out.println("/ -- Division");
        System.out.println("nrt -- nth Root (num, root)");
        System.out.println("e+ -- ax10^b");
        System.out.println("^ -- nth Power");
        System.out.println("logn -- Custom Base Log (num, base)");
        System.out.println("---Single Input Calculations---");
        System.out.println("sqrt -- Square Root");
        System.out.println("ln -- Natural Log");
        System.out.println("log -- Log Base 10");
        System.out.println("sin -- Sine");
        System.out.println("cos -- Cosine");
        System.out.println("tan -- Tangent");
        System.out.println("csc -- Cosecant");
        System.out.println("sec -- Secant");
        System.out.println("cot -- Cotangent");
        System.out.println("asin -- Inverse Sine");
        System.out.println("acos -- Inverse Cosine");
        System.out.println("atan -- Inverse Tangent");
        System.out.println("acsc -- Inverse Cosecant");
        System.out.println("asec -- Inverse Secant");
        System.out.println("acot -- Inverse Cotangent");
        System.out.println("Add a lowercase d to the end of the trig functions for input in degrees.");
        System.out.println("------------------------------------------------");

    }
}

