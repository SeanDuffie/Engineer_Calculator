import java.util.*;

public class Main {

    public static List<String> operatorsOne = Arrays.asList("sqrt", "ln", "log", "sin", "cos", "tan", "csc", "sec", "cot"
            , "asin", "acos", "atan", "acsc", "asec", "acot", "sind", "cosd", "tand", "cscd", "secd", "cotd","!");
    public static List<String> operatorsTwo = Arrays.asList("logn", "nrt");
    //%, ^
    //+-*/
    public static Calculator_B calc = new Calculator_B();

    public static void main(String[] args){

        Scanner in = new Scanner(System.in);

        System.out.print("Enter your calculation: ");
        String input = in.nextLine();

        while (input.contains(" ")) {
            int index = input.indexOf(" ");
            input = input.substring(0, index) + input.substring(index + 1);
        }

        System.out.println(calculate(input, true));

    }

    public static double calculate(String expression, boolean isFirst) {

        if (!isFirst)
            expression = expression.substring(1, expression.length()-1);

        ArrayList<String> elements = new ArrayList<String>();

        //grouping into elements
        int i = 0;
        while (i < expression.length()) {

            char c = expression.charAt(i);
            if (97 <= c && c <= 122) { //if letter detected, find the entire word and group into one element
                int start = i;
                while (i < expression.length() && 97 <= expression.charAt(i) && expression.charAt(i) < 122)
                    i++;
                elements.add(expression.substring(start, i));
            }
            else if (48 <= c && c <= 57) { //if number detected, find the entire number (including decimal)
                int start = i;
                while (i < expression.length() && ((48 <= expression.charAt(i) && expression.charAt(i) <= 57) || expression.charAt(i) == 46))
                    i++;
                elements.add(expression.substring(start, i));
            }
            else if (c == '+' || c == '-' || c == '*' || c == '/' || c == '!' || c == '%' || c == '^') { //if simple operator, just put that in
                elements.add(expression.substring(i, i+1));
                i++;
            }
            else if (c == '(') { //if parenthesis, find entire thing in parenthesis and put into one element
                int start = i;
                int netPar = 1;
                while (netPar > 0) {
                    i++;
                    if (expression.charAt(i) == '(')
                        netPar++;
                    else if (expression.charAt(i) == ')')
                        netPar--;
                }
                elements.add(expression.substring(start,i+1));
                i++;
            }

        }

        //replacing e, pi, and recursing on parenthesis
        for (int j = 0; j < elements.size(); j++) {
            if (elements.get(j).equals("e"))
                elements.set(j, Double.toString(Math.E));
            else if (elements.get(j).equals("pi"))
                elements.set(j, Double.toString(Math.PI));
            else if (elements.get(j).charAt(0) == '(') //if found element with parenthesis, recurse on it
                elements.set(j, Double.toString(calculate(elements.get(j), false)));
        }

        i = 0;
        while (i < elements.size()) {
            if (operatorsOne.contains(elements.get(i))) {
                elements.set(i, Double.toString(calc.calculate(elements.get(i), Double.parseDouble(elements.get(i+1)), 0)));
                elements.remove(i+1);
            }
            i++;
        }
        i = 0;
        while (i < elements.size()) {
            if (operatorsTwo.contains(elements.get(i))) {
                elements.set(i, Double.toString(calc.calculate(elements.get(i), Double.parseDouble(elements.get(i+1)), Double.parseDouble(elements.get(i+2)))));
                elements.remove(i+2);
                elements.remove(i+1);
            }
            i++;
        }
        i = 0;
        while (i < elements.size()) {
            if (elements.get(i).equals("^") || elements.get(i).equals("%")) {
                elements.set(i, Double.toString(calc.calculate(elements.get(i), Double.parseDouble(elements.get(i-1)), Double.parseDouble(elements.get(i+1)))));
                elements.remove(i+1);
                elements.remove(i-1);
            }
            else
                i++;
        }
        i = 0;
        while (i < elements.size()) {
            if (elements.get(i).equals("*") || elements.get(i).equals("/")) {
                elements.set(i, Double.toString(calc.calculate(elements.get(i), Double.parseDouble(elements.get(i-1)), Double.parseDouble(elements.get(i+1)))));
                elements.remove(i+1);
                elements.remove(i-1);
            }
            else
                i++;
        }
        i = 0;
        while (i < elements.size()) {
            if (elements.get(i).equals("+") || elements.get(i).equals("-")) {
                elements.set(i, Double.toString(calc.calculate(elements.get(i), Double.parseDouble(elements.get(i-1)), Double.parseDouble(elements.get(i+1)))));
                elements.remove(i+1);
                elements.remove(i-1);
            }
            else
                i++;
        }

        return Double.parseDouble(elements.get(0));

    }
}
