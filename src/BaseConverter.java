import java.text.DecimalFormat;
import java.util.Scanner;
import java.lang.Math;
import java.lang.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
public class BaseConverter{

    public String decimalToBase(int dec){
        String[] postTen = new String[]{"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a base (number): ");
        int baseMult = input.nextInt();
        String conversion = Integer.toString(dec);
        ArrayList<Integer> digits = new ArrayList<Integer>();
        ArrayList<String> newDigits = new ArrayList<String>();
        //Converts number through remainder division
        int currentDig = dec;
        String newNum = "";
        while(currentDig!=0){
            String newDig = Integer.toString(currentDig%baseMult);
            if(Integer.parseInt(newDig)>=10){
                newDig = (postTen[Integer.parseInt(newDig)-10]);
            }
            newNum+=newDig;
            currentDig/=baseMult;
        }
        String finalConvert = reverse(newNum);
        return finalConvert;
    }

    public int baseToDec(String baseN) {
        String[] postTen = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        int postTen_index;
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a base (number): ");
        int baseMult = input.nextInt();
        ArrayList<Integer> digits = new ArrayList<Integer>();
        ArrayList<String> newDigits = new ArrayList<String>();
        String nextDig = "";
        for (int i = 0; i < baseN.length(); i++) {
            nextDig = Character.toString(baseN.charAt(i));
            if (Character.isLetter(baseN.charAt(i))) {
                for (int j = 0; j < postTen.length; j++) {
                    if (postTen[j].equals(nextDig)) {
                        nextDig = Integer.toString(j + 10);
                    }
                }
            }
            newDigits.add(nextDig);
        }
        int finalDec = 0;
        //Calculation
        int listIterator = 0;
        for (int power = newDigits.size() - 1; power >= 0; power--) {
            finalDec += Integer.parseInt(newDigits.get(listIterator)) * Math.pow(baseMult, power);
            listIterator++;
        }
        return finalDec;
    }





    public String reverse(String str) {
        StringBuilder sb = new StringBuilder();

        for(int i = str.length() - 1; i >= 0; i--)
        {
            sb.append(str.charAt(i));
        }
        return sb.toString();
    }

}
